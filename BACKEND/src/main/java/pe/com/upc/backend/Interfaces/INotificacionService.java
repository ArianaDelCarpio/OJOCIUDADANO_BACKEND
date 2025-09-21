package pe.com.upc.backend.Interfaces;

import pe.com.upc.backend.DTOs.NotificacionDTO;

import java.util.List;

public interface INotificacionService {
    public  List<NotificacionDTO> listar();
    public NotificacionDTO registrar(NotificacionDTO dto);
    public NotificacionDTO findById(Long id);
    public NotificacionDTO actualizar(NotificacionDTO notificacionDTO);
    public void eliminar(Long id);
}
