package pe.com.upc.backend.security.dtos;

import lombok.*;
import pe.com.upc.backend.security.dtos.RoleDTO;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username; // El correo
    private String nombre;
    private String apellido;
    private LocalDate fechaRegistro;

    private Set<RoleDTO> roles;
}
