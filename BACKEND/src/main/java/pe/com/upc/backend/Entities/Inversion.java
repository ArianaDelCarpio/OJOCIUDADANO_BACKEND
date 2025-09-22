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
    private Long IdInversion;
    @Column(name= "Monto_total", length = 100)
    private String MontoTotal;
    @Column(name = "Fuente Financiamiento", length = 100)
    private String FuenteFinanciamiento;
    @Column(name= "Fecha Aprobacion")
    private LocalDate FechaAprobacion;

    //@ManyToOne
    //@JoinColumn(name="ObraPublica Id",referencedColumnName = "ID")
    //private ObraPublica obrapublica;

}
