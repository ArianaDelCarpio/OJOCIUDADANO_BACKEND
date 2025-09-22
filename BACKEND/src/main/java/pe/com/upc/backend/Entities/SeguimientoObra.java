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
public class SeguimientoObra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Fecha_Inicio")
    private LocalDate fechaInicio;

    @Column(name = "Activo", nullable = false)
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "ObraPublica_ID")
    private ObraPublica obraPublica;

    @ManyToOne
    @JoinColumn(name = "Usuario_ID")
    private Usuario usuario;

}
