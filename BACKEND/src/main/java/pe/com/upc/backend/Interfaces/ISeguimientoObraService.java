package pe.com.upc.backend.Interfaces;


import pe.com.upc.backend.DTOs.SeguimientoObraDTO;

import java.util.List;


public interface ISeguimientoObraService {
    List<SeguimientoObraDTO> listar();
    SeguimientoObraDTO registrar(SeguimientoObraDTO dto);
    SeguimientoObraDTO findById(Long id);
    SeguimientoObraDTO actualizar(SeguimientoObraDTO seguimientoObraDTO);
    void eliminar(Long id);
}
