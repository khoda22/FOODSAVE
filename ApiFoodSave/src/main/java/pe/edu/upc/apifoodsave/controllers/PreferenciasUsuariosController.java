package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.PreferenciasUsuarioDTO;
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
    public List<PreferenciasUsuarioDTO> Listar() {
        return puS.listar().stream().map( x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, PreferenciasUsuarioDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void Registrar(@RequestBody PreferenciasUsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        PreferenciasUsuario pu = m.map(dto, PreferenciasUsuario.class);
        puS.Registrar(pu);
    }
    @GetMapping("/{id}")
    public PreferenciasUsuarioDTO Listarporid(@PathVariable("id") int id){
        ModelMapper m = new ModelMapper();
        PreferenciasUsuarioDTO dto = m.map(puS.listarporid(id), PreferenciasUsuarioDTO.class);
        return dto;
    }
    @PutMapping
    public void Modificar(@RequestBody PreferenciasUsuario dto){
        ModelMapper m = new ModelMapper();
        PreferenciasUsuario fa = m.map(dto, PreferenciasUsuario.class);
        puS.Modificar(fa);

    }
    @DeleteMapping( "/{id}")
    public void Eliminar(@PathVariable("id") int id){
        puS.Eliminar(id);
    }
}
