package com.superx;

import com.superx.Controller.ViewController;
import com.superx.Controller.firebasesdk;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        firebasesdk.initialize();
        
       
        try {
            Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
            primaryStage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Could not load application icon: " + e.getMessage());
        }
        
        ViewController controller = new ViewController(primaryStage, getHostServices());
        primaryStage.setTitle("E-Help Desk");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
