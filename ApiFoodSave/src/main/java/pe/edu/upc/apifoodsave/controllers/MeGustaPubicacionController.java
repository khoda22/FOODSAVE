package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.LikeDTO;
import pe.edu.upc.apifoodsave.entities.MeGustaPublicacion;
import pe.edu.upc.apifoodsave.repositories.IMeGustaPublicacionRepository;
import pe.edu.upc.apifoodsave.repositories.IPublicacionRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IMeGustaPublicacionService;

import java.util.List;

@RestController
@RequestMapping("/megustapubicacion")
public class MeGustaPubicacionController {
    @Autowired private IMeGustaPublicacionService service;                 // service simple: save/delete/findAll/findById
    @Autowired private IMeGustaPublicacionRepository meGustaRepo;          // para validar duplicados (opcional)
    @Autowired private IUsuarioRepository usuarioRepo;          // resolver FK
    @Autowired private IPublicacionRepository publicacionRepo;  // resolver FK

    // Crear like (USUARIO)
    //@PreAuthorize("hasAuthority('USUARIO')")
    @PostMapping("/nuevos")
    public ResponseEntity<String> insertar(@RequestBody LikeDTO dto) {
        if (dto.getIdUsuario() <= 0 || dto.getIdPublicacion() <= 0) {
            return ResponseEntity.badRequest().body("idUsuario e idPublicacion deben ser > 0");
        }

        // Evitar duplicado (opcional pero recomendado)
        boolean existe = meGustaRepo.existsByUsuario_IdUsuarioAndPublicacion_IdPublicacion(
                dto.getIdUsuario(), dto.getIdPublicacion());
        if (existe) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe el like para esta publicación.");
        }

        MeGustaPublicacion mg = new MeGustaPublicacion();
        mg.setUsuario(usuarioRepo.getReferenceById(dto.getIdUsuario()));
        mg.setPublicacion(publicacionRepo.getReferenceById(dto.getIdPublicacion()));

        service.insert(mg);
        return ResponseEntity.ok("Like registrado.");
    }

    //Eliminar like por par (idUsuario, idPublicacion)
    //@PreAuthorize("hasAuthority('USUARIO')")
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarPorPar(@RequestBody LikeDTO dto) {
        if (!meGustaRepo.existsByUsuario_IdUsuarioAndPublicacion_IdPublicacion(dto.getIdUsuario(), dto.getIdPublicacion())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el like para ese usuario/publicación.");
        }
        meGustaRepo.deleteByUsuario_IdUsuarioAndPublicacion_IdPublicacion(dto.getIdUsuario(), dto.getIdPublicacion());
        return ResponseEntity.ok("Like eliminado.");
    }
}
