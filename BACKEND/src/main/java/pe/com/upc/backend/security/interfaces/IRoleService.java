package pe.com.upc.backend.security.interfaces;

import pe.com.upc.backend.security.dtos.RoleDTO;
import pe.com.upc.backend.security.entities.Role;

import java.util.List;

public interface IRoleService {
    public List<RoleDTO> listar();

    public void grabar(Role role);

    public RoleDTO findById(Long id);

    public RoleDTO actualizar(RoleDTO roleDTO);

    public void eliminar(Long id);
}
