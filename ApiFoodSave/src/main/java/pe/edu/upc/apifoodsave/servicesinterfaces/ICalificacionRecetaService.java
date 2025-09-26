package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.CalificacionReceta;

import java.util.List;

public interface ICalificacionRecetaService {
    public void insert(CalificacionReceta cr);
    public List<CalificacionReceta> list();
}
