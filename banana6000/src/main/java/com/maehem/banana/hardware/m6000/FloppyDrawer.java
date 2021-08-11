/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maehem.banana.hardware.m6000;

import com.maehem.banana.storage.Floppy;
import javafx.scene.layout.Pane;

/**
 *
 * @author mark
 */
public class FloppyDrawer extends Pane {

    public FloppyDrawer(Computer computer, double width, double height) {
        //setWidth(width);
        //setHeight(height);
        
        Floppy systemFloppy = new Floppy();
        systemFloppy.setX(Math.random()*width/4);
        systemFloppy.setY(Math.random()*height/4);
        systemFloppy.setOnMouseClicked((t) -> {
            computer.mediaInserted(systemFloppy);
        });
        getChildren().add(systemFloppy);
    }
    
    
}
