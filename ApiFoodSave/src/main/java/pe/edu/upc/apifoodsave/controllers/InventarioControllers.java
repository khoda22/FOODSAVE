package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.*;
//import pe.edu.upc.apifoodsave.dtos.ProductoDTO;
import pe.edu.upc.apifoodsave.entities.Inventario;
import pe.edu.upc.apifoodsave.entities.Producto;
import pe.edu.upc.apifoodsave.entities.Usuario;
import pe.edu.upc.apifoodsave.servicesinterfaces.IInventarioService;
import pe.edu.upc.apifoodsave.servicesinterfaces.IProductoService;
import pe.edu.upc.apifoodsave.servicesinterfaces.IUsuarioService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventario")
public class InventarioControllers {
    @Autowired
    private IInventarioService service;
    @Autowired
    private IProductoService productoService;
    @Autowired
    private IUsuarioService usuarioService;
    private ModelMapper modelMapper = new ModelMapper();

    //US30: Sincronizar inventario entre dispositivos
    //US21: Visualizar productos ordenados por fecha de vencimiento
    //US25: Agrupar productos por categorías
    //US27: Filtrar inventario por estado
    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listar(
            @RequestParam(required = false) boolean agruparPorCategoria,
            @RequestParam(required = false) String estado) {

        // Filtrar inventario según el estado si se proporcionó
        List<InventarioDTOList> listaDTO = service.filtrarPorEstado(estado).stream().map(inv -> {
            ModelMapper m = new ModelMapper();
            InventarioDTOList dto = m.map(inv, InventarioDTOList.class);

            // Mapear manualmente el producto a ProductoDTOList
            ProductoDTOList prodDto = new ProductoDTOList();
            prodDto.setIdProducto(inv.getProducto().getIdProducto());
            prodDto.setNombre(inv.getProducto().getNombre());
            prodDto.setCategoria(inv.getProducto().getCategoria());
            prodDto.setCodigoBarra(String.valueOf(inv.getProducto().getCodigoBarra()));
            dto.setProducto(prodDto);

            return dto;
        }).collect(Collectors.toList());

        if (agruparPorCategoria) {
            Map<String, List<InventarioDTOList>> agrupado = listaDTO.stream()
                    .collect(Collectors.groupingBy(inv -> inv.getProducto().getCategoria()));
            return ResponseEntity.ok(agrupado);
        } else {
            return ResponseEntity.ok(listaDTO);
        }
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

    //US13: Reconoce nombres, marca y fecha de caducidad automaticamente
    //US26: Buscar producto en inventario
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
    //US33: Eliminar producto escaneado
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
    //US21: Visualizar productos ordenados por fecha de vencimiento
    @GetMapping("/listas/ordenadas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<List<InventarioDTOList>> listarOrdenados() {
        List<Inventario> inventario = service.listarOrdenadosPorFecha();
        ModelMapper mapper = new ModelMapper();
        List<InventarioDTOList> listaDTO = inventario.stream()
                .map(inv -> mapper.map(inv, InventarioDTOList.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }
    //US22
    @PutMapping("/cambiarEstado/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> cambiarEstado(@PathVariable("id") Integer id, @RequestParam("estado") String estado) {
        Inventario inv = service.listId(id);
        if (inv == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        // Validamos que el estado sea uno permitido
        if (!estado.equalsIgnoreCase("Vigente") &&
                !estado.equalsIgnoreCase("Consumido") &&
                !estado.equalsIgnoreCase("Donado") &&
                !estado.equalsIgnoreCase("Desechado")) {
            return ResponseEntity.badRequest().body("Estado inválido. Debe ser: Vigente, Consumido, Donado o Desechado.");
        }

        service.cambiarEstado(id, estado);
        return ResponseEntity.ok("Estado del inventario con ID " + id + " actualizado a " + estado);
    }
    @PutMapping("/cantidad/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> actualizarCantidad(@PathVariable("id") int id, @RequestParam int nuevaCantidad) {
        Inventario inv = service.listId(id);

        if (inv == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el producto con ID: " + id);
        }

        if (nuevaCantidad <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La cantidad debe ser mayor que cero. No se modificó el producto.");
        }

        // Actualizamos la cantidad
        inv.setCantidadInventario(nuevaCantidad);
        service.edit(inv); // guardamos los cambios
        return ResponseEntity.ok("Cantidad actualizada correctamente a " + nuevaCantidad);
    }
    @GetMapping("/buscar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> buscar(@RequestParam(required = false) String nombre,
                                    @RequestParam(required = false) String categoria) {
        List<InventarioDTOList> listaDTO = service.buscar(nombre, categoria).stream()
                .map(a -> new ModelMapper().map(a, InventarioDTOList.class))
                .collect(Collectors.toList());

        if(listaDTO.isEmpty()){
            return ResponseEntity.ok("No se encontraron productos");
        }

        return ResponseEntity.ok(listaDTO);
    }

}
