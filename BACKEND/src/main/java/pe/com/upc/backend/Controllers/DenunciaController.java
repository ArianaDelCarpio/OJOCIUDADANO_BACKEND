package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<DenunciaDTO> actualizar(@PathVariable Long id, @RequestBody DenunciaDTO denunciaDTO) {

        // 1. Validar que el ID del cuerpo coincida con la URL (si viene en el cuerpo)
        if (denunciaDTO.getIdDenuncia() != null && !id.equals(denunciaDTO.getIdDenuncia())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 2. Llamar al servicio
        DenunciaDTO actualizado = denunciaService.actualizar(id, denunciaDTO);

        // 3. Manejar caso de no encontrado
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        denunciaService.eliminar(id);
    }

}
