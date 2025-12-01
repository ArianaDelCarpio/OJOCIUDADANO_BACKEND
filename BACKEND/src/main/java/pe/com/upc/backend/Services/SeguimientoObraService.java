package pe.com.upc.backend.Services;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.ObraPublicaDTO;
import pe.com.upc.backend.DTOs.SeguimientoObraDTO;
import pe.com.upc.backend.Entities.ObraPublica;
import pe.com.upc.backend.Entities.SeguimientoObra;
import pe.com.upc.backend.Interfaces.ISeguimientoObraService;
import pe.com.upc.backend.Repositories.ObraPublicaRepository;
import pe.com.upc.backend.Repositories.SeguimientoObraRepository;
import pe.com.upc.backend.security.entities.User;
import pe.com.upc.backend.security.repositories.UserRepository;

import java.util.List;

@Service
public class SeguimientoObraService implements ISeguimientoObraService {
    @Autowired
    private SeguimientoObraRepository seguimientoObraRepository;

    @Autowired
    private ObraPublicaRepository obraRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SeguimientoObraDTO> listar() {
        return seguimientoObraRepository.findAll().stream()
                .map(seguimientoObra -> modelMapper.map(seguimientoObra, SeguimientoObraDTO.class))
                .toList();
    }

    @Override
    public SeguimientoObraDTO registrar(SeguimientoObraDTO seguimientoObraDTO) {
        if(seguimientoObraDTO.getId()==null){
            SeguimientoObra seguimientoObra = modelMapper.map(seguimientoObraDTO,SeguimientoObra.class);
            return modelMapper.map(seguimientoObraRepository.save(seguimientoObra), SeguimientoObraDTO.class);
        }
        return null;
        /*// 1. Convertimos el DTO a Entidad base
        SeguimientoObra seguimiento = modelMapper.map(seguimientoObraDTO, SeguimientoObra.class);

        // 2. CORRECCIÓN: Buscamos la Obra Pública real en la BD
        if (seguimientoObraDTO.getObraPublica() != null) {
            ObraPublica obraReal = obraRepository.findById(seguimientoObraDTO.getObraPublica().getIdObra()) // O getId() según tu DTO
                    .orElseThrow(() -> new RuntimeException("Obra Pública no encontrada"));
            seguimiento.setObraPublica(obraReal);
        }

        // 3. CORRECCIÓN: Buscamos el Usuario real en la BD
        if (seguimientoObraDTO.getUsuario() != null) {
            User usuarioReal = userRepository.findById(seguimientoObraDTO.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario responsable no encontrado"));
            seguimiento.setUsuario(usuarioReal);
        }

        // 4. Guardamos
        return modelMapper.map(seguimientoObraRepository.save(seguimiento), SeguimientoObraDTO.class);*/
    }

    @Override
    public SeguimientoObraDTO findById(Long id) {
        return seguimientoObraRepository.findById(id)
                .map(seguimientoObra -> modelMapper.map(seguimientoObra, SeguimientoObraDTO.class))
                .orElseThrow(() -> new RuntimeException("Seguimiento de obra con ID " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public SeguimientoObraDTO actualizar(Long id, SeguimientoObraDTO dto) {
        SeguimientoObra existente = seguimientoObraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el seguimiento"));

        // Mapeamos datos simples (fecha, activo)
        existente.setFechaInicio(dto.getFechaInicio());
        existente.setActivo(dto.getActivo());

        // Actualizamos relaciones buscando en BD
        if (dto.getObraPublica() != null) {
            ObraPublica obra = obraRepository.findById(dto.getObraPublica().getIdObra())
                    .orElse(null);
            existente.setObraPublica(obra);
        }

        if (dto.getUsuario() != null) {
            User user = userRepository.findById(dto.getUsuario().getId())
                    .orElse(null);
            existente.setUsuario(user);
        }

        return modelMapper.map(seguimientoObraRepository.save(existente), SeguimientoObraDTO.class);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if(seguimientoObraRepository.existsById(id)){
            seguimientoObraRepository.deleteById(id);
        }
    }

}
