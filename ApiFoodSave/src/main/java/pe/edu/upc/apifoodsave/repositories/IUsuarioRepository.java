package pe.edu.upc.apifoodsave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.apifoodsave.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findOneByUsername(String nombreUsuario);
    @Query(value="SELECT \n" +
            "  u.idUsuario,\n" +
            "  u.username,\n" +
            "  u.email,\n" +
            "  u.estado\n" +
            "  u.foto,\n" +
            "  u.ubicacion,\n" +
            "  u.login,\n" +
            "  u.Creacion,\n" +
            "FROM usuario u;", nativeQuery = true)
    public List<String[]> ListarUsernameSinPassword();

}
