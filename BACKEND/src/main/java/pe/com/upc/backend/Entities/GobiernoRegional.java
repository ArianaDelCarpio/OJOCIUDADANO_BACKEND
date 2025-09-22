package pe.com.upc.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GobiernoRegional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(length = 200)
    private String ubicacion;

    @Column(length = 100)
    private String contacto;

    /*@OneToMany(mappedBy = "gobiernoRegional")
    private List<ObraPublica> obrasPublicas;*/
}
