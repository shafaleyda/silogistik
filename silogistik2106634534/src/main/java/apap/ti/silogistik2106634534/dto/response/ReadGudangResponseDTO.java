package apap.ti.silogistik2106634534.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang_Barang;

import java.util.List; 

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {
    private long idGudang; 
    private String namaGudang;
    private String alamatGudang; 
    private List<ReadBarangResponseDTO> listBarangGudang; 
}
