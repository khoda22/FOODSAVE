package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.PreferenciasUsuarioDTOUpdate;
import pe.edu.upc.apifoodsave.dtos.PreferenciasUsuariosDTOInsert;
import pe.edu.upc.apifoodsave.entities.PreferenciasUsuario;
import pe.edu.upc.apifoodsave.entities.Usuario;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IPreferenciasUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/preferenciasUsuario")
public class PreferenciasUsuariosController {
    @Autowired
    private IPreferenciasUsuarioService puS;
    @Autowired private IUsuarioRepository usuarioRepo;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<PreferenciasUsuarioDTOUpdate> Listar() {
        return puS.listar().stream().map( x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, PreferenciasUsuarioDTOUpdate.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> Registrar(@RequestBody PreferenciasUsuariosDTOInsert dto){

        // Resolver usuario (evita getReferenceById que lanza si no existe)
        Usuario usuario = usuarioRepo.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + dto.getIdUsuario()));

        // Mapear “a mano” (evita problemas de ModelMapper con FKs)
        PreferenciasUsuario pu = new PreferenciasUsuario();
        pu.setNotificacionesActivas(dto.getNotificacionesActivas());
        pu.setTemaVisual(dto.getTemaVisual());
        pu.setIdioma(dto.getIdioma());
        pu.setCanal(dto.getCanal());
        pu.setHoraInicio(dto.getHoraInicio());
        pu.setHoraFin(dto.getHoraFin());
        pu.setDiasAnticipacion(dto.getDiasAnticipacion());
        pu.setModoVacaciones(dto.getModoVacaciones());
        pu.setFechaInicioVacaciones(dto.getFechaInicioVacaciones());
        pu.setFechaFinVacaciones(dto.getFechaFinVacaciones());
        pu.setUsuario(usuario);

        // Guardar
        puS.Registrar(pu);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registrado correctamente.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public PreferenciasUsuarioDTOUpdate Listarporid(@PathVariable("id") int id){
        ModelMapper m = new ModelMapper();
        PreferenciasUsuarioDTOUpdate dto = m.map(puS.listarporid(id), PreferenciasUsuarioDTOUpdate.class);
        return dto;
    }

    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> Modificar(@RequestBody PreferenciasUsuarioDTOUpdate dto){
        ModelMapper m = new ModelMapper();
        PreferenciasUsuario fa = m.map(dto, PreferenciasUsuario.class);
        puS.Modificar(fa);
        return ResponseEntity.ok("Modificado correctamente.");
    }

    @DeleteMapping( "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void Eliminar(@PathVariable("id") int id){
        puS.Eliminar(id);
    }
}