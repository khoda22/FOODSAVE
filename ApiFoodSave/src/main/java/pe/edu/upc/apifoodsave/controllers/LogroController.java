package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.LogroInsertDTO;
import pe.edu.upc.apifoodsave.dtos.LogroListDTO;
import pe.edu.upc.apifoodsave.dtos.LogroUpdateDTO;
import pe.edu.upc.apifoodsave.entities.Logro;
import pe.edu.upc.apifoodsave.servicesinterfaces.ILogroService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logro")
public class LogroController {
    @Autowired
    private ILogroService service;

    @PostMapping("/nuevos")
    public void insertar(@RequestBody LogroInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Logro l = m.map(dto, Logro.class);
        service.insert(l);
    }

    @GetMapping("/listas")
    public List<LogroListDTO> listar() {
        return service.list().stream().map(l -> {
            ModelMapper m = new ModelMapper();
            return m.map(l, LogroListDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Logro existente = service.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede eliminar. No existe un logro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Logro con ID " + id + " eliminado correctamente.");
    }
}
