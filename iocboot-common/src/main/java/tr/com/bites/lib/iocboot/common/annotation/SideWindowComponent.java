/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.bites.lib.iocboot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author fatihs
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface SideWindowComponent {
    String titel() default "Middle Side";
    String windwoId() default "#Middle_Side";
    WINDOW_PLACEMENT placement() default  WINDOW_PLACEMENT.MIDDLE_SIDE;
    
    public enum WINDOW_PLACEMENT {
        LEFT_SIDE,
        BOTTOM_SIDE,
        RIGHT_SIDE,
        MIDDLE_SIDE
    }
    
}
