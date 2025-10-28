package pe.edu.upc.apifoodsave.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.apifoodsave.entities.Inventario;
import pe.edu.upc.apifoodsave.repositories.IInventarioRepository;
import pe.edu.upc.apifoodsave.servicesinterfaces.IInventarioService;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioServiceImplements implements IInventarioService {
    @Autowired
    private IInventarioRepository repository;

    @Override
    public List<Inventario> list() {
        return repository.findAll();
    }

    @Override
    public void insert(Inventario inv) {
        // Calculamos los días de duración
        int diasDuracion = (int) ChronoUnit.DAYS.between(LocalDate.now(), inv.getFechavencimientoInventario());
        inv.setDiasduracionInventario(diasDuracion);

        // Guardamos el inventario
        repository.save(inv);
    }

    @Override
    public Inventario listId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void edit(Inventario inv) {
        repository.save(inv);
    }

    @Override
    public List<Inventario> listarOrdenadosPorFecha() {
        return repository.findAllByOrderByFechavencimientoInventarioAsc();
    }

    @Override
    public void cambiarEstado(int id, String estado) {
        Inventario inv = repository.findById(id).orElse(null);
        if (inv != null) {
            inv.setEstadoInventario(estado);
            repository.save(inv);
        }
    }

    @Override
    public List<Inventario> buscar(String nombre, String categoria) {
        return repository.findAll().stream()
                .filter(inv -> (nombre == null || inv.getProducto().getNombre().toLowerCase().contains(nombre.toLowerCase())) &&
                        (categoria == null || inv.getProducto().getCategoria().toLowerCase().contains(categoria.toLowerCase())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Inventario> filtrarPorEstado(String estado) {
        if (estado == null || estado.isEmpty()) {
            return repository.findAll();
        }

        return repository.findAll().stream()
                .filter(inv -> {
                    LocalDate hoy = LocalDate.now();
                    switch (estado.toLowerCase()) {
                        case "vigente":
                            return inv.getFechavencimientoInventario().isAfter(hoy);
                        case "proximo":
                            return !inv.getFechavencimientoInventario().isBefore(hoy)
                                    && ChronoUnit.DAYS.between(hoy, inv.getFechavencimientoInventario()) <= 3;
                        case "vencido":
                            return inv.getFechavencimientoInventario().isBefore(hoy);
                        default:
                            return true;
                    }
                })
                .collect(Collectors.toList());
    }
}
