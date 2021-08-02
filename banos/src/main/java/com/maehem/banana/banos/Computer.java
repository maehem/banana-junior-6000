/*
 * Copyright 2021 Mark J. Koch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
public class Computer extends Application {

    private Stage stage;
    private static final String APP_NAME = "Banana Jr. 6000";
    
    private final StackPane glass = new StackPane();
    private SystemPane systemPane;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setResizable(false);
        stage.setTitle(APP_NAME);
        initProperties();
        glass.setPrefWidth(512);
        glass.setPrefHeight(342);
        
        var scene = new Scene(glass);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        
        systemPane = new SystemPane();
        glass.getChildren().add(systemPane);
        
        stage.show();
        systemPane.update();
        System.out.println("System Pane size: " + systemPane.getWidth() + "x" + systemPane.getHeight());
        
    }
    
    private void initProperties() {
        
    }

    public static void main(String[] args) {
        launch();
    }

    
}