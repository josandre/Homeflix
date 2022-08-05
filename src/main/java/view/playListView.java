package view;

import com.example.proyecto.Main;
import controller.BL;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.sql.SQLException;

public class playListView {
    @FXML
    public Label labelUserName;

    @FXML
    public Label nombrePlayList;

    @FXML
    public Circle photoUser;

    @FXML
    public GridPane GridPane;

    @FXML
    public ImageView back;

    private BL blConexion = BL.getInstanciaBl();

    public void initialize(){
        Main.userInformation(labelUserName, photoUser);

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

    public void loadVideosPlayList(){

    }


}
