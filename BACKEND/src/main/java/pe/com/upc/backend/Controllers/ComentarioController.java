package pe.com.upc.backend.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.upc.backend.DTOs.ComentarioDTO;
import pe.com.upc.backend.Interfaces.IComentarioService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", exposedHeaders = "Authorization")
@RequestMapping( "/apiOjoCiudadano")
public class ComentarioController {
    @Autowired
    private IComentarioService comentarioService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/comentario")
    public ResponseEntity<ComentarioDTO> registrar(@RequestBody ComentarioDTO comentarioDTO) {
        return ResponseEntity.ok(comentarioService.registrar(comentarioDTO));
    }

    @GetMapping("/comentarios")
    public ResponseEntity<List<ComentarioDTO>> listar() {
        return ResponseEntity.ok(comentarioService.listar());
    }

    // DEBES ACLARAR QUE TIPO DE BUSQUEDA ES EN ESTE CASO ES POR ID porque puede que halla otro que busque por EMAIL
    @GetMapping("/comentario/{id}")
    public ResponseEntity<ComentarioDTO> buscarId(@PathVariable Long id) {
        return ResponseEntity.ok(comentarioService.findById(id));   // devuelve DTO según tu service
    }
    @PutMapping("/comentario")
    public ResponseEntity<ComentarioDTO> actualizar(@RequestBody ComentarioDTO comentarioDTO) {
        return ResponseEntity.ok(comentarioService.actualizar(comentarioDTO)); // usa entidad según tu service
    }

    @DeleteMapping("/comentario/{id}")
    public void eliminar(@PathVariable Long id) {
        comentarioService.eliminar(id);
    }
}
