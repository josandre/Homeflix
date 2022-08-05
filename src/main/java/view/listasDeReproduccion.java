package view;

import com.example.proyecto.Main;
import controller.BL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import model.ListaReproduccion;
import model.Usuario;
import model.Video;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class listasDeReproduccion {
    @FXML
    public Label labelUserName;

    @FXML
    public Circle circlePhotoUser;

    @FXML
    public ImageView imgBack;

    @FXML
    public HBox hBox;

    @FXML
    public Button btnAddList;

    private BL blConexion = BL.getInstanciaBl();



    public void initialize() throws SQLException {
        Main.userInformation(labelUserName, circlePhotoUser);

        imgBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.cambiaPantalla("paginaPrincipal");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        Usuario usuarioActual = blConexion.getUsuarioActual();
        loadData(usuarioActual.getId());

    }


    public void handleButtonAddList(ActionEvent event) throws IOException {
        Main.cambiaPantalla("crearListaReproduccion");
    }

    public void loadData(int userId) throws SQLException {
        ArrayList<ListaReproduccion> listasDeReproduccion = blConexion.listarReproductionList(userId);

        for(int i = 0; i < listasDeReproduccion.size(); i ++){
            Image img;
            ListaReproduccion listaReproducion = listasDeReproduccion.get(i);

            if(listaReproducion.getArchivoImagen() != null && !listaReproducion.getArchivoImagen().equals("")){
                img = new Image("file:" + listaReproducion.getArchivoImagen());

            }else{
                URL urlImage =  Main.class.getResource("img/defaultReproductionList.jpeg");
                img = new Image(urlImage.toString());
            }

            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(Main.HEIGHT);
            imageView.setFitWidth(Main.WIDTH);
            imageView.setPickOnBounds(true);

            choosePlaylist(imageView, listaReproducion);
            hBox.getChildren().add(imageView);
        }
    }

    public void choosePlaylist(ImageView imageView, ListaReproduccion playList){
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                try {
                    addVideo(blConexion.getActualVideo().getId(), playList.getId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    public void addVideo(int idVideo, int idPlayList)throws SQLException{
        blConexion.addVideoToPlayList(idVideo, idPlayList);
    }


}
