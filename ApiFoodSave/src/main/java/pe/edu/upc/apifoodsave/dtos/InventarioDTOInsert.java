package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDate;

public class InventarioDTOInsert {
    private int idUsuario;
    private int idProducto;
    private int cantidadInventario;
    private int diasduracionInventario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(int cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    public int getDiasduracionInventario() {
        return diasduracionInventario;
    }

    public void setDiasduracionInventario(int diasduracionInventario) {
        this.diasduracionInventario = diasduracionInventario;
    }
}
