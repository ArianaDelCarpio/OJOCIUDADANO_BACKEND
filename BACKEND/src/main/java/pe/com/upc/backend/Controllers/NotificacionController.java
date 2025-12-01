package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.NotificacionDTO;
import pe.com.upc.backend.Interfaces.INotificacionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano/notificacion")
public class NotificacionController {

    @Autowired
    private INotificacionService notificacionService;

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<NotificacionDTO>> listar() {
        return ResponseEntity.ok(notificacionService.listar());
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<NotificacionDTO> registrar(@RequestBody NotificacionDTO notificacionDTO) {
        return ResponseEntity.ok(notificacionService.registrar(notificacionDTO));
    }

    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<NotificacionDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.findById(id));
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<NotificacionDTO> actualizar(@PathVariable Long id, @RequestBody NotificacionDTO notificacionDTO) {
        if (notificacionDTO.getId() != null && !id.equals(notificacionDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        NotificacionDTO actualizado = notificacionService.actualizar(id, notificacionDTO);

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id){
        notificacionService.eliminar(id);
    }
}
