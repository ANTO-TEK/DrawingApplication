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

public class ClosedShapeToolTest {
    
    private  ClosedShapeToolImpl closedShapeTool;
    private  ObjectProperty strokeColor, fillColor;

    
    @Before
    public void setUp() {
        
        this.strokeColor = new ReadOnlyObjectWrapper(UtilityTest.STROKE_COLOR);
        this.fillColor = new ReadOnlyObjectWrapper(UtilityTest.FILL_COLOR);
        this.closedShapeTool = new ClosedShapeToolImpl(strokeColor, fillColor);
 
    }

    /**
     * Test of getCurrentFillColor method, of class ClosedShapeTool.
     */
    @Test
    public void testGetCurrentFillColor() {
        System.out.println("getCurrentFillColor");
        
        //TEST: Assert that setted stroke and fill color and returned stroke and fill color from these getter are the same
        Assert.assertEquals(UtilityTest.STROKE_COLOR, this.closedShapeTool.getCurrentStrokeColor());
        Assert.assertEquals(UtilityTest.FILL_COLOR, this.closedShapeTool.getCurrentFillColor());
        
    }

    public class ClosedShapeToolImpl extends ClosedShapeTool {
        
        public ClosedShapeToolImpl(ObjectProperty strokeColor, ObjectProperty fillColor) {
            super(strokeColor, fillColor);
        }
    }

}