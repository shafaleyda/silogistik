package apap.ti.silogistik2106634534.dto.response;

import apap.ti.silogistik2106634534.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106634534.model.Gudang_Barang;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String merkBarang;
    private String sku;
    private String tipeBarang; 
    private int stokBarang; 
    private long hargaBarang;
    private List<CreateGudangBarangRequestDTO> listGudangBarang; 
    
}
