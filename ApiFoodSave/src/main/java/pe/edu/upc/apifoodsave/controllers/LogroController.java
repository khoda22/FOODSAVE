package pe.edu.upc.apifoodsave.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.LogroInsertDTO;
import pe.edu.upc.apifoodsave.dtos.LogroInsigniaDTO;
import pe.edu.upc.apifoodsave.dtos.LogroListDTO;
import pe.edu.upc.apifoodsave.dtos.LogroUpdateDTO;
import pe.edu.upc.apifoodsave.entities.Logro;
import pe.edu.upc.apifoodsave.servicesinterfaces.ILogroService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logro")
public class LogroController {
    @Autowired
    private ILogroService service;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody LogroInsertDTO dto) {
        ModelMapper m = new ModelMapper();
        Logro l = m.map(dto, Logro.class);
        service.insert(l);
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public List<LogroListDTO> listar() {
        return service.list().stream().map(l -> {
            ModelMapper m = new ModelMapper();
            return m.map(l, LogroListDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Logro l = service.listId(id);
        if (l == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un logro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        LogroUpdateDTO dto = m.map(l, LogroUpdateDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> editar(@RequestBody LogroUpdateDTO dto) {
        ModelMapper m = new ModelMapper();
        Logro l = m.map(dto, Logro.class);

        Logro existente = service.listId(l.getIdLogro());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un logro con el ID: " + l.getIdLogro());
        }

        service.edit(l);
        return ResponseEntity.ok("Logro con ID " + l.getIdLogro() + " modificado correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Logro existente = service.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede eliminar. No existe un logro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Logro con ID " + id + " eliminado correctamente.");
    }

    @GetMapping("/Insignia")
    public ResponseEntity<?>  listarInsignia() {
        List<String[]> insignias=service.InsigniasService();
        List<LogroInsigniaDTO> listarInsignias=new ArrayList<>();
        if (insignias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron Insignias");
        }
        for(String[] columna:insignias){
            LogroInsigniaDTO dto=new LogroInsigniaDTO();

            int idUsuario = Integer.parseInt(columna[0]);
            String nombre = columna[1];
            double kgSalvadosClasificacion = Double.parseDouble(columna[2]);
            int nombreLogro = Integer.parseInt(columna[3]);
            String descripcionLogro = columna[4];
            int puntosLogro = Integer.parseInt(columna[5]);

            dto.setIdUsuario(idUsuario);
            dto.setNombre(nombre);
            dto.setKgSalvadosClasificacion(kgSalvadosClasificacion);
            dto.setNombreLogro(nombreLogro);
            dto.setDescripcionLogro(descripcionLogro);
            dto.setPuntosLogro(puntosLogro);

            listarInsignias.add(dto);
        }
        return ResponseEntity.ok(listarInsignias);
    }
}
