package pe.edu.upc.apifoodsave.dtos;

public class LogroInsigniaDTO {
    private int idUsuario;
    private String nombre;
    private double kgSalvadosClasificacion;
    private int nombreLogro;
    private String descripcionLogro;
    private int puntosLogro; //debe ser 0

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getKgSalvadosClasificacion() {
        return kgSalvadosClasificacion;
    }

    public void setKgSalvadosClasificacion(double kgSalvadosClasificacion) {
        this.kgSalvadosClasificacion = kgSalvadosClasificacion;
    }

    public int getNombreLogro() {
        return nombreLogro;
    }

    public void setNombreLogro(int nombreLogro) {
        this.nombreLogro = nombreLogro;
    }

    public String getDescripcionLogro() {
        return descripcionLogro;
    }

    public void setDescripcionLogro(String descripcionLogro) {
        this.descripcionLogro = descripcionLogro;
    }

    public int getPuntosLogro() {
        return puntosLogro;
    }

    public void setPuntosLogro(int puntosLogro) {
        this.puntosLogro = puntosLogro;
    }
}
