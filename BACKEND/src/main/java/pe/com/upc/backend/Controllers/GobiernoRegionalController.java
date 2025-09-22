package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.GobiernoRegionalDTO;
import pe.com.upc.backend.Interfaces.IGobiernoRegionalService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping("/apiOjoCiudadano")
public class GobiernoRegionalController {
    @Autowired
    private IGobiernoRegionalService gobiernoRegionService;

    @PostMapping("/gobierno")
    public ResponseEntity<GobiernoRegionalDTO> registrar(@RequestBody GobiernoRegionalDTO gobiernoRegionalDTO) {
        return ResponseEntity.ok(gobiernoRegionService.registrar(gobiernoRegionalDTO));
    }

    @GetMapping("/gobiernos")
    public ResponseEntity<List<GobiernoRegionalDTO>> listar() {
        return ResponseEntity.ok(gobiernoRegionService.listar());
    }

    @GetMapping("/gobierno/{id}")
    public ResponseEntity<GobiernoRegionalDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(gobiernoRegionService.findById(id));
    }

    @PutMapping("/gobierno")
    public ResponseEntity<GobiernoRegionalDTO> actualizar(@RequestBody GobiernoRegionalDTO gobiernoRegionalDTO) {
        return ResponseEntity.ok(gobiernoRegionService.actualizar(gobiernoRegionalDTO));
    }

    @DeleteMapping("/gobierno/{id}")
    public void eliminar(@PathVariable Long id) {
        gobiernoRegionService.eliminar(id);
    }
}
