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
import pe.edu.upc.apifoodsave.entities.Usuario;
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
    public ResponseEntity<?> insertar(@RequestBody InventarioDTOInsert dto) {

        Inventario inv = new Inventario();
        inv.setCantidadInventario(dto.getCantidadInventario());
        inv.setDiasduracionInventario(dto.getDiasduracionInventario());

        // Ignorar campos derivados si vinieran en el DTO (defender la regla de negocio)
        inv.setFechavencimientoInventario(null);
        inv.setEstadoInventario(null);
        inv.setFechacreacionInventario(null); // se fijará en @PrePersist si viene null

        // Mapear relaciones por id (ModelMapper no setea nested por id automáticamente)
        Usuario u = new Usuario();
        u.setIdUsuario(dto.getIdUsuario());
        inv.setUsuario(u);

        Producto p = new Producto();
        p.setIdProducto(dto.getIdProducto());
        inv.setProducto(p);

        service.insert(inv);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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

        Inventario existente = service.listId(dto.getIdInventario());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dto.getIdInventario());
        }

        // Actualizar campos editables
        existente.setCantidadInventario(dto.getCantidadInventario());
        existente.setDiasduracionInventario(dto.getDiasduracionInventario());

        // Si se cambian relaciones:
        if (dto.getIdProducto() != 0) {
            Producto p = new Producto();
            p.setIdProducto(dto.getIdProducto());
            existente.setProducto(p);
        }

        if (dto.getIdUsuario() != 0) {
            Usuario u = new Usuario();
            u.setIdUsuario(dto.getIdUsuario());
            existente.setUsuario(u);
        }

        // Recalcular vencimiento y estado
        existente.recomputarFechasYEstado();

        // Guardar cambios
        service.edit(existente);
        return ResponseEntity.ok("Inventario con ID " + dto.getIdInventario() + " modificado y recalculado correctamente.");
    }
}
