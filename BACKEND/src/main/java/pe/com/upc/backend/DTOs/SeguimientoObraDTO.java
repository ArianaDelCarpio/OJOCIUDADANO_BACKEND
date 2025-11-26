package pe.com.upc.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.upc.backend.Entities.ObraPublica;
import pe.com.upc.backend.security.entities.User;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoObraDTO {
    private Long id;
    private LocalDate fechaInicio;
    private Boolean activo;

    private ObraPublica obraPublica;
    private User usuario;


}
