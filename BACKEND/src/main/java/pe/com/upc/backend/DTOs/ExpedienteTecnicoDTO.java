package pe.com.upc.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExpedienteTecnicoDTO {
    private Long id;
    private String documentoUR;
    private LocalDate fechaCarga;
    //private ObraPublica obraPublica;
}
