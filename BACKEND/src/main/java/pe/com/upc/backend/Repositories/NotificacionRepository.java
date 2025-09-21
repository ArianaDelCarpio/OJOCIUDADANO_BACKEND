package pe.com.upc.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.upc.backend.Entities.Notificacion;

@Repository

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}
