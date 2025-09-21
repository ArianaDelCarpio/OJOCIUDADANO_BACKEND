package pe.com.upc.backend.Services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.EvidenciaDTO;
import pe.com.upc.backend.Entities.Evidencia;
import pe.com.upc.backend.Interfaces.IEvidenciaService;
import pe.com.upc.backend.Repositories.EvidenciaRepository;

import java.util.List;

@Service
public class EvidenciaService implements IEvidenciaService {

    @Autowired
    private EvidenciaRepository evidenciaRepository;

    /*@Autowired
    private DenunciaRepository denunciaRepository;*/
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EvidenciaDTO> listar() {
        return evidenciaRepository.findAll().stream()
                .map(evidencia -> modelMapper.map(evidencia, EvidenciaDTO.class))
                .toList();
    }

    @Override
    public EvidenciaDTO registrar(EvidenciaDTO evidenciaDTO) {
        if(evidenciaDTO.getId()==null){
            Evidencia evidencia = modelMapper.map(evidenciaDTO,Evidencia.class);
            return modelMapper.map(evidenciaRepository.save(evidencia),EvidenciaDTO.class);
        }
        return null;
    }

    @Override
    public EvidenciaDTO findById(Long id) {
        return evidenciaRepository.findById(id)
                .map(evidencia -> modelMapper.map(evidencia, EvidenciaDTO.class))
                .orElseThrow(() -> new RuntimeException("Evidencia con ID " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public EvidenciaDTO actualizar(EvidenciaDTO evidenciaDTO) {
        return evidenciaRepository.findById(evidenciaDTO.getId())
                .map(existing -> {
                    Evidencia updatedEvidencia = modelMapper.map(evidenciaDTO, Evidencia.class);
                    return modelMapper.map(evidenciaRepository.save(updatedEvidencia), EvidenciaDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Evidencia con ID " + evidenciaDTO.getId() + " no encontrado"));
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if (evidenciaRepository.existsById(id)) {
            evidenciaRepository.deleteById(id);
        }
    }
}
