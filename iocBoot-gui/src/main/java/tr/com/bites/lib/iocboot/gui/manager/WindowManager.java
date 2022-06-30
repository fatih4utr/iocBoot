/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.gui.manager;

import tr.com.bites.lib.iocboot.gui.window.AbstractMainWindow;
import tr.com.bites.lib.iocboot.gui.window.DefaultMainWindow;

/**
 *
 * @author fatihs
 */
//Singelton
public class WindowManager {

    private static WindowManager instance = null;
    private AbstractMainWindow mainWindow = null;

    private WindowManager() {
    }

    //Lazy inst
    public static WindowManager getDefault() {
        if (instance == null) {
            instance = new WindowManager();
        }
        return instance;
    }

    public void initMainWindow() {
        if (this.mainWindow == null) {
            this.mainWindow = new DefaultMainWindow();
        }
        this.mainWindow.initWindow();
    }
    
    public AbstractMainWindow  getMainWindow() {
        return this.mainWindow;
    }
    
}
