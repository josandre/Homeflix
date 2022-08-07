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
    public Button btnRegistrarse;

    @FXML
    public  PasswordField txtConstrasenna;

    private BL blConexion = BL.getInstanciaBl();

    public void initialize() {


    }


    public void handleButtonLogIn(ActionEvent event) throws SQLException, IOException {
        System.out.println("entrando");
        Usuario usuario =  blConexion.buscarUsuario(txtConstrasenna.getText(), txtUsuario.getText());

        if(usuario == null && !(txtUsuario.getText().isEmpty() && txtConstrasenna.getText().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nombre de usuario o contraseña incorrecta");
            alert.setContentText("¿Desea intentar denuevo?");
            ButtonType tryAgainButton = new ButtonType("Intentar denuevo");
            ButtonType registerButton = new ButtonType("Registrarse");
            alert.getButtonTypes().setAll(tryAgainButton, registerButton);

            Optional<ButtonType> result = alert.showAndWait();


            if(result.get() == registerButton){
                Main.cambiaPantalla("registrarUsuario");
            }

        } else if (usuario != null) {
            Main.cambiaPantalla("paginaPrincipal");
        }
    }

    public void handleButtonExit(ActionEvent event){
        System.exit(0);
    }

    public void handleButtonRegister() throws IOException {
        Main.cambiaPantalla("registrarUsuario");
    }




}
