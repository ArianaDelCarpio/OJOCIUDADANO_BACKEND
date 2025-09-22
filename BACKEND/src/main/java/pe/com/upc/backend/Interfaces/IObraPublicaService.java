package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.ObraPublicaDTO;

import java.util.List;

public interface IObraPublicaService {
    List<ObraPublicaDTO> listar();
    ObraPublicaDTO registrar(ObraPublicaDTO obraDTO);
    ObraPublicaDTO findById(Long id);
    ObraPublicaDTO actualizar(ObraPublicaDTO obraDTO);
    void eliminar(Long id);
}
