/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.annotaionprocessor;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import tr.com.bites.lib.iocboot.common.annotation.SideWindowComponent;

/**
 *
 * @author fatihs
 */
 
@SupportedAnnotationTypes("tr.com.bites.lib.iocboot.common.annotation.SideWindowComponent")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class GuiComponentProcessor extends AbstractProcessor{

    
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment re) {
 
        Set<? extends Element> elementsAnnotatedWith = re.getElementsAnnotatedWith(SideWindowComponent.class);
        
        System.out.println("tr.com.bites.lib.iocboot.annotaionprocessor.GuiComponentProcessor.process() " + elementsAnnotatedWith.size());
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Fatih = ");
        for (Element element : elementsAnnotatedWith) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Fatih = "+ element.getEnclosingElement().toString());
            System.out.println("tr.com.bites.lib.iocboot.annotaionprocessor.GuiComponentProcessor.process() " + re.getRootElements().toString());
        }
        return true;
    }
    
}
