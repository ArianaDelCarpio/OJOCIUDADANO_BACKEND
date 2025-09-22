package pe.com.upc.backend.Repositories;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.upc.backend.Entities.Inversion;
@Repository
public interface InversionRepository extends JpaRepository<Inversion, Long>
{
}
