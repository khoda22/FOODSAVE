package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Intercambio;

import java.util.List;

public interface IIntercambioService {
    public void insert(Intercambio i);
    public List<Intercambio> list();
    public Intercambio listId(int id);
    public void edit(Intercambio i);
    public void delete(int id);
}
