package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.LikeDTO;
import pe.edu.upc.apifoodsave.entities.MeGustaPublicacion;
import pe.edu.upc.apifoodsave.repositories.IMeGustaPublicacionRepository;
import pe.edu.upc.apifoodsave.repositories.IPublicacionRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IMeGustaPublicacionService;

@RestController
@RequestMapping("/megustapublicacion")
public class MeGustaPublicacionController {
    @Autowired private IMeGustaPublicacionService service;
    @Autowired private IMeGustaPublicacionRepository meGustaRepo;
    @Autowired private IUsuarioRepository usuarioRepo;
    @Autowired private IPublicacionRepository publicacionRepo;

    // Crear like (USUARIO)  -> 201 o 409 si ya existe
    @PostMapping("/nuevos")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<String> insertar(@RequestBody LikeDTO dto) {

        if (dto.getIdUsuario() <= 0 || dto.getIdPublicacion() <= 0) {
            return ResponseEntity.badRequest().body("idUsuario e idPublicacion deben ser > 0.");
        }

        // validar existencia de FK (evita EntityNotFoundException de getReferenceById)
        var usuario = usuarioRepo.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + dto.getIdUsuario()));
        var publicacion = publicacionRepo.findById(dto.getIdPublicacion())
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada: " + dto.getIdPublicacion()));

        // evitar duplicado
        if (meGustaRepo.existsByUsuario_IdUsuarioAndPublicacion_IdPublicacion(dto.getIdUsuario(), dto.getIdPublicacion())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe el like para esta publicación.");
        }

        MeGustaPublicacion mg = new MeGustaPublicacion();
        mg.setUsuario(usuario);
        mg.setPublicacion(publicacion);

        service.insert(mg);
        return ResponseEntity.status(HttpStatus.CREATED).body("Like registrado.");
    }

    // Eliminar like por par (idUsuario, idPublicacion) -> 200 o 404
    @DeleteMapping("/eliminar")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<String> eliminarPorPar(@RequestBody LikeDTO dto) {
        if (dto.getIdUsuario() <= 0 || dto.getIdPublicacion() <= 0) {
            return ResponseEntity.badRequest().body("idUsuario e idPublicacion deben ser > 0.");
        }

        if (!meGustaRepo.existsByUsuario_IdUsuarioAndPublicacion_IdPublicacion(dto.getIdUsuario(), dto.getIdPublicacion())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el like para ese usuario/publicación.");
        }

        meGustaRepo.deleteByUsuario_IdUsuarioAndPublicacion_IdPublicacion(dto.getIdUsuario(), dto.getIdPublicacion());
        return ResponseEntity.ok("Like eliminado.");
    }
}
