package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.SeguidorDTOInsert;
import pe.edu.upc.apifoodsave.dtos.SeguidorDTOList;
import pe.edu.upc.apifoodsave.entities.Seguidor;
import pe.edu.upc.apifoodsave.repositories.ISeguidorRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.ISeguidorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seguidores")
public class SeguidorController {
    @Autowired
    private ISeguidorService service;
    @Autowired
    private ISeguidorRepository repo;
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/nuevos")
    public void insertar(@RequestBody SeguidorDTOInsert dto) {
        Seguidor s = new Seguidor();
        s.setFechaUnion(dto.getFechaUnion());
        if (dto.getIdSeguidor() != null) {
            s.setSeguidor(usuarioRepository.getReferenceById(dto.getIdSeguidor()));
        }
        if (dto.getIdSeguido() != null) {
            s.setSeguido(usuarioRepository.getReferenceById(dto.getIdSeguido()));
        }
        service.insert(s);
    }

    @GetMapping("/listas")
    public List<SeguidorDTOList> listar() {
        return service.list().stream().map(s -> {
            SeguidorDTOList dto = new SeguidorDTOList();
            dto.setIdSeguidorRel(s.getIdSeguidorRel());
            if (s.getSeguidor() != null) dto.setIdSeguidor(s.getSeguidor().getIdUsuario());
            if (s.getSeguido() != null) dto.setIdSeguido(s.getSeguido().getIdUsuario());
            dto.setFechaUnion(s.getFechaUnion());
            return dto;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/dejar-de-seguir")
    public ResponseEntity<String> dejarDeSeguir(@RequestBody SeguidorDTOInsert dto) {
        // opcional: evitar self-follow
        if (dto.getIdSeguidor() == dto.getIdSeguido()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Un usuario no puede dejar de seguirse a sí mismo.");
        }

        boolean existe = repo.existsBySeguidor_IdUsuarioAndSeguido_IdUsuario(
                dto.getIdSeguidor(), dto.getIdSeguido());

        if (!existe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario " + dto.getIdSeguidor() + " no sigue al usuario " + dto.getIdSeguido());
        }

        repo.deleteBySeguidor_IdUsuarioAndSeguido_IdUsuario(
                dto.getIdSeguidor(), dto.getIdSeguido());

        return ResponseEntity.ok("El usuario " + dto.getIdSeguidor()
                + " ha dejado de seguir al usuario " + dto.getIdSeguido());
    }
}
