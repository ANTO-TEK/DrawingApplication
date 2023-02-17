/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.command;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class HorizontalStretchCommandTest {
    
    private static final double TEST_HORIZONTAL_STRETCHED_SHAPE = 125;

    private CustomShape testShape;
    private Command horizontalStretchCommand;
    
    
    @Before
    public void setUp() {
        UtilityTest.createAndSetPane();
        this.testShape = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.horizontalStretchCommand = new HorizontalStretchCommand(this.testShape, HorizontalStretchCommandTest.TEST_HORIZONTAL_STRETCHED_SHAPE);
    }
    
    /**
     * Test of execute method, of class VerticalStretchCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST: Given a shape inside a pane, try to stretch it and check if its new scale is equal to the new one. 
        this.testShape.draw();
        this.horizontalStretchCommand.execute();
        
        assertEquals(HorizontalStretchCommandTest.TEST_HORIZONTAL_STRETCHED_SHAPE, this.testShape.getShape().getScaleX(), UtilityTest.EPSILON);
    }

    /**
     * Test of undo method, of class VerticalStretchCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        //TEST: Given a stretched shape inside a pane, check if after undo operation the previous scale is restored.
        this.testShape.draw();
        this.horizontalStretchCommand.execute();
        this.horizontalStretchCommand.undo();
        
        assertEquals(1.0, this.testShape.getShape().getScaleX(), UtilityTest.EPSILON);
    }   
}
