package pe.edu.upc.apifoodsave.servicesinterfaces;

import pe.edu.upc.apifoodsave.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> listar();
    public void Registrar(Usuario u);
    public Usuario listarporid(int id);
    public void Modificar(Usuario u);
    public void Eliminar(int id);
    public List<String[]> ListarUsernameSinPassword();
}
