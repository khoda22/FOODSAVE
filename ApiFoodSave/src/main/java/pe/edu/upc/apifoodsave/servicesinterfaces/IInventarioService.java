package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Inventario;
import pe.edu.upc.apifoodsave.entities.Producto;

import java.util.List;

public interface IInventarioService {
    public List<Inventario> list();
    public void insert(Inventario p);
    public Inventario listId(int id);
    public void delete(int id);
    public void edit(Inventario p);
}
