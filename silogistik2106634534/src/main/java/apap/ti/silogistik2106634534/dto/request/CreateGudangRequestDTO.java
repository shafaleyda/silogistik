package apap.ti.silogistik2106634534.dto.request;

import java.util.List;

import apap.ti.silogistik2106634534.model.Gudang_Barang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class CreateGudangRequestDTO {
    private String namaGudang;
    private String alamatGudang;
    List<Gudang_Barang> listGudangBarang; 
}
