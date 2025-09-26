package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<FavoritoRecetaDTOList> listar() {
        return service.list().stream().map(f -> {
            FavoritoRecetaDTOList dto = new FavoritoRecetaDTOList();
            dto.setIdFavorito(f.getIdFavorito());
            if (f.getReceta() != null) {
                dto.setIdReceta(f.getReceta().getIdReceta());
                dto.setTituloReceta(f.getReceta().getTitulo());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        service.delete(id);
    }
}
