package apap.ti.silogistik2106634534.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106634534.service.PermintaanPengirimanService;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.Valid;
import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang_Barang;
import apap.ti.silogistik2106634534.model.Karyawan;
import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman;
import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman_Barang;
import apap.ti.silogistik2106634534.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106634534.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106634534.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106634534.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106634534.service.BarangService;
import apap.ti.silogistik2106634534.service.KaryawanService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService; 
    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper; 
    @Autowired
    KaryawanService karyawanService; 
    @Autowired
    BarangService barangService; 

    @GetMapping("permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model) {
        
        List<ReadPermintaanPengirimanResponseDTO> listPermintaanPengiriman = new ArrayList<>(); 

        for (Permintaan_Pengiriman permintaanPengiriman: permintaanPengirimanService.getAllPermintaanPengirimanSorted()){
            ReadPermintaanPengirimanResponseDTO permintaanPengirimanDTO = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");

            permintaanPengirimanDTO.setWaktuPermintaan(permintaanPengiriman.getWaktuPermintaan().format(formatter).toString());
            permintaanPengirimanDTO.setIdPermintaanPengiriman(permintaanPengiriman.getIdPermintaanPengiriman());
            listPermintaanPengiriman.add(permintaanPengirimanDTO);
        }

        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        return "view-all-permintaan-pengiriman"; 
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable("idPermintaanPengiriman")long idPermintaanPengiriman, Model model){
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman); 
        var readPermintaanPengiriman = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman); 
        readPermintaanPengiriman.setJenisLayananString(getJenisLayananString(permintaanPengiriman.getJenisLayanan()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
        readPermintaanPengiriman.setWaktuPermintaan(permintaanPengiriman.getWaktuPermintaan().format(formatter).toString());


        model.addAttribute("permintaanPengiriman", readPermintaanPengiriman);
        return "view-permintaan-pengiriman";
    }

    public String getJenisLayananString(int jenisLayanan) {
        switch (jenisLayanan) {
            case 1:
                return "Same Day";
            case 2:
                return "Kilat"; 
            case 3:
                return "Reguler"; 
            case 4:
                return "Hemat";
            default:
                return "";
        }
    }

    @GetMapping("permintaan-pengiriman/tambah")
    public String formAddPermintaanPengiriman(Model model){
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan(); 
        List<String> jenisLayanan = new  ArrayList<String>(5); 

        jenisLayanan.add(0, "Same Day");
        jenisLayanan.add(1, "Kilat");
        jenisLayanan.add(2, "Reguler"); 
        jenisLayanan.add(3, "Hemat"); 

        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO); 
        model.addAttribute("listKaryawan", listKaryawan); 
        model.addAttribute("listJenisLayanan", jenisLayanan);
        return "form-create-permintaan-pengiriman"; 
    }

    public String generateNomorPengiriman(int kuantitas, int jenisLayanan, LocalDateTime waktuPermintaanPengiriman) {
        String nomorPengiriman; 
        String jenisLayananString; 
        if (kuantitas > 99){
            kuantitas = kuantitas%100; 
        }

        if (jenisLayanan == 1) {
            jenisLayananString = "SAM"; 
        } else if (jenisLayanan == 2) {
            jenisLayananString = "KIL"; 
        } else if (jenisLayanan == 3) {
            jenisLayananString = "REG"; 
        } else {
            jenisLayananString = "HEM"; 
        }

        int hour = waktuPermintaanPengiriman.getHour();
        int minute = waktuPermintaanPengiriman.getMinute();
        int second = waktuPermintaanPengiriman.getSecond();
        
        nomorPengiriman = "REQ" + String.format("%02d", kuantitas) + jenisLayananString + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second); 
        
        if (!checkNomorPengiriman(nomorPengiriman)) {
            return nomorPengiriman; 
        } else{
            return null; 
        }
    }

    boolean checkNomorPengiriman(String nomorPengiriman){
        boolean adaNomorPengiriman = false; 
        for (Permintaan_Pengiriman permintaanPengiriman: permintaanPengirimanService.getAllPermintaanPengiriman()){
            if (permintaanPengiriman.getNomorPengiriman().equals(nomorPengiriman)) {
                adaNomorPengiriman = true; 
            }
        }
        return adaNomorPengiriman; 
    }

    @PostMapping("permintaan-pengiriman/tambah")
    public String addPermintaanPengiriman(
    @Valid @ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO,
    BindingResult bindingResult,
    Model model) {
        //error handling
        if(bindingResult.hasErrors()) {
            List<String> error = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

            model.addAttribute("errorMessage", error);
            return "custom-error";
        }

        String tanggalPengirimanFormat = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(tanggalPengirimanFormat);
        LocalDate tanggalPengirimanDate = LocalDate.parse(createPermintaanPengirimanRequestDTO.getTanggalPengirimanString(), formatter);
        createPermintaanPengirimanRequestDTO.setTanggalPengiriman(tanggalPengirimanDate);

        var permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(createPermintaanPengirimanRequestDTO);
        for (Permintaan_Pengiriman_Barang permintaan_pengiriman_barang: createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang()){
            Barang barangFromDTO = barangService.getBarangBySku(permintaan_pengiriman_barang.getBarang().getSku());
            int kuantitasBarang = 0;
            for (Gudang_Barang gudang_barang: barangFromDTO.getListGudangBarang()){
                kuantitasBarang += gudang_barang.getStokBarang();
            }
            int kuantitasDipesan = permintaan_pengiriman_barang.getKuantitasPesanan();
            if (kuantitasDipesan > kuantitasBarang) {
                model.addAttribute("barang", barangFromDTO.getMerkBarang());
                return "stock-barang-kurang";
            }

            permintaan_pengiriman_barang.setBarang(barangFromDTO);
            //permintaanPengiriman.getListPermintaanPengirimanBarang().get(i).setBarang(barangFromDTO);
        }
        int kuantitas = 0; 

        for (Permintaan_Pengiriman_Barang barangDTO: createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang()){
            kuantitas += barangDTO.getKuantitasPesanan();
        }

        permintaanPengiriman.setNomorPengiriman(generateNomorPengiriman(kuantitas, createPermintaanPengirimanRequestDTO.getJenisLayanan(), LocalDateTime.now()));
        permintaanPengirimanService.createPermintaanPengiriman(permintaanPengiriman);

        model.addAttribute("permintaanPengiriman", permintaanPengiriman);

        return "success-create-permintaan-pengiriman";
    }

    @PostMapping(value = "permintaan-pengiriman/tambah", params = ("addRow"))
    public String addRowBuatPermintaanPengiriman(
        @ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO,
        BindingResult bindingResult,
        Model model
    ) {
        //error handling
        if(bindingResult.hasErrors()) {
            List<String> error = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

            model.addAttribute("errorMessage", error);
            return "custom-error";
        }
        if (createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang() == null || createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang().size() == 0) {
            createPermintaanPengirimanRequestDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }
        String tanggalPengirimanFormat = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(tanggalPengirimanFormat);
        LocalDate tanggalPengirimanDate = LocalDate.parse(createPermintaanPengirimanRequestDTO.getTanggalPengirimanString(), formatter);
        createPermintaanPengirimanRequestDTO.setTanggalPengiriman(tanggalPengirimanDate);

        createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang().add(new Permintaan_Pengiriman_Barang());
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan(); 
        List<String> jenisLayanan = new  ArrayList<String>(5); 

        jenisLayanan.add(0, "Same Day");
        jenisLayanan.add(1, "Kilat");
        jenisLayanan.add(2, "Reguler"); 
        jenisLayanan.add(3, "Hemat"); 

        model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanRequestDTO); 
        model.addAttribute("listBarangInStock", barangService.getAllBarang()); 
        model.addAttribute("listJenisLayanan", jenisLayanan);
        model.addAttribute("listKaryawan", listKaryawan); 
        
        return "form-create-permintaan-pengiriman";
    }   

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}/delete")
    public String deleteBuku(@PathVariable("idPermintaanPengiriman")long idPermintaanPengiriman, Model model) {
        var permintaan_pengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);

        if (Duration.between(LocalDateTime.now(ZoneId.of("Asia/Jakarta")), permintaan_pengiriman.getWaktuPermintaan()).getSeconds() > -1 * (24 * 3600)) {
            permintaanPengirimanService.deletePermintaanPengiriman(permintaan_pengiriman);
        } else {
            model.addAttribute("permintaanPengiriman", permintaan_pengiriman);
            return "gagal-cancel-permintaan-pengiriman";
        }
        model.addAttribute("nomorPengiriman", permintaan_pengiriman.getNomorPengiriman());
        return "success-cancel-permintaan-pengiriman";
    }

}
