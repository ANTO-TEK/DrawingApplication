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

public class RotationCommandTest {
    
    private static final double TEST_ROTATIONED_SHAPE = 46;

    private CustomShape testShape;
    private Command rotationCommand;
    
    
    @Before
    public void setUp() {
        UtilityTest.createAndSetPane();
        this.testShape = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.rotationCommand = new RotationCommand(this.testShape, RotationCommandTest.TEST_ROTATIONED_SHAPE);
    }
    
    /**
     * Test of execute method, of class VerticalStretchCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST: Given a shape inside a pane, with fixed dimension, try to resize it and check the sizes change
        this.testShape.draw();
        this.rotationCommand.execute();
        
        assertEquals(RotationCommandTest.TEST_ROTATIONED_SHAPE, this.testShape.getShape().getRotate(), UtilityTest.EPSILON);
    }

    /**
     * Test of undo method, of class VerticalStretchCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        this.testShape.draw();
        this.rotationCommand.execute(); 
        this.rotationCommand.undo();
        
        assertEquals(0.0, this.testShape.getShape().getRotate(), UtilityTest.EPSILON);
    }
    
}
