/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.testproject;

import javax.swing.JButton;
import tr.com.bites.lib.iocboot.common.annotation.SideWindowComponent;
import tr.com.bites.lib.iocboot.gui.window.SideWindow;

/**
@SideWindowComponent(placement = SideWindowComponent.WIND
 *
 * @author fatihs
 */
@SideWindowComponent(placement = SideWindowComponent.WINDOW_PLACEMENT.LEFT_SIDE)
public class SideWindowComponentTest  extends SideWindow{
    private int c;

    public SideWindowComponentTest() {
        JButton asd = new JButton("asdsad");
        this.add(asd);
    }
    
    
}
