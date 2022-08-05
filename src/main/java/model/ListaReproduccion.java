package model;

import java.util.ArrayList;

public class ListaReproduccion {
    private ArrayList<Video> listaVideos = new ArrayList<>();
    private String nombre;

    private String archivoImagen;

    private int id;

    private int userId;



    public ListaReproduccion(ArrayList<Video> listaVideos, String nombre, String archivoImagen, int id, int userId) {
        this.listaVideos = listaVideos;
        this.nombre = nombre;
        this.archivoImagen = archivoImagen;
        this.id = id;
        this.userId = userId;
    }

    public ListaReproduccion(){
        this.listaVideos =  new ArrayList<>();
    }

    public ArrayList<Video> getListaVideos() {
        return listaVideos;
    }

    public void setListaVideos(ArrayList<Video> listaVideos) {
        this.listaVideos = listaVideos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(String archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "ListaVideos{" +
                "listaVideos=" + listaVideos +
                ", nombre='" + nombre + '\'' +
                ", archivoImagen='" + archivoImagen + '\'' +
                '}';
    }
}
