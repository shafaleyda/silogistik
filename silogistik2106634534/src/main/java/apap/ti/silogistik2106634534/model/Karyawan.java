package apap.ti.silogistik2106634534.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "karyawan")
public class Karyawan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idKaryawan; 

    @NotNull
    @Column(name = "nama_karyawan", nullable = false)
    private String nama; 

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private int jenisKelamin; 

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tanggalLahir; 

    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Permintaan_Pengiriman> listPermintaanPengiriman; 
}
