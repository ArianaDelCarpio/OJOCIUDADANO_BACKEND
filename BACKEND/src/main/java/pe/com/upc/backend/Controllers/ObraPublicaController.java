package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.ObraPublicaDTO;
import pe.com.upc.backend.Interfaces.IObraPublicaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping("/apiOjoCiudadano")
public class ObraPublicaController {
    @Autowired
    private IObraPublicaService obraService;

    @GetMapping("/obras")
    public ResponseEntity<List<ObraPublicaDTO>> listar() {
        return ResponseEntity.ok(obraService.listar());
    }

    @PostMapping("/obra")
    public ResponseEntity<ObraPublicaDTO> registrar(@RequestBody ObraPublicaDTO obraDTO) {
        return ResponseEntity.ok(obraService.registrar(obraDTO));
    }

    @GetMapping("/obra/{id}")
    public ResponseEntity<ObraPublicaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(obraService.findById(id));
    }

    @PutMapping("/obra")
    public ResponseEntity<ObraPublicaDTO> actualizar(@RequestBody ObraPublicaDTO obraDTO) {
        return ResponseEntity.ok(obraService.actualizar(obraDTO));
    }

    @DeleteMapping("/obra/{id}")
    public void eliminar(@PathVariable Long id) {
        obraService.eliminar(id);
    }
}
