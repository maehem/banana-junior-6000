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
public class SystemPane extends StackPane {

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
    private final ImageView happyGlyph = new ImageView(new Image(
            getClass().getResourceAsStream("/glyphs/computer-happy.png"),
            32, 32, false, false
    ));
    private final ImageView sadGlyph = new ImageView(new Image(
            getClass().getResourceAsStream("/glyphs/computer-sad.png"),
            32, 32, false, false
    ));

    public SystemPane() {

        getChildren().addAll(
                backdrop,
                floppyGlyph, floppyQGlyph,
                happyGlyph, sadGlyph
        );
        setState(State.INSERT_DISK);
        
        setupFloppyAnimation();
    }

    public void update() {
        setOpacity(1.0);
        backdrop.update();
    }

    public final void setState(State s) {
        this.currentState = s;

        backdrop.toFront();
        switch (s) {
            case INSERT_DISK:
                floppyGlyph.toFront();
                floppyQGlyph.toFront();
                break;
            case HAPPY:
                happyGlyph.toFront();
                break;
            case SAD:
                sadGlyph.toFront();
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
}
