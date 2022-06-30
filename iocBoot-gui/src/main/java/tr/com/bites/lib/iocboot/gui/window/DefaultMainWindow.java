/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.gui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import tr.com.bites.lib.iocboot.common.annotation.SideWindowComponent;

/**
 *
 * @author fatihs
 */
public class DefaultMainWindow extends AbstractMainWindow {
    
    private JPanel pnlLeftSide = null;
    private JPanel pnlRightSide = null;
    private JPanel pnlBottomSide = null;
    private JPanel pnlMiddleSide = null;
    
    private HashMap<SideWindowComponent.WINDOW_PLACEMENT , JPanel > placementPanelMap  = new HashMap<>();
    
    public DefaultMainWindow() {
        
    }
    
    @Override
    public void initWindow() {
        this.setLayout(new BorderLayout(10 ,10 ));
        pnlLeftSide  = new JPanel(new BorderLayout());
        pnlLeftSide.setBackground(Color.red);
        
        
        
        pnlBottomSide  = new JPanel(new BorderLayout());
        pnlBottomSide.setBackground(Color.yellow);
        
        
        pnlMiddleSide  = new JPanel(new BorderLayout());
        pnlMiddleSide.setBackground(Color.yellow);
        
        
        pnlRightSide  = new JPanel(new BorderLayout());
        
        
        
        this.add(pnlLeftSide,BorderLayout.WEST);
        this.add(pnlRightSide,BorderLayout.EAST);
        this.add(pnlBottomSide,BorderLayout.SOUTH);
        this.add(pnlMiddleSide,BorderLayout.CENTER);
        
        this.placementPanelMap.put(SideWindowComponent.WINDOW_PLACEMENT.LEFT_SIDE, pnlLeftSide);
        this.placementPanelMap.put(SideWindowComponent.WINDOW_PLACEMENT.RIGHT_SIDE, pnlRightSide);
        this.placementPanelMap.put(SideWindowComponent.WINDOW_PLACEMENT.MIDDLE_SIDE, pnlMiddleSide);
        this.placementPanelMap.put(SideWindowComponent.WINDOW_PLACEMENT.BOTTOM_SIDE, pnlBottomSide);
        
        
        this.pack();
    }

    @Override
    public void addComponent(JComponent component) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addComponentToSide(JComponent component, SideWindowComponent.WINDOW_PLACEMENT placement) {
        
    }

    @Override
    public void addSideWindow(SideWindow window, SideWindowComponent.WINDOW_PLACEMENT placement) {
        this.placementPanelMap.get(placement).add(window, BorderLayout.CENTER);
        this.revalidate();
    }
    
    
    
}
