package apap.ti.silogistik2106634534.controller;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import java.util.stream.Collectors;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.ti.silogistik2106634534.service.BarangService;
import apap.ti.silogistik2106634534.service.GudangService;
import apap.ti.silogistik2106634534.service.KaryawanService;
import apap.ti.silogistik2106634534.service.PermintaanPengirimanService;
import apap.ti.silogistik2106634534.dto.GudangMapper;
import apap.ti.silogistik2106634534.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106634534.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106634534.dto.response.ReadBarangGudangResponseDTO;
import apap.ti.silogistik2106634534.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang;
import apap.ti.silogistik2106634534.model.Gudang_Barang;

import java.util.ArrayList;
import java.util.List; 

@Controller
public class GudangController {
    @Autowired
    private GudangService gudangService;
    @Autowired
    private GudangMapper gudangMapper; 
    @Autowired
    private BarangService barangService; 
    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService; 
    @Autowired
    private KaryawanService karyawanService; 


    @GetMapping("/")
    public String beranda(Model model){
        model.addAttribute("banyakBarang", barangService.getAllBarang().size()); 
        model.addAttribute("banyakGudang", gudangService.getAllGudang().size());
        model.addAttribute("banyakPermintaanPengiriman", permintaanPengirimanService.getAllPermintaanPengiriman().size());
        model.addAttribute("banyakKaryawan", karyawanService.getAllKaryawan().size());
        return "beranda";
    }

    @GetMapping("gudang")
    public String listGudang(Model model) {
        //Mendapatkan semua gudang
        List<Gudang> listGudang = gudangService.getAllGudang();

        //Add variabel semua buku ke "ListGudang" untuk dirender pada thymeleaf
        model.addAttribute("listGudang", listGudang);
        return "view-all-gudang";
    }

    @GetMapping("gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang")long idGudang, Model model) {
        var gudang = gudangService.getGudangById(idGudang); 
        var readGudang = gudangMapper.gudangToReadGudangResponseDTO(gudang);
        //Set barang 
        List<ReadBarangResponseDTO> listBarangGudang = new ArrayList<>(); 

        for (Gudang_Barang gudangBarang: gudang.getListGudangBarang()) {
            ReadBarangResponseDTO readBarangResponseDTO = new ReadBarangResponseDTO(); 
            var barang = gudangBarang.getBarang(); 
            
            readBarangResponseDTO.setSku(barang.getSku()); 
            readBarangResponseDTO.setMerkBarang(barang.getMerkBarang());
            readBarangResponseDTO.setStokBarang(gudangBarang.getStokBarang());
            readBarangResponseDTO.setHargaBarang(barang.getHargaBarang());
            listBarangGudang.add(readBarangResponseDTO); 
        }
        readGudang.setListBarangGudang(listBarangGudang);
        model.addAttribute("gudang", readGudang); 
        //model.addAttribute("listBarangGudang", gudangService.getGudangById(idGudang).getListGudangBarang());
        return "view-gudang"; 
    }

    @GetMapping("gudang/{idGudang}/restock-barang")
    public String addFormRestockBarang(@PathVariable("idGudang")long idGudang, Model model){
        var gudang = gudangService.getGudangById(idGudang); 
        var updateGudang = gudangMapper.gudangToUpdateGudangRequestDTO(gudang); 
        
        model.addAttribute("gudangDTO", updateGudang); 
        model.addAttribute("listBarangInStock", barangService.getAllBarang()); 
        //model.addAttribute("listBarangGudang", gudangService.getGudangById(idGudang).getListGudangBarang());
        
        return "form-restock-barang-gudang";

    }

    @PostMapping(value = "gudang/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowRestockBarang(
        @ModelAttribute UpdateGudangRequestDTO gudangDTO,
        BindingResult bindingResult,
        Model model
    ) {
        //error handling
        if(bindingResult.hasErrors()) {
            List<String> error = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

            model.addAttribute("errorMessage", error);
            return "custom-error";
        }
        Gudang gudang = gudangService.getGudangById(gudangDTO.getIdGudang()); 
        gudangDTO.setAlamatGudang(gudang.getAlamatGudang());
        gudangDTO.setNamaGudang(gudang.getNamaGudang());
        
        if (gudangDTO.getListGudangBarang() == null || gudangDTO.getListGudangBarang().size() == 0) {
            gudangDTO.setListGudangBarang(new ArrayList<>());
        }
        gudangDTO.getListGudangBarang().add(new Gudang_Barang()); 

        model.addAttribute("gudangDTO", gudangDTO); 
        model.addAttribute("listBarangInStock", barangService.getAllBarang()); 

        return "form-restock-barang-gudang";
    }

    @PostMapping("gudang/{idGudang}/restock-barang")
    public String restockBarang(@Valid @ModelAttribute UpdateGudangRequestDTO gudangDTO, BindingResult bindingResult, Model model){
        //error handling
        //error handling
        if(bindingResult.hasErrors()) {
            List<String> error = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

            model.addAttribute("errorMessage", error);
            return "custom-error";
        }

        var barangGudangFromDTO = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO); 
        var gudang = gudangService.updateStokBarangGudang(barangGudangFromDTO);
        
        model.addAttribute("gudang", gudang);
        return "success-restock-barang";
    }

    @GetMapping("gudang/cari-barang")
    public String getListBarangDicari(@RequestParam(name = "sku", required = false) String sku, Model model) {
        List<Gudang_Barang> listAllGudangBarang = gudangService.getAllGudangBarang();
        listAllGudangBarang.sort((g1, g2) -> g1.getBarang().getMerkBarang().compareToIgnoreCase(g2.getBarang().getMerkBarang()));

        model.addAttribute("listAllGudangBarang", listAllGudangBarang);

        if (sku != null){
            List<Gudang_Barang> listGudangBarangDicari = gudangService.listGudangBarangFiltered(sku); 
            model.addAttribute("listGudangBarangDicari", listGudangBarangDicari); 
        }

        model.addAttribute("sku", sku);
        return "view-gudang-cari-barang"; 
    }
}
