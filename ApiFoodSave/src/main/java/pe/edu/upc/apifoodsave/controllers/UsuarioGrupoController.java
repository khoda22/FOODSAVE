package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.UsuarioGrupoDTO;
import pe.edu.upc.apifoodsave.entities.UsuarioGrupo;
import pe.edu.upc.apifoodsave.repositories.IGrupoRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioGrupoRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IUsuarioGrupoService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/usuariogrupo")
public class UsuarioGrupoController {
    @Autowired
    private IUsuarioGrupoService service;
    @Autowired
    private IUsuarioGrupoRepository ugRepo;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IGrupoRepository grupoRepository;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> unir(@RequestBody UsuarioGrupoDTO dto) {
        if (ugRepo.existsByUsuario_IdUsuarioAndGrupo_IdGrupo(dto.getIdUsuario(), dto.getIdGrupo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya está en el grupo.");
        }
        UsuarioGrupo ug = new UsuarioGrupo();
        ug.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        ug.setGrupo(grupoRepository.getReferenceById(dto.getIdGrupo()));
        ug.setRolGrupo("PARTICIPANTE");
        ug.setFechaUnion(LocalDateTime.now());

        service.insert(ug);
        return ResponseEntity.ok("Usuario agregado al grupo.");
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> salir(@PathVariable("id") int idUsuarioGrupo) {
        if (!ugRepo.existsById(idUsuarioGrupo)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la membresía.");
        }
        service.delete(idUsuarioGrupo);
        return ResponseEntity.ok("Usuario removido del grupo.");
    }
}
