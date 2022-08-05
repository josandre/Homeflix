package view;

import com.example.proyecto.Main;
import controller.BL;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.ListaReproduccion;
import model.Video;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class playListView {
    @FXML
    public Label labelUserName;

    @FXML
    public Label nombrePlayList;

    @FXML
    public Circle photoUser;


    @FXML
    public GridPane gridPane;

    @FXML
    public ImageView back;

    private BL blConexion = BL.getInstanciaBl();



    private int nColumns = 3;





    public void initialize() throws SQLException {
        Main.userInformation(labelUserName, photoUser);
        loadVideosPlayList();


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

    }

    public void loadVideosPlayList()throws SQLException{
        ListaReproduccion actualPlayList = blConexion.getActualPlayList();
        ArrayList<Video> listaVideos = blConexion.videosInPlayListActual(actualPlayList.getId());
        gridPane.getChildren().clear();
        int count = 0;
        double nFilas = ((double)listaVideos.size()) / ((double)nColumns);
        int totalFilas = (int) Math.ceil(nFilas);

        for(int fila = 0; fila < totalFilas; fila ++){
            int filaActual = fila + 1;
            int cantMaximaVideos = filaActual * Main.NCOLUMNS;
            int camposSobrantes = listaVideos.size() < cantMaximaVideos ? cantMaximaVideos - listaVideos.size(): 0;
            int totalColumns = Main.NCOLUMNS - camposSobrantes;


            for(int columna = 0; columna < totalColumns; columna++ ){
                Image img;
                Video video = listaVideos.get(count);

                if(video.getThumbnailVideo() != null){
                    img = new Image("file:" + video.getThumbnailVideo());


                }else {
                    URL urlImage2 =  Main.class.getResource("img/defaultVideoImage.jpeg");
                    img = new Image(urlImage2.toString());

                }
                ImageView imageView = new ImageView(img);
                imageView.setFitWidth(Main.WIDTH);
                imageView.setFitHeight(Main.HEIGHT);

                GridPane.setRowIndex(imageView, fila);
                GridPane.setColumnIndex(imageView, columna);
                gridPane.getChildren().add(imageView);
                count ++;
            }



        }
    }

}
