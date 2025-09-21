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
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY/*AUTO*/)
    @Column(name = "ID")
    private Long idRol;
    @Column(name = "Nombre",length = 50)
    private String nombre;
    @Column(name = "Descripcion", length = 300)
    private String descripcion;
    /*@OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;*/
}
