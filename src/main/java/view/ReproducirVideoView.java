package view;

import controller.BL;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ListaReproduccion;
import model.ModoReproduccion;
import model.Video;
import com.example.proyecto.Main;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

    @FXML
    public Slider progresBar;

    @FXML
    public Button btnVolver;

    @FXML
    public Slider volumSlider;


    @FXML
    public ImageView fullScreen;

    @FXML
    public  ImageView addReproductionList;

    private BL blConexion = BL.getInstanciaBl();

    private  int posicionActual = 0;

    private ArrayList<Video> videos = new ArrayList<>();




    public void initialize(){

        loadVideos(blConexion.getModoReproduccion());
        reproducirVideo();

        volumSlider.setValue(mediaPlayer.getVolume() * 100);
        volumSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumSlider.getValue() / 100);
            }
        });

        fullScreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ImageView imageView = (ImageView) mouseEvent.getSource();
                Stage stage = (Stage) imageView.getScene().getWindow();

                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                } else {
                    stage.setFullScreen(true);
                }
            }
        });

        addReproductionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    mediaPlayer.stop();
                    Main.cambiaPantalla("listasDeReproduccion");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void reproducirVideo(){

        Video videoActual = videos.get(posicionActual);
        final String nombreArchivo = videoActual.getArchivo();

        File archivo = new File(nombreArchivo);
        Media video = new Media(archivo.toURI().toString());
        mediaPlayer  = new MediaPlayer(video);
        mediaPlayer.setAutoPlay(true);
        mediaVideo.setMediaPlayer(mediaPlayer);
        mediaVideo.setPreserveRatio(false);
        mediaVideo.setFitHeight(550);
        mediaVideo.setFitWidth(1000);

        mediaPlayer.setOnEndOfMedia( () ->
        {
            if(posicionActual == videos.size()-1){
                mediaPlayer.stop();
            }else {
                posicionActual++;
                reproducirVideo();
            }

        });

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                progresBar.setValue(newValue.toSeconds());

            }
        });


        progresBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(Duration.seconds(progresBar.getValue()));
            }
        });

        progresBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(Duration.seconds(progresBar.getValue()));
            }
        });



        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total = video.getDuration();
                progresBar.setMax(total.toSeconds());

            }
        });

    }



    public void handleButtonVolver(ActionEvent event) throws IOException {
        mediaPlayer.stop();
        Main.cambiaPantalla("paginaPrincipal");
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

    public void loadVideos(ModoReproduccion modoReproduccion){
        videos.clear();
        if(modoReproduccion.equals(ModoReproduccion.Simple)){
            videos.add(blConexion.getActualVideo());
        }else{
            videos.addAll(blConexion.getActualPlayList().getListaVideos());
        }
    }








}
