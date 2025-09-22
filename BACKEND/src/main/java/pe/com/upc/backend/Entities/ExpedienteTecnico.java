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
public class ExpedienteTecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Documento_UR")
    private String documentoUR;

    @Column(name = "Fecha_Carga")
    private LocalDate fechaCarga;

    /*@OneToOne(cascade = CascadeType.ALL, mappedBy = "expedienteTecnico")
    @JsonManagedReference
    private ObraPublica obraPublica;*/
}
