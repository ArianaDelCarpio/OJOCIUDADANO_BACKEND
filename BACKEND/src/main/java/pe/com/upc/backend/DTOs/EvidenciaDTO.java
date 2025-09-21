package pe.com.upc.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EvidenciaDTO {
    private Long id;
    private String tipo;
    private String urlArchivo;
    //private Long denunciaId;
}
