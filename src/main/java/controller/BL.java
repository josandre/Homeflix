package controller;

import dataaccess.DACalificacion;
import dataaccess.DAListaReproduccion;
import dataaccess.DAUsuario;
import dataaccess.DAVideo;
import model.Calificacion;
import model.ListaReproduccion;
import model.ModoReproduccion;
import model.Usuario;
import model.Video;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BL {
    DAVideo DAVideo = new DAVideo();

    DAUsuario DAUsuario = new DAUsuario();

    DAListaReproduccion DAListaReproduccion = new DAListaReproduccion();

    DACalificacion DACalificacion = new DACalificacion();

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
        return  DAVideo.annadirVideo(video);
    }

    public void adduser(Usuario usuario) throws SQLException {
        if (validarContrasenna(usuario.getContrasenna()) == true) {
            DAUsuario.annadirUsuario(usuario);
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
        DAListaReproduccion.addReproductionList(listaVideo);
    }



    public Usuario buscarUsuario(String contrasenna, String nombreUsuario)throws  SQLException{

        Usuario usuario = DAUsuario.buscarUsuario(contrasenna, nombreUsuario);
        if(usuario != null){
            usuarioActual = usuario;
        }

        return  usuario;
    }

    public boolean userExists(String userName) throws SQLException {
        return  DAUsuario.buscarUserName(userName);
    }



    public ArrayList<Video> listarVideos(int userId)throws SQLException{
        ArrayList<Video> videos = DAVideo.obtenerVideos(userId);
        return  videos;
    }

    public ArrayList<ListaReproduccion> listarReproductionList(int userId)throws SQLException{
        ArrayList<ListaReproduccion> listasDeReproduccion = DAListaReproduccion.toListReproductionLists(userId);
        return  listasDeReproduccion;
    }

    public ArrayList<Video> buscarVideo(String criterio)throws SQLException{
        ArrayList<Video> videosSolicitados = DAVideo.buscarVideos(criterio);
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
        DAListaReproduccion.addVideoToPlayList(idVideo, idPlayList);
    }

    public ArrayList<Video> videosInPlayListActual(int idListaReproduccion) throws SQLException {
        return DAListaReproduccion.listaVideos(idListaReproduccion);
    }

    public void guardarCalificacion(Calificacion calificacion) throws SQLException {
        DACalificacion.guardarCalificacion(calificacion);

    }

    public void borrarCalificacion(int idVideo, int idUsuario) throws SQLException {
        DACalificacion.borrarCalificacion(idVideo, idUsuario);
    }

    public Calificacion obtenerCalificacionActual(int idVideo, int idUsuario) throws SQLException {
        return DACalificacion.obtenerCalificacionActual(idVideo, idUsuario);
    }

    public ModoReproduccion getModoReproduccion() {
        return modoReproduccion;
    }

    public void setModoReproduccion(ModoReproduccion modoReproduccion) {
        this.modoReproduccion = modoReproduccion;
    }

    public void borarVideo(int idVideo) throws SQLException {
        DACalificacion.borrarVideo(idVideo);
        DAListaReproduccion.borrarVideoDeLista(idVideo);
        DAVideo.borrarVideo(idVideo);
    }


















}
