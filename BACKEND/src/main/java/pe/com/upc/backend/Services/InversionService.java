package pe.com.upc.backend.Services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.InversionDTO;
import pe.com.upc.backend.Entities.Inversion;
import pe.com.upc.backend.Interfaces.IInversionService;
import pe.com.upc.backend.Repositories.InversionRepository;

import java.util.List;


@Service
public class InversionService implements IInversionService {
    @Autowired
    private InversionRepository inversionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<InversionDTO> listar(){
        return inversionRepository.findAll().stream()
                .map(inversion -> modelMapper.map(inversion, InversionDTO.class))
                .toList();
    }
    @Override
    public InversionDTO registrar(InversionDTO inversionDTO){
        if(inversionDTO.getIdInversion()==null){
            Inversion inversion = modelMapper.map(inversionDTO, Inversion.class);
            return modelMapper.map(inversionRepository.save(inversion),InversionDTO.class);
        }
        return null;
    }
    @Override
    public InversionDTO findById(Long id){
        return inversionRepository.findById(id)
                .map(inversion -> modelMapper.map(inversion, InversionDTO.class))
                .orElseThrow(() -> new RuntimeException("Inversion con ID " + id + " no encontrado"));

    }
    @Override
    @Transactional
    public InversionDTO actualizar(InversionDTO inversionDTO){
        return inversionRepository.findById(inversionDTO.getIdInversion())
                .map(existing -> {
                    Inversion updatedInversion = modelMapper.map(inversionDTO, Inversion.class);
                    return modelMapper.map(inversionRepository.save(updatedInversion), InversionDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Rol con ID " + inversionDTO.getIdInversion() + " no encontrado"));

    }

    @Override
    @Transactional
    public void eliminar(Long id){
        if(inversionRepository.existsById(id)){
            inversionRepository.deleteById(id);
        }
    }
}
