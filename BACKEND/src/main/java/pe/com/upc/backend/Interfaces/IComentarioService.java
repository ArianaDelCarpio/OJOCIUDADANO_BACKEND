package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.ComentarioDTO;

import java.util.List;


public interface IComentarioService {
    List<ComentarioDTO> listar();
    ComentarioDTO registrar(ComentarioDTO dto);
    ComentarioDTO findById(Long id);
    ComentarioDTO actualizar(ComentarioDTO comentarioDTO);
    void eliminar(Long id);
}
