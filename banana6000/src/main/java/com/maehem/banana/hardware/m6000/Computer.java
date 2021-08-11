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
package com.maehem.banana.hardware.m6000;

import com.maehem.banana.banos.storage.Floppy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
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
    
    private static final double FLOPPY_DRAWER_W = 600;
    private static final double FLOPPY_DRAWER_H = 400;
    
    ImageView centerImageView = new ImageView( new Image (
            getClass().getResourceAsStream("/skins/skin-01/chassis-glass.png"),
            512,342, false, false
    ));
    ImageView topImageView = new ImageView( new Image(
            getClass().getResourceAsStream("/skins/skin-01/chassis-top.png"),
            740, 100, false, false
    ));
    ImageView leftImageView = new ImageView( new Image(
            getClass().getResourceAsStream("/skins/skin-01/chassis-left.png"),
            114, 342, false, false
    ));
    ImageView rightImageView = new ImageView( new Image(
            getClass().getResourceAsStream("/skins/skin-01/chassis-right.png"),
            114, 342, false, false
    ));
    ImageView bottomImageView = new ImageView( new Image(
            getClass().getResourceAsStream("/skins/skin-01/chassis-bottom.png"),
            740, 320, false, false
    ));
    Pane floppySlot = new Pane();
    Group bottomArea = new Group(bottomImageView,floppySlot);
    
    private final StackPane glass = new StackPane();
    private final BorderPane chassisPane = new BorderPane();
    private SystemPane systemPane;
    private Stage floppyDrawerPopup = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setResizable(false);
        stage.setTitle(APP_NAME);
        initProperties();
        glass.setPrefWidth(512);
        glass.setPrefHeight(342);
        
        var scene = new Scene(chassisPane);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        
        initFloppySlot();
        
        chassisPane.setTop(topImageView);
        chassisPane.setLeft(leftImageView);
        chassisPane.setRight(rightImageView);
        chassisPane.setBottom(bottomArea);
        chassisPane.setCenter(glass);
        
        systemPane = new SystemPane();
        systemPane.setOpacity(0);
        glass.getChildren().addAll(centerImageView,systemPane);
        
        stage.show();
        systemPane.update();
        System.out.println("System Pane size: " + systemPane.getWidth() + "x" + systemPane.getHeight());
        
        
    }
    
    private void initFloppySlot() {
        Scene scene = new Scene(new FloppyDrawer( 
                this, FLOPPY_DRAWER_W, FLOPPY_DRAWER_H
            ), 
            FLOPPY_DRAWER_W, FLOPPY_DRAWER_H 
        );
        floppyDrawerPopup.setTitle("Floppies");
        floppyDrawerPopup.setScene(scene);
        
        floppySlot.setLayoutX(355);
        floppySlot.setLayoutY(85);
        floppySlot.setPrefWidth(270);
        floppySlot.setPrefHeight(20);
        floppySlot.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        
        
        floppySlot.setOnMouseClicked((t) -> {
            // Load floppy drawer
            System.out.println("floppySlot: Open floppy drawer.");
            // 355 x 85      ,   625 x 105
            floppyDrawerPopup.showAndWait();
        });
    }
    
    private void initProperties() {
        
    }

    public void mediaInserted(Floppy f) {
        Logger.getGlobal().log(Level.WARNING, "Floppy Inserted.");
        floppyDrawerPopup.hide();
    }
    
    public static void main(String[] args) {
        launch();
    }

    
}
