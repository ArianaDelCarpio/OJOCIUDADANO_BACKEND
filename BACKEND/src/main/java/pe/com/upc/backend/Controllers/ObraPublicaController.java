package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.ObraPublicaDTO;
import pe.com.upc.backend.Interfaces.IObraPublicaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping("/apiOjoCiudadano/obra-publica")
public class ObraPublicaController {
    @Autowired
    private IObraPublicaService obraService;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<ObraPublicaDTO>> listar() {
        return ResponseEntity.ok(obraService.listar());
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<ObraPublicaDTO> registrar(@RequestBody ObraPublicaDTO obraDTO) {
        return ResponseEntity.ok(obraService.registrar(obraDTO));
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<ObraPublicaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(obraService.findById(id));
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<ObraPublicaDTO> actualizar(@PathVariable Long id, @RequestBody ObraPublicaDTO obraDTO) {
        if (obraDTO.getIdObra() != null && !id.equals(obraDTO.getIdObra())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ObraPublicaDTO actualizado = obraService.actualizar(id, obraDTO);
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        obraService.eliminar(id);
    }
}
