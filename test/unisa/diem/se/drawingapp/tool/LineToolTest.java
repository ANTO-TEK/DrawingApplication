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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class LineToolTest {
    
    private Pane drawingPane;
    private LineTool lineTool;
    private ObjectProperty strokeColor;

    @Before
    public void setUp() {
        this.drawingPane = UtilityTest.createAndSetPane();
        this.strokeColor = new ReadOnlyObjectWrapper(UtilityTest.STROKE_COLOR);
        this.lineTool = new LineTool(strokeColor); 
        DrawingSurfaceManager.getInstance().getShapes().clear();
    }
   
    /**
     * Test of onMouseDown method, of class LineTool.
     * Test left click inside the pane.
     */
    @Test
    public void testOnMouseDown() {
        System.out.println("onMouseDown");
        
        // TEST: Left click on the pane -> create the shape and insert it on the pane
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        
        UtilityTest.mouseEvent(this.lineTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        assertEquals(1, this.drawingPane.getChildren().size());
        assertEquals(1, DrawingSurfaceManager.getInstance().getShapes().size());
        Line drawedLine = (Line) this.drawingPane.getChildren().get(0);
        assertEquals(x, drawedLine.getStartX(), UtilityTest.EPSILON);
        assertEquals(y, drawedLine.getStartY(), UtilityTest.EPSILON);
        assertEquals(this.strokeColor.getValue(), drawedLine.getStroke());
    }
    
    /**
     * Test of onMouseDrag method, of class LineTool.
     * Drag the shape inside the drawing pane.
     */
    @Test
    public void testOnMouseDrag() {
        System.out.println("onMouseDrag");
       
        // TEST: Drag inside the pane
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        UtilityTest.mouseEvent(this.lineTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        
        double newX = this.drawingPane.getWidth();
        double newY = this.drawingPane.getHeight();
        UtilityTest.mouseEvent(this.lineTool, this.drawingPane, null, newX, newY, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        
        // Check that the dimension of the drawed line are correct
        Line drawedLine = (Line) this.drawingPane.getChildren().get(0);
        assertEquals(newX, drawedLine.getEndX(), UtilityTest.EPSILON);
        assertEquals(newY, drawedLine.getEndY(), UtilityTest.EPSILON);        
    }
    
    /**
     * Test of onMouseUp method, of class LineTool.
     * Release the mouse on the same point of down and drag.
     */
    @Test
    public void testOnMouseUpInSamePoint() {
        System.out.println("onMouseUpInSamePoint");
        
        // TEST : Click, drag and release on the same point -> shape not added
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        UtilityTest.mouseEvent(this.lineTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        UtilityTest.mouseEvent(this.lineTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        assertEquals(0, this.drawingPane.getChildren().size());
        assertEquals(0, DrawingSurfaceManager.getInstance().getShapes().size());
    }
    
    /**
     * Test of onMouseUp method, of class LineTool.
     * Release the mouse in all internal points.
     */
    @Test
    public void testOnMouseUpInDifferentInternalPoint() {
        System.out.println("onMouseUpInDifferentPoint");
        
        // TEST : Click, drag on a different point and release on this last one (all internal point) -> shape added to the Pane
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        UtilityTest.mouseEvent(this.lineTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        x = this.drawingPane.getWidth(); y = this.drawingPane.getHeight();
        UtilityTest.mouseEvent(this.lineTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        UtilityTest.mouseEvent(this.lineTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        assertEquals(1, this.drawingPane.getChildren().size());
        assertEquals(1, DrawingSurfaceManager.getInstance().getShapes().size());
        
        Line drawedLine = (Line) this.drawingPane.getChildren().get(0);
        assertEquals(x, drawedLine.getEndX(), UtilityTest.EPSILON);
        assertEquals(y, drawedLine.getEndY(), UtilityTest.EPSILON);
    }

}
