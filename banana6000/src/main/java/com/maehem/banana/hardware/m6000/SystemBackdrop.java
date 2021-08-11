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

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author mark
 */
public class SystemBackdrop extends Pane {
    Canvas canvas = new Canvas();
    ImageView iv = new ImageView();
    
    Color[] COL = { Color.BLACK, Color.LIGHTGRAY };

    public SystemBackdrop() {
        getChildren().add(iv);
    }
    
    
    public void update() {
        WritableImage wi = new WritableImage((int)getWidth(), (int)getHeight());
        int col = 0;
        PixelWriter pw = wi.getPixelWriter();
        for ( int y=0; y<wi.getHeight(); y++ ) {
            int rCol = col;
            for ( int x=0; x<wi.getWidth(); x++ ) {
                pw.setColor(x, y, COL[rCol]);
                if ( x % 2 == 1 ) rCol ^= 1;
            }
            if ( y % 2 == 1 ) col ^= 1;  // Next row starts with opposite color
        }
        
        iv.setImage(wi);
    }
}
