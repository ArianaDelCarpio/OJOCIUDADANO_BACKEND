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
public class AvanceObra {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvanceObra;
    @Column(name= "Fecha_Reporte", length = 100)
    private LocalDate fechaReporte;
    @Column(name = "Porcentaje_Avance", length = 100)
    private String porcentajeDeAvance;
    @Column(name= "Descripcion", length = 100)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="ObraPublica_ID")
    private ObraPublica obrapublica;
}
