package view;

import controller.BL;
import model.Usuario;
import model.Video;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Esta clase maneja el registro de los videos
 */
public class RegistroVideoView {

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
    public TextField txtSubirThumbnail;

    @FXML
    public AnchorPane mainPane;

    @FXML
    public Label labelActualUser;

    @FXML
    public Circle actualUserPhoto;



    private BL blConexion = BL.getInstanciaBl();
    Stage stage;
    {
        if(mainPane != null){
            stage = (Stage) mainPane.getScene().getWindow();
        }
    }

    public void initialize(){
        Usuario usuarioActual = blConexion.getUsuarioActual();
        labelActualUser.setText(usuarioActual.getNombreUsuario());

        if(usuarioActual.getArchivoImagen() != null && usuarioActual.getArchivoImagen().equalsIgnoreCase("")){
            Image image = new Image("file:" + usuarioActual.getArchivoImagen());
            actualUserPhoto.setFill(new ImagePattern(image));
        }else {
            URL urlImage =  Main.class.getResource("img/defaultImage.png");
            Image imageDefault = new Image(urlImage.toString());
            actualUserPhoto.setFill(new ImagePattern(imageDefault));
        }


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
            String subirArchivoImagen = txtSubirThumbnail.getText();
            video.setCalificacion(0);
            video.setNombre(nombre);
            video.setCategoria(categoria);
            video.setDescripcion(descripcion);
            video.setArchivo(subirArchivo);
            video.setThumbnailVideo(subirArchivoImagen);
            video.setUserId(blConexion.getUsuarioActual().getId());
            blConexion.getUsuarioActual().getUserVideos().add(video);
            System.out.println(blConexion.getUsuarioActual().getUserVideos());
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
        String subirThumbnail = txtSubirThumbnail.getText();


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



        if(!esValido){
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
    public void handleButtonCancelar(ActionEvent event) throws IOException {
        Main.cambiaPantalla("paginaPrincipal");

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
    @FXML
    public void handleButtonSubirThumbnail(ActionEvent event){
        this.txtSubirThumbnail.setEditable(false);
        System.out.println("subiendo archivo");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir recurso del video");
        File file = fileChooser.showOpenDialog(stage);

        if(file != null){
            this.txtSubirThumbnail.setText(file.getAbsolutePath());
        }
    }

}