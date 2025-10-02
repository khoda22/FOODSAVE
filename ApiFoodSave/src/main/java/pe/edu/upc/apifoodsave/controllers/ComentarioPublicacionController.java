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
import pe.edu.upc.apifoodsave.repositories.IPublicacionRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IComentarioPublicacionService;

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

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody ComentarioInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        ComentarioPublicacion c = m.map(dto, ComentarioPublicacion.class);

        if (dto.getIdUsuario() > 0)
            c.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        if (dto.getIdPublicacion() > 0)
            c.setPublicacion(publicacionRepository.getReferenceById(dto.getIdPublicacion()));

        service.insert(c);
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<ComentarioListDTO> listar() {
        return service.list().stream().map(c -> {
            ComentarioListDTO dto = new ComentarioListDTO();
            dto.setIdComentario(c.getIdComentario());
            dto.setContenido(c.getContenidoComentario());
            dto.setFechaCreacion(c.getFechaCreacionComentario());
            if (c.getUsuario() != null) dto.setNombreUsuario(c.getUsuario().getusername());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        ComentarioPublicacion c = service.listId(id);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ComentarioUpdateDTO dto = m.map(c, ComentarioUpdateDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> editar(@RequestBody ComentarioUpdateDTO dto) {
        ModelMapper m = new ModelMapper();
        ComentarioPublicacion c = m.map(dto, ComentarioPublicacion.class);

        ComentarioPublicacion existente = service.listId(c.getIdComentario());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + c.getIdComentario());
        }
        // conservar FKs
        c.setUsuario(existente.getUsuario());
        c.setPublicacion(existente.getPublicacion());

        service.edit(c);
        return ResponseEntity.ok("Registro con ID " + c.getIdComentario() + " modificado correctamente.");
    }
}
