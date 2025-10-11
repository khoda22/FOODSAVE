package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.RecetaDTOInsert;
import pe.edu.upc.apifoodsave.dtos.RecetaDTOList;
import pe.edu.upc.apifoodsave.dtos.RecetaDTOUpdate;
import pe.edu.upc.apifoodsave.entities.Receta;
import pe.edu.upc.apifoodsave.servicesinterfaces.IRecetaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recetas")
public class RecetaController {
    @Autowired
    private IRecetaService service;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody RecetaDTOInsert dto) {
        ModelMapper m = new ModelMapper();
        Receta r = m.map(dto, Receta.class);
        service.insert(r);
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<RecetaDTOList> listar() {
        return service.list().stream().map(r -> {
            RecetaDTOList dto = new RecetaDTOList();
            //dto.setIdReceta(r.getIdReceta());
            dto.setTitulo(r.getTitulo());
            dto.setDificultad(r.getDificultad());
            //dto.setTiempoPreparacion(r.getTiempoPreparacion());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Receta r = service.listId(id);
        if (r == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe receta " + id);
        ModelMapper m = new ModelMapper();
        RecetaDTOUpdate dto = m.map(r, RecetaDTOUpdate.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> editar(@RequestBody RecetaDTOUpdate dto) {
        ModelMapper m = new ModelMapper();
        Receta r = m.map(dto, Receta.class);
        Receta existente = service.listId(r.getIdReceta());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe receta " + r.getIdReceta());
        }
        service.edit(r);
        return ResponseEntity.ok("Receta " + r.getIdReceta() + " actualizada.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable("id") int id) {
        Receta r = service.listId(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
}
