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
package com.maehem.banana.banos.system;

import com.maehem.banana.banos.storage.VolumeLoader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author mark
 */
public class SystemPane {

    public enum State {
        INSERT_DISK, HAPPY, SAD
    }

    private State currentState = State.INSERT_DISK;

    private final SystemBackdrop backdrop = new SystemBackdrop();
    private final ImageView floppyGlyph = new ImageView(new Image(
            getClass().getResourceAsStream("/glyphs/floppy-disk.png"),
            32, 32, false, false
    ));
    private final ImageView floppyQGlyph = new ImageView(new Image(
            getClass().getResourceAsStream("/glyphs/floppy-question.png"),
            32, 32, false, false
    ));
    private final ImageView computerBaseGlyph = new ImageView(new Image(
            getClass().getResourceAsStream("/glyphs/computer-base.png"),
            32, 32, false, false
    ));
    private final ImageView computerHappyGlyph = new ImageView(new Image(
            getClass().getResourceAsStream("/glyphs/computer-happy.png"),
            32, 32, false, false
    ));
    private final ImageView computerSadGlyph = new ImageView(new Image(
            getClass().getResourceAsStream("/glyphs/computer-sad.png"),
            32, 32, false, false
    ));
    
    private final StackPane videoOut = new StackPane();
            
    public SystemPane() {        
        videoOut.getChildren().addAll(backdrop,floppyGlyph,floppyQGlyph,computerBaseGlyph, computerHappyGlyph,computerSadGlyph);
        setState(State.INSERT_DISK);
        
        setupFloppyAnimation();
    }

    public void update() {
        videoOut.setOpacity(1.0);
        backdrop.update();
    }

    public final void setState(State s) {
        this.currentState = s;

        floppyGlyph.setVisible(false);
        floppyQGlyph.setVisible(false);
        computerBaseGlyph.setVisible(false);
        computerHappyGlyph.setVisible(false);
        computerSadGlyph.setVisible(false);
        
        switch (s) {
            case INSERT_DISK:
                floppyGlyph.setVisible(true);
                floppyQGlyph.setVisible(true);
                break;
            case HAPPY:
                computerBaseGlyph.setVisible(true);
                computerHappyGlyph.setVisible(true);
                break;
            case SAD:
                computerBaseGlyph.setVisible(true);
                computerSadGlyph.setVisible(true);
                break;
        }
    }

    private void setupFloppyAnimation() {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                new KeyValue(floppyQGlyph.opacityProperty(), 0.0)));
        timeline.play();
    }
    
    public State getState() {
        return currentState;
    }
    
    /**
     * @return the videoOut
     */
    public StackPane getVideoOut() {
        return videoOut;
    }
    
    public void show() {
        videoOut.setOpacity(1.0);
    }
    
    public void hide() {
        videoOut.setOpacity(0.0);
    }
    
    public void loadVolume( String path ) {
        setState(State.HAPPY);
        try {
            VolumeLoader volumeLoader = new VolumeLoader(
                    new URL("file:///Users/mark/Documents/BanOS/bootdisk-1.0-SNAPSHOT.jar"),
                    "file:///Users/mark/Documents/BanOS/bootdisk-1.0-SNAPSHOT.jar"
            );
            volumeLoader.loadClass("Volume");
        } catch (MalformedURLException ex) {
            Logger.getLogger(SystemPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SystemPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SystemPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(SystemPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(SystemPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SystemPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(SystemPane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(SystemPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean ejectVolume() {
        return true;
    }
    
    public boolean hasVolume() {
        return false;
    }
}
