package pe.com.upc.backend.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.upc.backend.security.entities.User;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Contenido", nullable = false)
    private String contenido;

    @Column(name = "Fecha_Comentario")
    private LocalDate fechaComentario;

    @ManyToOne
    @JoinColumn(name = "Usuario_ID")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "ObraPublica_ID")
    private ObraPublica obraPublica;

}
