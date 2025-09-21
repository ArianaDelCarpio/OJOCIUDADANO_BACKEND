package pe.com.upc.backend.Services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upc.backend.DTOs.RolDTO;
import pe.com.upc.backend.Entities.Rol;
import pe.com.upc.backend.Interfaces.IRolService;
import pe.com.upc.backend.Repositories.RolRepository;

import java.util.List;

@Service
public class RolService implements IRolService {
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RolDTO> listar() {
        return rolRepository.findAll().stream()
                .map(rol->modelMapper.map(rol,RolDTO.class))
                .toList();
    }

    @Override
    /*public RolDTO registrar(RolDTO rolDTO) {
        Rol rol = modelMapper.map(rolDTO, Rol.class);
        rol.setIdRol(null); // asegura que JPA genere el ID
        return modelMapper.map(rolRepository.save(rol), RolDTO.class);
    }*/
    public RolDTO registrar(RolDTO rolDTO) {
        if(rolDTO.getIdRol()==null){
            Rol rol = modelMapper.map(rolDTO,Rol.class);
            return modelMapper.map(rolRepository.save(rol),RolDTO.class);
        }
        return null;
    }

    @Override
    public RolDTO findById(Long id) {
        return rolRepository.findById(id)
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .orElseThrow(() -> new RuntimeException("Rol con ID " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public RolDTO actualizar(RolDTO rolDTO) {
        return rolRepository.findById(rolDTO.getIdRol())
                .map(existing -> {
                    Rol updatedRol = modelMapper.map(rolDTO, Rol.class);
                    return modelMapper.map(rolRepository.save(updatedRol), RolDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Rol con ID " + rolDTO.getIdRol() + " no encontrado"));

        /*if(rol.getIdRol()!=null){
            if(rolRepository.existsById(rol.getIdRol())){
                return rolRepository.save(rol);
            }
        }
        return null;*/
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if(rolRepository.existsById(id)){
            rolRepository.deleteById(id);
        }
    }
}
