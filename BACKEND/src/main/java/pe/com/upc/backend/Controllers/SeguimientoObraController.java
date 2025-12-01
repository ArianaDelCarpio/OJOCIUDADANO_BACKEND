package pe.com.upc.backend.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.SeguimientoObraDTO;
import pe.com.upc.backend.Interfaces.ISeguimientoObraService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano/seguimiento-obra")

public class SeguimientoObraController {
    @Autowired
    private ISeguimientoObraService seguimientoObraService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<SeguimientoObraDTO> registrar(@RequestBody SeguimientoObraDTO seguimientoObraDTO) {
        return ResponseEntity.ok(seguimientoObraService.registrar(seguimientoObraDTO));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<SeguimientoObraDTO>> listar() {
        return ResponseEntity.ok(seguimientoObraService.listar());
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<SeguimientoObraDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok(seguimientoObraService.findById(id));   // devuelve DTO según tu service
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<SeguimientoObraDTO> actualizar(@PathVariable Long id, @RequestBody SeguimientoObraDTO seguimientoObraDTO) {

        // 1. Validación de seguridad: ID de URL vs ID del cuerpo
        if (seguimientoObraDTO.getId() != null && !id.equals(seguimientoObraDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // 2. Llamada al servicio
        SeguimientoObraDTO actualizado = seguimientoObraService.actualizar(id, seguimientoObraDTO);
        // 3. Manejo de error si no existe
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        seguimientoObraService.eliminar(id);
    }

}
