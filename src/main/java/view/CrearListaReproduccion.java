package view;

import com.example.proyecto.Main;
import controller.BL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ListaReproduccion;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class CrearListaReproduccion {
    @FXML
    public TextField txtNombre;

    @FXML
    public TextField txtEnlaceImagen;

    @FXML
    public Button btnSubirArchivo;

    @FXML
    public ImageView imgBack;

    @FXML
    public Label userNameLabel;

    @FXML
    public AnchorPane mainPane;

    @FXML
    public Circle circleUserPhoto;


    Stage stage;
    {
        if(mainPane != null){
            stage = (Stage) mainPane.getScene().getWindow();
        }
    }

    public Border border = Main.obtenerBordeError();

     private BL blConexion = BL.getInstanciaBl();


    public void initialize(){
        Main.userInformation(userNameLabel, circleUserPhoto);

        imgBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Main.cambiaPantalla("listasDeReproduccion");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    public void handleButonAddList() throws SQLException {
        if(verfificacionRegistro()){
            ListaReproduccion listaVideos =  new ListaReproduccion();

            String nombre = txtNombre.getText();
            String enlaceImagen = txtEnlaceImagen.getText();


            listaVideos.setNombre(nombre);
            listaVideos.setArchivoImagen(enlaceImagen);
            listaVideos.setUserId(blConexion.getUsuarioActual().getId());

            blConexion.addReproductionList(listaVideos);

        }
    }

    public void handleButtonSubirArchivo(ActionEvent event){
        this.txtEnlaceImagen.setEditable(false);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir recurso del video");
        File file = fileChooser.showOpenDialog(stage);

        if(file != null){
            this.txtEnlaceImagen.setText(file.getPath());
            System.out.println(txtEnlaceImagen);

        }
    }

    public boolean verfificacionRegistro(){
        String nombre = txtNombre.getText();


        boolean esValido = true;

        if(nombre == null || txtNombre.getText().isEmpty()){
            esValido = false;
            txtNombre.setBorder(border);
        }

        return esValido;
    }


}
