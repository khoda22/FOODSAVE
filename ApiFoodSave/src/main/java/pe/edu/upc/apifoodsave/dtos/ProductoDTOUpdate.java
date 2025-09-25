package pe.edu.upc.apifoodsave.dtos;

public class ProductoDTOUpdate {
    private int idProducto;
    private String nombre;
    private String categoria;
    private Integer vidaUtilDias;   // Integer para permitir update parcial
    private String estado;
    private String codigoBarra;
    private Double pesoUnitario;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getVidaUtilDias() {
        return vidaUtilDias;
    }

    public void setVidaUtilDias(Integer vidaUtilDias) {
        this.vidaUtilDias = vidaUtilDias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Double getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(Double pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }
}
