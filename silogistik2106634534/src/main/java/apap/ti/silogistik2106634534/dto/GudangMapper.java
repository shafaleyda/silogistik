package apap.ti.silogistik2106634534.dto;

import apap.ti.silogistik2106634534.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106634534.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106634534.model.Gudang;
import apap.ti.silogistik2106634534.dto.response.ReadGudangResponseDTO;

import org.aspectj.lang.annotation.After;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.AfterMapping;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang (CreateGudangRequestDTO createGudangRequestDTO); 
    ReadGudangResponseDTO gudangToReadGudangResponseDTO (Gudang gudang); 
    Gudang updateGudangRequestDTOToGudang (UpdateGudangRequestDTO updateGudangRequestDTO);
    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang); 
    // @AfterMapping
    // default void mapGudangToGudangRead(Gudang gudang, @MappingTarget ReadGudangResponseDTO readGudangResponseDTO){
    //     readGudangResponseDTO.setListGudangBarang(gudang.getListGudangBarang());;
    // }
}
