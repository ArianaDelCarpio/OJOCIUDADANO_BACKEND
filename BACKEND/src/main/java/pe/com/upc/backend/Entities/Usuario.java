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
public class Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY/*AUTO*/)
    @Column(name = "ID")
    @Id
    private Long idUsuario;
    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @Column(unique = true)
    private String correo;

    @Column(name = "Contrasena", length = 200)
    private String contrasena;

    @Column(name = "Fecha_Registro")
    private LocalDate fechaRegistro;

    @ManyToOne  //(optional = true)
    @JoinColumn(name = "Rol_ID"/*, referencedColumnName = "ID"*/)
    private Rol rol;
}
