package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.GobiernoRegionalDTO;
import pe.com.upc.backend.Interfaces.IGobiernoRegionalService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping("/apiOjoCiudadano/gobierno-regional")
public class GobiernoRegionalController {
    @Autowired
    private IGobiernoRegionalService gobiernoRegionService;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<GobiernoRegionalDTO> registrar(@RequestBody GobiernoRegionalDTO gobiernoRegionalDTO) {
        return ResponseEntity.ok(gobiernoRegionService.registrar(gobiernoRegionalDTO));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<GobiernoRegionalDTO>> listar() {
        return ResponseEntity.ok(gobiernoRegionService.listar());
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<GobiernoRegionalDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(gobiernoRegionService.findById(id));
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<GobiernoRegionalDTO> actualizar(@PathVariable Long id, @RequestBody GobiernoRegionalDTO gobiernoRegionalDTO) {
        if (gobiernoRegionalDTO.getId() != null && !id.equals(gobiernoRegionalDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GobiernoRegionalDTO actualizado = gobiernoRegionService.actualizar(id, gobiernoRegionalDTO);
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        gobiernoRegionService.eliminar(id);
    }
}
