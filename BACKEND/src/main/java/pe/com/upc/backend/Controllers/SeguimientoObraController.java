package pe.com.upc.backend.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.SeguimientoObraDTO;
import pe.com.upc.backend.Interfaces.ISeguimientoObraService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano/seguimiento-obra")

public class SeguimientoObraController {
    @Autowired
    private ISeguimientoObraService seguimientoObraService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<SeguimientoObraDTO> registrar(@RequestBody SeguimientoObraDTO seguimientoObraDTO) {
        return ResponseEntity.ok(seguimientoObraService.registrar(seguimientoObraDTO));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<SeguimientoObraDTO>> listar() {
        return ResponseEntity.ok(seguimientoObraService.listar());
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<SeguimientoObraDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok(seguimientoObraService.findById(id));   // devuelve DTO según tu service
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<SeguimientoObraDTO> actualizar(@RequestBody SeguimientoObraDTO seguimientoObraDTO) {
        return ResponseEntity.ok(seguimientoObraService.actualizar(seguimientoObraDTO)); // usa entidad según tu service
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        seguimientoObraService.eliminar(id);
    }

}
