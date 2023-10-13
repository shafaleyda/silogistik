package apap.ti.silogistik2106634534.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106634534.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106634534.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106634534.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang_Barang;
import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    ReadPermintaanPengirimanResponseDTO permintaanPengirimanToReadPermintaanPengirimanResponseDTO(Permintaan_Pengiriman permintaan_pengiriman);
    Permintaan_Pengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO); 

    @AfterMapping 
    default void setBarang(Barang barang, @MappingTarget ReadBarangResponseDTO readBarangResponseDTO) {
        
    }
}
