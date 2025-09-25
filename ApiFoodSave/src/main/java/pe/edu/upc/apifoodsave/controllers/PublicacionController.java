package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.PublicacionInsertDTO;
import pe.edu.upc.apifoodsave.dtos.PublicacionListDTO;
import pe.edu.upc.apifoodsave.dtos.PublicacionUpdateDTO;
import pe.edu.upc.apifoodsave.entities.Publicacion;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IPublicacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publicacion")
public class PublicacionController {
    @Autowired
    private IPublicacionService service;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/nuevos")
    public void insertar(@RequestBody PublicacionInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Publicacion p = m.map(dto, Publicacion.class);

        // resolver FK usuario por id
        if (dto.getIdUsuario() > 0) {
            p.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        }
        service.insert(p);
    }

    @GetMapping("/listas")
    public List<PublicacionListDTO> listar() {
        return service.list().stream().map(p -> {
            PublicacionListDTO dto = new PublicacionListDTO();
            dto.setIdPublicacion(p.getIdPublicacion());
            dto.setContenido(p.getContenidoPublicacion());
            dto.setFotoUrl(p.getFotoUrlPublicacion());
            dto.setFechaCreacion(p.getFechaCreacionPublicacion());

            if (p.getUsuario() != null) {
                //dto.setNombreUsuario(p.getUsuario().getNombreUsuario());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Publicacion p = service.listId(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        PublicacionUpdateDTO dto = new PublicacionUpdateDTO();
        dto.setIdPublicacion(p.getIdPublicacion());
        dto.setContenido(p.getContenidoPublicacion());
        dto.setFotoUrl(p.getFotoUrlPublicacion());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    public ResponseEntity<String> editar(@RequestBody PublicacionUpdateDTO dto) {
        ModelMapper m = new ModelMapper();
        Publicacion p = m.map(dto, Publicacion.class);

        Publicacion existente = service.listId(p.getIdPublicacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + p.getIdPublicacion());
        }
        // mantener el usuario existente si no viene en el cuerpo
        p.setUsuario(existente.getUsuario());

        service.edit(p);
        return ResponseEntity.ok("Registro con ID " + p.getIdPublicacion() + " modificado correctamente.");
    }
}
