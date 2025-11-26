package pe.com.upc.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.upc.backend.security.entities.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "Denuncia")
public class Denuncia {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDenuncia;
    @Column(name= "Titulo", length = 100)
    private String titulo;
    @Column(name = "Descripcion", length = 100)
    private String descripcion;
    @Column(name= "Estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name="Usuario_id",referencedColumnName = "ID")
    private User usuario;

    @ManyToOne
    @JoinColumn(name="ObraPublica_ID",referencedColumnName = "ID")
    private ObraPublica obraPublica;

}
