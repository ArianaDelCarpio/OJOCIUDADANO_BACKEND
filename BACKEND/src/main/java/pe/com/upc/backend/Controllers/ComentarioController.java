package pe.com.upc.backend.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.ComentarioDTO;
import pe.com.upc.backend.Interfaces.IComentarioService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano/comentario")
public class ComentarioController {
    @Autowired
    private IComentarioService comentarioService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<ComentarioDTO> registrar(@RequestBody ComentarioDTO comentarioDTO) {
        return ResponseEntity.ok(comentarioService.registrar(comentarioDTO));
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<List<ComentarioDTO>> listar() {
        return ResponseEntity.ok(comentarioService.listar());
    }

    // DEBES ACLARAR QUE TIPO DE BUSQUEDA ES EN ESTE CASO ES POR ID porque puede que halla otro que busque por EMAIL
    @GetMapping("/obtener-por-id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR','CIUDADANO')")
    public ResponseEntity<ComentarioDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.findById(id));   // devuelve DTO según tu service
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public ResponseEntity<ComentarioDTO> actualizar(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO) {

        // 1. Validación: Si el DTO trae ID, debe coincidir con la URL
        if (comentarioDTO.getId() != null && !id.equals(comentarioDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 2. Llamada al servicio
        ComentarioDTO actualizado = comentarioService.actualizar(id, comentarioDTO);

        // 3. Manejo de error si no existe
        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DESARROLLADOR')")
    public void eliminar(@PathVariable Long id) {
        comentarioService.eliminar(id);
    }
}
