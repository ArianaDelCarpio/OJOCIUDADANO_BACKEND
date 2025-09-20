package pe.com.upc.backend.Services;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.UsuarioDTO;
import pe.com.upc.backend.Entities.Usuario;
import pe.com.upc.backend.Interfaces.IUsuarioService;
import pe.com.upc.backend.Repositories.UsuarioRepository;

import java.util.List;


@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .toList();
    }

    @Override
    public UsuarioDTO registrar(UsuarioDTO usuarioDTO) {
        if(usuarioDTO.getIdUsuario() == null){
            Usuario usuario = modelMapper.map(usuarioDTO,Usuario.class);
            return modelMapper.map(usuarioRepository.save(usuario),UsuarioDTO.class);
        }
        return null;
    }

    @Override
    public UsuarioDTO findById(Long id) {
        return usuarioRepository.findById(id)
                .map((element)->modelMapper.map(element,UsuarioDTO.class))
                .orElseThrow(()-> new RuntimeException("Usuario con ID \" + id + \" no encontrado"));
    }

    @Override
    public UsuarioDTO actualizar(UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(usuarioDTO.getIdUsuario())
                .map(existing -> {
                    Usuario usuarioEntidad = modelMapper.map(usuarioDTO, Usuario.class);
                    Usuario guardado = usuarioRepository.save(usuarioEntidad);
                    return modelMapper.map(guardado, UsuarioDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + usuarioDTO.getIdUsuario() +
                        " no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario con id " + id + " no existe");
        }
        usuarioRepository.deleteById(id);
    }
}
