package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.UsuarioLogroDTO;
import pe.edu.upc.apifoodsave.entities.UsuarioLogro;
import pe.edu.upc.apifoodsave.repositories.ILogroRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioLogroRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IUsuarioLogroService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/usuariologro")
public class UsuarioLogroController {
    @Autowired
    private IUsuarioLogroService service;
    @Autowired
    private IUsuarioLogroRepository ulRepo;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private ILogroRepository logroRepository;

    @PostMapping("/nuevos")
    public ResponseEntity<String> asignar(@RequestBody UsuarioLogroDTO dto) {
        if (ulRepo.existsByUsuario_IdUsuarioAndLogro_IdLogro(dto.getIdUsuario(), dto.getIdLogro())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya tiene este logro.");
        }
        UsuarioLogro ul = new UsuarioLogro();
        ul.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        ul.setLogro(logroRepository.getReferenceById(dto.getIdLogro()));
        ul.setFechaLogro(dto.getFechaLogro() != null ? dto.getFechaLogro() : LocalDateTime.now());

        service.insert(ul);
        return ResponseEntity.ok("Logro asignado al usuario.");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> quitar(@PathVariable("id") int idUsuarioLogro) {
        if (!ulRepo.existsById(idUsuarioLogro)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe el registro de logro para ese ID.");
        }
        service.delete(idUsuarioLogro);
        return ResponseEntity.ok("Logro removido del usuario.");
    }
}
