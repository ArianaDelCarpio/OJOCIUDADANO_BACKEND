package pe.com.upc.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.upc.backend.Entities.SeguimientoObra;

@Repository
public interface SeguimientoObraRepository extends JpaRepository<SeguimientoObra,Long> {
    /*List<SeguimientoObra> findByObraPublica_Id(Long obraPublicaId);
    List<SeguimientoObra> findByUsuario_Id(Long usuarioId);

    // Activos/Inactivos
    List<SeguimientoObra> findByActivoTrue();
    List<SeguimientoObra> findByActivoFalse();

    // Por fecha de inicio
    List<SeguimientoObra> findByFechaInicioBetween(LocalDate desde, LocalDate hasta);

    // Último seguimiento de un usuario sobre una obra
    Optional<SeguimientoObra> findFirstByObraPublica_IdAndUsuario_IdOrderByFechaInicioDesc(
            Long obraPublicaId, Long usuarioId);*/
}
