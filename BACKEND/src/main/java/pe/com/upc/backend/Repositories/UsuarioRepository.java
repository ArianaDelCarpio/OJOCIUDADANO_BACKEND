package pe.com.upc.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.upc.backend.Entities.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
