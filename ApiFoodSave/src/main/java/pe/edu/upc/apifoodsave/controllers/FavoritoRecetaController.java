package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.FavoritoRecetaDTOInsert;
import pe.edu.upc.apifoodsave.dtos.FavoritoRecetaDTOList;
import pe.edu.upc.apifoodsave.entities.FavoritoReceta;
import pe.edu.upc.apifoodsave.repositories.IRecetaRepository;
import pe.edu.upc.apifoodsave.repositories.IUsuarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IFavoritoRecetaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favoritos")
public class FavoritoRecetaController {
    @Autowired
    private IFavoritoRecetaService service;
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IRecetaRepository recetaRepository;

    @PostMapping("/nuevos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public void insertar(@RequestBody FavoritoRecetaDTOInsert dto) {
        FavoritoReceta f = new FavoritoReceta();
        if (dto.getIdUsuario() != null) {
            f.setUsuario(usuarioRepository.getReferenceById(dto.getIdUsuario()));
        }
        if (dto.getIdReceta() != null) {
            f.setReceta(recetaRepository.getReferenceById(dto.getIdReceta()));
        }
        service.insert(f);
    }

    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR')")
    public List<FavoritoRecetaDTOList> listar() {
        return service.list().stream().map(f -> {
            FavoritoRecetaDTOList dto = new FavoritoRecetaDTOList();
            dto.setIdFavorito(f.getIdFavorito());
            if (f.getReceta() != null) {
                dto.setIdReceta(f.getReceta().getIdReceta());
                dto.setTituloReceta(f.getReceta().getTitulo());
            }
            if (f.getUsuario() != null) {
                dto.setIdUsuario(f.getUsuario().getIdUsuario());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','PROGRAMADOR','CLIENTE')")
    public ResponseEntity<String> eliminar(@PathVariable("id") int id) {
        FavoritoReceta f = service.listId(id);
        if (f == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
}
