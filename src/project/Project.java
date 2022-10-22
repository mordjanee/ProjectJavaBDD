/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JFrame;

/**
 *
 * @author mordj
 */
public class Project extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btnAdmin = new Button();
        btnAdmin.setText("Admin");
        btnAdmin.setTranslateX(-50);
        btnAdmin.setTranslateY(-50);
        

        Button btnUser = new Button();
        btnUser.setText("User");
        btnUser.setTranslateX(50);
        btnUser.setTranslateY(-50);

        Button btnExit = new Button();
        btnExit.setText("Exit");
        
        
        StackPane root = new StackPane();
        root.getChildren().addAll(btnAdmin, btnUser, btnExit);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        btnAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JFrame frame = new Admin();
                frame.pack();
                frame.setVisible(true);
            }
        });
        
        btnUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JFrame frame = new User();
                frame.pack();
                frame.setVisible(true);
            }
        });
        
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
