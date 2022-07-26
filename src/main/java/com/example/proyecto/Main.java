package com.example.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene  escenaPrincipal;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("paginaPrincipal.fxml"));
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
}