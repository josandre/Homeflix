package View;

import Controller.BL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.sql.SQLException;

public class ReproducirVideoView {

    @FXML
    public MediaView mediaVideo;

    @FXML
    public Button btnPausar;

    @FXML
    public Button btnAdelantar;

    @FXML
    public Button btnAtrasar;



    private BL blConexion = BL.getInstanciaBl();

    Stage stage;

    public void initialize(){
        System.out.println("reproduciendo video");
        final String nombreArchivo = "video.mp4";
        File archivo = new File(nombreArchivo);

        Media video = new Media(archivo.toURI().toString());
        MediaPlayer reproductor = new MediaPlayer(video);
        reproductor.setAutoPlay(true);
        mediaVideo.setMediaPlayer(reproductor);

    }



    public void handleButtonPausar(ActionEvent event)  {

    }

    public void handleButtonAdelantar(ActionEvent event){

    }

    public void handleButtonRegresar(ActionEvent event){

    }



}
