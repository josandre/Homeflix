package model;

import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contrasenna;
    private String archivoImagen;
    private ArrayList<Video> userVideos;

    private int id;

    public Usuario(String nombre, String apellido, String nombreUsuario, String contrasenna, String archivoImagen, ArrayList<Video> userVideos, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contrasenna = contrasenna;
        this.archivoImagen = archivoImagen;
        this.userVideos = userVideos;
        this.id = id;
    }

    public Usuario() {
        userVideos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ArrayList<Video> getUserVideos() {
        return userVideos;
    }

    public void setUserVideos(ArrayList<Video> userVideos) {
        this.userVideos = userVideos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String usuario) {
        this.nombreUsuario = usuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(String archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasenna='" + contrasenna + '\'' +
                ", archivoImagen='" + archivoImagen + '\'' +
                ", userVideos='" + userVideos + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}


