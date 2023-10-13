package apap.ti.silogistik2106634534.dto.request; 

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data; 
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    @NotBlank(message = "Field merk barang tidak boleh kosong")
    private String merkBarang;

    @NotNull
    private int tipeBarang;

    @Min(value = 0, message = "Harga barang tidak boleh kurang dari 0")
    private long hargaBarang; 
}
