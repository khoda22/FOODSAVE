package pe.edu.upc.apifoodsave.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.apifoodsave.dtos.IngredienteRecetaDTOInsert;
import pe.edu.upc.apifoodsave.dtos.IngredienteRecetaDTOList;
import pe.edu.upc.apifoodsave.entities.IngredienteReceta;
import pe.edu.upc.apifoodsave.repositories.IProductoRepository;
import pe.edu.upc.apifoodsave.repositories.IRecetaRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IIngredienteRecetaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteRecetaController {
    @Autowired
    private IIngredienteRecetaService service;
    @Autowired
    private IRecetaRepository recetaRepository;
    @Autowired
    private IProductoRepository productoRepository;

    @PostMapping("/nuevos")
    public void insertar(@RequestBody IngredienteRecetaDTOInsert dto) {
        IngredienteReceta ir = new IngredienteReceta();
        ir.setCantidadProductos(dto.getCantidadProductos());
        ir.setUnidad(dto.getUnidad());
        ir.setNota(dto.getNota());
        if (dto.getIdReceta() != null && dto.getIdReceta() > 0) {
            ir.setReceta(recetaRepository.getReferenceById(dto.getIdReceta()));
        }
        if (dto.getIdProducto() != null && dto.getIdProducto() > 0) {
            ir.setProducto(productoRepository.getReferenceById(dto.getIdProducto()));
        }
        service.insert(ir);
    }

    @GetMapping("/listas")
    public List<IngredienteRecetaDTOList> listar() {
        return service.list().stream().map(ir -> {
            IngredienteRecetaDTOList dto = new IngredienteRecetaDTOList();
            dto.setIdIngredienteReceta(ir.getIdIngredienteReceta());
            if (ir.getProducto() != null) {
                dto.setIdProducto(ir.getProducto().getIdProducto());
                dto.setNombreProducto(ir.getProducto().getNombre());
            }
            dto.setCantidadProductos(ir.getCantidadProductos());
            dto.setUnidad(ir.getUnidad());
            dto.setNota(ir.getNota());
            return dto;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id) {
        service.delete(id);
    }
}
