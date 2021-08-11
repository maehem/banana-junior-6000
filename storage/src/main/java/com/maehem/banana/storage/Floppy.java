/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maehem.banana.storage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author mark
 */
public class Floppy extends ImageView {
    private final Image image = new Image(
        getClass().getResourceAsStream("/other/green-floppy-disk.png"),
        160, 160, false, false
    );

    public Floppy() {
        setImage(image);
        setRotate((Math.random()-0.5)*90);
    }
    
    
}
