package com.example.watch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    static Stage mainStage;

    public static void switchTo(String fxmlFile){
        try {
            Parent root = FXMLLoader.load(App.class.getResource(fxmlFile));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.setTitle("watch");
            mainStage.setResizable(false);
            mainStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        switchTo("main-view.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}