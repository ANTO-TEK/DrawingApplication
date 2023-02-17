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

public class ResizeCommandTest {
    
    private static final double TEST_WIDTH_RESIZED_SHAPE = 75;
    private static final double TEST_HEIGHT_RESIZED_SHAPE = 75;

    private CustomShape testShape;
    private Command resizeCommand;
    
    @Before
    public void setUp() {
        UtilityTest.createAndSetPane();
        this.testShape = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.resizeCommand = new ResizeCommand(this.testShape, ResizeCommandTest.TEST_WIDTH_RESIZED_SHAPE, ResizeCommandTest.TEST_HEIGHT_RESIZED_SHAPE);
    }

    /**
     * Test of execute method, of class ResizeCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST: Given a shape inside a pane, with fixed dimension, try to resize it and check the sizes change
        this.testShape.draw();
        this.resizeCommand.execute();
        
        assertEquals(ResizeCommandTest.TEST_WIDTH_RESIZED_SHAPE, this.testShape.getWidth(), UtilityTest.EPSILON);
        assertEquals(ResizeCommandTest.TEST_HEIGHT_RESIZED_SHAPE, this.testShape.getHeight(), UtilityTest.EPSILON);
    }

    /**
     * Test of undo method, of class ResizeCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        //TEST: Given a resized shape, try to undo and check the new dimensions are equals to the initial ones
        this.testShape.draw();
        this.resizeCommand.execute();
        
        this.resizeCommand.undo();
        
        assertEquals(UtilityTest.TEST_WIDTH_SHAPE, this.testShape.getWidth(), UtilityTest.EPSILON);
        assertEquals(UtilityTest.TEST_HEIGHT_SHAPE, this.testShape.getHeight(), UtilityTest.EPSILON);
    }

}