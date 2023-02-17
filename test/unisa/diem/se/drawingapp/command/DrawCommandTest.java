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
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class DrawCommandTest {
    
    private  DrawCommand command;
    private  Pane drawingPane;
    private  RectangleShape addedShape;
    
    @Before
    public void setUp(){
        this.drawingPane = UtilityTest.createAndSetPane();
        this.addedShape = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.command = new DrawCommand(this.addedShape);
    }
    
    /**
     * Test of execute method, of class DrawCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST : Add the shape in the drawing pane
        int oldSize = this.drawingPane.getChildren().size();
        this.command.execute();
        
        assertTrue(DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().contains(this.addedShape.getShape()));
        assertEquals(oldSize + 1, this.drawingPane.getChildren().size());
    }

    /**
     * Test of undo method, of class DrawCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        //TEST: undo the last adding operation
        this.command.execute();
        int oldSize = this.drawingPane.getChildren().size();
        this.command.undo();
        
        assertFalse(DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().contains(this.addedShape.getShape()));
        assertEquals(oldSize - 1, this.drawingPane.getChildren().size()); 
    }

}