package pe.com.upc.backend.security.services;


import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import pe.com.upc.backend.security.dtos.RoleDTO;
import pe.com.upc.backend.security.dtos.UserDTO;
import pe.com.upc.backend.security.entities.Role;
import pe.com.upc.backend.security.entities.User;
import pe.com.upc.backend.security.repositories.RoleRepository;
import pe.com.upc.backend.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public User save(User user) {
        // 1. Preparamos una lista para los roles REALES de la base de datos
        Set<Role> rolesReales = new HashSet<>();

        // 2. ¿El frontend envió roles? (Caso ADMIN creando usuario)
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            for (Role rolQueVinoDelFront : user.getRoles()) {
                // Buscamos el rol en la BD por su ID para asegurarnos de que es el objeto real
                // Si no lo encuentra por ID, intenta buscarlo por nombre
                Role rolEncontrado = roleRepository.findById(rolQueVinoDelFront.getId())
                        .orElse(roleRepository.findByName(rolQueVinoDelFront.getName()));

                if (rolEncontrado != null) {
                    rolesReales.add(rolEncontrado);
                }
            }
        }

        // 3. Si después de todo la lista está vacía (Caso Registro Público), ponemos CIUDADANO
        if (rolesReales.isEmpty()) {
            Role rolDefault = roleRepository.findByName("ROLE_CIUDADANO");
            if (rolDefault != null) {
                rolesReales.add(rolDefault);
            }
        }

        // 4. Asignamos los roles "vivos" (conectados a la BD) al usuario
        user.setRoles(rolesReales);

        // 5. Guardamos
        return userRepository.save(user);
    }

    @Transactional
    public Integer insertUserRol(Long user_id, Long rol_id) {
        Integer result = 0;
        userRepository.insertUserRol(user_id, rol_id);
        return 1;
    }

    public List<UserDTO> listar() {
        return userRepository.findAll().stream()
                .map(usuario -> modelMapper.map(usuario, UserDTO.class))
                .toList();
    }

    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map((element)->modelMapper.map(element,UserDTO.class))
                .orElseThrow(()-> new RuntimeException("Usuario con ID \" + id + \" no encontrado"));
    }

    public UserDTO actualizar(Long id, UserDTO userDTO) {
        User usuarioAntiguo = userRepository.findById(id).orElse(null);

        if (usuarioAntiguo != null) {
            usuarioAntiguo.setNombre(userDTO.getNombre());
            usuarioAntiguo.setApellido(userDTO.getApellido());
            usuarioAntiguo.setUsername(userDTO.getUsername());

            if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
                // Convertimos los DTOs de roles a Entidades Roles
                Set<Role> nuevosRoles = new HashSet<>();
                for (RoleDTO rolDto : userDTO.getRoles()) {
                    // Buscamos el rol real en la BD por su ID o Nombre
                    Role rolReal = roleRepository.findById(rolDto.getId()).orElse(null);
                    if (rolReal != null) {
                        nuevosRoles.add(rolReal);
                    }
                }
                usuarioAntiguo.setRoles(nuevosRoles);
            }
            // -------------------------------------
            User usuarioGuardado = userRepository.save(usuarioAntiguo);
            // Retornar mapeado...
            return modelMapper.map(usuarioGuardado, UserDTO.class);
        }
        return null;
    }

    public void eliminar(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario con id " + id + " no existe");
        }
        userRepository.deleteById(id);
    }
}
