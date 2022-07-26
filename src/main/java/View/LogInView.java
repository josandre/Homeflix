package View;

import Controller.BL;
import Model.Usuario;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
        System.out.println("entrando");
       Usuario usuario =  blConexion.buscarUsuario(txtConstrasenna.getText(), txtUsuario.getText());
      if(usuario != null){

          System.out.println("pantalla principal");
      }else {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Esta cuenta no existe en nuestro sistema");
          alert.setContentText("Desea abrir una cuenta");
          ButtonType okButton = new ButtonType("OK");
          ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
          alert.getButtonTypes().setAll(okButton, cancelButton);

          Optional<ButtonType> result = alert.showAndWait();

          if(result.get() == okButton){
              Main.cambiaPantalla("registrarUsuario");
          }else if(result.get() == cancelButton){

              txtUsuario.setText("");
              txtConstrasenna.setText("");
          }

      }
    }




}
