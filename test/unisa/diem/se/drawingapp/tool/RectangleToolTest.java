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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class RectangleToolTest {

    private  Pane drawingPane;
    private  RectangleTool rectangleTool;
    private  ObjectProperty strokeColor;
    private  ObjectProperty fillColor;
    
    @Before
    public void setUp() {
        this.drawingPane = UtilityTest.createAndSetPane();
        this.strokeColor = new ReadOnlyObjectWrapper(UtilityTest.STROKE_COLOR);
        this.fillColor = new ReadOnlyObjectWrapper(UtilityTest.FILL_COLOR); 
        this.rectangleTool = new RectangleTool(this.strokeColor,this.fillColor);
        DrawingSurfaceManager.getInstance().getShapes().clear();
    }


    /**
     * Test of onMouseDown method, of class RectangleTool.
     * Test left click inside the pane.
     */
    @Test
    public void testOnMouseDown() {
        System.out.println("onMouseDown");
        
        // TEST: Click on a pane with left mouse button just once, no modifiers
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        
        UtilityTest.mouseEvent(this.rectangleTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        assertEquals(1, this.drawingPane.getChildren().size());
        assertEquals(this.strokeColor.getValue(), ((Shape) this.drawingPane.getChildren().get(0)).getStroke());
        assertEquals(this.fillColor.getValue(), ((Shape) this.drawingPane.getChildren().get(0)).getFill());
    }
    
    /**
     * Test of onMouseDrag method, of class RectangleTool.
     * Drag the shape inside the drawing pane.
     */
    @Test
    public void testOnMouseDrag() {
        System.out.println("onMouseDrag");
        
        // TEST: Drag inside the pane
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;        
        UtilityTest.mouseEvent(this.rectangleTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        
        double newX = this.drawingPane.getWidth();
        double newY = this.drawingPane.getHeight(); 
        UtilityTest.mouseEvent(this.rectangleTool, this.drawingPane, null, newX, newY, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        assertEquals(1, this.drawingPane.getChildren().size());

        // Assert that the dragged figure has the right dimensions
        assertEquals(newX - x, ((Rectangle) this.drawingPane.getChildren().get(0)).getWidth(), UtilityTest.EPSILON);
        assertEquals(newY - y, ((Rectangle) this.drawingPane.getChildren().get(0)).getHeight(), UtilityTest.EPSILON);
        
        // Assert top-left corner are the setted one
        assertEquals(x, ((Rectangle) this.drawingPane.getChildren().get(0)).getX(), UtilityTest.EPSILON);
        assertEquals(y, ((Rectangle) this.drawingPane.getChildren().get(0)).getY(), UtilityTest.EPSILON);  
    }
    
    
    /**
     * Test of onMouseUp method, of class RectangleTool.
     * Release the mouse on the same point of down and drag.
     */
    @Test
    public void testOnMouseUpInSamePoint() {
        System.out.println("onMouseUpInSamePoint");
        
        // TEST: Click, drag and release on the same point -> shape not added
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        UtilityTest.mouseEvent(this.rectangleTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        
        // Drag in the pane
        UtilityTest.mouseEvent(this.rectangleTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        
        // Release mouse in the same point
        UtilityTest.mouseEvent(this.rectangleTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        // Mouse released with a shape that has 0 width and 0 height. The figure mustn't be in the pane.
        assertEquals(0, this.drawingPane.getChildren().size());
    }
    
    /**
     * Test of onMouseUp method, of class RectangleTool.
     * Release the mouse in all internal points.
     */
    @Test
    public void testOnMouseUpDifferentInternalPoint() {
        System.out.println("onMouseUpDifferentInternalPoint");
        
        // TEST: Click, drag on a different point and release on this last one (all internal point) -> shape added to the Pane
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        UtilityTest.mouseEvent(this.rectangleTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        
        // Drag in the pane
        x = this.drawingPane.getWidth();
        y = this.drawingPane.getHeight();
        UtilityTest.mouseEvent(this.rectangleTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        
        // Release in the last drag point
        UtilityTest.mouseEvent(this.rectangleTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        assertEquals(1, this.drawingPane.getChildren().size());
    }

}
