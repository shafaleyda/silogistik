package apap.ti.silogistik2106634534.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman_barang")
public class Permintaan_Pengiriman_Barang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPermintaanPengirimanBarang;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "id_permintaan_pengiriman", referencedColumnName = "idPermintaanPengiriman")
    private Permintaan_Pengiriman permintaan_pengiriman;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barang;  

    @NotNull
    @Column(name = "kuantitas_pesanan")
    private int kuantitasPesanan; 


}
