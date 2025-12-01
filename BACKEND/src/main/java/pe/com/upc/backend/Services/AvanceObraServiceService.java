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

    @Transactional
    @Override
    public AvanceObraDTO actualizar(Long id, AvanceObraDTO avanceObraDTO) {
        // 1. Buscar
        AvanceObra avanceExistente = avanceObraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avance de Obra con ID " + id + " no encontrado"));

        // 2. Mapear datos nuevos sobre la entidad existente
        modelMapper.map(avanceObraDTO, avanceExistente);

        // Aseguramos el ID
        avanceExistente.setIdAvanceObra(id);

        // 3. Guardar
        AvanceObra avanceGuardado = avanceObraRepository.save(avanceExistente);

        // 4. Retornar
        return modelMapper.map(avanceGuardado, AvanceObraDTO.class);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (avanceObraRepository.existsById(id)) {
            avanceObraRepository.deleteById(id);
        }
    }
}

