package pe.com.upc.backend.security.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.upc.backend.security.dtos.RoleDTO;
import pe.com.upc.backend.security.entities.Role;
import pe.com.upc.backend.security.interfaces.IRoleService;
import pe.com.upc.backend.security.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RoleDTO> listar() {
        return roleRepository.findAll().stream()
                .map(role->modelMapper.map(role, RoleDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public void grabar(Role role) {
            roleRepository.save(role);
    }

    @Override
    public RoleDTO findById(Long id) {
        return roleRepository.findById(id)
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .orElseThrow(() -> new RuntimeException("Role con ID " + id + " no encontrado"));
    }

    @Transactional
    @Override
    public RoleDTO actualizar(RoleDTO roleDTO) {
        return roleRepository.findById(roleDTO.getId())
                .map(existing -> {
                    Role updatedRole = modelMapper.map(roleDTO, Role.class);
                    return modelMapper.map(roleRepository.save(updatedRole), RoleDTO.class);
                })
                .orElseThrow(() -> new RuntimeException("Role con ID " + roleDTO.getId() + " no encontrado"));
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
        }
    }
}
