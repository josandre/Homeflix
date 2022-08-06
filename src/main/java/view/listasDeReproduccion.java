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

    @FXML
    public GridPane gridPane;

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
        ArrayList<ListaReproduccion> playList = blConexion.listarReproductionList(userId);
        gridPane.getChildren().clear();
         int count = 0;
         double nFilas = ((double) playList.size()) / ((double) Main.NCOLUMNS);
         int totalFilas = (int)Math.ceil(nFilas);

         for(int fila = 0; fila < totalFilas; fila++ ){
             int filaActual = fila + 1;
             int cantMaximaVideos = filaActual * Main.NCOLUMNS;
             int camposSobrantes = playList.size() < cantMaximaVideos ? cantMaximaVideos - playList.size(): 0;
             int totalColumnas = Main.NCOLUMNS - camposSobrantes;

             for(int columna = 0; columna < totalColumnas; columna++){
                 Image img;
                 ListaReproduccion listaReproduccion = playList.get(count);

                 if(listaReproduccion.getArchivoImagen() != null && !listaReproduccion.getArchivoImagen().equals("")){
                     img = new Image("file:" + listaReproduccion.getArchivoImagen());
                 }else{
                     URL urlImagen = Main.class.getResource("img/defaultReproductionList.jpeg");
                     img = new Image(urlImagen.toString());
                 }
                 ImageView imageView = new ImageView(img);
                 imageView.setFitHeight(120);
                 imageView.setFitWidth(100);
                 GridPane.setRowIndex(imageView, fila);
                 GridPane.setColumnIndex(imageView, columna);
                 gridPane.getChildren().add(imageView);
                 gridPane.setAlignment(Pos.CENTER);
                 count++;
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
