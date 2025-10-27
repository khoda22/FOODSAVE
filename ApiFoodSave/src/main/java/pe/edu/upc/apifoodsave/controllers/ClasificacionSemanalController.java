package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.ClasificacionInsertDTO;
import pe.edu.upc.apifoodsave.dtos.ClasificacionListDTO;
import pe.edu.upc.apifoodsave.dtos.ClasificacionSemanalRankDTO;
import pe.edu.upc.apifoodsave.dtos.ClasificacionUpdateDTO;
import pe.edu.upc.apifoodsave.entities.ClasificacionSemanal;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IClasificacionSemanalService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clasificacionsemanal")
public class ClasificacionSemanalController {
    @Autowired
    private IClasificacionSemanalService service;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> insertar(@RequestBody ClasificacionInsertDTO dto) {
        if (dto.getIdUsuario() <= 0) {
            return ResponseEntity.badRequest().body("idUsuario es obligatorio y debe ser > 0");
        }

        ClasificacionSemanal c = new ClasificacionSemanal();
        c.setPeriodoClasificacion(dto.getPeriodo());
        c.setKgSalvadosClasificacion(dto.getKgSalvados());
        c.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));

        // 👇 Ya no hace falta calcular puntaje: se hace automático al persistir
        service.insert(c);
        return ResponseEntity.ok("Clasificación registrada correctamente.");
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<ClasificacionListDTO> listar() {
        return service.list().stream().map(c -> {
            ClasificacionListDTO dto = new ClasificacionListDTO();
            dto.setIdClasificacion(c.getIdClasificacion());
            dto.setPeriodo(c.getPeriodoClasificacion());
            dto.setPuntaje(c.getPuntajeClasificacion());
            dto.setKgSalvados(c.getKgSalvadosClasificacion());
            if (c.getUsuario() != null) {
                dto.setNombreUsuario(c.getUsuario().getUsername());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        ClasificacionSemanal c = service.listId(id);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una clasificación con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ClasificacionUpdateDTO dto = m.map(c, ClasificacionUpdateDTO.class);
        // si quieres devolver también idUsuario:
        if (c.getUsuario() != null) {
            // agrega campo idUsuario en el UpdateDTO si lo necesitas
            // dto.setIdUsuario(c.getUsuario().getIdUsuario());
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> editar(@RequestBody ClasificacionUpdateDTO dto) {
        ClasificacionSemanal existente = service.listId(dto.getIdClasificacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe una clasificación con el ID: " + dto.getIdClasificacion());
        }

        existente.setPeriodoClasificacion(dto.getPeriodoClasificacion());
        existente.setKgSalvadosClasificacion(dto.getKgSalvadosClasificacion());
        // El puntaje se recalcula automáticamente por @PreUpdate

        if (dto.getIdUsuario() > 0) {
            existente.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        }

        service.edit(existente);
        return ResponseEntity.ok("Clasificación actualizada correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ClasificacionSemanal existente = service.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede eliminar. No existe una clasificación con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Clasificación con ID " + id + " eliminada correctamente.");
    }

    // Ranking por período explícito, ej: 2025-W37
    @GetMapping("/clasificacion-semanal/ranking/{periodo}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<ClasificacionSemanalRankDTO> rankingPorPeriodo(@PathVariable String periodo) {
        List<Object[]> rows = service.rankingSemanal(periodo);
        List<ClasificacionSemanalRankDTO> out = new ArrayList<>(rows.size());
        for (Object[] r : rows) {
            ClasificacionSemanalRankDTO dto = new ClasificacionSemanalRankDTO();
            dto.setPosicion(((Number) r[0]).intValue());
            dto.setIdUsuario(((Number) r[1]).intValue());
            dto.setUsername((String) r[2]);
            dto.setKgTotales(r[3] == null ? 0.0 : ((Number) r[3]).doubleValue());
            dto.setPuntosTotales(r[4] == null ? 0 : ((Number) r[4]).intValue());
            dto.setPeriodo((String) r[5]);
            out.add(dto);
        }
        return out;
    }

    // Ranking de la semana ISO actual (2025-W37, etc.)
    @GetMapping("/ranking/actual")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<ClasificacionSemanalRankDTO> rankingSemanaActual() {
        List<Object[]> rows = service.rankingSemanalActual();
        List<ClasificacionSemanalRankDTO> out = new ArrayList<>(rows.size());
        for (Object[] r : rows) {
            ClasificacionSemanalRankDTO dto = new ClasificacionSemanalRankDTO();
            dto.setPosicion(((Number) r[0]).intValue());
            dto.setIdUsuario(((Number) r[1]).intValue());
            dto.setUsername((String) r[2]);
            dto.setKgTotales(r[3] == null ? 0.0 : ((Number) r[3]).doubleValue());
            dto.setPuntosTotales(r[4] == null ? 0 : ((Number) r[4]).intValue());
            dto.setPeriodo((String) r[5]); // periodo_actual
            out.add(dto);
        }
        return out;
    }
}
