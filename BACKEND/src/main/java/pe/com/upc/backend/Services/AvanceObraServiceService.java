package pe.com.upc.backend.Services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.AvanceObraDTO;
import pe.com.upc.backend.Entities.AvanceObra;
import pe.com.upc.backend.Interfaces.IAvanceObraService;
import pe.com.upc.backend.Repositories.AvanceObraRepository;

import java.util.List;

@Service
public class AvanceObraServiceService implements IAvanceObraService {
    @Autowired private AvanceObraRepository avanceObraRepository;
    @Autowired private ModelMapper modelMapper;

    @Override
    public List<AvanceObraDTO> listar() {
        return avanceObraRepository.findAll().stream()
                .map(e -> modelMapper.map(e, AvanceObraDTO.class))
                .toList();
    }

    @Override
    public AvanceObraDTO registrar(AvanceObraDTO dto) {
        if (dto.getIdAvanceObra() == null) {
            AvanceObra e = modelMapper.map(dto, AvanceObra.class);
            e.setIdAvanceObra(null); // asegurar create
            return modelMapper.map(avanceObraRepository.save(e), AvanceObraDTO.class);
        }
        return null;
    }

    @Override
    public AvanceObraDTO findById(Long id) {
        return avanceObraRepository.findById(id)
                .map(e -> modelMapper.map(e, AvanceObraDTO.class))
                .orElseThrow(() -> new RuntimeException("AvanceObra con ID " + id + " no encontrado"));
    }

    @Override
    @Transactional
    public AvanceObraDTO actualizar(AvanceObraDTO dto) {
        return avanceObraRepository.findById(dto.getIdAvanceObra())
                .map(existing -> {
                    AvanceObra e = modelMapper.map(dto, AvanceObra.class);
                    // conservar el ID de la entidad existente
                    e.setIdAvanceObra(existing.getIdAvanceObra());
                    return modelMapper.map(avanceObraRepository.save(e), AvanceObraDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("AvanceObra con ID " + dto.getIdAvanceObra() + " no encontrado"));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (avanceObraRepository.existsById(id)) {
            avanceObraRepository.deleteById(id);
        }
    }
}

