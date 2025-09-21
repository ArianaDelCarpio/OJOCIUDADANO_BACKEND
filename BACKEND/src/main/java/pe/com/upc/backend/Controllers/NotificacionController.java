package pe.com.upc.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.NotificacionDTO;
import pe.com.upc.backend.Interfaces.INotificacionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano")
public class NotificacionController {

    @Autowired
    private INotificacionService notificacionService;

    @GetMapping("/notificaciones")
    public ResponseEntity<List<NotificacionDTO>> listar() {
        return ResponseEntity.ok(notificacionService.listar());
    }

    @PostMapping("/notificacion")
    public ResponseEntity<NotificacionDTO> registrar(@RequestBody NotificacionDTO notificacionDTO) {
        return ResponseEntity.ok(notificacionService.registrar(notificacionDTO));
    }

    @GetMapping("/notificacion/{id}")
    public ResponseEntity<NotificacionDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.findById(id));
    }

    @PutMapping("/notificacion")
    public ResponseEntity<NotificacionDTO> actualizar(@RequestBody NotificacionDTO notificacionDTO) {
        return ResponseEntity.ok(notificacionService.actualizar(notificacionDTO));
    }

    @DeleteMapping( "/notificacion/{id}")
    public void eliminar(@PathVariable Long id){
        notificacionService.eliminar(id);
    }
}
