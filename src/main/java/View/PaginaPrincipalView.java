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
    public TableView<Video> table;

    @FXML
    public TableColumn<Video, String> colNombre;

    @FXML
    public TableColumn<Video, String> colDescripcion;

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



        colNombre.setCellValueFactory(video -> new SimpleStringProperty(video.getValue().getNombre()));
        colDescripcion.setCellValueFactory(video -> new SimpleStringProperty(video.getValue().getDescripcion()));
        loadData();
    }



    public void loadData() throws SQLException {
        listaVideos.removeAll();
        listaVideos.addAll(blConexion.listarVideos());
        table.setItems(listaVideos);

    }

    public void handleButtonSearch(ActionEvent event)throws SQLException{
        ArrayList<Video> videosEncontrados = blConexion.buscarVideo(txtBuscar.getText());
        loadDataVideo(videosEncontrados);

    }

    public void loadDataVideo(ArrayList<Video> videosEncontrados)throws SQLException{
        table.getItems().clear();
        listaVideos.removeAll();
        listaVideos.addAll(videosEncontrados);
        table.setItems(listaVideos);
    }

    public void handleButtonExit() throws IOException {
        Main.cambiaPantalla("login");
    }


}
