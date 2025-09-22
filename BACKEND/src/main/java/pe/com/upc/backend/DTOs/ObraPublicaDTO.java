package pe.com.upc.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.upc.backend.Entities.ExpedienteTecnico;
import pe.com.upc.backend.Entities.GobiernoRegional;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ObraPublicaDTO {
    private Long idObra;
    private String nombreObra;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private GobiernoRegional gobiernoRegional;
    private ExpedienteTecnico expedienteTecnico;
}
