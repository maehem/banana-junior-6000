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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author mark
 */
public class SystemPane extends StackPane {
    private final SystemBackdrop backdrop = new SystemBackdrop();
    private final Image floppyGlyph = new Image(
            getClass().getResourceAsStream("/glyphs/floppy-disk.png"),
            32, 32, false, false
    );
    
    public SystemPane() {
        
        getChildren().addAll(backdrop, new ImageView(floppyGlyph));
    }
    
    public void update() {
        backdrop.update();
    }
    
}
