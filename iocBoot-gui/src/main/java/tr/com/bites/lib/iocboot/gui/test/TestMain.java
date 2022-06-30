/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.gui.test;

import tr.com.bites.lib.iocboot.gui.factory.MainWindowFactory;
import tr.com.bites.lib.iocboot.gui.window.AbstractMainWindow;

/**
 *
 * @author fatihs
 */
public class TestMain {
    
    public static void main(String[] args) {
      
        AbstractMainWindow produceMainWindow = MainWindowFactory.produceMainWindow("tr.com.bites.lib.iocboot.gui.window.DefaultMainWindow");
        produceMainWindow.setSize(1000, 1000);
        produceMainWindow.initWindow();
        produceMainWindow.revalidate();
        produceMainWindow.setVisible(true);
        
    }
}
