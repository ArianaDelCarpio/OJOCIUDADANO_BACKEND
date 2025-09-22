package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.AvanceObraDTO;
import java.util.List;

public interface IAvanceObra {
    public List<AvanceObraDTO> listar();
    public AvanceObraDTO registrar( AvanceObraDTO avanceObraDTO);
    public AvanceObraDTO findById( Long id);
    public AvanceObraDTO actualizar(AvanceObraDTO avanceObraDTO);
    public void eliminar( Long id);
}
