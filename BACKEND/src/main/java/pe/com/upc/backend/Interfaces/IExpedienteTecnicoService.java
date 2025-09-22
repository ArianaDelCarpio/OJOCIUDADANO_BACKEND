package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.ExpedienteTecnicoDTO;

import java.util.List;

public interface IExpedienteTecnicoService {
    List<ExpedienteTecnicoDTO> listar();
    ExpedienteTecnicoDTO registrar(ExpedienteTecnicoDTO expedienteTecnicoDTO);
    ExpedienteTecnicoDTO findById(Long id);
    ExpedienteTecnicoDTO actualizar(ExpedienteTecnicoDTO expedienteTecnicoDTO);
    void eliminar(Long id);
}
