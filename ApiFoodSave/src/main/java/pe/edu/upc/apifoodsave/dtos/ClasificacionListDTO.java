package pe.edu.upc.apifoodsave.dtos;

public class ClasificacionListDTO {
    private int idClasificacion;
    private String periodo;
    private int puntaje;
    private double kgSalvados;
    private String nombreUsuario;

    public int getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(int idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public double getKgSalvados() {
        return kgSalvados;
    }

    public void setKgSalvados(double kgSalvados) {
        this.kgSalvados = kgSalvados;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
