package apap.ti.silogistik2106634534.dto.request;

import java.util.List;

import apap.ti.silogistik2106634534.model.Gudang_Barang;
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO {
    private long idGudang; 
}
