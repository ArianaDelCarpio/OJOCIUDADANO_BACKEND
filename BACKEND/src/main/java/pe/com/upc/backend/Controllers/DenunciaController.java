package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.DenunciaDTO;
import pe.com.upc.backend.Interfaces.IDenunciaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano/denuncia")
public class DenunciaController {
    @Autowired
    private IDenunciaService denunciaService;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<DenunciaDTO>> listar() {
        return ResponseEntity.ok(denunciaService.listar());
    }

    @PostMapping("/registar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<DenunciaDTO> registrar(@RequestBody DenunciaDTO denunciaDTO) {
        return ResponseEntity.ok(denunciaService.registrar(denunciaDTO));
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<DenunciaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(denunciaService.findById(id));
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<DenunciaDTO> actualizar(@RequestBody DenunciaDTO denunciaDTO) {
        return ResponseEntity.ok(denunciaService.actualizar(denunciaDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        denunciaService.eliminar(id);
    }

}
