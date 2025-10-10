package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.EscaneoInsertDTO;
import pe.edu.upc.apifoodsave.dtos.EscaneoListDTO;
import pe.edu.upc.apifoodsave.entities.Escaneo;
import pe.edu.upc.apifoodsave.repositories.IEscaneoRepository;
import pe.edu.upc.apifoodsave.repositories.IProductoRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IEscaneoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/escaneos")
public class EscaneoController {
    @Autowired
    private IEscaneoRepository escaneoRepository;
    @Autowired
    private IEscaneoService service;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IProductoRepository productoRepository;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody EscaneoInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Escaneo e = m.map(dto, Escaneo.class);
        if (dto.getIdUsuario() > 0) {
            e.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        }
        if (dto.getIdProducto() > 0) {
            e.setProducto(productoRepository.getReferenceById(dto.getIdProducto()));
        }
        service.insert(e);
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
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

    @GetMapping("/ultimos")
    public ResponseEntity<?> obtenerEscaneosUltimosDias(@RequestParam(defaultValue = "7") int dias) {
        LocalDate fechaInicio = LocalDate.now().minusDays(dias);
        List<Escaneo> lista = escaneoRepository.findEscaneosUltimosDias(fechaInicio);

        if (lista.isEmpty()) {
            return ResponseEntity.ok("No se encontraron escaneos en los últimos " + dias + " días.");
        }

        return ResponseEntity.ok(lista);
    }
}
