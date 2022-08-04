package controller;

import dataaccess.DAUsuario;
import dataaccess.DAVideo;
import model.Usuario;
import model.Video;

import java.sql.SQLException;
import java.util.ArrayList;

public class BL {
    DAVideo DAVideo = new DAVideo();

    DAUsuario DAUsuario = new DAUsuario();

    private static BL instanciaBl;

    private Usuario usuarioActual;

    private Video actualVideo;

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

    public void crearUsuario(Usuario usuario) throws SQLException {
        DAUsuario.annadirUsuario(usuario);
    }



    public Usuario buscarUsuario(String contrasenna, String nombreUsuario)throws  SQLException{

        Usuario usuario = DAUsuario.buscarUsuario(contrasenna, nombreUsuario);
        if(usuario != null){
            usuarioActual = usuario;
        }

        return  usuario;
    }



    public ArrayList<Video> listarVideos(int userId)throws SQLException{
        ArrayList<Video> videos = DAVideo.obtenerVideos(userId);
        return  videos;
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









}
