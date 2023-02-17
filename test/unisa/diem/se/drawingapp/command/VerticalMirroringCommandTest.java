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
import unisa.diem.se.drawingapp.shape.EllipseShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class VerticalMirroringCommandTest {

    private EllipseShape toMirror;
    private VerticalMirroringCommand command;
    
    @Before
    public void setUp() {
        this.toMirror = new EllipseShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        UtilityTest.createAndSetPane();
        this.command = new VerticalMirroringCommand(this.toMirror);
    }


    /**
     * Test of execute method, of class VerticalMirroringCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        double originalScaleX = this.toMirror.getShape().getScaleX();
        double originalAngle = this.toMirror.getShape().getRotate();
        
        //TEST: mirror a shape -> when a shape is vertically mirrored, its scaleY remains the same but its sign is changed
        this.command.execute();
        assertEquals(originalScaleX * -1, this.toMirror.getShape().getScaleX(), UtilityTest.EPSILON);
        assertEquals(originalAngle * -1, this.toMirror.getShape().getRotate(), UtilityTest.EPSILON);
    }

    /**
     * Test of undo method, of class VerticalMirroringCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");

        double originalScaleX = this.toMirror.getShape().getScaleX();
        double originalAngle = this.toMirror.getShape().getRotate();
        
        //TEST: mirror a shape and execute undo -> when a shape is vertically mirrored, its scaleY remains the same but its sign is changed, so
        //when undo command is execute, the original scaleY is restored 
        
        this.command.execute();
        assertEquals(originalScaleX * -1, this.toMirror.getShape().getScaleX(), UtilityTest.EPSILON);
        assertEquals(originalAngle * -1, this.toMirror.getShape().getRotate(), UtilityTest.EPSILON);
        
        this.command.undo();
        assertEquals(originalScaleX, this.toMirror.getShape().getScaleX(), UtilityTest.EPSILON);
        assertEquals(originalAngle, this.toMirror.getShape().getRotate(), UtilityTest.EPSILON);
    }

}