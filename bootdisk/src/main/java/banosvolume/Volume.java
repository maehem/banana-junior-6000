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
package banosvolume;

import com.maehem.banana.banos.storage.Floppy;
import javafx.scene.image.Image;

/**
 *
 * @author mark
 */
public class Volume extends Floppy {

    public Volume() {
        setImage(new Image(getClass().getResourceAsStream("/media.png")));
        System.out.println("Boot Disk Loaded.");
    }
    
}
