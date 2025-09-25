package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.GrupoDTOInsert;
import pe.edu.upc.apifoodsave.dtos.GrupoDTOList;
import pe.edu.upc.apifoodsave.dtos.GrupoDTOUpdate;
import pe.edu.upc.apifoodsave.entities.Grupo;
import pe.edu.upc.apifoodsave.servicesinterfaces.IGrupoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
    @Autowired
    private IGrupoService service;

    @PostMapping("/nuevos")
    public void insertar(@RequestBody GrupoDTOInsert dto) {
        ModelMapper m = new ModelMapper();
        Grupo g = m.map(dto, Grupo.class);
        service.insert(g);
    }

    @GetMapping("/listas")
    public List<GrupoDTOList> listar() {
        return service.list().stream().map(g -> {
            ModelMapper m = new ModelMapper();
            return m.map(g, GrupoDTOList.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Grupo g = service.listId(id);
        if (g == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un grupo con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        GrupoDTOUpdate dto = m.map(g, GrupoDTOUpdate.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    public ResponseEntity<String> editar(@RequestBody GrupoDTOUpdate dto) {
        ModelMapper m = new ModelMapper();
        Grupo g = m.map(dto, Grupo.class);

        Grupo existente = service.listId(g.getIdGrupo());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un grupo con el ID: " + g.getIdGrupo());
        }

        service.edit(g);
        return ResponseEntity.ok("Grupo con ID " + g.getIdGrupo() + " modificado correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Grupo existente = service.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede eliminar. No existe un grupo con el ID: " + id);
        }

        service.delete(id);
        return ResponseEntity.ok("Grupo con ID " + id + " eliminado correctamente.");
    }
}
