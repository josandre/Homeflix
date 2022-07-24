package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInView {
    @FXML
    public Label loginMessageLabel;
    @FXML
    public Button cancelButton;
    @FXML
    public ImageView brandingImageView;
    @FXML
    public ImageView lockImageView;
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField enterPasswordField;

    //Arreglando el bug de las imagenes
    public void initialize() {


    }

    //Validacion
    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
            validarLogin();
        } else {
            loginMessageLabel.setText("Por favor ingrese su ID y/o PW");
        }
    }


    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validarLogin() {

    }
}
