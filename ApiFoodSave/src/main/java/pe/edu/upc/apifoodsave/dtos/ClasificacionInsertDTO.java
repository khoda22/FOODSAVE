package pe.edu.upc.apifoodsave.dtos;

public class ClasificacionInsertDTO {
    private String periodo;
    private int puntaje;
    private double kgSalvados;
    private int idUsuario;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public double getKgSalvados() {
        return kgSalvados;
    }

    public void setKgSalvados(double kgSalvados) {
        this.kgSalvados = kgSalvados;
    }
}
