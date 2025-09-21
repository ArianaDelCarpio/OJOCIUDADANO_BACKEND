package pe.com.upc.backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.upc.backend.Entities.Usuario;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {
    private Long id;
    private String mensaje;
    private LocalDate fechaEnvio;
    private boolean leida;
    private Usuario usuario;
}
