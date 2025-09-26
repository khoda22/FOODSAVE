package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.ReporteContenido;

import java.util.List;

public interface IReporteContenidoService {
    public void insert(ReporteContenido rc);
    public List<ReporteContenido> list();
    public ReporteContenido listId(int id);
    public void edit(ReporteContenido rc);
}
