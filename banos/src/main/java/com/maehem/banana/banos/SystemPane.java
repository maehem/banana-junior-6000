/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maehem.banana.banos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author mark
 */
public class SystemPane extends StackPane {
    private final Image floppyGlyph = new Image(getClass().getResourceAsStream("/glyphs/floppy-disk.png"));
    
    public SystemPane() {
        
        getChildren().add(new ImageView(floppyGlyph));
    }
    
    
}
