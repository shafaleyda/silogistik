package apap.ti.silogistik2106634534.dto;

import apap.ti.silogistik2106634534.dto.request.CreateKaryawanRequestDTO; 
import apap.ti.silogistik2106634534.model.Karyawan;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan (CreateKaryawanRequestDTO createKaryawanRequestDTO); 
    
    
}