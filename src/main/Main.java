package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage){
        GamePanel gamePanel = new GamePanel();
        Scene scene = new Scene(gamePanel);
        stage.setTitle("The Tenth Door");
        stage.setScene(scene);
        stage.show();

        gamePanel.setUpGame();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
