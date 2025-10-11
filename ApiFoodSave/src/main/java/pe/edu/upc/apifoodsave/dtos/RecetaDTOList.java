package pe.edu.upc.apifoodsave.dtos;

public class RecetaDTOList {
    //private Integer idReceta;
    private String titulo;
    private Integer dificultad;
    //private Integer tiempoPreparacion;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDificultad() {
        return dificultad;
    }

    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }
}
