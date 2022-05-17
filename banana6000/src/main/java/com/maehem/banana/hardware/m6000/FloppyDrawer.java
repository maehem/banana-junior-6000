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

import banosvolume.Volume;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javafx.scene.layout.Pane;

/**
 *
 * @author mark
 */
public class FloppyDrawer extends Pane {

    public FloppyDrawer(Computer computer, double width, double height) {
        buildItems(computer, width, height);

    }

    private void buildItems(Computer computer, double width, double height) {
//        // List floppy folder in user directory
//        File drawer = new File(System.getProperty("user.home") + File.separatorChar
//                + "Documents" + File.separatorChar
//                + "BanOS"
//        );
//
//        String[] list = drawer.list();
//        //System.out.println(Arrays.toString(list));
        Package[] packages = Package.getPackages();
        for (Package p : packages) {
            System.out.println(" ==> " + p.getName());
        }

        List<String> volumes = findPackageNamesStartingWith("banosvolume");

        volumes.forEach(pkg -> {
            System.out.println("Volume [" + pkg + "]");
        });

        Volume v = new Volume();

//        // Create a floppy for each Jar file found.
//        for (String filename : list) {
//            File f = new File(filename);
//            
//            System.out.println("Floppy: " + f.getAbsolutePath());
//            Floppy floppy = new Floppy();
//            floppy.setRotate((Math.random() - 0.5) * 90);
//            floppy.setX(Math.random() * width / 4);
//            floppy.setY(Math.random() * height / 4);
//            floppy.setOnMouseClicked((t) -> {
//                computer.mediaInserted(floppy);
//            });
//            getChildren().add(floppy);
//        }
    }

    public final List<String> findPackageNamesStartingWith(String prefix) {
        return Arrays.stream(Package.getPackages())
                .map((t) -> t.getName())
                .filter((t) -> t.startsWith(prefix))
                .collect(toList());
    }

    private void loader(File myJar) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        URLClassLoader child = new URLClassLoader(
                new URL[]{myJar.toURI().toURL()},
                this.getClass().getClassLoader()
        );
        Class classToLoad = Class.forName("com.MyClass", true, child);
        Method method = classToLoad.getDeclaredMethod("myMethod");
        Object[] initargs = new Object[]{};
        Object instance = classToLoad.getDeclaredConstructor(new Class[]{}).newInstance(initargs);
        Object result = method.invoke(instance);
    }
}
