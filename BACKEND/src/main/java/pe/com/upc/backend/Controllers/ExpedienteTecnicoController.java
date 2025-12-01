package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.ExpedienteTecnicoDTO;
import pe.com.upc.backend.Interfaces.IExpedienteTecnicoService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping("/apiOjoCiudadano/expediente-tecnico")
public class ExpedienteTecnicoController {
    @Autowired
    private IExpedienteTecnicoService expedienteTecnicoService;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<ExpedienteTecnicoDTO> registrar(@RequestBody ExpedienteTecnicoDTO expedienteTecnicoDTO) {
        return ResponseEntity.ok(expedienteTecnicoService.registrar(expedienteTecnicoDTO));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<ExpedienteTecnicoDTO>> listar() {
        return ResponseEntity.ok(expedienteTecnicoService.listar());
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<ExpedienteTecnicoDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(expedienteTecnicoService.findById(id));
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<ExpedienteTecnicoDTO> actualizar(@PathVariable Long id, @RequestBody ExpedienteTecnicoDTO expedienteTecnicoDTO) {
        if (expedienteTecnicoDTO.getId() != null && !id.equals(expedienteTecnicoDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ExpedienteTecnicoDTO actualizado = expedienteTecnicoService.actualizar(id, expedienteTecnicoDTO);
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        expedienteTecnicoService.eliminar(id);
    }
}
