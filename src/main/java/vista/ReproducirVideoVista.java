package vista;

import com.example.proyecto.Main;
import controlador.BL;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Calificacion;
import modelo.ModoReproduccion;
import modelo.Video;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReproducirVideoVista {

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
    public Button btnBorrar;
    @FXML
    public Button btnModificar;
    @FXML
    public Slider progresBar;
    @FXML
    public Button btnVolver;
    @FXML
    public Slider volumSlider;
    @FXML
    public ImageView fullScreen;
    @FXML
    public ImageView addReproductionList;
    @FXML
    public ToggleButton btnLike;
    @FXML
    public Label liked;
    private BL blConexion = BL.getInstanciaBl();
    private int posicionActual = 0;
    private ArrayList<Video> videos = new ArrayList<>();

    public void initialize() throws SQLException {
        indicadorLike();
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
                Stage stage = (Stage) Main.getEscenaPrincipal().getWindow();
                ChangeListener changeListener = (ChangeListener) (obs, oldValue, newValue) ->
                {
                    mediaVideo.setFitHeight(550);
                    mediaVideo.setFitWidth(1000);
                };

                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                    mediaVideo.setFitHeight(550);
                    mediaVideo.setFitWidth(1000);
                    stage.fullScreenProperty().removeListener(changeListener);

                } else {
                    stage.setFullScreen(true);
                    double height = stage.getHeight() - 75;
                    mediaVideo.setFitHeight(height);
                    mediaVideo.setFitWidth(stage.getWidth());
                    stage.fullScreenProperty().addListener(changeListener);
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

    public void reproducirVideo() throws SQLException {
        Video videoActual = videos.get(posicionActual);
        final String nombreArchivo = videoActual.getArchivo();
        boolean tienePermiso = usuarioPermiso(blConexion.getUsuarioActual().getId(), videoActual);
        btnBorrar.setVisible(tienePermiso);
        btnModificar.setVisible(tienePermiso);

        File archivo = new File(nombreArchivo);
        Media video = new Media(archivo.toURI().toString());

        mediaPlayer = new MediaPlayer(video);
        mediaPlayer.setAutoPlay(true);
        mediaVideo.setMediaPlayer(mediaPlayer);
        mediaVideo.setPreserveRatio(false);
        mediaVideo.setFitHeight(550);
        mediaVideo.setFitWidth(1000);
        mediaPlayer.setOnEndOfMedia(() ->
        {
            if (posicionActual == videos.size() - 1) {
                mediaPlayer.stop();
            } else {
                posicionActual++;
                try {
                    reproducirVideo();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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

    public void handleButtonReproducir(ActionEvent event) {
        mediaPlayer.play();
    }

    public void handleButtonPausar(ActionEvent event) {
        mediaPlayer.pause();
    }

    public void handleButtonParar(ActionEvent event) {
        mediaPlayer.stop();
    }

    public void handleButtonLento(ActionEvent event) {
        mediaPlayer.setRate(0.5);
    }

    public void handleButtonRapido(ActionEvent event) {
        mediaPlayer.setRate(2);
    }

    public void handleButtonModificar(ActionEvent event) throws IOException {
        Video video = videos.get(posicionActual);
        blConexion.setVideoActual(video);
        mediaPlayer.stop();
        Main.cambiaPantalla("modificarVideo");
    }

    public void handleButtonBorrar(ActionEvent event) throws SQLException, IOException {
        Video video = videos.get(posicionActual);
        blConexion.borrarVideo(video.getId());
        mediaPlayer.stop();
        Main.cambiaPantalla("paginaPrincipal");
    }

    public void loadVideos(ModoReproduccion modoReproduccion) {
        videos.clear();
        if (modoReproduccion.equals(ModoReproduccion.Simple)) {
            videos.add(blConexion.getVideoActual());
        } else {
            videos.addAll(blConexion.getPlayListActual().getListaVideos());
        }
    }

    @FXML
    public void handleDarleLike() throws SQLException {
        Calificacion calificacion = new Calificacion();
        int idVideoActual = blConexion.getVideoActual().getId();
        int idUsuarioActual = blConexion.getUsuarioActual().getId();

        if (btnLike.isSelected()) {
            calificacion.setIdVideo(idVideoActual);
            calificacion.setIdUsuario(idUsuarioActual);
            calificacion.setEstado(true);

            blConexion.guardarCalificacion(calificacion);

        } else {
            blConexion.borrarCalificacion(idVideoActual, idUsuarioActual);
            liked.setText("");
        }
        indicadorLike();
    }

    public void indicadorLike() throws SQLException {
        int idVideoActual = blConexion.getVideoActual().getId();
        int idUsuarioActual = blConexion.getUsuarioActual().getId();

        Calificacion calificacionActual = blConexion.obtenerCalificacionActual(idVideoActual, idUsuarioActual);

        if (calificacionActual != null) {
            if (calificacionActual.isEstado() == true) {
                liked.setText("liked");

            } else {
                liked.setText("");
            }
        }
    }

    public boolean usuarioPermiso(int idUsuarioActual, Video videoActual) {
        return videoActual.getIdUsuario() == idUsuarioActual;
    }
}
