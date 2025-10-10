package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Escaneo;

import java.util.List;

public interface IEscaneoService {
    public void insert(Escaneo e);
    public List<Escaneo> list();
    List<Escaneo> obtenerHistorialUltimos7DiasService();
}
