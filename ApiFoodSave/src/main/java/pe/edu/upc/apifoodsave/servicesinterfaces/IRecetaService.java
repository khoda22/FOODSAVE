package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Receta;

import java.util.List;

public interface IRecetaService {
    public void insert(Receta r);
    public List<Receta> list();
    public Receta listId(int id);
    public void edit(Receta r);
    public void delete(int id);
}
