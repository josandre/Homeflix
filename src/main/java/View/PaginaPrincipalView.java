package View;

import Controller.BL;
import Model.Usuario;
import Model.Video;
import com.example.proyecto.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class PaginaPrincipalView {

    public static final int HEIGHT_VIDEO_IMAGE = 200;
    public static final int WIDTH_VIDEO_IMAGE = 150;
    private static PaginaPrincipalView instancia;

    @FXML
    public Label labelUserName;

    @FXML
    public Circle imageView;

    @FXML
    public Button btnBuscar;

    @FXML
    public TextField txtBuscar;

    @FXML
    public HBox listaVideos;

    @FXML
    public Button btnAddVideo;

    private BL blConexion = BL.getInstanciaBl();


    public void initialize() throws SQLException {
        Usuario usuarioActual = blConexion.getUsuarioActual();
        labelUserName.setText(usuarioActual.getNombreUsuario());

        if(usuarioActual.getArchivoImagen() != null && usuarioActual.getArchivoImagen().equalsIgnoreCase(" ")){
            Image image = new Image("file:" + usuarioActual.getArchivoImagen());

            imageView.setFill(new ImagePattern(image));
        }else {
            URL urlImage =  Main.class.getResource("img/defaultImage.png");
            Image imageDefault = new Image(urlImage.toString());
            imageView.setFill(new ImagePattern(imageDefault));
        }

        listaVideos.setSpacing(5);

        loadData();
    }



    public void loadData() throws SQLException {
        ArrayList<Video> videos = blConexion.listarVideos();
        for (int i = 0; i < videos.size(); i++) {




            if(videos.get(i).getThumbnailVideo() != null && videos.get(i).getThumbnailVideo().equals(" ")){
                Image img = new Image("file: " + videos.get(i).getThumbnailVideo());

                ImageView imageView = new ImageView(img);
                imageView.setFitHeight(HEIGHT_VIDEO_IMAGE);
                imageView.setFitWidth(WIDTH_VIDEO_IMAGE);

            }else {
                URL urlImage2 =  Main.class.getResource("img/defaullImageVideo.jpeg");
                Image imageDefault = new Image(urlImage2.toString());
                ImageView imageView = new ImageView(imageDefault);
                imageView.setFitHeight(HEIGHT_VIDEO_IMAGE);
                imageView.setFitWidth(WIDTH_VIDEO_IMAGE);
            }

            listaVideos.getChildren().add(imageView);


        }



    }

    public void handleButtonSearch(ActionEvent event)throws SQLException{
        ArrayList<Video> videosEncontrados = blConexion.buscarVideo(txtBuscar.getText());

    }

    public void handleButtonAddVideo() throws IOException {
        Main.cambiaPantalla("registrarVideo");
    }
    /**public void loadDataVideo(ArrayList<Video> videosEncontrados)throws SQLException{
        table.getItems().clear();
        listaVideos.removeAll();
        listaVideos.addAll(videosEncontrados);
        table.setItems(listaVideos);
    }**/

    public void handleButtonExit() throws IOException {
        Main.cambiaPantalla("login");
    }


}
