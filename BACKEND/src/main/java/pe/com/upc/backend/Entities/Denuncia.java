package pe.com.upc.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "Denuncia")
public class Denuncia {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdDenuncia;
    @Column(name= "Titulo", length = 100)
    private String Titulo;
    @Column(name = "Descripcion", length = 100)
    private String Descripcion;
    @Column(name= "Estado")
    private Boolean Estado;

    @ManyToOne
    @JoinColumn(name="Usuario_id",referencedColumnName = "ID")
    private Usuario usuario;



}
