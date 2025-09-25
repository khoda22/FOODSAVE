package pe.edu.upc.apifoodsave.dtos;

import java.time.LocalDate;

public class InventarioDTODetail {
    private int idInventario;
    private int idUsuario;
    private String nombreUsuario;
    private int idProducto;
    private String nombreProducto;
    private int cantidadInventario;
    private int diasduracionInventario;
    private String estadoInventario;
    private LocalDate fechavencimientoInventario;
    private LocalDate fechacreacionInventario;

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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

    public String getEstadoInventario() {
        return estadoInventario;
    }

    public void setEstadoInventario(String estadoInventario) {
        this.estadoInventario = estadoInventario;
    }

    public LocalDate getFechavencimientoInventario() {
        return fechavencimientoInventario;
    }

    public void setFechavencimientoInventario(LocalDate fechavencimientoInventario) {
        this.fechavencimientoInventario = fechavencimientoInventario;
    }

    public LocalDate getFechacreacionInventario() {
        return fechacreacionInventario;
    }

    public void setFechacreacionInventario(LocalDate fechacreacionInventario) {
        this.fechacreacionInventario = fechacreacionInventario;
    }
}
