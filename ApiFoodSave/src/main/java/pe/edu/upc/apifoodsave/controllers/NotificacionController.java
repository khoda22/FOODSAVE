package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.NotificacionInsertDTO;
import pe.edu.upc.apifoodsave.dtos.NotificacionListDTO;
import pe.edu.upc.apifoodsave.entities.Inventario;
import pe.edu.upc.apifoodsave.entities.Notificacion;
import pe.edu.upc.apifoodsave.repositories.IInventarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.INotificacionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private static final int DIAS_ANTES_POR_VENCER = 3;   // simple y claro

    @Autowired private INotificacionService service;
    @Autowired private IInventarioRepository inventarioRepository;

    /*
    // ============ EXISTENTES ============
    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public void insertar(@RequestBody NotificacionInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Notificacion n = m.map(dto, Notificacion.class);
        if (dto.getIdInventario() > 0) {
            n.setInventario(inventarioRepository.getReferenceById(dto.getIdInventario()));
        }
        service.insert(n);
    }

     */

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<NotificacionListDTO> listar() {
        return service.list().stream().map(n -> {
            NotificacionListDTO dto = new NotificacionListDTO();
            dto.setIdNotificacion(n.getIdNotificacion());
            dto.setTipo(n.isTipo());
            dto.setMensaje(n.getMensaje());
            dto.setFechaProgramada(n.getFechaProgramada());
            return dto;
        }).collect(Collectors.toList());
    }

    // ============ NUEVO: AUTOGENERAR/ACTUALIZAR POR INVENTARIO ============
    @PostMapping("/auto/{inventarioId}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> autogenerar(@PathVariable int inventarioId) {
        Inventario inv = inventarioRepository.findById(inventarioId).orElse(null);
        if (inv == null || inv.getFechavencimientoInventario() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Inventario no encontrado o sin fecha de vencimiento.");
        }

        LocalDate venc = inv.getFechavencimientoInventario();
        LocalDateTime ahora = LocalDateTime.now();

        // 1) POR_VENCER = true (3 días antes del vencimiento, solo si aún no pasó)
        LocalDateTime fechaPorVencer = venc.minusDays(DIAS_ANTES_POR_VENCER).atStartOfDay();
        if (fechaPorVencer.isAfter(ahora)) {
            upsertNotificacion(inv, true,
                    "El inventario ID " + inv.getIdInventario() + " está por vencer el " + venc,
                    fechaPorVencer);
        }

        // 2) VENCIDO = false (un día después del vencimiento)
        LocalDateTime fechaVencido = venc.plusDays(1).atStartOfDay();

        // Si ya pasó esa fecha, programa la notificación "ahora"
        if (fechaVencido.isBefore(ahora)) {
            fechaVencido = ahora;
        }

        upsertNotificacion(inv, false,
                "El inventario ID " + inv.getIdInventario() + " se encuentra vencido desde " + venc,
                fechaVencido);

        return ResponseEntity.ok("Notificaciones actualizadas para inventario " + inventarioId);
    }

    // ============ HELPER PRIVADO (lógica de upsert) ============
    private void upsertNotificacion(Inventario inv, boolean tipo, String mensaje, LocalDateTime fechaProgramada) {
        // Buscar existente por (inventario, tipo)
        Optional<Notificacion> opt = service.findByInventarioIdAndTipo(inv.getIdInventario(), tipo);

        if (opt.isPresent()) {
            // Update
            Notificacion n = opt.get();
            n.setMensaje(mensaje);
            n.setFechaProgramada(fechaProgramada);
            // Reusar insert() para persistir (save hace merge/update)
            service.insert(n);
        } else {
            // Insert
            Notificacion n = new Notificacion();
            n.setInventario(inv);
            n.setTipo(tipo); // true=POR_VENCER, false=VENCIDO
            n.setMensaje(mensaje);
            n.setFechaProgramada(fechaProgramada);
            service.insert(n);
        }
    }
}
