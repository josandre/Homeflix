package vista;

import com.example.proyecto.Main;
import controlador.BL;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import modelo.ListaReproduccion;
import modelo.ModoReproduccion;
import modelo.Video;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayListVista {
    @FXML
    public Label labelUserName;
    @FXML
    public Label nombrePlayList;
    @FXML
    public Circle photoUser;
    @FXML
    public VBox vBox;
    @FXML
    public ImageView back;
    @FXML
    public ImageView playAll;
    private BL blConexion = BL.getInstanciaBl();

    public void initialize() throws SQLException {
        Main.userInformation(labelUserName, photoUser);
        loadVideosPlayList();
        nombrePlayList.setText(blConexion.getPlayListActual().getNombre());

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.cambiaPantalla("paginaPrincipal");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        playAll.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    blConexion.setModoReproduccion(ModoReproduccion.Multiple);
                    playVideo();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void loadVideosPlayList() throws SQLException {
        ListaReproduccion actualPlayList = blConexion.getPlayListActual();
        ArrayList<Video> listaVideos = blConexion.videoEnPlayListActual(actualPlayList.getId());
        actualPlayList.setListaVideos(listaVideos);
        vBox.getChildren().clear();

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        vBox.setSpacing(10);
        int count = 0;

        for (int i = 0; i < listaVideos.size(); i++) {
            count = i + 1;
            Image img;
            Video video = listaVideos.get(i);

            if (video.getThumbnailVideo() != null && !video.getThumbnailVideo().equals("")) {
                img = new Image("file:" + video.getThumbnailVideo());
            } else {
                URL urlImagen = Main.class.getResource("img/defaultVideoImage.jpeg");
                img = new Image(urlImagen.toString());
            }

            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(Main.HEIGHT);
            imageView.setFitWidth(Main.WIDTH);
            Label label = new Label();
            label.setText(video.getNombre());

            VBox contenedor = new VBox();
            contenedor.setSpacing(5);
            contenedor.getChildren().addAll(label, imageView);
            hBox.getChildren().add(contenedor);
            reproducirPlayList(imageView, video);
            if (count % Main.NCOLUMNS == 0 || count == listaVideos.size()) {
                vBox.getChildren().add(hBox);
                hBox = new HBox();
                hBox.setSpacing(5);
            }
        }
    }

    public void reproducirPlayList(ImageView imageView, Video video) {
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                blConexion.setVideoActual(video);
                blConexion.setModoReproduccion(ModoReproduccion.Simple);
                try {
                    playVideo();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void playVideo() throws IOException {
        Main.cambiaPantalla("reproducirVideo");
    }
}
