package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Publicacion;

import java.util.List;

public interface IPublicacionService {
    public void insert (Publicacion p);
    public List<Publicacion> list();
    public Publicacion listId (int id);
    public void edit (Publicacion p);
    public void delete (int id);
}
