package view;

import javafx.scene.control.*;
import model.Usuario;
import controller.BL;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioView {
    @FXML
    public Button btnRegistrarUsuario;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtApellido;
    @FXML
    public TextField txtNombreUsuario;

    @FXML
    public TextField txtContrasenna;
    @FXML
    public TextField txtArchivoImagen;
    @FXML
    public Button btnCancelarRegistro;


    @FXML
    public AnchorPane mainPane;

    @FXML
    public Border border = Main.obtenerBordeError();

    private BL blConexion = BL.getInstanciaBl();

    Stage stage;
    {
        if(mainPane != null){
            stage = (Stage) mainPane.getScene().getWindow();
        }
    }

    public void initialize() {
        System.out.println("inicializando");
    }

    @FXML
    public void handleButtonRegistrarUsuario(ActionEvent event) throws SQLException, IOException {
        if(registrarUsuarioVerificacion()){
            Usuario usuario = new Usuario();

            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String nombreUsuario = txtNombreUsuario.getText();
            String contrasenna = txtContrasenna.getText();
            String archivoImagen = txtArchivoImagen.getText();


            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasenna(contrasenna);
            usuario.setArchivoImagen(archivoImagen);


            blConexion.adduser(usuario);
        }
    }

    public boolean registrarUsuarioVerificacion() throws SQLException, IOException {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String nombreUsuario = txtNombreUsuario.getText();
        String contrasenna = txtContrasenna.getText();

        boolean esValido = true;

        if (nombre == null || txtNombre.getText().isEmpty()) {
            esValido = false;
            txtNombre.setBorder(border);
        }

        if (apellido == null || txtApellido.getText().isEmpty()) {
            esValido = false;
            txtApellido.setBorder(border);
        }

        if (nombreUsuario != null && !txtNombreUsuario.getText().isEmpty()){
            boolean userExists = blConexion.userExists(txtNombreUsuario.getText());
            if(userExists){
                esValido = false;
                txtNombreUsuario.setBorder(border);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setContentText("This userName is already taken");
                ButtonType okButton = new ButtonType("OK");
                ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, cancelButton);

                Optional<ButtonType> result = alert.showAndWait();

                if(result.get() == okButton){
                    txtNombreUsuario.setText("");
                }else if(result.get() == cancelButton){
                    Main.cambiaPantalla("login");
                }
            }

        } else {
            esValido = false;
            txtNombreUsuario.setBorder(border);

        }

        if (contrasenna == null || txtContrasenna.getText().isEmpty()) {
            esValido = false;
            txtContrasenna.setBorder(border);
        }

        return esValido;
    }

    @FXML
    public void handleButtonSubirArchivoImagen(ActionEvent event){
        this.txtArchivoImagen.setEditable(false);
        System.out.println("subiendo archivo");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir recurso del video");
        File file = fileChooser.showOpenDialog(stage);

        if(file != null){
            this.txtArchivoImagen.setText(file.getAbsolutePath());
        }
    }
    @FXML
    public void handleButtonCancelarRegsitro(ActionEvent event) throws IOException {
        Main.cambiaPantalla("login");
        System.out.println("cancelando...");
    }
}

