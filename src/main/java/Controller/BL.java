package Controller;

import DataAcces.DAUsuario;
import DataAcces.DAVideo;
import Model.Usuario;
import Model.Video;

import java.sql.SQLException;

public class BL {
    DAVideo DAVideo = new DAVideo();

    DAUsuario DAUsuario = new DAUsuario();

    private static BL instanciaBl;

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
}
