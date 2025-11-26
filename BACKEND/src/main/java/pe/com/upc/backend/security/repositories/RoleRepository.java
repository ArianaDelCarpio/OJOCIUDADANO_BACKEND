package pe.com.upc.backend.security.repositories;
import org.springframework.stereotype.Repository;
import pe.com.upc.backend.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
