package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.ActividadUsuarioDTOInsert;
import pe.edu.upc.apifoodsave.dtos.ActividadUsuarioDTOUpdate;
import pe.edu.upc.apifoodsave.entities.ActividadUsuario;
import pe.edu.upc.apifoodsave.entities.Usuario;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IActividadUsuarioService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actividadUsuarios")
public class ActividadUsuariosController {
    @Autowired
    private IActividadUsuarioService auS;
    @Autowired
    private IUsuarioRepository usuarioRepo;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<ActividadUsuarioDTOUpdate> Listar() {
        return auS.listar().stream().map( x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, ActividadUsuarioDTOUpdate.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> Registrar(@RequestBody ActividadUsuarioDTOInsert dto){

        // Resolver usuario (evita getReferenceById que lanza si no existe)
        Usuario usuario = usuarioRepo.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + dto.getIdUsuario()));

        // Mapear “a mano” (evita problemas de ModelMapper con FKs)
        ActividadUsuario au = new ActividadUsuario();
        au.setPreferenciasjson(dto.getPreferenciasjson());
        au.setTipoAccion(dto.getTipoAccion());
        au.setDescripcion(dto.getDescripcion());
        au.setUsuario(usuario);
        au.setFechaCreacion(dto.getFechaCreacion()); // si viene null, @PrePersist pondrá ahora

        // Guardar
        auS.Registrar(au);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registrado correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ActividadUsuarioDTOUpdate Listarporid(@PathVariable("id") int id){
        ModelMapper m = new ModelMapper();
        ActividadUsuarioDTOUpdate dto = m.map(auS.listarporid(id), ActividadUsuarioDTOUpdate.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> Modificar(@RequestBody ActividadUsuarioDTOUpdate dto){
        ModelMapper m = new ModelMapper();
        ActividadUsuario fa = m.map(dto, ActividadUsuario.class);
        auS.Modificar(fa);

        return ResponseEntity.ok("Modificado correctamente.");
    }

    @DeleteMapping( "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void Eliminar(@PathVariable("id") int id){
        auS.Eliminar(id);
    }
}
