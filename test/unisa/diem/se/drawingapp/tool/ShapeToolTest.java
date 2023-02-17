/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.tool;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class ShapeToolTest {
    
    private  ShapeToolImpl shapeTool;
    private  ObjectProperty strokeColor;


    @Before
    public void setUp() {
        this.strokeColor = new ReadOnlyObjectWrapper();
        this.strokeColor.setValue(UtilityTest.STROKE_COLOR);
        this.shapeTool = new ShapeToolImpl(strokeColor);
    }
    /**
     * Test of getCurrentStrokeColor method, of class ShapeTool.
     */
    @Test
    public void testGetCurrentStrokeColor() {
        System.out.println("getCurrentStrokeColor");
        
        //TEST: Assert that setted stroke color and the returned one from the getter are the same
        Assert.assertEquals(UtilityTest.STROKE_COLOR, this.shapeTool.getCurrentStrokeColor());  
    }

    public class ShapeToolImpl extends ShapeTool {

        public ShapeToolImpl(ObjectProperty strokeColor) {
            super(strokeColor);
        }
    }

}