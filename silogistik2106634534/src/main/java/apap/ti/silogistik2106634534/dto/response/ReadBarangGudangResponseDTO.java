package apap.ti.silogistik2106634534.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangGudangResponseDTO {
    String sku; 
    String merkBarang; 
    int stokBarang;
}
