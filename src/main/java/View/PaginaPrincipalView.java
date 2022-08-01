package View;

import Controller.BL;
import Model.Usuario;
import Model.Video;
import com.example.proyecto.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class PaginaPrincipalView {

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
    public AnchorPane idAnchorPane;

    @FXML
    public Button btnAddVideo;

    ObservableList<Video> listaVideos = FXCollections.observableArrayList();
    private BL blConexion = BL.getInstanciaBl();


    public void initialize() throws SQLException {
        Usuario usuarioActual = blConexion.getUsuarioActual();
        labelUserName.setText(usuarioActual.getNombreUsuario());

        if(usuarioActual.getArchivoImagen() != null && usuarioActual.getArchivoImagen().equalsIgnoreCase(" ")){
            Image image = new Image("file:" + usuarioActual.getArchivoImagen());

            imageView.setFill(new ImagePattern(image));
        }else {
            URL urlImage =  Main.class.getResource("img/defaultImage.png");
            Image imageDefault = new Image(urlImage.toString());
            imageView.setFill(new ImagePattern(imageDefault));
        }
        loadData();
    }



    public void loadData() throws SQLException {
        for (int i = 0; i < blConexion.listarVideos().size(); i++) {
            Image img = new Image("file: " + blConexion.listarVideos().get(i).getThumbnailVideo());
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(200);
            imageView.setFitWidth(150);
            idAnchorPane.getChildren().add(imageView);
        }



    }

    public void handleButtonSearch(ActionEvent event)throws SQLException{
        ArrayList<Video> videosEncontrados = blConexion.buscarVideo(txtBuscar.getText());

    }

    public void handleButtonAddVideo() throws IOException {
        Main.cambiaPantalla("registrarVideo");
    }
    /**public void loadDataVideo(ArrayList<Video> videosEncontrados)throws SQLException{
        table.getItems().clear();
        listaVideos.removeAll();
        listaVideos.addAll(videosEncontrados);
        table.setItems(listaVideos);
    }**/

    public void handleButtonExit() throws IOException {
        Main.cambiaPantalla("login");
    }


}
