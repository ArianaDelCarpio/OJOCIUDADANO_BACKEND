package pe.com.upc.backend.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InversionDTO {
    private Long IdInversion;
    private String MontoTotal;
    private String FuenteFinanciamiento;
    private LocalDate FechaAprobacion;

}
