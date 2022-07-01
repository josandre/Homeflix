package Model;

import java.time.LocalDate;


public class Video {
    private String id;

    private String nombre;
    private String categoria;
    private LocalDate fecha;
    private String descripcion;
    private int calificacion;

    private String archivo;

    public Video(String idVideo, String nombre, String categoria, LocalDate fecha, String descripcion, int calificacion, String archivo) {
        this.id = idVideo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.archivo = archivo;
    }

    public Video(){

    }

    public String getIdVideo() {
        return id;
    }

    public void setIdVideo(String idVideo) {
        this.id = idVideo;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }



    @Override
    public String toString() {
        return "Video{" +
                "idVideo='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", Fecha=" + fecha +
                ", Descripcion='" + descripcion + '\'' +
                ", calificacion=" + calificacion +
                '}';
    }
}
