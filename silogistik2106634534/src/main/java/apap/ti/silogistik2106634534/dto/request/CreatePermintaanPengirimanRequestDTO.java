package apap.ti.silogistik2106634534.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import apap.ti.silogistik2106634534.model.Karyawan;
import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman_Barang;

import java.util.List;

import jakarta.validation.constraints.Min;
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
public class CreatePermintaanPengirimanRequestDTO {
    private Karyawan karyawan;

    private LocalDate tanggalPengiriman;

    @NotNull(message = "Tanggal Pengiriman tidak boleh kosong")
    private String tanggalPengirimanString;

    @NotBlank(message = "Nama Penerima tidak boleh kosong")
    private String namaPenerima;
    private int jenisLayanan;

    @NotBlank(message = "Alamat Penerima tidak boleh kosong")
    private String alamatPenerima;

    @Min(value = 0, message = "Biaya pengiriman tidak boleh kurang dari 0")
    private int biayaPengiriman;

    private List<Permintaan_Pengiriman_Barang> listPermintaanPengirimanBarang; 
    private LocalDateTime waktuPermintaan = LocalDateTime.now(); 
}
