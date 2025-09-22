package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.DenunciaDTO;
import pe.com.upc.backend.Interfaces.IDenunciaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano")
public class DenunciaController {
    @Autowired
    private IDenunciaService denunciaService;

    @GetMapping("/denuncias")
    public ResponseEntity<List<DenunciaDTO>> listar() {
        return ResponseEntity.ok(denunciaService.listar());
    }
    @PostMapping("/denuncia")
    public ResponseEntity<DenunciaDTO> registrar(@RequestBody DenunciaDTO denunciaDTO) {
        return ResponseEntity.ok(denunciaService.registrar(denunciaDTO));
    }

    @GetMapping("/denuncia/{id}")
    public ResponseEntity<DenunciaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(denunciaService.findById(id));
    }

    @PutMapping("/denuncia")
    public ResponseEntity<DenunciaDTO> actualizar(@RequestBody DenunciaDTO denunciaDTO) {
        return ResponseEntity.ok(denunciaService.actualizar(denunciaDTO));
    }

    @DeleteMapping("/denuncia/{id}")
    public void eliminar(@PathVariable Long id) {
        denunciaService.eliminar(id);
    }

}
