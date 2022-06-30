/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.common.element;

import org.w3c.dom.Document;

/**
 *
 * @author fatihs
 */
public class SideWindowElement {

    private String className = "";
    private String side = "";
    private String title = "";

    public SideWindowElement() {
    }

    
    public SideWindowElement(String className, String side, String title) {
        this(className, side);
        this.title = title;

    }

    public SideWindowElement(String className, String side) {
        this.side = side;
        this.className = className;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public org.w3c.dom.Element toElement(Document doc) {
        org.w3c.dom.Element windowElement = doc.createElement("window");
        windowElement.setAttribute("title", this.getTitle());
        windowElement.setAttribute("classPath", this.getClassName());
        windowElement.setAttribute("side", this.getSide());
        return windowElement;
    }

    public void fromElemnt(org.w3c.dom.Element element) {
        this.title = element.getAttribute("title"); 
        this.className = element.getAttribute("classPath"); 
        this.side = element.getAttribute("side");
    }
    
}
