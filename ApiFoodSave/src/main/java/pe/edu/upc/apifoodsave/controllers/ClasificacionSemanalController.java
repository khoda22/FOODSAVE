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
        ModelMapper m = new ModelMapper();
        ClasificacionSemanal c = m.map(dto, ClasificacionSemanal.class);

        if (dto.getIdUsuario() <= 0) {
            return ResponseEntity.badRequest().body("idUsuario es obligatorio y debe ser > 0");
        }
        c.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        service.insert(c);
        return ResponseEntity.ok("Clasificación registrada.");
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
                dto.setNombreUsuario(c.getUsuario().getusername());
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
        ModelMapper m = new ModelMapper();
        ClasificacionSemanal c = m.map(dto, ClasificacionSemanal.class);

        ClasificacionSemanal existente = service.listId(c.getIdClasificacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe una clasificación con el ID: " + c.getIdClasificacion());
        }

        if (dto.getIdUsuario() > 0) {
            c.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        } else {
            c.setUsuario(existente.getUsuario());
        }
        c.setUsuario(existente.getUsuario());

        service.edit(c);
        return ResponseEntity.ok("Clasificación con ID " + c.getIdClasificacion() + " modificada correctamente.");
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

    @GetMapping("/rankSemanal")
    public ResponseEntity<?> rankSemanal() {
        List<String[]> rank=service.RankClasificacionSemanalService();
        List<ClasificacionSemanalRankDTO> listaRank=new ArrayList<>();

        if (rank.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron proveedores registrados ");
        }
        for(String[] columna:rank){
            ClasificacionSemanalRankDTO dto=new ClasificacionSemanalRankDTO();
            // columna[0] = amount
            int idUsuario = Integer.parseInt(columna[0]);
            String nombre = columna[1];
            int puntosLogro = Integer.parseInt(columna[2]);
            double kgSalvadosClasificacion = Double.parseDouble(columna[3]);

            dto.setIdUsuario(idUsuario);
            dto.setNombre(nombre);
            dto.setPuntosLogro(puntosLogro);
            dto.setKgSalvadosClasificacion(kgSalvadosClasificacion);

            listaRank.add(dto);
        }
        return ResponseEntity.ok(listaRank);

    }
}
