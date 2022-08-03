package view;

import controller.BL;
import model.Usuario;
import model.Video;
import com.example.proyecto.Main;
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
    public HBox hBoxVideos;

    @FXML
    public Button btnAddVideo;

    @FXML
    public ImageView trash;

    @FXML
    public ImageView search;

    @FXML
    public ImageView exit;

    @FXML
    public ImageView add;

    @FXML
    public HBox idHboxLista;

    private BL blConexion = BL.getInstanciaBl();

    private Usuario usuarioActual = blConexion.getUsuarioActual();




    public void initialize() throws SQLException {

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

        hBoxVideos.setSpacing(5);

        loadData(usuarioActual.getId());

        trash.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hBoxVideos.getChildren().clear();
                txtBuscar.setText("");
                try {
                    loadData(usuarioActual.getId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        search.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hBoxVideos.getChildren().clear();
                if(txtBuscar.getText().trim().isEmpty()){
                    try {
                        loadData(usuarioActual.getId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }else
                {
                    try {
                        searchLoadData();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });

        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.cambiaPantalla("login");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.cambiaPantalla("registrarVideo");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });





    }




    public void loadData(int userId) throws SQLException {
        ArrayList<Video> videos = blConexion.listarVideos(userId);
        for (int i = 0; i < videos.size(); i++) {

            Image img;
            Video video = videos.get(i);


            if(video.getThumbnailVideo() != null && !video.getThumbnailVideo().equals("")){
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

            playingVideo(imageView, video);
            hBoxVideos.getChildren().add(imageView);

        }

    }


    public void searchLoadData() throws SQLException {
        ArrayList<Video> videos = blConexion.buscarVideo(txtBuscar.getText());
        hBoxVideos.getChildren().clear();
        for (int i = 0; i < videos.size(); i++) {
            Image img;
            Video video = videos.get(i);


            if(video.getThumbnailVideo() != null && !videos.get(i).getThumbnailVideo().equals("")){
                img = new Image("file:" + videos.get(i).getThumbnailVideo());
                System.out.println(img.getUrl());

            }else {
                URL urlImage2 =  Main.class.getResource("img/defaulImageVideo.jpeg");
                img = new Image(urlImage2.toString());

            }

            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(HEIGHT_VIDEO_IMAGE);
            imageView.setFitWidth(WIDTH_VIDEO_IMAGE);
            imageView.setPickOnBounds(true);

           playingVideo(imageView, video);
           hBoxVideos.getChildren().add(imageView);

        }
    }

    public void playingVideo(ImageView imageView, Video video){
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
    }

    public void playVideo(Video video) throws IOException {
        Main.cambiaPantalla("reproducirVideo");
    }








}
