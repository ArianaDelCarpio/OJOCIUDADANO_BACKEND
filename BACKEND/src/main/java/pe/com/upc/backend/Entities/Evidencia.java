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
@Table(name = "evidencias")
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    @Column(/*nullable = false*/)
    private String tipo;

    //@NotBlank
    @Column(/*nullable = false*/)
    private String urlArchivo;

    @ManyToOne
    @JoinColumn(name = "denuncia_id")
    private Denuncia denuncia;
}