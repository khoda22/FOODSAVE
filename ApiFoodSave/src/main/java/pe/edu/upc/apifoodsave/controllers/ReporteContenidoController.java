package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.ReporteContenidoDTOInsert;
import pe.edu.upc.apifoodsave.dtos.ReporteContenidoDTOList;
import pe.edu.upc.apifoodsave.dtos.ReporteContenidoDTOUpdate;
import pe.edu.upc.apifoodsave.entities.ReporteContenido;
import pe.edu.upc.apifoodsave.repositories.IPublicacionRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IReporteContenidoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
public class ReporteContenidoController {
    @Autowired
    private IReporteContenidoService service;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IPublicacionRepository publicacionRepository;

    @PostMapping("/nuevos")
    public void insertar(@RequestBody ReporteContenidoDTOInsert dto) {
        ReporteContenido r = new ReporteContenido();
        r.setMotivo(dto.getMotivo());
        r.setFechaReporte(dto.getFechaReporte());
        r.setEstado(dto.getEstado());
        if (dto.getIdUsuario() != null) {
            r.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        }
        if (dto.getIdPublicacion() != null) {
            r.setPublicacion(publicacionRepository.getReferenceById(dto.getIdPublicacion()));
        }
        service.insert(r);
    }

    @GetMapping("/listas")
    public List<ReporteContenidoDTOList> listar() {
        return service.list().stream().map(r -> {
            ReporteContenidoDTOList dto = new ReporteContenidoDTOList();
            dto.setIdReporte(r.getIdReporte());
            dto.setMotivo(r.getMotivo());
            dto.setFechaReporte(r.getFechaReporte());
            dto.setEstado(r.getEstado());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        ReporteContenido r = service.listId(id);
        if (r == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe reporte " + id);
        ReporteContenidoDTOList dto = new ReporteContenidoDTOList();
        dto.setIdReporte(r.getIdReporte());
        dto.setMotivo(r.getMotivo());
        dto.setFechaReporte(r.getFechaReporte());
        dto.setEstado(r.getEstado());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    public ResponseEntity<String> editar(@RequestBody ReporteContenidoDTOUpdate dto) {
        ReporteContenido existente = service.listId(dto.getIdReporte());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe reporte " + dto.getIdReporte());
        }
        existente.setEstado(dto.getEstado());
        service.edit(existente);
        return ResponseEntity.ok("Reporte " + dto.getIdReporte() + " actualizado.");
    }
}
