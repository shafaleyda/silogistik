package apap.ti.silogistik2106634534.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106634534.model.Karyawan;

@Repository
public interface KaryawanDb extends JpaRepository<Karyawan, Long>{
    
}
