package pe.com.upc.backend.Services;

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

    @Override
    public ObraPublicaDTO actualizar(ObraPublicaDTO obraDTO) {
        return obraPublicaRepository.findById(obraDTO.getIdObra())
                .map(existing -> {
                    ObraPublica updatedObraPublica = modelMapper.map(obraDTO, ObraPublica.class);
                    return modelMapper.map(obraPublicaRepository.save(updatedObraPublica), ObraPublicaDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Obra Publica con ID " + obraDTO.getIdObra() + " no encontrado"));
        /*if (obra.getIdObra() != null && obraPublicaRepository.existsById(obra.getIdObra())) {
            return obraPublicaRepository.save(obra);
        }
        return null;*/
    }

    @Override
    public void eliminar(Long id) {
        if(obraPublicaRepository.existsById(id)){
            obraPublicaRepository.deleteById(id);
        }
    }
}

