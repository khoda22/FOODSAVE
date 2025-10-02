package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.UsernameSinPasswordDTO;
import pe.edu.upc.apifoodsave.dtos.UsuarioDTO;
import pe.edu.upc.apifoodsave.entities.Usuario;
import pe.edu.upc.apifoodsave.servicesinterfaces.IUsuarioService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping
    public List<UsuarioDTO> Listar() {
        return uS.listar().stream().map( x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/listarsinpassword")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<UsernameSinPasswordDTO> ListarUsernameSinPassword()
    {
        List<String[]> lista = uS.ListarUsernameSinPassword();
        List<UsernameSinPasswordDTO> ListDTO=new ArrayList<>();
        for(String[] columna:lista){
            UsernameSinPasswordDTO dto=new UsernameSinPasswordDTO();
            dto.setIdUsuario(Integer.parseInt(columna[0]));
            dto.setEmail(columna[1]);
            dto.setFoto(columna[2]);
            dto.setUbicacion(columna[4]);
            dto.setLogin(LocalDateTime.parse(columna[5]));
            dto.setCreacion(LocalDateTime.parse(columna[6]));
            ListDTO.add(dto);
        }
        return ListDTO;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void Registrar(@RequestBody UsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto,Usuario.class);
        uS.Registrar(u);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public UsuarioDTO Listarporid(@PathVariable("id") int id){
        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(uS.listarporid(id),UsuarioDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void Modificar(@RequestBody UsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto,Usuario.class);
        uS.Modificar(u);
    }

    @DeleteMapping( "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")

    public void Eliminar(@PathVariable("id") int id){
        uS.Eliminar(id);
    }

}
