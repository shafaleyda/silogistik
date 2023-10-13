package apap.ti.silogistik2106634534.model;

import java.util.Date;
import java.util.List;



import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE permintaan_pengiriman SET is_cancelled = true WHERE id_permintaan_pengiriman=?")
@Table(name = "permintaan_pengiriman")
public class Permintaan_Pengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPermintaanPengiriman;
    
    @NotNull
    @Column(name = "nomor_pengiriman", length = 16)
    private String nomorPengiriman; 

    @NotNull
    @Column(name = "nama_penerima")
    private String namaPenerima; 

    @NotNull
    @Column(name = "alamat_penerima")
    private String alamatPenerima; 

    @NotNull
    @Column(name = "tanggal_pengiriman")
    private LocalDate tanggalPengiriman; 

    @NotNull
    @Column(name = "biaya_pengiriman")
    private int biayaPengiriman; 

    @NotNull
    @Column(name = "jenis_layanan")
    private int jenisLayanan; 

    @NotNull
    @Column(name = "waktu_permintaan")
    private LocalDateTime waktuPermintaan; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "idKaryawan")
    private Karyawan karyawan; 

    @OneToMany(mappedBy = "permintaan_pengiriman", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Permintaan_Pengiriman_Barang> listPermintaanPengirimanBarang; 

    @NotNull
    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled = Boolean.FALSE;

}
