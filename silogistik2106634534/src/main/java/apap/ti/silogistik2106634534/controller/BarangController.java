package apap.ti.silogistik2106634534.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.ObjectError;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import apap.ti.silogistik2106634534.service.BarangService;
import jakarta.validation.Valid;
import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang_Barang;
import apap.ti.silogistik2106634534.dto.BarangMapper;
import apap.ti.silogistik2106634534.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106634534.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106634534.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106634534.repository.BarangDb;


@Controller
public class BarangController {
    @Autowired
    private BarangService barangService; 

    @Autowired
    private BarangMapper barangMapper; 

    @Autowired
    private BarangDb barangDb; 

    private int barangCounter; 

    @GetMapping("barang")
    public String listBarang (Model model) {
        List<Barang> listBarang = barangService.getAllBarang(); 
        
        List<ReadBarangResponseDTO> listReadBarang = new ArrayList<ReadBarangResponseDTO>();
        for (Barang barang: listBarang) {
            ReadBarangResponseDTO barangDTO = barangMapper.barangToReadBarangResponseDTO(barang);
            listReadBarang.add(barangDTO);
        }
        model.addAttribute("listBarang", listReadBarang); 
        return "view-all-barang"; 
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model){
        var barangDTO = new CreateBarangRequestDTO(); 
        List<String> tipeBarang = new ArrayList<String>(5); 

        tipeBarang.add(0, "Produk Elektronik");
        tipeBarang.add(1, "Pakaian & Aksesoris");
        tipeBarang.add(2, "Makanan & Minuman");
        tipeBarang.add(3, "Kosmetik");
        tipeBarang.add(4, "Perlengkapan Rumah");
        model.addAttribute("barangDTO", barangDTO); 
        model.addAttribute("listTipeBarang", tipeBarang);

        return "form-create-barang"; 
    }

    public String generateSKU (int tipeBarang){ 
         barangCounter++;
        switch (tipeBarang) {
            case 1:
                return "ELEC" + String.format("%03d", barangCounter);
            case 2:
                return "CLOT" + String.format("%03d", barangCounter);
            case 3:
                return "FOOD" + String.format("%03d", barangCounter);
            case 4:
                return "COSM" + String.format("%03d", barangCounter);
            case 5: 
                return "TOOL" + String.format("%03d", barangCounter);
            default:
                return null;
        }
    }

    @PostMapping("barang/tambah")
    public String addBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model){
        //error handling
        if(bindingResult.hasErrors()) {
            List<String> error = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

            model.addAttribute("errorMessage", error);
            return "custom-error";
        }

        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO); 
       
        barang.setSku(generateSKU(barangDTO.getTipeBarang()));
        barangService.saveBarang(barang);
        
        model.addAttribute("barang", barang);
        return "success-create-barang";
    }

    @GetMapping("barang/{skuBarang}")
    public String detailBarang(@PathVariable("skuBarang")String sku, Model model){
        var barang = barangService.getBarangBySku(sku); 
        var readBarangResponseDTO = barangMapper.barangToReadBarangResponseDTO(barang); 
        
        readBarangResponseDTO.setTipeBarang(barangService.getTipeBarangString(barang.getTipeBarang()));
        model.addAttribute("barang", readBarangResponseDTO); 

        return "view-barang"; 
    }

    @GetMapping("barang/{skuBarang}/ubah")
    public String formUpdateBarang(@PathVariable("skuBarang")String sku, Model model){
        var barang = barangService.getBarangBySku(sku); 
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang); 
        
        barangDTO.setTipeBarangString(barangService.getTipeBarangString(barang.getTipeBarang()));
        model.addAttribute("barangDTO", barangDTO); 

        return "form-update-barang"; 
    }

    @PostMapping("barang/{skuBarang}/ubah")
    public String updateBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model){
        //Error handling
        if(bindingResult.hasErrors()) {
            List<String> error = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

            model.addAttribute("errorMessage", error);
            return "custom-error";
        }
        var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);

        barangFromDTO.setSku(barangDTO.getSku());
        Barang barang = barangService.updateBarang(barangFromDTO);
        
        model.addAttribute("barang", barang);

        return "success-update-barang";
    }
}
