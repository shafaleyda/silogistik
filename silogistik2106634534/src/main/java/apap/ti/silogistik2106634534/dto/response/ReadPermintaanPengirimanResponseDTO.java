package apap.ti.silogistik2106634534.dto.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106634534.model.Karyawan;
import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman_Barang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadPermintaanPengirimanResponseDTO {
    private long idPermintaanPengiriman; 
    private String nomorPengiriman; 
    private String waktuPermintaan; 
    private Karyawan karyawan; 
    private String tanggalPengiriman; 
    private String namaPenerima; 
    private String alamatPenerima; 
    private int jenisLayanan; 
    private String jenisLayananString; 
    private int biayaPengiriman; 
    private List<Permintaan_Pengiriman_Barang> listPermintaanPengirimanBarang;
    private Boolean isCancelled;
}
