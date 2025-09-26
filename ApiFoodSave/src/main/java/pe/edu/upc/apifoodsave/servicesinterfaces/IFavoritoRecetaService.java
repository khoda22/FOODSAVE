package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.FavoritoReceta;

import java.util.List;

public interface IFavoritoRecetaService {
    public void insert(FavoritoReceta fr);
    public List<FavoritoReceta> list();
    public FavoritoReceta listId(int id);
    public void delete(int id);
}
