package apap.ti.silogistik2106634534.model;

import apap.ti.silogistik2106634534.model.Gudang_Barang;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List; 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang")
public class Gudang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGudang; 

    @NotNull
    @Column(name = "nama_gudang")
    private String namaGudang; 

    @NotNull
    @Column(name = "alamat_gudang")
    private String alamatGudang; 

    @OneToMany(mappedBy = "gudang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Gudang_Barang> listGudangBarang; 
}