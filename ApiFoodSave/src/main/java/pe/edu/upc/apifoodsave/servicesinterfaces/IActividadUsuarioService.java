package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.ActividadUsuario;

import java.util.List;

public interface IActividadUsuarioService {
    public List<ActividadUsuario> listar();
    public void Registrar(ActividadUsuario au);
    public ActividadUsuario listarporid(int id);
    public void Modificar(ActividadUsuario au);
    public void Eliminar(int id);
}
