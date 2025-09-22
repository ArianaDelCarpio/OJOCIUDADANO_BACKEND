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
public class ObraPublica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long idObra;

    @Column(name = "Nombre_Obra", length = 100)
    private String nombreObra;

    @Column(name = "Descripcion", length = 300)
    private String descripcion;

    @Column(name = "Fecha_Inicio")
    private LocalDate fechaInicio;

    @Column(name = "Fecha_Fin")
    private LocalDate fechaFin;

    @Column(name = "Estado", length = 50)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "Gobierno_Regional_ID"/*, referencedColumnName = "ID"*/)
    private GobiernoRegional gobiernoRegional;

    @OneToOne/*(cascade = CascadeType.ALL)*/
    @JoinColumn(name = "expediente_id")
    private ExpedienteTecnico expedienteTecnico;

}
