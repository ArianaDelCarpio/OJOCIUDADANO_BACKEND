package pe.com.upc.backend.Services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.NotificacionDTO;
import pe.com.upc.backend.Entities.Notificacion;
import pe.com.upc.backend.Interfaces.INotificacionService;
import pe.com.upc.backend.Repositories.NotificacionRepository;

import java.util.List;

@Service
public class NotificacionService implements INotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    /*@Autowired
    private UsuarioRepository usuarioRepository;*/

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NotificacionDTO> listar() {
        return notificacionRepository.findAll().stream()
                .map(notificacion -> modelMapper.map(notificacion,NotificacionDTO.class))
                .toList();
    }

    @Override
    public NotificacionDTO registrar(NotificacionDTO notificacionDTO) {
        if(notificacionDTO.getId()==null){
            Notificacion notificacion = modelMapper.map(notificacionDTO,Notificacion.class);
            return modelMapper.map(notificacionRepository.save(notificacion),NotificacionDTO.class);
        }
        return null;
    }

    @Override
    public NotificacionDTO findById(Long id) {
        return notificacionRepository.findById(id)
                .map(notificacion -> modelMapper.map(notificacion, NotificacionDTO.class))
                .orElseThrow(() -> new RuntimeException("Notificacion con ID " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public NotificacionDTO actualizar(NotificacionDTO notificacionDTO) {
        return notificacionRepository.findById(notificacionDTO.getId())
                .map(existing -> {
                    Notificacion updatedNotificacion = modelMapper.map(notificacionDTO, Notificacion.class);
                    return modelMapper.map(notificacionRepository.save(updatedNotificacion), NotificacionDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Notificacion con ID " + notificacionDTO.getId() + " no encontrado"));
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if(notificacionRepository.existsById(id)){
            notificacionRepository.deleteById(id);
        }
    }
}
