package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Grupo;

import java.util.List;

public interface IGrupoService {
    public void insert(Grupo g);
    public List<Grupo> list();
    public Grupo listId(int id);
    public void edit(Grupo g);
    public void delete(int id);
}
