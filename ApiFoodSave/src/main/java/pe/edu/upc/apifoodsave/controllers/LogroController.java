package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.LogroInsertDTO;
import pe.edu.upc.apifoodsave.dtos.LogroInsigniaDTO;
import pe.edu.upc.apifoodsave.dtos.LogroListDTO;
import pe.edu.upc.apifoodsave.dtos.LogroUpdateDTO;
import pe.edu.upc.apifoodsave.entities.Logro;
import pe.edu.upc.apifoodsave.repositories.IClasificacionSemanalRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.ILogroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logro")
public class LogroController {
    @Autowired
    private ILogroService service;
    @Autowired
    private IClasificacionSemanalRepository clasificacionRepo;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody LogroInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Logro l = m.map(dto, Logro.class);
        service.insert(l);
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<LogroListDTO> listar() {
        return service.list().stream().map(l -> {
            ModelMapper m = new ModelMapper();
            return m.map(l, LogroListDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Logro l = service.listId(id);
        if (l == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un logro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        LogroUpdateDTO dto = m.map(l, LogroUpdateDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> editar(@RequestBody LogroUpdateDTO dto) {
        ModelMapper m = new ModelMapper();
        Logro l = m.map(dto, Logro.class);

        Logro existente = service.listId(l.getIdLogro());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un logro con el ID: " + l.getIdLogro());
        }

        service.edit(l);
        return ResponseEntity.ok("Logro con ID " + l.getIdLogro() + " modificado correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Logro existente = service.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede eliminar. No existe un logro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Logro con ID " + id + " eliminado correctamente.");
    }

    @PostMapping("/asignar-auto")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public Map<String, Object> asignarLogrosAutomaticamente() {
        // 1) Asegurar la regla: puntos = 10 * kg (JPQL)
        int filasPuntos = clasificacionRepo.recalcularPuntosParaTodos();

        // 2) Asignar logros con los totales actuales (usa SUM(puntaje_clasificacion))
        int insertados = service.asignarLogrosAutomaticamente();

        return Map.of(
                "filas_puntos_actualizadas", filasPuntos,
                "logros_nuevos_asignados", insertados,
                "mensaje", "Puntos recalculados y logros asignados"
        );
    }

    @GetMapping("/usuarios/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<LogroInsigniaDTO> verLogrosDeUsuario(@PathVariable int idUsuario) {
        List<Object[]> rows = service.verLogrosDeUsuario(idUsuario);
        List<LogroInsigniaDTO> out = new ArrayList<>(rows.size());
        for (Object[] r : rows) {
            LogroInsigniaDTO dto = new LogroInsigniaDTO();
            dto.setUsername((String) r[0]);
            dto.setNombreLogro((String) r[1]);
            dto.setPuntosTotales(r[2] == null ? 0 : ((Number) r[2]).intValue());
            dto.setKgTotales(r[3] == null ? 0.0 : ((Number) r[3]).doubleValue());
            Object f = r[4];
            if (f instanceof java.sql.Timestamp ts) dto.setFechaLogro(ts.toLocalDateTime());
            else if (f instanceof java.time.OffsetDateTime odt) dto.setFechaLogro(odt.toLocalDateTime());
            else dto.setFechaLogro((java.time.LocalDateTime) f);
            out.add(dto);
        }
        return out;
    }
}
