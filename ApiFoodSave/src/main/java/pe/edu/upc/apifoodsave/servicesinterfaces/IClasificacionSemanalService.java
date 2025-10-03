package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.ClasificacionSemanal;

import java.util.List;

public interface IClasificacionSemanalService {
    public void insert (ClasificacionSemanal cs);
    public void edit (ClasificacionSemanal cs);
    public List<ClasificacionSemanal> list();
    public ClasificacionSemanal listId (int id);
    public void delete (int id);
    public List<String[]> RankClasificacionSemanalService();
}
