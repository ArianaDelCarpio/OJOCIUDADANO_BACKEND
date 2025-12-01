package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<InversionDTO> actualizar(@PathVariable Long id, @RequestBody InversionDTO inversionDTO) {

        // 1. Validación: Si el DTO trae ID, debe coincidir con la URL
        // NOTA: Si tu DTO usa otro nombre (ej: getIdInversion), ajusta el método aquí.
        if (inversionDTO.getIdInversion() != null && !id.equals(inversionDTO.getIdInversion())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 2. Llamada al servicio
        InversionDTO actualizado = inversionService.actualizar(id, inversionDTO);

        // 3. Manejo de no encontrado
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        inversionService.eliminar(id);
    }
}
