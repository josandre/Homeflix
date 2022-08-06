package view;

import com.example.proyecto.Main;
import controller.BL;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
   public VBox vBox;

    @FXML
    public ImageView back;

    private BL blConexion = BL.getInstanciaBl();



    private int nColumns = 3;





    public void initialize() throws SQLException {
        Main.userInformation(labelUserName, photoUser);
        loadVideosPlayList();
        nombrePlayList.setText(blConexion.getActualPlayList().getNombre());


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
        vBox.getChildren().clear();

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        vBox.setSpacing(10);
        int count = 0;

        for(int i = 0; i < listaVideos.size(); i++){
            count = i + 1;
            Image img;
            Video video = listaVideos.get(i);


            if(video.getThumbnailVideo() != null && !video.getThumbnailVideo().equals("")){
                img = new Image("file:" + video.getThumbnailVideo());
            }else{
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

            if(count % Main.NCOLUMNS == 0 || count == listaVideos.size()){
                vBox.getChildren().add(hBox);
                hBox = new HBox();
                hBox.setSpacing(5);
            }
        }


    }

}
