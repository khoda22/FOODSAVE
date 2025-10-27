package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.ComentarioInsertDTO;
import pe.edu.upc.apifoodsave.dtos.ComentarioListDTO;
import pe.edu.upc.apifoodsave.dtos.ComentarioUpdateDTO;
import pe.edu.upc.apifoodsave.entities.ComentarioPublicacion;
import pe.edu.upc.apifoodsave.entities.Publicacion;
import pe.edu.upc.apifoodsave.entities.Usuario;
import pe.edu.upc.apifoodsave.repositories.IPublicacionRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IComentarioPublicacionService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentariopublicacion")
public class ComentarioPublicacionController {
    @Autowired
    private IComentarioPublicacionService service;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IPublicacionRepository publicacionRepository;

    // === CREAR COMENTARIO ===
    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> insertar(@RequestBody ComentarioInsertDTO dto) {
        ComentarioPublicacion comentario = new ComentarioPublicacion();

        // Mapeo manual (evita el problema con ModelMapper)
        comentario.setContenidoComentario(dto.getContenido());

        // Vincular FKs
        comentario.setUsuario(
                usuarioRepository.findById(dto.getIdUsuario())
                        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + dto.getIdUsuario()))
        );

        comentario.setPublicacion(
                publicacionRepository.findById(dto.getIdPublicacion())
                        .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada con ID: " + dto.getIdPublicacion()))
        );

        // Insertar
        service.insert(comentario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Comentario registrado correctamente ✅");
    }

    // === LISTAR TODOS ===
    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<ComentarioListDTO> listar() {
        return service.list().stream().map(c -> {
            ComentarioListDTO dto = new ComentarioListDTO();
            dto.setIdComentario(c.getIdComentario());
            dto.setContenido(c.getContenidoComentario());
            dto.setFechaCreacion(c.getFechaCreacionComentario());
            if (c.getUsuario() != null) dto.setNombreUsuario(c.getUsuario().getUsername());
            return dto;
        }).collect(Collectors.toList());
    }

    // === OBTENER POR ID ===
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        ComentarioPublicacion c = service.listId(id);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un comentario con ID: " + id);
        }

        ComentarioUpdateDTO dto = new ComentarioUpdateDTO();
        dto.setIdComentario(c.getIdComentario());
        dto.setContenido(c.getContenidoComentario());
        return ResponseEntity.ok(dto);
    }

    // === EDITAR ===
    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> editar(@RequestBody ComentarioUpdateDTO dto) {
        ComentarioPublicacion existente = service.listId(dto.getIdComentario());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un comentario con ID: " + dto.getIdComentario());
        }

        // Actualizar contenido, conservar FKs y fecha
        existente.setContenidoComentario(dto.getContenido());
        service.edit(existente);

        return ResponseEntity.ok("Comentario actualizado correctamente ✅");
    }

    // === ELIMINAR ===
    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ComentarioPublicacion existente = service.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un comentario con ID: " + id);
        }

        service.delete(id);
        return ResponseEntity.ok("Comentario eliminado correctamente 🗑️");
    }
}
