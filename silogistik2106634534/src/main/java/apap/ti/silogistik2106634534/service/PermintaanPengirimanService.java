package apap.ti.silogistik2106634534.service;

import apap.ti.silogistik2106634534.model.Gudang;
import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman;

import java.util.List; 

public interface PermintaanPengirimanService {
    List<Permintaan_Pengiriman> getAllPermintaanPengiriman(); 
    List<Permintaan_Pengiriman> getAllPermintaanPengirimanSorted(); 
    Permintaan_Pengiriman getPermintaanPengirimanById(long idPermintaanPengiriman); 
    void createPermintaanPengiriman(Permintaan_Pengiriman permintaanPengiriman); 
    void kurangStokBarang(int kuantitasPesana, String sku); 
    void deletePermintaanPengiriman(Permintaan_Pengiriman permintaanPengiriman);
}
