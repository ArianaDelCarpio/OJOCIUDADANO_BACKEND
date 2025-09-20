package pe.com.upc.backend.DTOs;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
//import pe.com.upc.backend.entities.Rol;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {
    private Long idUsuario;
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    private String apellido;
    @Email(message = "El correo debe ser válido")
    private String correo;
    private String contrasena;
    private LocalDate fechaRegistro;
    //private Rol rol;
    /*private Long rolId;*/
}
