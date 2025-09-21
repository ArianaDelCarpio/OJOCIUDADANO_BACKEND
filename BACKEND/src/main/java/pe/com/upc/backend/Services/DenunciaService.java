package pe.com.upc.backend.Services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.DenunciaDTO;
import pe.com.upc.backend.Entities.Denuncia;
import pe.com.upc.backend.Interfaces.IDenunciaService;
import pe.com.upc.backend.Repositories.DenunciaRepository;

import java.util.List;

@Service
public class DenunciaService implements IDenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DenunciaDTO> listar() {
        return denunciaRepository.findAll().stream()
                .map(d -> modelMapper.map(d, DenunciaDTO.class))
                .toList();
    }


    @Override
    public DenunciaDTO registrar( DenunciaDTO dto) {
        if (dto.getIdDenuncia() == null) {
            Denuncia e = modelMapper.map(dto, Denuncia.class);
            e.setIdDenuncia(null);
            return modelMapper.map(denunciaRepository.save(e), DenunciaDTO.class);
        }
        return null;
    }
    @Override
    public DenunciaDTO findById( Long id){
        return denunciaRepository.findById(id)
                .map(denuncia -> modelMapper.map(denuncia, DenunciaDTO.class))
                .orElseThrow(() -> new RuntimeException("Rol con ID " + id + " no encontrado"));
    }
    @Transactional
    @Override
    public DenunciaDTO actualizar(DenunciaDTO dto){
        return denunciaRepository.findById(dto.getIdDenuncia())
                .map(ex -> {
                    Denuncia e = modelMapper.map(dto, Denuncia.class);
                    return modelMapper.map(denunciaRepository.save(e), DenunciaDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Denuncia con ID " + dto.getIdDenuncia() + " no encontrada"));

    }
    @Transactional
    @Override
    public void eliminar(Long id){
        if (denunciaRepository.existsById(id)) {
            denunciaRepository.deleteById(id);
        }
    }
}
