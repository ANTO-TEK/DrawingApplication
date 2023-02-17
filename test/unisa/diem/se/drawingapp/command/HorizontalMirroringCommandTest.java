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

public class HorizontalMirroringCommandTest {

    private EllipseShape toMirror;
    private HorizontalMirroringCommand command;

    @Before
    public void setUp() {
        this.toMirror = new EllipseShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        UtilityTest.createAndSetPane();
        this.command = new HorizontalMirroringCommand(this.toMirror);
    }


    /**
     * Test of execute method, of class HorizontalMirroringCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        double originalScaleY = this.toMirror.getShape().getScaleY();
        double originalAngle = this.toMirror.getShape().getRotate();
        
        //TEST: mirror horizontallt a shape -> when a shape is horizontally mirrored, its scaleX remains the same but its sign is changed
        this.command.execute();
        assertEquals(originalScaleY * -1, this.toMirror.getShape().getScaleY(), UtilityTest.EPSILON);
        assertEquals(originalAngle * -1, this.toMirror.getShape().getRotate(), UtilityTest.EPSILON);
    }

    /**
     * Test of undo method, of class HorizontalMirroringCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");

        double originalScaleY = this.toMirror.getShape().getScaleY();
        double originalAngle = this.toMirror.getShape().getRotate();
        
        //TEST: mirror horizontally a shape and execute undo -> when a shape is horizontally mirrored, its scaleX remains the same but its sign is changed, so
        //when undo command is execute, the original scaleX is restored 
        
        this.command.execute();
        assertEquals(originalScaleY * -1, this.toMirror.getShape().getScaleY(), UtilityTest.EPSILON);
        assertEquals(originalAngle * -1, this.toMirror.getShape().getRotate(), UtilityTest.EPSILON);
        
        this.command.undo();
        assertEquals(originalScaleY, this.toMirror.getShape().getScaleY(), UtilityTest.EPSILON);
        assertEquals(originalAngle, this.toMirror.getShape().getRotate(), UtilityTest.EPSILON);
    }

}