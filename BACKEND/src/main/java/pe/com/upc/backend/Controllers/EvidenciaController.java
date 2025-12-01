package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<EvidenciaDTO> registrar(@RequestBody EvidenciaDTO evidenciaDTO) {
        return ResponseEntity.ok(evidenciaService.registrar(evidenciaDTO));
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<EvidenciaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(evidenciaService.findById(id));
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<EvidenciaDTO> actualizar(@PathVariable Long id, @RequestBody EvidenciaDTO evidenciaDTO) {

        // 1. Validación de seguridad: ID de URL vs ID del cuerpo
        if (evidenciaDTO.getId() != null && !id.equals(evidenciaDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 2. Llamada al servicio
        EvidenciaDTO actualizado = evidenciaService.actualizar(id, evidenciaDTO);

        // 3. Manejo de error si no existe
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        evidenciaService.eliminar(id);
    }
}
