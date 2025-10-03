package pe.edu.upc.apifoodsave.dtos;

public class ClasificacionSemanalRankDTO {
    private int idUsuario;
    private String nombre;
    private int puntosLogro; //debe ser 0
    private double kgSalvadosClasificacion;

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

    public int getPuntosLogro() {
        return puntosLogro;
    }

    public void setPuntosLogro(int puntosLogro) {
        this.puntosLogro = puntosLogro;
    }

    public double getKgSalvadosClasificacion() {
        return kgSalvadosClasificacion;
    }

    public void setKgSalvadosClasificacion(double kgSalvadosClasificacion) {
        this.kgSalvadosClasificacion = kgSalvadosClasificacion;
    }
}
