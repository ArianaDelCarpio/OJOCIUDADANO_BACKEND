package pe.com.upc.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Mensaje",length = 300)
    private String mensaje;

    private LocalDate fechaEnvio;

    private boolean leida;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
