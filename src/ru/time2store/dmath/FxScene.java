package ru.time2store.dmath;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class FxScene extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("fxscene.fxml"));
        primaryStage.setTitle("Лабораторная работ №2");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }
}
