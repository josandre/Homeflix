package view;

import controller.BL;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Usuario;
import model.Video;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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

    @FXML
    public ImageView imgBack;

    public Border border = Main.obtenerBordeError();



    private BL blConexion = BL.getInstanciaBl();
    Stage stage;
    {
        if(mainPane != null){
            stage = (Stage) mainPane.getScene().getWindow();
        }
    }

    public void initialize(){
        Main.userInformation(labelActualUser, actualUserPhoto);

        imgBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.cambiaPantalla("paginaPrincipal");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    /**
     * @param event Este evento registra el video
     */
    @FXML
    public void handleButtonRegistrar(ActionEvent event) throws SQLException, IOException {

        if(registrarVerificacion() == true){
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

            Main.showAlert("Exito", "Registro Exitoso", "OK", "paginaPrincipal", Alert.AlertType.INFORMATION);
        }
    }



    /**
     * Esta funcion permite la verfificacion de los espacios en blanco del registro
     */
    public boolean registrarVerificacion(){
        String nombre = txtNombreVideo.getText();
        String categoria = txtCategoria.getText();
        String descripcion = txtDescripcion.getText();
        String subirArchivo = txtSubirArchivo.getText();



        boolean esValido = true;


        if(nombre == null || txtNombreVideo.getText().isEmpty()){
            esValido = false;
            txtNombreVideo.setBorder(border);

        }

        if(categoria == null || txtCategoria.getText().isEmpty()){
            esValido = false;
            txtCategoria.setBorder(border);

        }

        if(descripcion == null || txtDescripcion.getText().isEmpty()){
            esValido = false;
            txtDescripcion.setBorder(border);


        }

        if(subirArchivo == null || txtSubirArchivo.getText().isEmpty()){
            esValido = false;
            txtSubirArchivo.setBorder(border);


        }

        return esValido;

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