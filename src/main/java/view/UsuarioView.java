package view;

import model.Usuario;
import controller.BL;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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
    public void handleButtonRegistrarUsuario(ActionEvent event) throws SQLException {
        if(registrarUsuarioVerificacion() == true){
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


            blConexion.crearUsuario(usuario);
        }
    }

    public boolean registrarUsuarioVerificacion() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String nombreUsuario = txtNombreUsuario.getText();
        String contrasenna = txtContrasenna.getText();
        String archivoImagen = txtArchivoImagen.getText();


        boolean esValido = false;

        if (nombre != null && !txtNombre.getText().isEmpty()) {
            esValido = true;
        } else {
            esValido = false;
            txtNombre.setBorder(obtenerBorderError());
        }

        if (apellido != null && !txtApellido.getText().isEmpty()) {
            esValido = true;
        } else {
            esValido = false;
            txtApellido.setBorder(obtenerBorderError());
        }

        if (nombreUsuario != null && !txtNombreUsuario.getText().isEmpty()) {
            esValido = true;
        } else {
            esValido = false;
            txtNombreUsuario.setBorder(obtenerBorderError());
        }

        if (contrasenna != null && !txtContrasenna.getText().isEmpty()) {
            esValido = true;
        } else {
            esValido = false;
            txtContrasenna.setBorder(obtenerBorderError());
        }



        if(esValido){
            return true;
        }else {
            return false;
        }
    }

    public static Border obtenerBorderError() {
        return new Border(new BorderStroke(Color.RED, Color.RED, Color.RED, Color.RED,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY));
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

