package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.DonacionInsertDTO;
import pe.edu.upc.apifoodsave.dtos.DonacionListDTO;
import pe.edu.upc.apifoodsave.dtos.DonacionUpdateDTO;
import pe.edu.upc.apifoodsave.entities.Donacion;
import pe.edu.upc.apifoodsave.repositories.IInventarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IDonacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/donaciones")
public class DonacionController {
    @Autowired
    private IDonacionService service;
    @Autowired
    private IInventarioRepository inventarioRepository;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody DonacionInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Donacion d = m.map(dto, Donacion.class);
        if (dto.getIdInventario() > 0) {
            d.setInventario(inventarioRepository.getReferenceById(dto.getIdInventario()));
        }
        service.insert(d);
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<DonacionListDTO> listar() {
        return service.list().stream().map(d -> {
            DonacionListDTO dto = new DonacionListDTO();
            dto.setIdDonacion(d.getIdDonacion());
            dto.setUbicacion(d.getUbicacion());
            dto.setFechaProgramada(d.getFechaProgramada());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Donacion d = service.listId(id);
        if (d == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe donación " + id);
        DonacionUpdateDTO dto = new DonacionUpdateDTO();
        dto.setIdDonacion(d.getIdDonacion());
        dto.setUbicacion(d.getUbicacion());
        dto.setFechaProgramada(d.getFechaProgramada());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> editar(@RequestBody DonacionUpdateDTO dto) {
        ModelMapper m = new ModelMapper();
        Donacion d = m.map(dto, Donacion.class);
        Donacion existente = service.listId(d.getIdDonacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe donación " + d.getIdDonacion());
        }
        // mantener FK existente si no viene
        d.setInventario(existente.getInventario());
        service.edit(d);
        return ResponseEntity.ok("Donación " + d.getIdDonacion() + " actualizada.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable("id") int id) {
        Donacion d = service.listId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
}
