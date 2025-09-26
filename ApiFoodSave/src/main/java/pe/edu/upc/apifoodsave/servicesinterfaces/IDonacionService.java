package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Donacion;

import java.util.List;

public interface IDonacionService {
    public void insert(Donacion d);
    public List<Donacion> list();
    public Donacion listId(int id);
    public void edit(Donacion d);
    public void delete(int id);
}
