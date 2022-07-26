package View;

import Controller.BL;
import Model.Video;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class PaginaPrincipalView {
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


}
