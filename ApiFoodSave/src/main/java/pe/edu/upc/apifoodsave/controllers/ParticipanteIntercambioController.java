package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.ParticipanteIntercambioDTOInsert;
import pe.edu.upc.apifoodsave.entities.ParticipanteIntercambio;
import pe.edu.upc.apifoodsave.repositories.IIntercambioRepository;
import pe.edu.upc.apifoodsave.repositories.IParticipanteIntercambioRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IParticipanteIntercambioService;

@RestController
@RequestMapping("/participantes-intercambio")
public class ParticipanteIntercambioController {
    @Autowired
    private IParticipanteIntercambioService service;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IParticipanteIntercambioRepository repo;
    @Autowired
    private IIntercambioRepository intercambioRepository;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody ParticipanteIntercambioDTOInsert dto) {
        ParticipanteIntercambio p = new ParticipanteIntercambio();
        if (dto.getIdUsuario() != null) {
            p.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        }
        if (dto.getIdIntercambio() != null) {
            p.setIntercambio(intercambioRepository.getReferenceById(dto.getIdIntercambio()));
        }
        service.insert(p);
    }

    @DeleteMapping("/cancelar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> cancelar(@RequestBody ParticipanteIntercambioDTOInsert dto) {
        if (dto.getIdUsuario() == null || dto.getIdIntercambio() == null) {
            return ResponseEntity.badRequest().body("idUsuario e idIntercambio son obligatorios.");
        }

        boolean existe = repo.existsByUsuario_IdUsuarioAndIntercambio_IdIntercambio(
                dto.getIdUsuario(), dto.getIdIntercambio());

        if (!existe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario " + dto.getIdUsuario() +
                            " no está inscrito en el intercambio " + dto.getIdIntercambio());
        }

        repo.deleteByUsuario_IdUsuarioAndIntercambio_IdIntercambio(
                dto.getIdUsuario(), dto.getIdIntercambio());

        return ResponseEntity.ok("Participación cancelada: usuario " + dto.getIdUsuario() +
                " en intercambio " + dto.getIdIntercambio());
    }
}
