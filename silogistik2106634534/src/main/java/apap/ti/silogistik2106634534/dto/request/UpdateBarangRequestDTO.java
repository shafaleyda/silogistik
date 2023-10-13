package apap.ti.silogistik2106634534.dto.request;

import lombok.AllArgsConstructor; 
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UpdateBarangRequestDTO extends CreateBarangRequestDTO{
    private String sku; 
    private String tipeBarangString; 
}
