package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.ProductoDTOInsert;
import pe.edu.upc.apifoodsave.dtos.ProductoDTOList;
import pe.edu.upc.apifoodsave.dtos.ProductoDTOUpdate;
import pe.edu.upc.apifoodsave.entities.Producto;
import pe.edu.upc.apifoodsave.servicesinterfaces.IProductoService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private IProductoService service;
    //US14: Ingresar producto manuelamente si no está en la base de datos
    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<ProductoDTOList> listar(){
        return service.list().stream().map(a->{
            ModelMapper m=new ModelMapper();
            return m.map(a,ProductoDTOList.class);
        }).collect(Collectors.toList());
    }
    //US13: Reconoce nombres, marca y fecha de caducidad automaticamente
    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> insertar(@RequestBody ProductoDTOInsert dto) {
        ModelMapper m = new ModelMapper();
        Producto nuevoProducto = m.map(dto, Producto.class);
        List<Producto> productosExistentes = service.list();
        for (Producto p : productosExistentes) {
            if (p.getNombre().equalsIgnoreCase(nuevoProducto.getNombre())) {
                return ResponseEntity
                        .badRequest()
                        .body("El producto con el nombre '" + nuevoProducto.getNombre() + "' ya existe.");
            }
            if (p.getCodigoBarra() == nuevoProducto.getCodigoBarra()) {
                return ResponseEntity
                        .badRequest()
                        .body("El producto con el código de barras '" + nuevoProducto.getCodigoBarra() + "' ya existe.");
            }
        }

        service.insert(nuevoProducto);
        return ResponseEntity.ok("Producto '" + nuevoProducto.getNombre() + "' registrado correctamente.");
    }


    //US14: Ingresar producto manuelamente si no está en la base de datos
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
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

    //US32: Eliminar producto agregado manualmente
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Producto p = service.listId(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
    //US31: Modificar producto agregado manualmente
    @PutMapping("/edit")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
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
    @GetMapping("/buscar/{nombre}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<ProductoDTOList> buscarPorNombre(@PathVariable("nombre") String nombre) {
        return service.buscarPorNombre(nombre)
                .stream()
                .map(p -> new ModelMapper().map(p, ProductoDTOList.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/reconocer/{codigo}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> reconocerProducto(@PathVariable("codigo") Integer codigo) {
        List<Producto> productos = service.buscarPorCodigoBarra(codigo);

        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un producto con el código de barras: " + codigo);
        }

        ModelMapper m = new ModelMapper();
        List<ProductoDTOList> dtoList = productos.stream()
                .map(p -> m.map(p, ProductoDTOList.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }
    //US19
    @GetMapping("/sugerencias/{codigo}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<List<ProductoDTOList>> sugerencias(@PathVariable Integer codigo) {
        Producto p = service.listId(codigo);
        if (p != null) {
            // Si encuentra el producto, no hay sugerencias
            return ResponseEntity.ok(Collections.emptyList());
        }

        String termino = codigo.toString(); // o extraer algo del código
        List<Producto> sugerencias = service.sugerirProductosSimilares(termino);

        List<ProductoDTOList> dtoList = sugerencias.stream()
                .map(prod -> new ModelMapper().map(prod, ProductoDTOList.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }
}
