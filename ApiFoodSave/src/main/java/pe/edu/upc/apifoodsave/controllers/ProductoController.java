package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.ProductoDTOInsert;
import pe.edu.upc.apifoodsave.dtos.ProductoDTOList;
import pe.edu.upc.apifoodsave.dtos.ProductoDTOUpdate;
import pe.edu.upc.apifoodsave.entities.Producto;
import pe.edu.upc.apifoodsave.servicesinterfaces.IProductoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private IProductoService service;

    @GetMapping("/listas")
    public List<ProductoDTOList> listar(){
        return service.list().stream().map(a->{
            ModelMapper m=new ModelMapper();
            return m.map(a,ProductoDTOList.class);
        }).collect(Collectors.toList());
    }
    @PostMapping("/nuevos")
    public void insertar(@RequestBody ProductoDTOInsert dto) {
        ModelMapper m=new ModelMapper();
        Producto prov=m.map(dto,Producto.class);
        service.insert(prov);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Producto prov = service.listId(id);
        if (prov == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ProductoDTOList dto = m.map(prov, ProductoDTOList.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Producto p = service.listId(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
    @PutMapping("/edit")
    public ResponseEntity<String> modificar(@RequestBody ProductoDTOUpdate dto) {
        ModelMapper m = new ModelMapper();
        Producto p = m.map(dto, Producto.class);
        Producto existente = service.listId(p.getIdProducto());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + p.getIdProducto());
        }
        service.edit(p);
        return ResponseEntity.ok("Registro con ID " + p.getIdProducto() + " modificado correctamente.");
    }
}
