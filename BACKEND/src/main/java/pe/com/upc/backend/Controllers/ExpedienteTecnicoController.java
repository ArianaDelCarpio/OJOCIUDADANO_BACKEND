package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.ExpedienteTecnicoDTO;
import pe.com.upc.backend.Interfaces.IExpedienteTecnicoService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping("/apiOjoCiudadano")
public class ExpedienteTecnicoController {
    @Autowired
    private IExpedienteTecnicoService expedienteTecnicoService;

    @PostMapping("/expediente")
    public ResponseEntity<ExpedienteTecnicoDTO> registrar(@RequestBody ExpedienteTecnicoDTO expedienteTecnicoDTO) {
        return ResponseEntity.ok(expedienteTecnicoService.registrar(expedienteTecnicoDTO));
    }

    @GetMapping("/expedientes")
    public ResponseEntity<List<ExpedienteTecnicoDTO>> listar() {
        return ResponseEntity.ok(expedienteTecnicoService.listar());
    }

    @GetMapping("/expediente/{id}")
    public ResponseEntity<ExpedienteTecnicoDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(expedienteTecnicoService.findById(id));
    }

    @PutMapping("/expediente")
    public ResponseEntity<ExpedienteTecnicoDTO> actualizar(@RequestBody ExpedienteTecnicoDTO expedienteTecnicoDTO) {
        return ResponseEntity.ok(expedienteTecnicoService.actualizar(expedienteTecnicoDTO));
    }

    @DeleteMapping("/expediente/{id}")
    public void eliminar(@PathVariable Long id) {
        expedienteTecnicoService.eliminar(id);
    }
}
