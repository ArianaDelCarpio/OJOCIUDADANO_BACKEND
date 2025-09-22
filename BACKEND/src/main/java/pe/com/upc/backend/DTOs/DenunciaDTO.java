package pe.com.upc.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.upc.backend.Entities.ObraPublica;
import pe.com.upc.backend.Entities.Usuario;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaDTO {
    private Long IdDenuncia;
    private String Titulo;
    private String Descripcion;
    private Boolean Estado;
    private Usuario usuario;
    private ObraPublica obraPublica;
}
