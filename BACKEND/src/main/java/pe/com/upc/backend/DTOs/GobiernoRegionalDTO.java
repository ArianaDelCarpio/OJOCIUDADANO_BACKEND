package pe.com.upc.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GobiernoRegionalDTO {
    private Long id;
    private String nombre;
    private String ubicacion;
    private String contacto;
}
