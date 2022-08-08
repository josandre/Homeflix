package controlador;

import dataaccess.DACalificacion;
import dataaccess.DAListaReproduccion;
import dataaccess.DAUsuario;
import dataaccess.DAVideo;
import modelo.Calificacion;
import modelo.ListaReproduccion;
import modelo.ModoReproduccion;
import modelo.Usuario;
import modelo.Video;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BL {
    DAVideo daVideo = new DAVideo();

    DAUsuario daUsuario = new DAUsuario();

    DAListaReproduccion daListaReproduccion = new DAListaReproduccion();

    DACalificacion daCalificacion = new DACalificacion();

    private static BL instanciaBl;

    private Usuario usuarioActual;

    private Video actualVideo;

    private ListaReproduccion actualPlayList;


    private ModoReproduccion modoReproduccion;




    private BL() {
    }

    public static BL getInstanciaBl(){
        if(instanciaBl == null){
            instanciaBl = new BL();
        }
        return instanciaBl;
    }

    public int annadirVideo(Video video) throws SQLException {
        return  daVideo.annadirVideo(video);
    }

    public void adduser(Usuario usuario) throws SQLException {
        if (validarContrasenna(usuario.getContrasenna()) == true) {
            daUsuario.annadirUsuario(usuario);
        }
    }

    public static boolean validarContrasenna(String contrsenna) {
        String regex = "^(?=.*[0-9])" +
                       "(?=.*[a-z])(?=.*[A-Z])" +
                       "(?=.*[@#$%^&+=])" +
                       "(?=\\S+$).{8,20}$";

        Pattern pattern = Pattern.compile(regex);

        if (contrsenna == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(contrsenna);

        return matcher.matches();
    }

    public void addReproductionList(ListaReproduccion listaVideo) throws SQLException {
        daListaReproduccion.addReproductionList(listaVideo);
    }



    public Usuario buscarUsuario(String contrasenna, String nombreUsuario)throws  SQLException{

        Usuario usuario = daUsuario.buscarUsuario(contrasenna, nombreUsuario);
        if(usuario != null){
            usuarioActual = usuario;
        }

        return  usuario;
    }

    public boolean userExists(String userName) throws SQLException {
        return  daUsuario.buscarUserName(userName);
    }



    public ArrayList<Video> listarVideos(int userId)throws SQLException{
        ArrayList<Video> videos = daVideo.obtenerVideos(userId);
        return  videos;
    }

    public ArrayList<ListaReproduccion> listarReproductionList(int userId)throws SQLException{
        ArrayList<ListaReproduccion> listasDeReproduccion = daListaReproduccion.toListReproductionLists(userId);
        return  listasDeReproduccion;
    }

    public ArrayList<Video> buscarVideo(String criterio)throws SQLException{
        ArrayList<Video> videosSolicitados = daVideo.buscarVideos(criterio);
        return videosSolicitados;
    }

    public Usuario getUsuarioActual(){
        return usuarioActual;
    }

    public Video getActualVideo() {
        return actualVideo;
    }

    public void setActualVideo(Video actualVideo) {
        this.actualVideo = actualVideo;
    }

    public ListaReproduccion getActualPlayList() {
        return actualPlayList;
    }

    public void setActualPlayList(ListaReproduccion actualPlayList) {
        this.actualPlayList = actualPlayList;
    }

    public void addVideoToPlayList(int idVideo, int idPlayList)throws SQLException{
        daListaReproduccion.addVideoToPlayList(idVideo, idPlayList);
    }

    public ArrayList<Video> videosInPlayListActual(int idListaReproduccion) throws SQLException {
        return daListaReproduccion.listaVideos(idListaReproduccion);
    }

    public void guardarCalificacion(Calificacion calificacion) throws SQLException {
        daCalificacion.guardarCalificacion(calificacion);

    }

    public void borrarCalificacion(int idVideo, int idUsuario) throws SQLException {
        daCalificacion.borrarCalificacion(idVideo, idUsuario);
    }

    public Calificacion obtenerCalificacionActual(int idVideo, int idUsuario) throws SQLException {
        return daCalificacion.obtenerCalificacionActual(idVideo, idUsuario);
    }

    public ModoReproduccion getModoReproduccion() {
        return modoReproduccion;
    }

    public void setModoReproduccion(ModoReproduccion modoReproduccion) {
        this.modoReproduccion = modoReproduccion;
    }

    public void borarVideo(int idVideo) throws SQLException {
        daCalificacion.borrarVideo(idVideo);
        daListaReproduccion.borrarVideoDeLista(idVideo);
        daVideo.borrarVideo(idVideo);
    }

    public void modificar(Video videoActual) throws SQLException {
        daVideo.modificarVideo(videoActual);
    }

}
