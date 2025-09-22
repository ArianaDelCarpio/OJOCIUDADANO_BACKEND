package pe.com.upc.backend.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.GobiernoRegionalDTO;
import pe.com.upc.backend.Entities.GobiernoRegional;
import pe.com.upc.backend.Interfaces.IGobiernoRegionalService;
import pe.com.upc.backend.Repositories.GobiernoRegionalRepository;

import java.util.List;

@Service
public class GobiernoRegionalService implements IGobiernoRegionalService {
    @Autowired
    private GobiernoRegionalRepository gobiernoRegionalrepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<GobiernoRegionalDTO> listar() {
        return gobiernoRegionalrepository.findAll().stream()
                .map(GobiernoRegional -> modelMapper.map(GobiernoRegional, GobiernoRegionalDTO.class))
                .toList();
    }

    @Override
    public GobiernoRegionalDTO registrar(GobiernoRegionalDTO dto) {
        if (dto.getId() == null) {
            GobiernoRegional gobiernoRegional = modelMapper.map(dto, GobiernoRegional.class);
            return modelMapper.map(gobiernoRegionalrepository.save(gobiernoRegional), GobiernoRegionalDTO.class);
        }
        return null;
    }

    @Override
    public GobiernoRegionalDTO findById(Long id) {
        return gobiernoRegionalrepository.findById(id)
                .map(gobiernoRegional -> modelMapper.map(gobiernoRegional,GobiernoRegionalDTO.class))
                .orElseThrow(() -> new RuntimeException("Gobierno Regional con ID " + id + " no encontrado"));
    }

    @Override
    public GobiernoRegionalDTO actualizar(GobiernoRegionalDTO gobiernoRegionalDTO) {
        return gobiernoRegionalrepository.findById(gobiernoRegionalDTO.getId())
                .map(existing -> {
                    GobiernoRegional updatedGobiernoRegional = modelMapper.map(gobiernoRegionalDTO, GobiernoRegional.class);
                    return modelMapper.map(gobiernoRegionalrepository.save(updatedGobiernoRegional), GobiernoRegionalDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Gobierno Regional con ID " + gobiernoRegionalDTO.getId() + " no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        if (gobiernoRegionalrepository.existsById(id)){
            gobiernoRegionalrepository.deleteById(id);
        }
    }
}
