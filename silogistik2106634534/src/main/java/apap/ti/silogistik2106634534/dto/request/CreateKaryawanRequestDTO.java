package apap.ti.silogistik2106634534.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor; 
import lombok.Data; 
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
        @NotNull
        private String nama; 

        @Pattern(regexp = "^[01]$")
        private int jenisKelamin; 

        @NotNull
        private Date tanggalLahir; 
}
