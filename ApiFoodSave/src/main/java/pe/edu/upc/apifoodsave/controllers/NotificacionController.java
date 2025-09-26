package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.NotificacionInsertDTO;
import pe.edu.upc.apifoodsave.dtos.NotificacionListDTO;
import pe.edu.upc.apifoodsave.entities.Notificacion;
import pe.edu.upc.apifoodsave.repositories.IInventarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.INotificacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    @Autowired
    private INotificacionService service;
    @Autowired
    private IInventarioRepository inventarioRepository;

    @PostMapping("/nuevos")
    public void insertar(@RequestBody NotificacionInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Notificacion n = m.map(dto, Notificacion.class);
        if (dto.getIdInventario() > 0) {
            n.setInventario(inventarioRepository.getReferenceById(dto.getIdInventario()));
        }
        service.insert(n);
    }

    @GetMapping("/listas")
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
}
