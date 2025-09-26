package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Seguidor;

import java.util.List;

public interface ISeguidorService {
    public void insert(Seguidor s);
    public List<Seguidor> list();
    public void delete(int id);
}
