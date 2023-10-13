package apap.ti.silogistik2106634534.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106634534.model.Permintaan_Pengiriman;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<Permintaan_Pengiriman, Long>{
    
    List<Permintaan_Pengiriman> findByOrderByWaktuPermintaanDesc();
}
