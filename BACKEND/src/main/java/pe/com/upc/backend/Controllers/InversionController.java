package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.AvanceObraDTO;
import pe.com.upc.backend.DTOs.InversionDTO;
import pe.com.upc.backend.Interfaces.IAvanceObraService;
import pe.com.upc.backend.Interfaces.IInversionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano")
public class InversionController {
    @Autowired
    private IInversionService inversionService;

    @GetMapping("/inversion")
    public ResponseEntity<List<InversionDTO>> listar() {
        return ResponseEntity.ok(inversionService.listar());
    }
    @PostMapping("/inversion")
    public ResponseEntity<InversionDTO> registrar(@RequestBody InversionDTO inversionDTO) {
        return ResponseEntity.ok(inversionService.registrar(inversionDTO));
    }

    @GetMapping("/inversion/{id}")
    public ResponseEntity<InversionDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inversionService.findById(id));
    }

    @PutMapping("/inversion")
    public ResponseEntity<InversionDTO> actualizar(@RequestBody InversionDTO inversionDTO) {
        return ResponseEntity.ok(inversionService.actualizar(inversionDTO));
    }

    @DeleteMapping("/inversion/{id}")
    public void eliminar(@PathVariable Long id) {
        inversionService.eliminar(id);
    }
}
