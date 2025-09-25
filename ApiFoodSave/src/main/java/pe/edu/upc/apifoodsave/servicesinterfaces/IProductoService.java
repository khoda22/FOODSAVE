package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> list();
    public void insert(Producto p);
    public Producto listId(int id);
    public void delete(int id);
    public void edit(Producto p);
}
