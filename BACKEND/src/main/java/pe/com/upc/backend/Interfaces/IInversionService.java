package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.InversionDTO;

import java.util.List;

public interface IInversionService {
    public List<InversionDTO> listar();
    public InversionDTO registrar(InversionDTO inversionDTO);
    public InversionDTO findById(Long id);
    public InversionDTO actualizar(InversionDTO inversionDTO);
    public void eliminar(Long id);
}
