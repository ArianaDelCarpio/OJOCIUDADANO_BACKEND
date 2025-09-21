package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.DenunciaDTO;

import java.util.List;


public interface IDenunciaService {
    public List<DenunciaDTO> listar();
    public DenunciaDTO registrar( DenunciaDTO denunciaDTO);
    public DenunciaDTO findById( Long id);
    public DenunciaDTO actualizar(DenunciaDTO denunciaDTO);
    public void eliminar( Long id);

}
