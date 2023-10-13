package apap.ti.silogistik2106634534.dto;

import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106634534.model.Barang; 
import apap.ti.silogistik2106634534.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106634534.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106634534.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106634534.model.Gudang_Barang;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO); 
    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO); 
    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO (Barang barang); 
    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang); 

    // @AfterMapping 
    // default void tipeBarangCreate(CreateBarangRequestDTO createBarangRequestDTO, Barang barang){
    //     int tipeBarangInt = createBarangRequestDTO.getTipeBarang(); 
    // }

    @AfterMapping 
    default void setStok(Barang barang, @MappingTarget ReadBarangResponseDTO readBarangResponseDTO) {
        int stok = 0; 
        for (Gudang_Barang gudangBarang: barang.getListGudangBarang()) {
            stok += gudangBarang.getStokBarang();
        }
        readBarangResponseDTO.setStokBarang(stok);
    }
}
