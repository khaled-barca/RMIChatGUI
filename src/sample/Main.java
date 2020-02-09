package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        HomeController homeController = loader.getController();
        homeController.stage = primaryStage;
        primaryStage.setX(500);
        primaryStage.setY(20);
        primaryStage.setTitle("JETS Chat");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 450, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
