package pe.com.upc.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RolDTO {
    private Long idRol;
    private String nombre;
    private String descripcion;
    /*private List<UsuarioDTO> usuarios;*/
}
