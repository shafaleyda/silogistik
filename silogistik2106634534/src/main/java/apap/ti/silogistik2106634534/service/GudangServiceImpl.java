package apap.ti.silogistik2106634534.service;

import apap.ti.silogistik2106634534.model.Gudang_Barang;
import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang;
import apap.ti.silogistik2106634534.repository.GudangDb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GudangServiceImpl implements GudangService{
    @Autowired
    GudangDb gudangDb; 

    @Override
    public void saveGudang(Gudang gudang) {
        gudangDb.save(gudang); 
    }
    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll(); 
    }

    @Override
    public Gudang getGudangById(long idGudang) {
        for (Gudang gudang: getAllGudang()) {
            if (((Long)gudang.getIdGudang()).equals(idGudang)) {
                return gudang; 
            }
        } return null; 
    }
    
    @Override
    public Gudang updateStokBarangGudang(Gudang gudangFromDTO) {
        Gudang gudang = getGudangById(gudangFromDTO.getIdGudang());

        for (Gudang_Barang gudangBarangDTO: gudangFromDTO.getListGudangBarang()) {
            Barang barang = gudangBarangDTO.getBarang(); 

            if (checkBarangExist(barang.getSku(), gudang.getListGudangBarang())){ 
                Gudang_Barang gudangBarangToSet = getGudangBarang(barang.getSku(), gudang.getListGudangBarang()); 
                gudangBarangDTO.setStokBarang(gudangBarangDTO.getStokBarang() + gudangBarangToSet.getStokBarang());
            } else {
                gudangBarangDTO.setGudang(gudang);
                gudang.getListGudangBarang().add(gudangBarangDTO);
            }
        }

        gudangDb.save(gudang); 

        return gudang; 
    }
    
    @Override
    public Boolean checkBarangExist(String sku, List<Gudang_Barang> listGudangBarang) {
        boolean result = false; 
        for (Gudang_Barang gudangBarang: listGudangBarang){
            if (gudangBarang.getBarang().getSku().equals(sku)) {
                result = true; 
            }
        }
        return result; 
    }

    @Override
    public Gudang_Barang getGudangBarang(String sku, List<Gudang_Barang> listGudangBarang) {
        for (Gudang_Barang gudangBarang: listGudangBarang){
            if (gudangBarang.getBarang().getSku().equals(sku)) {
                return gudangBarang; 
            }
        } return null; 
    }
    
    @Override
    public List<Gudang_Barang> listGudangBarangFiltered(String sku) {
        List<Gudang_Barang> result = new ArrayList<>(); 
        for (Gudang gudang: getAllGudang()) {
            for (Gudang_Barang gudang_barang: gudang.getListGudangBarang()){
                if (gudang_barang.getBarang().getSku().equals(sku)) {
                    result.add(gudang_barang); 
                }
            }
        }
        
        return result; 
    }
    @Override
    public List<Gudang_Barang> getAllGudangBarang() {
        List<Gudang_Barang> result = new ArrayList<>(); 
        for (Gudang gudang: getAllGudang()) {
            for (Gudang_Barang gudang_barang: gudang.getListGudangBarang()){
                result.add(gudang_barang); 
            }
        }
        return result; 
    }


}
