package pe.com.upc.backend.Services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.ObraPublicaDTO;
import pe.com.upc.backend.Entities.ObraPublica;
import pe.com.upc.backend.Interfaces.IObraPublicaService;
import pe.com.upc.backend.Repositories.ObraPublicaRepository;

import java.util.List;

@Service
public class ObraPublicaService implements IObraPublicaService {
    @Autowired
    private ObraPublicaRepository obraPublicaRepository;

    /*@Autowired
    private GobiernoRegionalRepository gobiernoRegionalRepository;*/

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ObraPublicaDTO> listar() {
        return obraPublicaRepository.findAll().stream()
                .map(obraPublica -> modelMapper.map(obraPublica,ObraPublicaDTO.class )/*{ObraPublicaDTO dto = modelMapper.map(ObraPublica, ObraPublicaDTO.class);
                    return dto;
                }*/)
                .toList();
    }

    @Override
    public ObraPublicaDTO registrar(ObraPublicaDTO obraDTO) {
        if(obraDTO.getIdObra()==null){
            ObraPublica obraPublica = modelMapper.map(obraDTO,ObraPublica.class);
            return modelMapper.map(obraPublicaRepository.save(obraPublica),ObraPublicaDTO.class);
        }
        return null;
        /*ObraPublica obra = modelMapper.map(obraDTO, ObraPublica.class);

        // Vinculamos el GobiernoRegional
        GobiernoRegional gov = gobiernoRegionalRepository.findById(obraDTO.getIdGobiernoRegional())
                .orElse(null);
        obra.setGobiernoRegional(gov);

        return modelMapper.map(obraPublicaRepository.save(obra), ObraPublicaDTO.class);*/
    }

    @Override
    public ObraPublicaDTO findById(Long id) {
        return obraPublicaRepository.findById(id)
                .map(obraPublica -> modelMapper.map(obraPublica, ObraPublicaDTO.class))
                .orElseThrow(() -> new RuntimeException("Obra Publica con ID " + id + " no encontrado"));
        /*return obraPublicaRepository.findById(id).orElse(null);*/
    }

    @Transactional
    @Override
    public ObraPublicaDTO actualizar(Long id, ObraPublicaDTO obraDTO) {
        // 1. Buscar la obra existente
        ObraPublica obraExistente = obraPublicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obra Pública con ID " + id + " no encontrada"));

        // 2. Copiar los datos del DTO a la Entidad
        modelMapper.map(obraDTO, obraExistente);

        // Aseguramos que el ID no se pierda
        obraExistente.setIdObra(id);

        // 3. Guardar
        ObraPublica obraGuardada = obraPublicaRepository.save(obraExistente);

        // 4. Retornar DTO
        return modelMapper.map(obraGuardada, ObraPublicaDTO.class);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if(obraPublicaRepository.existsById(id)){
            obraPublicaRepository.deleteById(id);
        }
    }
}

