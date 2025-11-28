package pe.com.upc.backend.security.services;


import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import pe.com.upc.backend.security.dtos.UserDTO;
import pe.com.upc.backend.security.entities.Role;
import pe.com.upc.backend.security.entities.User;
import pe.com.upc.backend.security.repositories.RoleRepository;
import pe.com.upc.backend.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Role rolDefault = roleRepository.findByName("ROLE_CIUDADANO");
        if (rolDefault == null) {
            throw new RuntimeException("Error: El rol ROLE_CIUDADANO no existe en la BD");
        }
        user.setRoles(Set.of(rolDefault));
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
        // 1. Buscamos al usuario ANTIGUO en la base de datos (Entidad)
        User usuarioAntiguo = userRepository.findById(id).orElse(null);

        if (usuarioAntiguo != null) {
            // 2. Pasamos los datos del DTO a la Entidad
            // NO tocamos el password, ni el id, ni la fecha de registro si no queremos
            usuarioAntiguo.setUsername(userDTO.getUsername());
            usuarioAntiguo.setNombre(userDTO.getNombre());
            usuarioAntiguo.setApellido(userDTO.getApellido());

            // NOTA: Como userDto NO tiene password, aquí es imposible
            // que sobrescribas la contraseña con un null o vacío.
            // La contraseña de usuarioAntiguo sigue siendo la que estaba en BD.

            // 3. Guardamos los cambios en la BD
            User usuarioGuardado = userRepository.save(usuarioAntiguo);

            // 4. Convertimos la entidad guardada de vuelta a DTO para responder
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
