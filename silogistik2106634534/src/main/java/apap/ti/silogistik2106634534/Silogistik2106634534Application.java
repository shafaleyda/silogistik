package apap.ti.silogistik2106634534;

import apap.ti.silogistik2106634534.controller.PermintaanPengirimanController;
import apap.ti.silogistik2106634534.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106634534.model.Karyawan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Locale;
import java.util.Random;

import apap.ti.silogistik2106634534.service.*;
import apap.ti.silogistik2106634534.controller.BarangController;
import apap.ti.silogistik2106634534.dto.*;
import apap.ti.silogistik2106634534.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106634534.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106634534.dto.request.CreateKaryawanRequestDTO; 

@SpringBootApplication
public class Silogistik2106634534Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106634534Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run (GudangService gudangService, KaryawanService karyawanService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper) {
		return args -> {
			Random random = new Random();
			var faker = new Faker(new Locale("in-ID")); 
			for (int i = 0; i <= 9; i++){
				
				var gudangDTO = new CreateGudangRequestDTO(); 
				var fakeNamaGudang = "Gudang " + faker.lorem().word();
				var fakeAlamatGudang = faker.address(); 

				gudangDTO.setNamaGudang(fakeNamaGudang);
				gudangDTO.setAlamatGudang(fakeAlamatGudang.fullAddress());

				var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO); 

				var karyawanDTO = new CreateKaryawanRequestDTO(); 
				var fakeNamaKaryawan = faker.name(); 
				
				int fakeJenisKelamin = random.nextInt(2); 
				var fakeTanggalLahir = faker.date().birthday(20, 65); 

				karyawanDTO.setNama(fakeNamaKaryawan.fullName());
				karyawanDTO.setJenisKelamin(fakeJenisKelamin);
				karyawanDTO.setTanggalLahir(fakeTanggalLahir);

				var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);

				gudangService.saveGudang(gudang);
				karyawanService.saveKaryawan(karyawan);
			}

		};
	}
}
