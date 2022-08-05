package com.example.proyecto;

import controller.BL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Usuario;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static Scene  escenaPrincipal;

    public static final int HEIGHT = 150;
    public static final int WIDTH = 150;

    public static final int NCOLUMNS = 3;

    private static BL blConexion = BL.getInstanciaBl();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 630, 430);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        escenaPrincipal = scene;
        stage.show();

    }



    public static void cambiaPantalla(String nombrePantlla) throws IOException {
        String pantalla = nombrePantlla + ".fxml";
        Parent parent =  FXMLLoader.load(Main.class.getResource(pantalla));
        escenaPrincipal.setRoot(parent);

    }

    public static void userInformation(Label userNameLabel, Circle circlePhoto){
        Usuario usuarioActual = blConexion.getUsuarioActual();
        userNameLabel.setText(usuarioActual.getNombreUsuario());

        if(usuarioActual.getArchivoImagen() != null && !usuarioActual.getArchivoImagen().equalsIgnoreCase("")){
            Image image = new Image("file:" + usuarioActual.getArchivoImagen());
            circlePhoto.setFill(new ImagePattern(image));
            System.out.println(image.getUrl());
        }else {
            URL urlImage =  Main.class.getResource("img/defaultImage.png");
            Image imageDefault = new Image(urlImage.toString());
            circlePhoto.setFill(new ImagePattern(imageDefault));
        }
    }

    public static Border obtenerBordeError(){
        return new Border(new BorderStroke(Color.RED, Color.RED, Color.RED, Color.RED,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY));
    }
}