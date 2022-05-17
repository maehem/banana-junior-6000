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
 *
 */
package com.maehem.banana.banos.storage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * This class loads a JAR from a URL (or file-URL) and puts those resources
 * into the live classpath (systemClassLoader).
 *
 * This is a great tool for plugable features.
 *
 * Lifted from:
 * @url http://jimlife.wordpress.com/2007/12/19/java-adding-new-classpath-at-runtime/
 *
 *
 * @author mark
 */
public class VolumeLoader extends URLClassLoader {

    public VolumeLoader(URL url, String jarPath) throws MalformedURLException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super(new URL[]{url});
//        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
//        Class urlClass = URLClassLoader.class;
//        Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
//        method.setAccessible(true);
//        method.invoke(urlClassLoader, new Object[]{url});

        List<String> volumes = findPackageNamesStartingWith("banosvolume.");
        
        volumes.forEach(pkg -> {
            System.out.println("Volume [" + pkg + "]");
        });
    }
    
    public final List<String> findPackageNamesStartingWith(String prefix) {
        return Arrays.stream(Package.getPackages())
                .map((t) -> t.getName() )
                .filter((t) -> t.startsWith(prefix))
                .collect(toList());        
    }

    /*
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
try {
    Method method = classLoader.getClass().getDeclaredMethod("addURL", URL.class);
    method.setAccessible(true);
    method.invoke(classLoader, new File(jarPath).toURI().toURL());
} catch (NoSuchMethodException e) {
    Method method = classLoader.getClass()
            .getDeclaredMethod("appendToClassPathForInstrumentation", String.class);
    method.setAccessible(true);
    method.invoke(classLoader, jarPath);
}
    
    File file = ...
    URL url = file.toURI().toURL();
    URLClassLoader sysLoader = new URLClassLoader(new URL[0]);

    Method sysMethod = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
    sysMethod.setAccessible(true);
    sysMethod.invoke(sysLoader, new Object[]{url});
    
    
    */
}
