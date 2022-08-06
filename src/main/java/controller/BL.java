package controller;

import dataaccess.DAListaReproduccion;
import dataaccess.DAUsuario;
import dataaccess.DAVideo;
import model.ListaReproduccion;
import model.Usuario;
import model.Video;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BL {
    DAVideo DAVideo = new DAVideo();

    DAUsuario DAUsuario = new DAUsuario();

    DAListaReproduccion DAListasReproduccion = new DAListaReproduccion();

    private static BL instanciaBl;

    private Usuario usuarioActual;

    private Video actualVideo;

    private ListaReproduccion actualPlayList;



    private BL() {
    }

    public static BL getInstanciaBl(){
        if(instanciaBl == null){
            instanciaBl = new BL();
        }
        return instanciaBl;
    }

    public void annadirVideo(Video video) throws SQLException {
        DAVideo.annadirVideo(video);
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
        DAListasReproduccion.addReproductionList(listaVideo);
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
        ArrayList<ListaReproduccion> listasDeReproduccion = DAListasReproduccion.toListReproductionLists(userId);
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
        DAListasReproduccion.addVideoToPlayList(idVideo, idPlayList);
    }

    public ArrayList<Video> videosInPlayListActual(int idListaReproduccion) throws SQLException {
        return DAListasReproduccion.listaVideos(idListaReproduccion);
    }















}
