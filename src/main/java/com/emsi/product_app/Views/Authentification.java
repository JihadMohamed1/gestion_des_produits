package com.emsi.product_app.Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Authentification extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Authentification.class.getResource("Authentification.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 240);
        stage.setTitle("Authentification");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
      launch();
    }
}