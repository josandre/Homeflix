package View;

import Controller.BL;
import Model.Usuario;
import Model.Video;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class PaginaPrincipalView {

    public static final int HEIGHT_VIDEO_IMAGE = 150;
    public static final int WIDTH_VIDEO_IMAGE = 150;
    private static PaginaPrincipalView instancia;

    @FXML
    public Label labelUserName;

    @FXML
    public Circle imageView;

    @FXML
    public Button btnBuscar;

    @FXML
    public TextField txtBuscar;

    @FXML
    public HBox listaVideos;

    @FXML
    public Button btnAddVideo;

    private BL blConexion = BL.getInstanciaBl();


    public void initialize() throws SQLException {
        Usuario usuarioActual = blConexion.getUsuarioActual();
        labelUserName.setText(usuarioActual.getNombreUsuario());

        if(usuarioActual.getArchivoImagen() != null && !usuarioActual.getArchivoImagen().equalsIgnoreCase("")){
            Image image = new Image("file:" + usuarioActual.getArchivoImagen());
            imageView.setFill(new ImagePattern(image));
            System.out.println(image.getUrl());
        }else {
            URL urlImage =  Main.class.getResource("img/defaultImage.png");
            Image imageDefault = new Image(urlImage.toString());
            imageView.setFill(new ImagePattern(imageDefault));
        }

        listaVideos.setSpacing(5);

        loadData(usuarioActual.getId());
    }



    public void loadData(int userId) throws SQLException {
        ArrayList<Video> videos = blConexion.listarVideos(userId);
        for (int i = 0; i < videos.size(); i++) {
            Image img;
            Video video = videos.get(i);


            if(videos.get(i).getThumbnailVideo() != null && !video.getThumbnailVideo().equals("")){
                img = new Image("file:" + video.getThumbnailVideo());
                System.out.println(img.getUrl());

            }else {
                URL urlImage2 =  Main.class.getResource("img/defaulImageVideo.jpeg");
                img = new Image(urlImage2.toString());

            }

            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(HEIGHT_VIDEO_IMAGE);
            imageView.setFitWidth(WIDTH_VIDEO_IMAGE);
            imageView.setPickOnBounds(true);

            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    blConexion.setActualVideo(video);
                    try {
                        playVideo(video);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
            listaVideos.getChildren().add(imageView);

        }

    }

    public void handleButtonSearch(ActionEvent event)throws SQLException{
        ArrayList<Video> videosEncontrados = blConexion.buscarVideo(txtBuscar.getText());

    }

    public void playVideo(Video video) throws IOException {
        Main.cambiaPantalla("reproducirVideo");
    }

    public void handleButtonAddVideo() throws IOException {
        Main.cambiaPantalla("registrarVideo");
    }

    public void handleButtonExit() throws IOException {
        Main.cambiaPantalla("login");
    }





}
