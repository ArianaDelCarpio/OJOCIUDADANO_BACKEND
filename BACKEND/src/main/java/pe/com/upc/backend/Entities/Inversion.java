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
@Table(name = "Inversion")
public class Inversion {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInversion;
    @Column(name= "Monto_total", length = 100)
    private String montoTotal;
    @Column(name = "Fuente Financiamiento", length = 100)
    private String fuenteFinanciamiento;
    @Column(name= "Fecha Aprobacion")
    private LocalDate fechaAprobacion;

    @ManyToOne
    @JoinColumn(name="ObraPublica_ID",referencedColumnName = "ID")
    private ObraPublica obrapublica;

}
