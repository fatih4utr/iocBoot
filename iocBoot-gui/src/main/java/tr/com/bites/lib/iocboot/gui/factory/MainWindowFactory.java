/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.gui.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.com.bites.lib.iocboot.gui.window.AbstractMainWindow;

/**
 *
 * @author fatihs
 */
public class MainWindowFactory {
    
    
    
    public static AbstractMainWindow produceMainWindow(String className) {
        try {
            Class<?> loadedClas = ClassLoader.getSystemClassLoader().loadClass(className);
            
            if(!AbstractMainWindow.class.isAssignableFrom(loadedClas)) {
                Logger.getLogger(MainWindowFactory.class.getName()).log(Level.SEVERE, null, "Main Window Class Exception");
                return null;
            }
            AbstractMainWindow window = (AbstractMainWindow) loadedClas.getDeclaredConstructor(null).newInstance(null);
            return window;
            
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MainWindowFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
