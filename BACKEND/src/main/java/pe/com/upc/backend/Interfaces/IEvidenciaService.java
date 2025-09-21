package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.EvidenciaDTO;

import java.util.List;

public interface IEvidenciaService {
    public List<EvidenciaDTO> listar();
    public EvidenciaDTO registrar(EvidenciaDTO dto);
    public EvidenciaDTO findById(Long id);
    public EvidenciaDTO actualizar(EvidenciaDTO evidenciaDTO);
    public void eliminar(Long id);
}
