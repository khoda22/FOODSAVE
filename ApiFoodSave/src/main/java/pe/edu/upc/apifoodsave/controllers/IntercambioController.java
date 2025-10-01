package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.IntercambioDTOInsert;
import pe.edu.upc.apifoodsave.dtos.IntercambioDTOList;
import pe.edu.upc.apifoodsave.dtos.IntercambioDTOUpdate;
import pe.edu.upc.apifoodsave.entities.Intercambio;
import pe.edu.upc.apifoodsave.servicesinterfaces.IIntercambioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/intercambios")
public class IntercambioController {
    @Autowired
    private IIntercambioService service;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody IntercambioDTOInsert dto) {
        ModelMapper m = new ModelMapper();
        Intercambio i = m.map(dto, Intercambio.class);
        service.insert(i);
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<IntercambioDTOList> listar() {
        return service.list().stream().map(i -> {
            IntercambioDTOList dto = new IntercambioDTOList();
            dto.setIdIntercambio(i.getIdIntercambio());
            dto.setTitulo(i.getTitulo());
            dto.setFecha(i.getFecha());
            dto.setUbicacion(i.getUbicacion());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Intercambio i = service.listId(id);
        if (i == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe intercambio " + id);
        ModelMapper m = new ModelMapper();
        IntercambioDTOUpdate dto = m.map(i, IntercambioDTOUpdate.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> editar(@RequestBody IntercambioDTOUpdate dto) {
        ModelMapper m = new ModelMapper();
        Intercambio i = m.map(dto, Intercambio.class);
        Intercambio existente = service.listId(i.getIdIntercambio());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe intercambio " + i.getIdIntercambio());
        }
        service.edit(i);
        return ResponseEntity.ok("Intercambio " + i.getIdIntercambio() + " actualizado.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable("id") int id) {
        Intercambio i = service.listId(id);
        if (i == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
}
