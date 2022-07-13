package View;

import Controller.BL;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.sql.SQLException;

public class ReproducirVideoView {

    @FXML
    public MediaView mediaVideo;

    private MediaPlayer mediaPlayer;

    @FXML
    public Button btnReproducir;

    @FXML
    public Button btnPausar;

    @FXML
    public Button btnParar;

    @FXML
    public Button btnLento;

    @FXML
    public Button btnRapido;

    @FXML
    public Button btnMenosDiezSe;

    @FXML
    public Button btnMasDiezSeg;




    public void initialize(){
        System.out.println("reproduciendo video");
        final String nombreArchivo = "Video2.mp4";
        File archivo = new File(nombreArchivo);

        Media video = new Media(archivo.toURI().toString());
        mediaPlayer  = new MediaPlayer(video);
        mediaPlayer.setAutoPlay(true);
        mediaVideo.setMediaPlayer(mediaPlayer);

        DoubleProperty widhtPro = mediaVideo.fitWidthProperty();
        DoubleProperty heightPro = mediaVideo.fitHeightProperty();
        widhtPro.bind(Bindings.selectDouble(mediaVideo.sceneProperty(), "Width"));
        heightPro.bind(Bindings.selectDouble(mediaVideo.sceneProperty(), "height"));




    }



    public void handleButtonVolver(ActionEvent event){
        //to do
    }



    public void handleButtonReproducir(ActionEvent event)  {
           mediaPlayer.play();
    }

    public void handleButtonPausar(ActionEvent event){
        mediaPlayer.pause();
    }

    public void handleButtonParar(ActionEvent event){
        mediaPlayer.stop();
    }

    public void handleButtonLento(ActionEvent event){
        mediaPlayer.setRate(0.5);
    }

    public void handleButtonRapido(ActionEvent event){
        mediaPlayer.setRate(2);
    }

    public void handleButtonMasDiezSeg(){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));
    }

    public void handleButtonMenosDiezSeg(){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-10)));
    }



}
