package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.InventarioDTODetail;
import pe.edu.upc.apifoodsave.dtos.InventarioDTOInsert;
import pe.edu.upc.apifoodsave.dtos.InventarioDTOUpdate;
import pe.edu.upc.apifoodsave.dtos.InventarioDTOList;
//import pe.edu.upc.apifoodsave.dtos.ProductoDTO;
import pe.edu.upc.apifoodsave.entities.Inventario;
import pe.edu.upc.apifoodsave.entities.Producto;
import pe.edu.upc.apifoodsave.servicesinterfaces.IInventarioService;
import pe.edu.upc.apifoodsave.servicesinterfaces.IProductoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventario")
public class InventarioControllers {
    @Autowired
    private IInventarioService service;

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<InventarioDTOList> listar(){
        return service.list().stream().map(a->{
            ModelMapper m=new ModelMapper();
            return m.map(a,InventarioDTOList.class);
        }).collect(Collectors.toList());
    }
    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody InventarioDTOInsert dto) {
        ModelMapper m=new ModelMapper();
        Inventario inv=m.map(dto,Inventario.class);
        service.insert(inv);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Inventario inv = service.listId(id);
        if (inv == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        InventarioDTOList dto = m.map(inv, InventarioDTOList.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Inventario i = service.listId(id);
        if (i == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> modificar(@RequestBody InventarioDTOUpdate dto) {
        ModelMapper m = new ModelMapper();
        Inventario p = m.map(dto, Inventario.class);
        Inventario existente = service.listId(p.getIdInventario());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + p.getIdInventario());
        }
        service.edit(p);
        return ResponseEntity.ok("Registro con ID " + p.getIdInventario() + " modificado correctamente.");
    }
}
