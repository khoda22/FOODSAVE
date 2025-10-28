package pe.edu.upc.apifoodsave.dtos;

public class NotificacionPorGrupoDTO {
    private String nombreGrupo;
    private int totalNotificaciones;
    private int totalUsuarios;
    private double promedioNotificacionesPorUsuario;

    public NotificacionPorGrupoDTO() {}

    public NotificacionPorGrupoDTO(String nombreGrupo, int totalNotificaciones,
                                   int totalUsuarios, double promedioNotificacionesPorUsuario) {
        this.nombreGrupo = nombreGrupo;
        this.totalNotificaciones = totalNotificaciones;
        this.totalUsuarios = totalUsuarios;
        this.promedioNotificacionesPorUsuario = promedioNotificacionesPorUsuario;
    }

    // Getters y Setters
    public String getNombreGrupo() { return nombreGrupo; }
    public void setNombreGrupo(String nombreGrupo) { this.nombreGrupo = nombreGrupo; }

    public int getTotalNotificaciones() { return totalNotificaciones; }
    public void setTotalNotificaciones(int totalNotificaciones) { this.totalNotificaciones = totalNotificaciones; }

    public int getTotalUsuarios() { return totalUsuarios; }
    public void setTotalUsuarios(int totalUsuarios) { this.totalUsuarios = totalUsuarios; }

    public double getPromedioNotificacionesPorUsuario() { return promedioNotificacionesPorUsuario; }
    public void setPromedioNotificacionesPorUsuario(double promedioNotificacionesPorUsuario) {
        this.promedioNotificacionesPorUsuario = promedioNotificacionesPorUsuario;
    }
}