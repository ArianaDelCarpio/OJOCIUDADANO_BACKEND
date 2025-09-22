package pe.com.upc.backend.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.SeguimientoObraDTO;
import pe.com.upc.backend.Interfaces.ISeguimientoObraService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano")

public class SeguimientoObraController {
    @Autowired
    private ISeguimientoObraService seguimientoObraService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/seguimiento-obra")
    public ResponseEntity<SeguimientoObraDTO> registrar(@RequestBody SeguimientoObraDTO seguimientoObraDTO) {
        return ResponseEntity.ok(seguimientoObraService.registrar(seguimientoObraDTO));
    }

    @GetMapping("/seguimientos-obra")
    public ResponseEntity<List<SeguimientoObraDTO>> listar() {
        return ResponseEntity.ok(seguimientoObraService.listar());
    }

    @GetMapping("/seguimiento-obra/{id}")
    public ResponseEntity<SeguimientoObraDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok(seguimientoObraService.findById(id));   // devuelve DTO según tu service
    }

    @PutMapping("/seguimiento-obra")
    public ResponseEntity<SeguimientoObraDTO> actualizar(@RequestBody SeguimientoObraDTO seguimientoObraDTO) {
        return ResponseEntity.ok(seguimientoObraService.actualizar(seguimientoObraDTO)); // usa entidad según tu service
    }

    @DeleteMapping("/seguimiento-obra/{id}")
    public void eliminar(@PathVariable Long id) {
        seguimientoObraService.eliminar(id);
    }

}
