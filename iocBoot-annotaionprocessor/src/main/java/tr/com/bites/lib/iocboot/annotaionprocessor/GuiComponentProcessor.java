/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.annotaionprocessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

import tr.com.bites.lib.iocboot.common.annotation.SideWindowComponent;
import tr.com.bites.lib.iocboot.common.element.SideWindowElement;

/**
 *
 * @author fatihs
 */
@SupportedAnnotationTypes("tr.com.bites.lib.iocboot.common.annotation.SideWindowComponent")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class GuiComponentProcessor extends AbstractProcessor {

    Elements elements;

    

    @Override
    public void init(ProcessingEnvironment environment) {
        super.init(environment);
        elements = environment.getElementUtils(); // (1)

    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment re) {
        if (re.processingOver() || set.size() == 0) {
            return false;
        }
        
        Set<? extends Element> elementsAnnotatedWith = re.getElementsAnnotatedWith(SideWindowComponent.class);
        System.out.println("tr.com.bites.lib.iocboot.annotaionprocessor.GuiComponentProcessor.process() " + elementsAnnotatedWith.size());
        
        List<SideWindowElement> sideWindowElementList = new ArrayList<>();

        for (Element element : elementsAnnotatedWith) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Fatih = " + element.getEnclosingElement().toString());
            System.out.println("tr.com.bites.lib.iocboot.annotaionprocessor.GuiComponentProcessor.process() " + element.asType().toString());
            SideWindowComponent annotation = element.getAnnotation(SideWindowComponent.class);
            sideWindowElementList.add(new SideWindowElement(element.asType().toString(), annotation.placement().toString(), annotation.titel()));

        }

        final FileObject source;

        try {
            
            source = processingEnv.getFiler()
                    .createResource(StandardLocation.SOURCE_OUTPUT,
                            Paths.get("module").toString(), Paths.get("windows_map.xml").toString(),
                            null);
            System.out.println("tr.com.bites.lib.iocboot.annotaionprocessor.GuiComponentProcessor.process()  = " + source.toString());
            printToXMl(source, sideWindowElementList);

        } catch (IOException ex) {
            Logger.getLogger(GuiComponentProcessor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("tr.com.bites.lib.iocboot.annotaionprocessor.GuiComponentProcessor.process()  1" );

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(GuiComponentProcessor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("tr.com.bites.lib.iocboot.annotaionprocessor.GuiComponentProcessor.process()  2 " );
        }
        return true;
    }

    private void printToXMl(FileObject outputFile, List<SideWindowElement> list) throws TransformerConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;

        try {

            docBuilder = docFactory.newDocumentBuilder();
            Document newDocument = docBuilder.newDocument();
            org.w3c.dom.Element windowsElement = newDocument.createElement("windows");

            for (SideWindowElement sideWindowElement : list) {
                org.w3c.dom.Element windowElement = sideWindowElement.toElement(newDocument); 
                windowsElement.appendChild(windowElement);
            }
            
            newDocument.appendChild(windowsElement);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(newDocument);

            OutputStream outputStream = outputFile.openOutputStream();
            StreamResult result = new StreamResult(outputStream);

            transformer.transform(source, result);

            result.getOutputStream().close();

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GuiComponentProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GuiComponentProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GuiComponentProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    
    
}
