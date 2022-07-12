package View;

import Controller.BL;
import Model.Video;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Esta clase maneja el registro de los videos
 */
public class RegistroView {

    @FXML
    public TextField txtNombreVideo;

    @FXML
    public TextField txtCategoria;

    @FXML
    public TextArea txtDescripcion;

    @FXML
    public Button btnRegistrar;

    @FXML
    public Button btnCancelar;

    @FXML
    public TextField txtSubirArchivo;

    @FXML
    public AnchorPane mainPane;

    private BL blConexion = BL.getInstanciaBl();
    Stage stage;
    {
        if(mainPane != null){
            stage = (Stage) mainPane.getScene().getWindow();
        }
    }



    public void initialize(){
        System.out.println("initialize");
    }

    /**
     * @param event Este evento registra el video
     */
    @FXML
    public void handleButtonRegistrar(ActionEvent event) throws SQLException {

        if(registrarVerificacion()){
            Video video = new Video();
            video.setFecha(LocalDate.now());
            String nombre = txtNombreVideo.getText();
            String categoria = txtCategoria.getText();
            String descripcion = txtDescripcion.getText();
            String subirArchivo = txtSubirArchivo.getText();
            video.setCalificacion(0);
            video.setNombre(nombre);
            video.setCategoria(categoria);
            video.setDescripcion(descripcion);
            video.setArchivo(subirArchivo);

            blConexion.annadirVideo(video);

        }
    }

    public void handleButtonReproducir(ActionEvent event) throws IOException {
        Main.cambiaPantalla("reproducirVideo");
    }


    /**
     * Esta funcion permite la verfificacion de los espacios en blanco del registro
     */
    public boolean registrarVerificacion(){
        String nombre = txtNombreVideo.getText();
        String categoria = txtCategoria.getText();
        String descripcion = txtDescripcion.getText();
        String subirArchivo = txtSubirArchivo.getText();

        boolean esValido = false;


        if(nombre != null && !txtNombreVideo.getText().isEmpty()){
            esValido = true;

        }else{
            esValido = false;
            txtNombreVideo.setBorder(obtenerBordeError());
        }

        if(categoria != null && !txtCategoria.getText().isEmpty()){
            esValido = true;

        }else{
            esValido = false;
            txtCategoria.setBorder(obtenerBordeError());
        }

        if(descripcion != null && !txtDescripcion.getText().isEmpty()){
            esValido = true;


        }else {
            esValido = false;
            txtDescripcion.setBorder(obtenerBordeError());
        }

        if(subirArchivo != null && !txtSubirArchivo.getText().isEmpty()){
            esValido = true;

        }else{
            esValido = false;
            txtSubirArchivo.setBorder(obtenerBordeError());
        }

        if(!esValido ){
            return  false;
        }else {
            return true;
        }

    }

    /**
     * Esta funcion crea un borde rojo con el que se trabaja las validaciones de espacios en blanco
     * @return Esta funcion retorna un borde rojo
     *
     */
    public static Border obtenerBordeError(){
       return new Border(new BorderStroke(Color.RED, Color.RED, Color.RED, Color.RED,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY));
    }

    /**
     * @param event Este evento cancela el registro y envia al usuario a la pagina principal
     */
    @FXML
    public void handleButtonCancelar(ActionEvent event){
        System.out.println("cancelando");
    }

    /**
     * @param event Este evento permite subir el archivo del video que se esta registrando
     */
    @FXML
    public void handleButtonSubirArchivo(ActionEvent event){
        this.txtSubirArchivo.setEditable(false);
        System.out.println("subiendo archivo");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir recurso del video");
        File file = fileChooser.showOpenDialog(stage);

        if(file != null){
            this.txtSubirArchivo.setText(file.getAbsolutePath());
        }
    }



}
