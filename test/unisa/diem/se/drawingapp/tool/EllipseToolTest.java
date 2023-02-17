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
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class EllipseToolTest {
    
    private  Pane drawingPane;
    private  EllipseTool ellipseTool;
    private  ObjectProperty strokeColor;
    private  ObjectProperty fillColor;

   
    @Before
    public void setUp() {
        this.drawingPane = UtilityTest.createAndSetPane();
        this.strokeColor = new ReadOnlyObjectWrapper(UtilityTest.STROKE_COLOR);
        this.fillColor = new ReadOnlyObjectWrapper(UtilityTest.FILL_COLOR); 
        this.ellipseTool = new EllipseTool(this.strokeColor, this.fillColor);
        DrawingSurfaceManager.getInstance().getShapes().clear();
    }


    /**
     * Test of onMouseDown method, of class EllipseTool.
     * Test left click inside the pane.
     */
    @Test
    public void testOnMouseDown() {
        System.out.println("onMouseDown"); 
        
        // TEST: Left click on the pane -> create the shape and insert it on the pane
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        
        UtilityTest.mouseEvent(this.ellipseTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        assertEquals(1, this.drawingPane.getChildren().size());
        assertEquals(this.strokeColor.getValue(), ((Shape) this.drawingPane.getChildren().get(0)).getStroke());
        assertEquals(this.fillColor.getValue(), ((Shape) this.drawingPane.getChildren().get(0)).getFill());
    }

    /**
     * Test of onMouseDrag method, of class EllipseTool.
     * Test drag inside the pane,
     */
    @Test
    public void testOnMouseDrag() {
        System.out.println("onMouseDrag");
        
        // TEST: Drag inside the pane
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;        
        UtilityTest.mouseEvent(this.ellipseTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        double newX = this.drawingPane.getWidth();
        double newY = this.drawingPane.getHeight(); 
        UtilityTest.mouseEvent(this.ellipseTool, this.drawingPane, null, newX, newY, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        assertEquals(newX - x, ((Ellipse) this.drawingPane.getChildren().get(0)).getRadiusX(), UtilityTest.EPSILON);
        assertEquals(newX - y, ((Ellipse) this.drawingPane.getChildren().get(0)).getRadiusY(), UtilityTest.EPSILON);
    }


    
    /**
     * Test of onMouseUp method, of class EllipseTool.
     * Test mouse up in the same point of down and drag.
     */
    @Test
    public void testOnMouseUpInSamePoint() {
        System.out.println("onMouseUpInSamePoint");
        
        // TEST: Click, drag and release on the same point -> shape not added
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        UtilityTest.mouseEvent(this.ellipseTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        UtilityTest.mouseEvent(this.ellipseTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        UtilityTest.mouseEvent(this.ellipseTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        assertEquals(0, this.drawingPane.getChildren().size()); 
    }
    
    /**
     * Test of onMouseUp method, of class EllipseTool.
     * Test mouse up in the all internal points.
     */
    @Test
    public void testOnMouseUpDifferentInternalPoint() {
        System.out.println("onMouseUpDifferentInternalPoint");
        
        // TEST: Click, drag on a different point and release on this last one (all internal point) -> shape added to the Pane
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        
        UtilityTest.mouseEvent(this.ellipseTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        x = this.drawingPane.getWidth();
        y = this.drawingPane.getHeight();
        UtilityTest.mouseEvent(this.ellipseTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        UtilityTest.mouseEvent(this.ellipseTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        assertEquals(1, this.drawingPane.getChildren().size());
    }
 
}