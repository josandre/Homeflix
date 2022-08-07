package view;

import controller.BL;
import javafx.scene.layout.VBox;
import model.ListaReproduccion;
import model.ModoReproduccion;
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
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class PaginaPrincipalView {


    @FXML
    public Label labelUserName;

    @FXML
    public Circle circlePhoto;


    @FXML
    public TextField txtBuscar;

    @FXML
    public HBox hBoxVideos;


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
    @FXML
    public Label resultadoBusqueda;



    private BL blConexion = BL.getInstanciaBl();

    private Usuario usuarioActual = blConexion.getUsuarioActual();




    public void initialize() throws SQLException {

        Main.userInformation(labelUserName, circlePhoto);

        hBoxVideos.setSpacing(5);

        loadDataVideos(usuarioActual.getId());

        idHboxLista.setSpacing(5);

        loadDataPlayList(usuarioActual.getId());

        trash.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hBoxVideos.getChildren().clear();
                txtBuscar.setText("");
                try {
                    loadDataVideos(usuarioActual.getId());
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
                        loadDataVideos(usuarioActual.getId());
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


    public void loadDataVideos(int userId) throws SQLException {
        resultadoBusqueda.setText("Mis Videos");
        ArrayList<Video> videos = blConexion.listarVideos(userId);
        for (int i = 0; i < videos.size(); i++) {

            Image img;
            Video video = videos.get(i);


            if(video.getThumbnailVideo() != null && !video.getThumbnailVideo().equals("")){
                img = new Image("file:" + video.getThumbnailVideo());
                System.out.println(img.getUrl());

            }else {
                URL urlImage2 =  Main.class.getResource("img/defaultVideoImage.jpeg");
                img = new Image(urlImage2.toString());
            }

            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(Main.HEIGHT);
            imageView.setFitWidth(Main.WIDTH);
            imageView.setPickOnBounds(true);
            Label label = new Label();
            VBox vBox = new VBox();
            label.setText(video.getNombre());
            vBox.getChildren().addAll(imageView, label);
            playingVideo(imageView, video);
            hBoxVideos.getChildren().add(vBox);
        }
    }

    public void loadDataPlayList(int userId)throws SQLException{
        ArrayList<ListaReproduccion> playList = blConexion.listarReproductionList(userId);
        for(int i = 0; i < playList.size(); i++){
            Image imgPlayList;
            ListaReproduccion list = playList.get(i);

            if(list.getArchivoImagen() != null && !list.getArchivoImagen().equals("")){
                imgPlayList = new Image ("file:" + list.getArchivoImagen());
            }else {
                URL urlImage = Main.class.getResource("img/defaultReproductionList.jpeg");
                imgPlayList = new Image(urlImage.toString());
            }

            ImageView imageView = new ImageView(imgPlayList);
            imageView.setFitHeight(Main.HEIGHT);
            imageView.setFitWidth(Main.WIDTH);
            imageView.setPickOnBounds(true);
            Label label = new Label();
            VBox vBox = new VBox();
            label.setText(list.getNombre());
            vBox.getChildren().addAll(imageView, label);
            seeVideos(imageView, list);
            idHboxLista.getChildren().add(vBox);
        }

    }

    public void searchLoadData() throws SQLException {
        ArrayList<Video> videos = blConexion.buscarVideo(txtBuscar.getText());
        hBoxVideos.getChildren().clear();
        resultadoBusqueda.setText("Resultados");

        for (int i = 0; i < videos.size(); i++) {
            Image img;
            Video video = videos.get(i);


            if(video.getThumbnailVideo() != null && !videos.get(i).getThumbnailVideo().equals("")){
                img = new Image("file:" + videos.get(i).getThumbnailVideo());
                System.out.println(img.getUrl());

            }else {
                URL urlImage2 =  Main.class.getResource("img/defaultVideoImage.jpeg");
                img = new Image(urlImage2.toString());

            }

            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(Main.HEIGHT);
            imageView.setFitWidth(Main.WIDTH);
            imageView.setPickOnBounds(true);
            Label label = new Label();
            VBox vBox = new VBox();
            label.setText(video.getNombre());
            vBox.getChildren().addAll(imageView, label);
            playingVideo(imageView, video);
            hBoxVideos.getChildren().add(vBox);
        }
    }

    public void playingVideo(ImageView imageView, Video video){
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                blConexion.setActualVideo(video);
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

    public void seeVideos(ImageView imageView, ListaReproduccion actualPlayList){
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                blConexion.setActualPlayList(actualPlayList);
                try {
                    Main.cambiaPantalla("playList");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }



}
