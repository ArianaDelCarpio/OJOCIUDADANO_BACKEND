package pe.com.upc.backend.Controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.UsuarioDTO;
import pe.com.upc.backend.Entities.Usuario;
import pe.com.upc.backend.Interfaces.IUsuarioService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping("/apiOjoCiudadano")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO> Registrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("Registrando usuario {}", usuarioDTO.getNombre());
        UsuarioDTO creado = usuarioService.registrar(usuarioDTO);

        // Agregar enlaces
        creado.add(linkTo(methodOn(UsuarioController.class).BuscarId(creado.getIdUsuario())).withSelfRel());
        creado.add(linkTo(methodOn(UsuarioController.class).Listar()).withRel("listar-usuarios"));

        return ResponseEntity.ok(creado);
    }
    /*@PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO> Registrar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
    log.info("Registrando usuario {}",usuarioDTO.getNombre());
        return ResponseEntity.ok(usuarioService.registrar(usuarioDTO));
    }*/

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> Listar() {
        log.info("Lista de usuarios");
        List<UsuarioDTO> usuarios = usuarioService.listar();

        // A cada usuario le añadimos sus links
        usuarios.forEach(usuario -> {
            usuario.add(linkTo(methodOn(UsuarioController.class).BuscarId(usuario.getIdUsuario())).withSelfRel());
            usuario.add(linkTo(methodOn(UsuarioController.class).Listar()).withRel("todos"));
        });

        return ResponseEntity.ok(usuarios);
    }
    /*@GetMapping("/usuarios")
    public List<UsuarioDTO> Listar() {
            log.info("Lista de usuarios");
            return usuarioService.listar();
    }*/

    @GetMapping("/usuario/{id}")
    public ResponseEntity<EntityModel<UsuarioDTO>> BuscarId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.findById(id);

        EntityModel<UsuarioDTO> resource = EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).BuscarId(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).Listar()).withRel("all-users"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).actualizar(usuario)).withRel("update")
                //WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).eliminar(id)).withRel("delete")
        );
        return ResponseEntity.ok(resource);
    }
    /*@GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioDTO> BuscarId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }*/

    @PutMapping("/usuario")
    public ResponseEntity<EntityModel<UsuarioDTO>> actualizar(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO actualizado = usuarioService.actualizar(usuarioDTO);

        EntityModel<UsuarioDTO> resource = EntityModel.of(actualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).BuscarId(actualizado.getIdUsuario())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).Listar()).withRel("all-users")
                /*WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).eliminar(actualizado.getIdUsuario())).withRel("delete")*/
        );

        return ResponseEntity.ok(resource);
    }
    /*@PutMapping("/usuario")
    public ResponseEntity<UsuarioDTO> actualizar(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.actualizar(usuarioDTO));
    }*/

    /*@DeleteMapping("/usuario/{id}")
    public ResponseEntity<EntityModel<String>> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);

        EntityModel<String> resource = EntityModel.of("Usuario eliminado correctamente",
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).Listar()).withRel("all-users"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).Registrar(new UsuarioDTO())).withRel("create-user")
        );

        return ResponseEntity.ok(resource);
    }*/
    @DeleteMapping( "/usuario/{id}")
    public void eliminar(@PathVariable Long id){
        usuarioService.eliminar(id);
    }
}
