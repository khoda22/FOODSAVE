package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Rol;

import java.util.List;

public interface IRolService {
    public List<Rol> listar();
    public void Registrar(Rol r);
    public Rol listarporid(int id);
    public void Modificar(Rol r);
    public void Eliminar(int id);
}
