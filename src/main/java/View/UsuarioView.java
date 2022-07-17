package View;

import Model.Usuario;
import Controller.BL;
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
    public TextField txtId;
    @FXML
    public TextField txtContrasenna;
    @FXML
    public TextField txtArchivoImagen;
    @FXML
    public Button btnCancelarRegistro;
    @FXML
    public AnchorPane mainPane;

    private final BL blConexion = BL.getInstanciaBl();

    Stage stage;
    {
        if(mainPane != null){
            stage = (Stage) mainPane.getScene().getWindow();
        }
    }

    @FXML
    public void handleButtonRegistrarUsuario(ActionEvent event) throws SQLException {
        if(registrarUsuarioVerificacion()){
            Usuario usuario = new Usuario();

            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String nombreUsuario = txtNombreUsuario.getText();
            String id = txtId.getText();
            String contrasenna = txtContrasenna.getText();
            String archivoImagen = txtArchivoImagen.getText();

            usuario.setNombreUsuario(nombre);
            usuario.setApellido(apellido);
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setIdentificacion(Integer.parseInt(id));
            usuario.setContrasenna(contrasenna);
            usuario.setArchivoImagen(archivoImagen);

            blConexion.crearUsuario(usuario);
        }
    }

    public boolean registrarUsuarioVerificacion() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String nombreUsuario = txtNombreUsuario.getText();
        String id = txtId.getText();
        String contrasenna = txtContrasenna.getText();
        String archivoImagen = txtArchivoImagen.getText();

        boolean esValido;

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

        if (id != null && !txtId.getText().isEmpty()) {
            esValido = true;
        } else {
            esValido = false;
            txtId.setBorder(obtenerBorderError());
        }

        if (contrasenna != null && !txtContrasenna.getText().isEmpty()) {
            esValido = true;
        } else {
            esValido = false;
            txtContrasenna.setBorder(obtenerBorderError());
        }

        if (archivoImagen != null && !txtArchivoImagen.getText().isEmpty()) {
            esValido = true;
        } else {
            esValido = false;
            txtArchivoImagen.setBorder(obtenerBorderError());
        }

        return esValido;
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
    public void handleButtonCancelarRegsitro(ActionEvent event){
        System.out.println("cancelando...");
    }
}

