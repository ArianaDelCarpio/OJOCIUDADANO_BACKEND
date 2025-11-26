package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.AvanceObraDTO;
import pe.com.upc.backend.DTOs.InversionDTO;
import pe.com.upc.backend.Interfaces.IAvanceObraService;
import pe.com.upc.backend.Interfaces.IInversionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano/inversion")
public class InversionController {
    @Autowired
    private IInversionService inversionService;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<InversionDTO>> listar() {
        return ResponseEntity.ok(inversionService.listar());
    }


    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<InversionDTO> registrar(@RequestBody InversionDTO inversionDTO) {
        return ResponseEntity.ok(inversionService.registrar(inversionDTO));
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<InversionDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inversionService.findById(id));
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<InversionDTO> actualizar(@RequestBody InversionDTO inversionDTO) {
        return ResponseEntity.ok(inversionService.actualizar(inversionDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        inversionService.eliminar(id);
    }
}
