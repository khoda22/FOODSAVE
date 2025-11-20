package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.EscaneoInsertDTO;
import pe.edu.upc.apifoodsave.dtos.EscaneoListDTO;
import pe.edu.upc.apifoodsave.entities.Escaneo;
import pe.edu.upc.apifoodsave.entities.Producto;
import pe.edu.upc.apifoodsave.entities.Usuario;
import pe.edu.upc.apifoodsave.repositories.IProductoRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IEscaneoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/escaneos")
public class EscaneoController {
    @Autowired private IEscaneoService service;
    @Autowired private IUsuarioRepository usuarioRepository;
    @Autowired private IProductoRepository productoRepository;

    @PostMapping("/nuevos")
    //@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> insertar(@RequestBody EscaneoInsertDTO dto) {
        // 1) validar origen
        String origen = dto.getOrigen() != null ? dto.getOrigen().toUpperCase() : "";
        if (!origen.equals("QR") && !origen.equals("BARRAS") && !origen.equals("MANUAL")) {
            return ResponseEntity.badRequest().body("Origen inválido. Use: QR, BARRAS o MANUAL.");
        }

        // 2) validar usuario
        if (dto.getIdUsuario() == null || dto.getIdUsuario() <= 0) {
            return ResponseEntity.badRequest().body("idUsuario es requerido.");
        }
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + dto.getIdUsuario()));

        // 3) resolver producto
        Producto producto = null;

        boolean tieneId = dto.getIdProducto() != null && dto.getIdProducto() > 0;
        boolean tieneCB = dto.getCodigoBarra() != null && !dto.getCodigoBarra().isBlank();

        if (!tieneId && !tieneCB) {
            return ResponseEntity.badRequest().body("Debe enviar idProducto o codigoBarra.");
        }

        if (tieneCB) {
            producto = productoRepository.findByCodigoBarra(dto.getCodigoBarra().trim())
                    .orElse(null);
            if (producto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Producto no encontrado para el código de barras proporcionado. Regístrelo primero en /productos.");
            }
        }

        if (tieneId) {
            Producto porId = productoRepository.findById(dto.getIdProducto())
                    .orElse(null);
            if (porId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Producto no encontrado con idProducto=" + dto.getIdProducto());
            }
            // si llegaron ambos, verificar consistencia
            if (producto != null && porId.getIdProducto() != producto.getIdProducto()) {
                return ResponseEntity.badRequest()
                        .body("idProducto y codigoBarra no corresponden al mismo producto.");
            }
            // si venía solo id, usar ese
            if (producto == null) producto = porId;
        }

        // 4) construir y guardar
        Escaneo e = new Escaneo();
        e.setOrigen(origen);
        e.setUsuario(usuario);
        e.setProducto(producto);
        e.setFechaEscaneo(dto.getFechaEscaneo()); // si null, @PrePersist pone hoy

        service.insert(e);
        return ResponseEntity.status(HttpStatus.CREATED).body("Escaneo registrado correctamente.");
    }

    @GetMapping("/listas")
    //@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<EscaneoListDTO> listar() {
        return service.list().stream().map(e -> {
            EscaneoListDTO dto = new EscaneoListDTO();
            dto.setIdEscaneo(e.getIdEscaneo());
            dto.setFechaEscaneo(e.getFechaEscaneo());
            dto.setOrigen(e.getOrigen());
            if (e.getProducto() != null) {
                dto.setIdProducto(e.getProducto().getIdProducto());
                dto.setNombreProducto(e.getProducto().getNombre());
            }
            return dto;
        }).collect(Collectors.toList());
    }
}
