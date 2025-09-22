package pe.com.upc.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.upc.backend.Entities.ObraPublica;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvanceObraDTO{
    private Long IdAvanceObra;
    private LocalDate FechaReporte;
    private String PorcentajeDeAvance;
    private String Descripcion;
    private ObraPublica obrapublica;
}
