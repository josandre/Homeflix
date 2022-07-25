package Model;

public class Usuario {

    private String nombre;
    private String apellido;
    private String identificacion;
    private String nombreUsuario;
    private String contrasenna;
    private String archivoImagen;



    public Usuario(String nombre, String apellido, String identificacion, String nombreUsuario, String contrasenna, String archivoImagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.nombreUsuario = nombreUsuario;
        this.contrasenna = contrasenna;
        this.archivoImagen = archivoImagen;
    }

    public Usuario() {
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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
                ", identificacion=" + identificacion +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasenna='" + contrasenna + '\'' +
                ", archivoImagen='" + archivoImagen + '\'' +
                '}';
    }
}


