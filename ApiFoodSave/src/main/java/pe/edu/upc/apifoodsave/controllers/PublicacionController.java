package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.PublicacionInsertDTO;
import pe.edu.upc.apifoodsave.dtos.PublicacionListDTO;
import pe.edu.upc.apifoodsave.dtos.PublicacionUpdateDTO;
import pe.edu.upc.apifoodsave.entities.Publicacion;
import pe.edu.upc.apifoodsave.entities.Receta;
import pe.edu.upc.apifoodsave.entities.Usuario;
import pe.edu.upc.apifoodsave.repositories.IRecetaRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IPublicacionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publicacion")
public class PublicacionController {
    @Autowired
    private IPublicacionService service;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IRecetaRepository recetaRepository;

    @PostMapping("/nuevos")
    public ResponseEntity<?> crear(@RequestBody PublicacionInsertDTO dto) {
        // Validación mínima
        if (dto.getContenidoPublicacion() == null || dto.getContenidoPublicacion().isBlank())
            return ResponseEntity.badRequest().body("contenidoPublicacion es obligatorio.");
        if (dto.getIdUsuario() <= 0 || dto.getIdReceta() <= 0)
            return ResponseEntity.badRequest().body("idUsuario e idReceta deben ser > 0.");

        // Resolver FKs (si no existen, 400)
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()).orElse(null);
        if (usuario == null) return ResponseEntity.badRequest().body("Usuario no existe: " + dto.getIdUsuario());
        Receta receta = recetaRepository.findById(dto.getIdReceta()).orElse(null);
        if (receta == null) return ResponseEntity.badRequest().body("Receta no existe: " + dto.getIdReceta());

        // Armar entidad (sin complicar con ModelMapper)
        Publicacion p = new Publicacion();
        p.setContenidoPublicacion(dto.getContenidoPublicacion());
        p.setFotoUrlPublicacion(dto.getFotoUrlPublicacion());
        p.setFechaCreacionPublicacion(LocalDateTime.now()); // también lo hace @PrePersist
        p.setUsuario(usuario);
        p.setReceta(receta);

        service.insert(p);
        return ResponseEntity.status(HttpStatus.CREATED).body("Publicación creada. ID: " + p.getIdPublicacion());
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<PublicacionListDTO> listar() {
        ModelMapper m = new ModelMapper();
        return service.list().stream().map(p -> {
            PublicacionListDTO dto = m.map(p, PublicacionListDTO.class);
            if (p.getUsuario() != null) {
                dto.setUsername(p.getUsuario().getUsername());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Publicacion p = service.listId(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        PublicacionListDTO dto = m.map(p, PublicacionListDTO.class);
        dto.setUsername(p.getUsuario() != null ? p.getUsuario().getUsername() : null);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> editar(@RequestBody PublicacionUpdateDTO dto) {
        Publicacion existente = service.listId(dto.getIdPublicacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dto.getIdPublicacion());
        }

        ModelMapper m = new ModelMapper();
        m.getConfiguration().setSkipNullEnabled(true);
        m.typeMap(PublicacionUpdateDTO.class, Publicacion.class).addMappings(mp -> {
            mp.skip(Publicacion::setIdPublicacion);
            mp.skip(Publicacion::setUsuario);
            mp.skip(Publicacion::setReceta);
            mp.skip(Publicacion::setFechaCreacionPublicacion);
        });
        m.map(dto, existente); // patch

        service.edit(existente);
        return ResponseEntity.ok("Registro con ID " + dto.getIdPublicacion() + " modificado correctamente.");
    }
}
