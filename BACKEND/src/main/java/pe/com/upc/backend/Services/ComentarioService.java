package pe.com.upc.backend.Services;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.ComentarioDTO;
import pe.com.upc.backend.Entities.Comentario;
import pe.com.upc.backend.Interfaces.IComentarioService;
import pe.com.upc.backend.Repositories.ComentarioRepository;

import java.util.List;

@Service
public class ComentarioService implements IComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ComentarioDTO> listar() {
        return comentarioRepository.findAll().stream()
                .map(comentario -> modelMapper.map(comentario, ComentarioDTO.class))
                .toList();
    }

    @Override
    public ComentarioDTO registrar(ComentarioDTO comentarioDTO) {
        if (comentarioDTO.getId() == null) {
            Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
            return modelMapper.map(comentarioRepository.save(comentario), ComentarioDTO.class);
        }
        return null;
    }

    @Override
    public ComentarioDTO findById(Long id) {
        return comentarioRepository.findById(id)
                .map(comentario -> modelMapper.map(comentario, ComentarioDTO.class))
                .orElseThrow(() -> new RuntimeException("Comentario con ID " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public ComentarioDTO actualizar(ComentarioDTO comentarioDTO) {
        return comentarioRepository.findById(comentarioDTO.getId())
                .map(existing -> {
                    Comentario updatedComentario = modelMapper.map(comentarioDTO, Comentario.class);
                    return modelMapper.map(comentarioRepository.save(updatedComentario), ComentarioDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Comentario con ID " + comentarioDTO.getId() + " no encontrado"));
        /*if (comentarioDTO.getId() != null && comentarioRepository.existsById(comentarioDTO.getId())) {
            return comentarioRepository.save(comentarioDTO);
        }
        return null;*/
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if(comentarioRepository.existsById(id)){
            comentarioRepository.deleteById(id);
        }
    }
}
