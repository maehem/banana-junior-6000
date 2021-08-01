/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maehem.banana.banos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author mark
 */
public class System extends Application {

    private Stage stage;
    private static final String APP_NAME = "Banana Jr. 6000";
    
    private StackPane glass = new StackPane();

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle(APP_NAME);
        initProperties();
        glass.setPrefWidth(512);
        glass.setPrefHeight(342);
        
        var scene = new Scene(glass);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        
        glass.getChildren().add(new SystemPane());
        
        stage.show();
    }
    
    private void initProperties() {
        
    }

    public static void main(String[] args) {
        launch();
    }

}
