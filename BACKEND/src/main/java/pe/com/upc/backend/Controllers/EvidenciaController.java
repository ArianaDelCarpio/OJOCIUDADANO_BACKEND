package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.EvidenciaDTO;
import pe.com.upc.backend.Interfaces.IEvidenciaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping("/apiOjoCiudadano")
public class EvidenciaController {

    @Autowired
    private IEvidenciaService evidenciaService;

    @GetMapping("/evidencias")
    public ResponseEntity<List<EvidenciaDTO>> listar() {
        return ResponseEntity.ok(evidenciaService.listar());
    }

    @PostMapping("/evidencia")
    public ResponseEntity<EvidenciaDTO> registrar(@RequestBody EvidenciaDTO evidenciaDTO) {
        return ResponseEntity.ok(evidenciaService.registrar(evidenciaDTO));
    }

    @GetMapping("/evidencia/{id}")
    public ResponseEntity<EvidenciaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(evidenciaService.findById(id));
    }

    @PutMapping("/evidencia")
    public ResponseEntity<EvidenciaDTO> actualizar(@RequestBody EvidenciaDTO evidenciaDTO) {
        return ResponseEntity.ok(evidenciaService.actualizar(evidenciaDTO));
    }

    @DeleteMapping("/evidencia/{id}")
    public void eliminar(@PathVariable Long id) {
        evidenciaService.eliminar(id);
    }
}
