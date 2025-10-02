package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.CalificacionRecetaDTOInsert;
import pe.edu.upc.apifoodsave.dtos.CalificacionRecetaDTOList;
import pe.edu.upc.apifoodsave.entities.CalificacionReceta;
import pe.edu.upc.apifoodsave.repositories.IRecetaRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.ICalificacionRecetaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionRecetaController {
    @Autowired
    private ICalificacionRecetaService service;
    @Autowired
    private IRecetaRepository recetaRepository;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody CalificacionRecetaDTOInsert dto) {
        CalificacionReceta c = new CalificacionReceta();
        c.setCalificacion(dto.getCalificacion());
        if (dto.getIdReceta() != null && dto.getIdReceta() > 0) {
            c.setReceta(recetaRepository.getReferenceById(dto.getIdReceta()));
        }
        service.insert(c);
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<CalificacionRecetaDTOList> listar() {
        return service.list().stream().map(c -> {
            CalificacionRecetaDTOList dto = new CalificacionRecetaDTOList();
            dto.setIdCalificacionReceta(c.getIdCalificacionReceta());
            dto.setCalificacion(c.getCalificacion());
            if (c.getReceta() != null) {
                dto.setIdReceta(c.getReceta().getIdReceta());
                dto.setTituloReceta(c.getReceta().getTitulo());
            }
            return dto;
        }).collect(Collectors.toList());
    }
}
