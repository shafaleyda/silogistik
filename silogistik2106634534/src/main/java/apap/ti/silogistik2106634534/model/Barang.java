package apap.ti.silogistik2106634534.model;

import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman_Barang;

import java.util.List;

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
@Table(name = "barang")
public class Barang {
    
    @Id
    @Column(name = "sku")
    private String sku; 

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private int tipeBarang; 

    @NotNull
    @Column(name = "merk_barang", nullable = false)
    private String merkBarang; 

    @NotNull
    @Column(name = "harga_barang")
    private long hargaBarang; 
    
    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Gudang_Barang> listGudangBarang; 

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Permintaan_Pengiriman_Barang> listPermintaanPengirimanBarang; 
}
