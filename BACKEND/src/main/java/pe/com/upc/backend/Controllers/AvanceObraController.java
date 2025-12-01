package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<AvanceObraDTO> actualizar(@PathVariable Long id, @RequestBody AvanceObraDTO avanceObraDTO) {

        // 1. Validación de seguridad
        if (avanceObraDTO.getIdAvanceObra() != null && !id.equals(avanceObraDTO.getIdAvanceObra())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 2. Llamada al servicio
        AvanceObraDTO actualizado = avanceObraService.actualizar(id, avanceObraDTO);

        // 3. Manejo de error
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        avanceObraService.eliminar(id);
    }
}
