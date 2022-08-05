package view;

import com.example.proyecto.Main;
import controller.BL;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.shape.Circle;
import model.ListaReproduccion;

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
    public Circle circleUserPhoto;

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
