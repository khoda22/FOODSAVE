package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.RolDTO;
import pe.edu.upc.apifoodsave.entities.Rol;
import pe.edu.upc.apifoodsave.servicesinterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/rol")
public class RolController {
    @Autowired
    private IRolService rS;
    @GetMapping
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<RolDTO> Listar() {
        return rS.listar().stream().map( x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,RolDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')") // <-- usa hasRole si tus authorities tienen prefijo ROLE_
    public ResponseEntity<String> Registrar(@RequestBody RolDTO dto){
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);

        // ¡Clave!: asegurar que sea INSERT
        r.setIdRol(null);  // ignorar cualquier id que venga en el JSON

        rS.Registrar(r);
        return ResponseEntity.status(HttpStatus.CREATED).body("Rol registrado correctamente.");
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public RolDTO Listarporid(@PathVariable("id") int id){
        ModelMapper m = new ModelMapper();
        RolDTO dto = m.map(rS.listarporid(id),RolDTO.class);
        return dto;
    }

    @PutMapping
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String> modificar(@RequestBody RolDTO dto) {
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);
        Rol existente = rS.listarporid(r.getIdRol());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un rol con el ID: " + r.getIdRol());
        }
        rS.Modificar(r);
        return ResponseEntity.ok("Rol con ID " + r.getIdRol() + " modificado correctamente.");
    }

    @DeleteMapping( "/{id}")
    //@PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void Eliminar(@PathVariable("id") int id){
        rS.Eliminar(id);
    }
}
