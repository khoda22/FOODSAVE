package pe.edu.upc.apifoodsave.dtos;

public class ProductoDTOInsert {
    private String nombre;
    private String categoria;
    private int vidaUtilDias;
    private String estado;        // (en tu entidad: columna "estado")
    private String codigoBarra;   // RECOMENDADO: String (puede tener ceros iniciales)
    private double pesoUnitario;

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

    public int getVidaUtilDias() {
        return vidaUtilDias;
    }

    public void setVidaUtilDias(int vidaUtilDias) {
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

    public double getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(double pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }
}
