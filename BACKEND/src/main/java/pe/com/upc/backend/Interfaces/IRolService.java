package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.RolDTO;

import java.util.List;

public interface IRolService {
    public List<RolDTO> listar();
    public RolDTO registrar(RolDTO rolDto);
    public RolDTO findById(Long id);
    public RolDTO actualizar(RolDTO rolDTO);
    public void eliminar(Long id);
}
