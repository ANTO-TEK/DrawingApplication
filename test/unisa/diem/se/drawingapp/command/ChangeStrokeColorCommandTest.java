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

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class ChangeStrokeColorCommandTest {

    private  ChangeStrokeColorCommand command;
    private  RectangleShape modifiedShape;
    private  Paint newColor;
    
    private final static Paint STARTING_COLOR = Color.BLACK;
    private final static Paint ENDING_COLOR = Color.BLUE;
    
    @Before
    public void setUp() {
        this.modifiedShape = new RectangleShape(UtilityTest.POS, UtilityTest.POS,UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.modifiedShape.getShape().setStroke(ChangeStrokeColorCommandTest.STARTING_COLOR);
        this.newColor = ChangeStrokeColorCommandTest.ENDING_COLOR;
        this.command = new ChangeStrokeColorCommand(this.modifiedShape, this.newColor);
    }

    /**
     * Test of execute method, of class ChangeFillColorCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST: Assert that after the execution the color has changed
        Assert.assertEquals(ChangeStrokeColorCommandTest.STARTING_COLOR, this.modifiedShape.getShape().getStroke());
        
        this.command.execute();
        Assert.assertEquals(ChangeStrokeColorCommandTest.ENDING_COLOR, this.modifiedShape.getShape().getStroke());
    }

    /**
     * Test of undo method, of class ChangeFillColorCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        //TEST: Assert that the previous color is restored
        this.command.execute();
        Assert.assertEquals(ChangeStrokeColorCommandTest.ENDING_COLOR, this.modifiedShape.getShape().getStroke());
        
        this.command.undo();
        Assert.assertEquals(ChangeStrokeColorCommandTest.STARTING_COLOR, this.modifiedShape.getShape().getStroke());
    }

}