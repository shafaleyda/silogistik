package apap.ti.silogistik2106634534.model; 

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
@Table(name = "gudang_barang")
public class Gudang_Barang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGudangBarang; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gudang", referencedColumnName = "idGudang")
    private Gudang gudang; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barang; 

    @NotNull
    @Column(name = "stok", length = 255)
    private int stokBarang; 

}