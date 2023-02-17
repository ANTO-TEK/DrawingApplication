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


import javafx.scene.layout.Pane;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import unisa.diem.se.drawingapp.shape.*;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class ToBackCommandTest {
    
    private  ToBackCommand command;
    private  Pane drawingPane;
    private  RectangleShape toBackShape;
    private  EllipseShape originalInBackShape;

    private final double offset = 10;
    
    @Before
    public void setUp(){
        this.toBackShape = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.drawingPane = UtilityTest.createAndSetPane();
        this.command = new ToBackCommand(this.toBackShape);
        this.originalInBackShape = new EllipseShape(UtilityTest.POS + this.offset, UtilityTest.POS + this.offset,UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
    }

    /**
     * Test of execute method, of class ToBackCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST: Add 2 shape on the pane, bringing the one initially added in front to the back, exploiting the fact that in the observable list associated to the children of the pane, 
        //the figures that are in a higher level are found at a higher index than those that are at a lowest level
        
        this.originalInBackShape.draw();
        this.toBackShape.draw();
        
        assertEquals(toBackShape.getShape(),this.drawingPane.getChildren().get(1));
        
        this.command.execute();
        
        //What is expected is that after the execute, the figure carried "ToBack" is at an index smaller than 1 from its original position
        assertEquals(toBackShape.getShape(),this.drawingPane.getChildren().get(0));
    }

    /**
     * Test of undo method, of class ToBackCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        //TEST: Add 2 shape on the pane, bringing the one initially added in front to the back, exploiting the fact that in the observable list associated to the children of the pane, 
        //the figures that are in a higher level are found at a higher index than those that are at a lowest level
        
        this.originalInBackShape.draw();
        this.toBackShape.draw();
        assertEquals(toBackShape.getShape(),this.drawingPane.getChildren().get(1));
        
        this.command.execute();
        this.command.undo();
        
        //What is expected is that after the undo, the figure brought "ToBack" is at the same starting index in the list associated with the children of the Pane
        assertEquals(toBackShape.getShape(),this.drawingPane.getChildren().get(1));
    }

}