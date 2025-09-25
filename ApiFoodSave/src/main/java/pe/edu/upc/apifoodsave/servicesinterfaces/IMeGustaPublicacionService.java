package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.MeGustaPublicacion;

public interface IMeGustaPublicacionService {
    public void insert (MeGustaPublicacion mg);
    public void delete (int id);
}
