/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.gui.window;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JWindow;
import tr.com.bites.lib.iocboot.common.annotation.SideWindowComponent;

/**
 *
 * @author fatihs
 */
public abstract class AbstractMainWindow extends JFrame{
    
    public abstract void initWindow();
    public abstract void addComponent(JComponent component);
    public abstract void addComponentToSide(JComponent component,SideWindowComponent.WINDOW_PLACEMENT placement);
    public abstract void addSideWindow(SideWindow window,SideWindowComponent.WINDOW_PLACEMENT placement);
}
