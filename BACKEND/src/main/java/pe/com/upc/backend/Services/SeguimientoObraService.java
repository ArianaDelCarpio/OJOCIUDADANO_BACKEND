package pe.com.upc.backend.Services;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.SeguimientoObraDTO;
import pe.com.upc.backend.Entities.SeguimientoObra;
import pe.com.upc.backend.Interfaces.ISeguimientoObraService;
import pe.com.upc.backend.Repositories.SeguimientoObraRepository;

import java.util.List;

@Service
public class SeguimientoObraService implements ISeguimientoObraService {
    @Autowired
    private SeguimientoObraRepository seguimientoObraRepository;

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
        if (seguimientoObraDTO.getId() == null) {
            SeguimientoObra seguimientoObra = modelMapper.map(seguimientoObraDTO, SeguimientoObra.class);
            return modelMapper.map(seguimientoObraRepository.save(seguimientoObra), SeguimientoObraDTO.class);
        }
        return null;
    }

    @Override
    public SeguimientoObraDTO findById(Long id) {
        return seguimientoObraRepository.findById(id)
                .map(seguimientoObra -> modelMapper.map(seguimientoObra, SeguimientoObraDTO.class))
                .orElseThrow(() -> new RuntimeException("Seguimiento de obra con ID " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public SeguimientoObraDTO actualizar(SeguimientoObraDTO seguimientoObraDTO) {
        return seguimientoObraRepository.findById(seguimientoObraDTO.getId())
                .map(existing -> {
                    SeguimientoObra updatedSeguimientoObra = modelMapper.map(seguimientoObraDTO, SeguimientoObra.class);
                    return modelMapper.map(seguimientoObraRepository.save(updatedSeguimientoObra), SeguimientoObraDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Seguimiento de obra con ID " + seguimientoObraDTO.getId() + " no encontrado"));
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if(seguimientoObraRepository.existsById(id)){
            seguimientoObraRepository.deleteById(id);
        }
    }

}
