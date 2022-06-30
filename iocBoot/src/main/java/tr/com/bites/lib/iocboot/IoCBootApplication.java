/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tr.com.bites.lib.iocboot.common.annotation.SideWindowComponent;
import tr.com.bites.lib.iocboot.common.element.SideWindowElement;
import tr.com.bites.lib.iocboot.gui.manager.WindowManager;
import tr.com.bites.lib.iocboot.gui.window.AbstractMainWindow;
import tr.com.bites.lib.iocboot.gui.window.SideWindow;

/**
 *
 * @author fatihs
 */
public class IoCBootApplication {

    static String windowsModulePostFixPath = "target/generated-sources/annotations/module/windows_map.xml";

    public static void run(Class type, String args[]) {
        WindowManager.getDefault().initMainWindow();

        AbstractMainWindow mainWindow = WindowManager.getDefault().getMainWindow();
        String exPath = System.getProperty("user.dir");
        System.out.println("tr.com.bites.lib.iocboot.IoCBootApplication.run() ->> " + exPath + "/" + windowsModulePostFixPath);
        
        File windowFile = new File(exPath + "/" + windowsModulePostFixPath);
        
        generateWindows(mainWindow, windowFile);
        mainWindow.setSize(500,5000);
        mainWindow.setVisible(true);
    }

    private static void generateWindows(AbstractMainWindow window, File windowFile) {
        if (!windowFile.exists()) {
            // TODO exception;
            System.out.println("tr.com.bites.lib.iocboot.IoCBootApplication.generateWindows() file not found exception");
            return;
        }
        List<SideWindowElement> sideWindowElements = new ArrayList<>();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder documentBuilder;

        try {
            //factory.setValidating(true);
            documentBuilder = factory.newDocumentBuilder();
            Document parsedDocument = documentBuilder.parse(windowFile);
            NodeList elementModule = parsedDocument.getElementsByTagName("window");

            for (int i = 0; i < elementModule.getLength(); i++) {
                Node moduleNode = elementModule.item(i);
                if (moduleNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element moduleElement = (Element) moduleNode;
                    SideWindowElement element = new SideWindowElement();
                    element.fromElemnt(moduleElement);
                    sideWindowElements.add(element);
                    
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (SideWindowElement sideWindowElement : sideWindowElements) {
            
            try {
                
                Class<?> sideClass = ClassLoader.getSystemClassLoader().loadClass(sideWindowElement.getClassName());
                
                SideWindow  sideWindowInstance = (SideWindow) sideClass.getDeclaredConstructor(null).newInstance(null);
                SideWindowComponent.WINDOW_PLACEMENT sideValue = SideWindowComponent.WINDOW_PLACEMENT.valueOf(sideWindowElement.getSide());
                window.addSideWindow(sideWindowInstance, sideValue);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(IoCBootApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
}
