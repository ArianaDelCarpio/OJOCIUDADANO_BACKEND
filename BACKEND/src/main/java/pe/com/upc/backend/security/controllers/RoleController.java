package pe.com.upc.backend.security.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.security.dtos.RoleDTO;
import pe.com.upc.backend.security.entities.Role;
import pe.com.upc.backend.security.interfaces.IRoleService;

import java.util.List;

//@CrossOrigin(origins = "${ip.frontend}", allowCredentials = "true", exposedHeaders = "Authorization") //para cloud
@RestController
@RequestMapping("/apiOjoCiudadano/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping("/Listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<List<RoleDTO>> Listar() {
        return ResponseEntity.ok(roleService.listar());
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasRole('ADMIN')")
    public void createRol(@RequestBody Role role) {
        roleService.grabar(role);
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<RoleDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findById(id));
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDTO> actualizar(@RequestBody RoleDTO roleDTO){
        return ResponseEntity.ok(roleService.actualizar(roleDTO));
    }

    @DeleteMapping( "/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Long id){
        roleService.eliminar(id);
    }
}
