package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.PreferenciasUsuarioDTO;
import pe.edu.upc.apifoodsave.dtos.PreferenciasUsuariosDTOInsert;
import pe.edu.upc.apifoodsave.entities.PreferenciasUsuario;
import pe.edu.upc.apifoodsave.servicesinterfaces.IPreferenciasUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/preferenciasUsuario")
public class PreferenciasUsuariosController {
    @Autowired
    private IPreferenciasUsuarioService puS;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<PreferenciasUsuarioDTO> Listar() {
        return puS.listar().stream().map( x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, PreferenciasUsuarioDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void Registrar(@RequestBody PreferenciasUsuariosDTOInsert dto){
        ModelMapper m = new ModelMapper();
        PreferenciasUsuario pu = m.map(dto, PreferenciasUsuario.class);
        puS.Registrar(pu);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public PreferenciasUsuarioDTO Listarporid(@PathVariable("id") int id){
        ModelMapper m = new ModelMapper();
        PreferenciasUsuarioDTO dto = m.map(puS.listarporid(id), PreferenciasUsuarioDTO.class);
        return dto;
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void Modificar(@RequestBody PreferenciasUsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        PreferenciasUsuario fa = m.map(dto, PreferenciasUsuario.class);
        puS.Modificar(fa);

    }
    @DeleteMapping( "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void Eliminar(@PathVariable("id") int id){
        puS.Eliminar(id);
    }
}
