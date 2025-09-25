package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.ActividadUsuarioDTO;
import pe.edu.upc.apifoodsave.entities.ActividadUsuario;
import pe.edu.upc.apifoodsave.servicesinterfaces.IActividadUsuarioService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actividadUsuarios")
public class ActividadUsuariosController {
    @Autowired
    private IActividadUsuarioService auS;
    @GetMapping
    public List<ActividadUsuarioDTO> Listar() {
        return auS.listar().stream().map( x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, ActividadUsuarioDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void Registrar(@RequestBody ActividadUsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        ActividadUsuario au = m.map(dto, ActividadUsuario.class);
        auS.Registrar(au);
    }
    @GetMapping("/{id}")
    public ActividadUsuarioDTO Listarporid(@PathVariable("id") int id){
        ModelMapper m = new ModelMapper();
        ActividadUsuarioDTO dto = m.map(auS.listarporid(id), ActividadUsuarioDTO.class);
        return dto;
    }
    @PutMapping
    public void Modificar(@RequestBody ActividadUsuario dto){
        ModelMapper m = new ModelMapper();
        ActividadUsuario fa = m.map(dto, ActividadUsuario.class);
        auS.Modificar(fa);

    }
    @DeleteMapping( "/{id}")
    public void Eliminar(@PathVariable("id") int id){
        auS.Eliminar(id);
    }
}
