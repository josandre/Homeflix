package Model;

public class Registro {

    private String nombre;
    private String apellido;
    private int identificacion;
    private String usuario;
    private String contrasenna;
    private String archivoImagen;

    public Registro(String nombre, String apellido, int identificacion, String usuario, String contrasenna, String archivoImagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.usuario = usuario;
        this.contrasenna = contrasenna;
        this.archivoImagen = archivoImagen;
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

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
                ", identificacion=" + identificacion +
                ", usuario='" + usuario + '\'' +
                ", contrasenna='" + contrasenna + '\'' +
                ", archivoImagen='" + archivoImagen + '\'' +
                '}';
    }
}


