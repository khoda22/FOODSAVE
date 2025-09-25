package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.PreferenciasUsuario;

import java.util.List;

public interface IPreferenciasUsuarioService {
    public List<PreferenciasUsuario> listar();
    public void Registrar(PreferenciasUsuario pu);
    public PreferenciasUsuario listarporid(int id);
    public void Modificar(PreferenciasUsuario pu);
    public void Eliminar(int id);
}
