package apap.ti.silogistik2106634534.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106634534.model.Barang;
import apap.ti.silogistik2106634534.model.Gudang;
import apap.ti.silogistik2106634534.model.Gudang_Barang;
import apap.ti.silogistik2106634534.model.Karyawan;
import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman;
import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman_Barang;
import apap.ti.silogistik2106634534.repository.GudangDb;
import apap.ti.silogistik2106634534.repository.PermintaanPengirimanDb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List; 

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService{
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb; 
    @Autowired
    KaryawanService karyawanService; 
    @Autowired
    BarangService barangService; 
    @Autowired
    GudangService gudangService;
    @Autowired
    GudangDb gudangDb; 

    public List<Permintaan_Pengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDb.findAll(); 
    }

    @Override
    public Permintaan_Pengiriman getPermintaanPengirimanById(long idPermintaanPengiriman) {
       for (Permintaan_Pengiriman permintaanPengiriman: getAllPermintaanPengiriman()) {
        if (permintaanPengiriman.getIdPermintaanPengiriman() == idPermintaanPengiriman) {
            return permintaanPengiriman; 
        }
       } return null; 
    }

    @Override
    public void createPermintaanPengiriman(Permintaan_Pengiriman permintaanPengirimanFromDTO){
        //Set permintaan pengiriman di Karyawan 
        Karyawan karyawan = karyawanService.getKaryawanById(permintaanPengirimanFromDTO.getKaryawan().getIdKaryawan()); 
        karyawan.getListPermintaanPengiriman().add(permintaanPengirimanFromDTO);

        for (Permintaan_Pengiriman_Barang permintaanPengirimanBarangDTO: permintaanPengirimanFromDTO.getListPermintaanPengirimanBarang()){
            Barang barang = barangService.getBarangBySku(permintaanPengirimanBarangDTO.getBarang().getSku());
            permintaanPengirimanBarangDTO.setBarang(barang);
            permintaanPengirimanBarangDTO.setPermintaan_pengiriman(permintaanPengirimanFromDTO);
            kurangStokBarang(permintaanPengirimanBarangDTO.getKuantitasPesanan(), barang.getSku());
        }
        permintaanPengirimanDb.save(permintaanPengirimanFromDTO);
    }

    @Override
    public void kurangStokBarang(int kuantitasPesanan, String sku) {
        //Cari gudang 
        List<Gudang> gudangTempatBarang = new ArrayList<>(); 

        for (Gudang_Barang gudangBarang: gudangService.listGudangBarangFiltered(sku)) {
            gudangBarang.setStokBarang(gudangBarang.getStokBarang() - kuantitasPesanan);
            gudangTempatBarang.add(gudangBarang.getGudang()); 
        }

        for (Gudang gudang: gudangTempatBarang) {
            gudangDb.save(gudang);
        }

    }

    @Override
    public void deletePermintaanPengiriman(Permintaan_Pengiriman permintaanPengiriman) {
        permintaanPengirimanDb.delete(permintaanPengiriman);
    }

    @Override
    public List<Permintaan_Pengiriman> getAllPermintaanPengirimanSorted() {
        return permintaanPengirimanDb.findByOrderByWaktuPermintaanDesc(); 
    }

    

    
}
