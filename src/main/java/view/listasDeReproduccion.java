package view;

import com.example.proyecto.Main;
import controller.BL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    public Button btnAddList;

    @FXML
    public VBox vBox;

    private BL blConexion = BL.getInstanciaBl();



    public void initialize() throws SQLException {
        Usuario usuarioActual = blConexion.getUsuarioActual();
        Main.userInformation(labelUserName, circlePhotoUser);
        loadPlayList(usuarioActual.getId());

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



    }

    public void loadPlayList(int userId)throws SQLException {
        ArrayList<ListaReproduccion> playLists = blConexion.listarReproductionList(userId);
        vBox.getChildren().clear();

         HBox hBox = new HBox();
         hBox.setSpacing(5);
         vBox.setSpacing(10);
         int count = 0;

         for(int i = 0; i < playLists.size(); i++){
             count = i +1;
             Image img;
             ListaReproduccion listaReproduccion = playLists.get(i);

             if(listaReproduccion.getArchivoImagen() != null && !listaReproduccion.getArchivoImagen().equals("")){
                 img = new Image("file:" + listaReproduccion.getArchivoImagen());
             }else{
                 URL urlImagen = Main.class.getResource("img/defaultReproductionList.jpeg");
                 img = new Image(urlImagen.toString());
             }

             ImageView imageView = new ImageView(img);
             imageView.setFitHeight(Main.HEIGHT);
             imageView.setFitWidth(Main.WIDTH);
             Label label = new Label();
             label.setText(listaReproduccion.getNombre());
             VBox contenedor = new VBox();
             contenedor.setSpacing(5);
             contenedor.getChildren().addAll(label, imageView);
             hBox.getChildren().add(contenedor);

             if(count % Main.NCOLUMNS == 0 || count == playLists.size()){
                 vBox.getChildren().add(hBox);
                 hBox = new HBox();
                 hBox.setSpacing(5);
             }





         }
    }


    public void handleButtonAddList(ActionEvent event) throws IOException {
        Main.cambiaPantalla("crearListaReproduccion");
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
