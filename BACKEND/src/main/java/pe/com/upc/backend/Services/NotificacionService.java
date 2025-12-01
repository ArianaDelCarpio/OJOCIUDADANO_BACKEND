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
    public NotificacionDTO actualizar(Long id, NotificacionDTO notificacionDTO) {
        Notificacion existingNotificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion con ID " + id + " no encontrado"));

        // 2. IMPORTANTE: Mapeamos los datos del DTO SOBRE la entidad existente.
        // Esto actualiza los campos de 'existingNotificacion' con los valores de 'notificacionDTO'
        modelMapper.map(notificacionDTO, existingNotificacion);

        // 3. Guardamos la entidad actualizada
        Notificacion saved = notificacionRepository.save(existingNotificacion);

        // 4. Retornamos el DTO
        return modelMapper.map(saved, NotificacionDTO.class);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if(notificacionRepository.existsById(id)){
            notificacionRepository.deleteById(id);
        }
    }
}
