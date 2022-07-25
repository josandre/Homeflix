package View;

import Controller.BL;
import Model.Usuario;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInView {

    @FXML
    public Button cancelButton;

    @FXML
    public  TextField txtUsuario;

    @FXML
    public  TextField txtConstrasenna;


    private BL blConexion = BL.getInstanciaBl();

    //Arreglando el bug de las imagenes
    public void initialize() {


    }


    public void handleButtonLogIn(ActionEvent event) throws SQLException, IOException {
       Usuario usuario =  blConexion.buscarUsuario(txtUsuario.getText(), txtConstrasenna.getText());
      if(usuario != null){
          System.out.println("pantalla principal");
      }else {
          Main.cambiaPantalla("registrarUsuario");
      }
    }


    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validarLogin() {

    }
}
