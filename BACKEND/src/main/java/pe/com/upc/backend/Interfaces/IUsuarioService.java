package pe.com.upc.backend.Interfaces;


import pe.com.upc.backend.DTOs.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    public List<UsuarioDTO> listar();
    public UsuarioDTO registrar(UsuarioDTO usuarioDTO);
    public UsuarioDTO findById(Long id);
    public UsuarioDTO actualizar(UsuarioDTO usuarioDTO);
    public void eliminar(Long id);
}
