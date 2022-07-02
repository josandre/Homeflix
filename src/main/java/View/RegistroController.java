package View;

import Model.Video;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Esta clase maneja el registro de los videos
 */
public class RegistroController {

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
    public void handleButtonRegistrar(ActionEvent event){
        registrarVerificacion();
    }

    /**
     * Esta funcion permite la verfificacion de los espacios en blanco del registro
     */
    public void registrarVerificacion(){
        Video video = new Video();
        String nombre = txtNombreVideo.getText();
        String categoria = txtCategoria.getText();
        String descripcion = txtDescripcion.getText();
        String subirArchivo = txtSubirArchivo.getText();

        if(nombre != null && !txtNombreVideo.getText().isEmpty()){
            video.setNombre(nombre);

        }else{
            txtNombreVideo.setBorder(obtenerBordeError());
        }

        if(categoria != null && !txtCategoria.getText().isEmpty()){
            video.setCategoria(categoria);
        }else{
            txtCategoria.setBorder(obtenerBordeError());
        }

        if(descripcion != null && !txtDescripcion.getText().isEmpty()){
            video.setDescripcion(descripcion);
        }else {
            txtDescripcion.setBorder(obtenerBordeError());
        }

        if(subirArchivo != null && !txtSubirArchivo.getText().isEmpty()){
            video.setArchivo(subirArchivo);
        }else{
            txtSubirArchivo.setBorder(obtenerBordeError());
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
