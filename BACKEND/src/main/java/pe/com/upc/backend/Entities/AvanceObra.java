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
@Table(name = "Avance Obra")
public class AvanceObra {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvanceObra;
    @Column(name= "Fecha Reporte", length = 100)
    private LocalDate fechaReporte;
    @Column(name = "Porcentaje de Avance", length = 100)
    private String porcentajeDeAvance;
    @Column(name= "Descripcion", length = 100)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="ObraPublica_ID")
    private ObraPublica obrapublica;
}
