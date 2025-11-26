package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.AvanceObraDTO;
import pe.com.upc.backend.DTOs.DenunciaDTO;
import pe.com.upc.backend.Interfaces.IAvanceObraService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano/avance-obra")
public class AvanceObraController {
    @Autowired
    private IAvanceObraService avanceObraService;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<AvanceObraDTO>> listar() {
        return ResponseEntity.ok(avanceObraService.listar());
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<AvanceObraDTO> registrar(@RequestBody AvanceObraDTO avanceObraDTO) {
        return ResponseEntity.ok(avanceObraService.registrar(avanceObraDTO));
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<AvanceObraDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(avanceObraService.findById(id));
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<AvanceObraDTO> actualizar(@RequestBody AvanceObraDTO avanceObraDTO) {
        return ResponseEntity.ok(avanceObraService.actualizar(avanceObraDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        avanceObraService.eliminar(id);
    }
}
