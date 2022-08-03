package view;

import controller.BL;
import model.Usuario;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LogInView {

    @FXML
    public TextField txtUsuario;



    @FXML
    public  TextField txtConstrasenna;

    private BL blConexion = BL.getInstanciaBl();




    public void initialize() {


    }


    public void handleButtonLogIn(ActionEvent event) throws SQLException, IOException {
        System.out.println("entrando");
        Usuario usuario =  blConexion.buscarUsuario(txtConstrasenna.getText(), txtUsuario.getText());
      if(usuario != null){
          Main.cambiaPantalla("paginaPrincipal");

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

    public void handleButtonExit(){
        System.exit(0);
    }


}
