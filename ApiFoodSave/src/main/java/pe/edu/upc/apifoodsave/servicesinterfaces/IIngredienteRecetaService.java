package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.IngredienteReceta;

import java.util.List;

public interface IIngredienteRecetaService {
    public void insert(IngredienteReceta ir);
    public List<IngredienteReceta> list();
    public void delete(int id);
}
