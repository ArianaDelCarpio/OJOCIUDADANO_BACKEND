package pe.com.upc.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.upc.backend.Entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
    /*// Por obra pública o usuario (usa navegación de propiedad con _)
    List<Comentario> findByObraPublica_Id(Long obraPublicaId);
    List<Comentario> findByUsuario_Id(Long usuarioId);

    // Por rango de fechas
    List<Comentario> findByFechaComentarioBetween(LocalDate desde, LocalDate hasta);

    // Útiles
    long countByObraPublica_Id(Long obraPublicaId);*/
}
