package apap.ti.silogistik2106634534.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106634534.repository.BarangDb;
import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang_Barang;


@Service
public class BarangServiceImpl implements BarangService{
    @Autowired
    BarangDb barangDb; 

    @Override
    public void saveBarang(Barang barang) {
        barangDb.save(barang); 
        
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll(); 
    }

    @Override
    public Barang getBarangBySku(String sku) {
        for (Barang barang: getAllBarang()){
            if(barang.getSku().equalsIgnoreCase(sku)) {
                return barang; 
            }
        } return null; 
    }

    @Override
    public Barang updateBarang(Barang barangFromDTO) {
        Barang barang = getBarangBySku(barangFromDTO.getSku()); 

        // if (barang != null){
            barang.setMerkBarang(barangFromDTO.getMerkBarang());
            barang.setHargaBarang(barangFromDTO.getHargaBarang());
            barangDb.save(barang); 
        // }
        return barang; 
    }

    @Override
    public List<Gudang_Barang> getListGudangBarang(String sku) {
        Barang barang = getBarangBySku(null);
        return barang.getListGudangBarang(); 
    }

    @Override
    public String getTipeBarangString(int tipeBarangInt) {
        switch (tipeBarangInt) {
            case 1:
                return "Produk Elektronik";
            case 2:
                return "Pakaian & Aksesoris"; 
            case 3:
                return "Makanan & Minuman"; 
            case 4:
                return "Kosmetik";
            case 5: 
                return "Perlengkapan Rumah";
            default:
                return "Tipe Barang Salah";
        }
    }
    
}
