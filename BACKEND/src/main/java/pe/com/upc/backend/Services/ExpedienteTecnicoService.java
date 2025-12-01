package pe.com.upc.backend.Services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.ExpedienteTecnicoDTO;
import pe.com.upc.backend.Entities.ExpedienteTecnico;
import pe.com.upc.backend.Interfaces.IExpedienteTecnicoService;
import pe.com.upc.backend.Repositories.ExpedienteTecnicoRepository;

import java.util.List;

@Service
public class ExpedienteTecnicoService implements IExpedienteTecnicoService {
    @Autowired
    private ExpedienteTecnicoRepository expedienteTecnicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ExpedienteTecnicoDTO> listar() {
        return expedienteTecnicoRepository.findAll().stream()
                .map(ExpedienteTecnico -> modelMapper.map(ExpedienteTecnico, ExpedienteTecnicoDTO.class))
                .toList();
    }

    @Override
    public ExpedienteTecnicoDTO registrar(ExpedienteTecnicoDTO dto) {
        if (dto.getId() == null) {
            ExpedienteTecnico entity = modelMapper.map(dto, ExpedienteTecnico.class);
            return modelMapper.map(expedienteTecnicoRepository.save(entity), ExpedienteTecnicoDTO.class);
        }
        return null;
    }

    @Override
    public ExpedienteTecnicoDTO findById(Long id) {
        return expedienteTecnicoRepository.findById(id)
                .map(expedienteTecnico -> modelMapper.map(expedienteTecnico, ExpedienteTecnicoDTO.class))
                .orElseThrow(() -> new RuntimeException("Expediente Tecnico con ID " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public ExpedienteTecnicoDTO actualizar(Long id, ExpedienteTecnicoDTO expedienteTecnicoDTO) {
        ExpedienteTecnico expedienteExistente = expedienteTecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expediente Técnico con ID " + id + " no encontrado"));

        modelMapper.map(expedienteTecnicoDTO, expedienteExistente);
        expedienteExistente.setId(id);
        ExpedienteTecnico saved = expedienteTecnicoRepository.save(expedienteExistente);
        return modelMapper.map(saved, ExpedienteTecnicoDTO.class);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        expedienteTecnicoRepository.deleteById(id);
    }
}
