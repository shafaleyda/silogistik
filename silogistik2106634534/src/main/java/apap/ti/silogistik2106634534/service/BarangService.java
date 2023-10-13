package apap.ti.silogistik2106634534.service;

import java.util.List;

import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang_Barang;

public interface BarangService {
    void saveBarang(Barang barang); 
    List<Barang> getAllBarang(); 
    Barang getBarangBySku(String sku); 
    Barang updateBarang(Barang barang); 
    List<Gudang_Barang> getListGudangBarang(String sku); 
    String getTipeBarangString(int tipeBarangInt); 
}
