package apap.ti.silogistik2106634534.service;

import apap.ti.silogistik2106634534.model.Gudang;
import apap.ti.silogistik2106634534.model.Gudang_Barang;

import java.util.List; 

public interface GudangService {
    void saveGudang(Gudang gudang); 

    Gudang getGudangById(long idGudang); 

    List<Gudang> getAllGudang(); 

    Gudang updateStokBarangGudang(Gudang gudangFromDTO); 

    Boolean checkBarangExist(String sku, List<Gudang_Barang> listGudangBarang);
    Gudang_Barang getGudangBarang(String sku, List<Gudang_Barang> listGudangBarang); 
    List<Gudang_Barang> listGudangBarangFiltered(String sku); 
    List<Gudang_Barang> getAllGudangBarang(); 

    //cari gudangnya
    //iterate gudang barang dari gudnagDTO 
    //cek barang udh ada atau blm di gudang (sku)
    ////kalau udah ada, set stok aja 
    ////gudang barang dto di set gudang (objek sebenarnya)
    ////gudang barang dto add ke gudang barang asli 
}
