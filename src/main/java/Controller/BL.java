package Controller;

import DataAcces.DAVideo;
import Model.Video;

import java.sql.SQLException;

public class BL {
    DAVideo DAVideo = new DAVideo();
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


}
