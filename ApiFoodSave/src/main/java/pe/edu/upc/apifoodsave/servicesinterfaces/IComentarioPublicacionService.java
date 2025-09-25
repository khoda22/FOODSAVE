package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.ComentarioPublicacion;

import java.util.List;

public interface IComentarioPublicacionService {
    public void insert (ComentarioPublicacion c);
    public List<ComentarioPublicacion> list();
    public ComentarioPublicacion listId (int id);
    public void edit (ComentarioPublicacion c);
    public void delete (int id);
}
