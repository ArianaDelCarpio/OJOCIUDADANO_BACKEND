package pe.com.upc.backend.Controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.RolDTO;
import pe.com.upc.backend.Interfaces.IRolService;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano")
public class RolController {
    @Autowired
    private IRolService rolService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/roles")
    public ResponseEntity<List<RolDTO>> Listar() {
        return ResponseEntity.ok(rolService.listar());
    }
    @PostMapping("/rol")
    public ResponseEntity<RolDTO> registrar(@RequestBody RolDTO rolDTO){
        return ResponseEntity.ok(rolService.registrar(rolDTO));
        /*RolDTO rol = modelMapper.map(rolDTO, RolDTO.class);
        return ResponseEntity.ok(rol);*/
    }
    @GetMapping("/rol/{id}")
    public ResponseEntity<RolDTO> buscarId(@PathVariable Long id){
        return ResponseEntity.ok(rolService.findById(id));
    }
    @PutMapping("/rol")
    public ResponseEntity<RolDTO> actualizar(@RequestBody RolDTO rolDTO){
       return ResponseEntity.ok(rolService.actualizar(rolDTO));
    }
    @DeleteMapping( "/rol/{id}")
    public void eliminar(@PathVariable Long id){
        rolService.eliminar(id);
    }
}
