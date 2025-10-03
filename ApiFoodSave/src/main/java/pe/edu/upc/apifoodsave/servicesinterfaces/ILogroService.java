package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Logro;

import java.util.List;

public interface ILogroService {
    public void insert (Logro l);
    public List<Logro> list();
    public Logro listId(int id);
    public void edit (Logro l);
    public void delete (int id);
    public List<String[]> InsigniasService();
}
