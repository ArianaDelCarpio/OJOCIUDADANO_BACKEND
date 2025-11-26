package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.EvidenciaDTO;
import pe.com.upc.backend.Interfaces.IEvidenciaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping("/apiOjoCiudadano/evidencia")
public class EvidenciaController {

    @Autowired
    private IEvidenciaService evidenciaService;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<EvidenciaDTO>> listar() {
        return ResponseEntity.ok(evidenciaService.listar());
    }

    @PostMapping("/registar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<EvidenciaDTO> registrar(@RequestBody EvidenciaDTO evidenciaDTO) {
        return ResponseEntity.ok(evidenciaService.registrar(evidenciaDTO));
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<EvidenciaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(evidenciaService.findById(id));
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<EvidenciaDTO> actualizar(@RequestBody EvidenciaDTO evidenciaDTO) {
        return ResponseEntity.ok(evidenciaService.actualizar(evidenciaDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        evidenciaService.eliminar(id);
    }
}
