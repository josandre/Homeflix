package model;

import java.util.ArrayList;

public class ListaVideos {
    private ArrayList<Video> listaVideos = new ArrayList<>();
    private String nombre;
    private String categoria;

    public ListaVideos(ArrayList<Video> listaVideos, String nombre, String categoria) {
        this.listaVideos = listaVideos;
        this.nombre = nombre;
        this.categoria = categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ListaVideos{" +
                "listaVideos=" + listaVideos +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
