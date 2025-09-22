package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.GobiernoRegionalDTO;

import java.util.List;

public interface IGobiernoRegionalService {
    List<GobiernoRegionalDTO> listar();
    GobiernoRegionalDTO registrar(GobiernoRegionalDTO gobiernoRegionalDTO);
    GobiernoRegionalDTO findById(Long id);
    GobiernoRegionalDTO actualizar(GobiernoRegionalDTO gobiernoRegionalDTO);
    void eliminar(Long id);
}
