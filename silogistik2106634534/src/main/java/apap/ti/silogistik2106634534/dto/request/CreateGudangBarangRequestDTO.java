package apap.ti.silogistik2106634534.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangBarangRequestDTO {
    private long idGudangBarang;

    @NotNull
    private Barang barang;

    private Gudang gudang;

    @Min(value = 0, message = "Stok barang tidak boleh kurang dari 0")
    private int stokBarang; 
}
