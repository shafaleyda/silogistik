package apap.ti.silogistik2106634534.service;

import java.util.List;

import apap.ti.silogistik2106634534.model.Karyawan;

public interface KaryawanService {
    void saveKaryawan(Karyawan karyawan); 
    List<Karyawan> getAllKaryawan(); 
    Karyawan getKaryawanById(long idKaryawan); 
}
