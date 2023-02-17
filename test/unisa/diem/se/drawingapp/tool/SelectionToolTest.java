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
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import unisa.diem.se.drawingapp.command.CommandExecutor;
import unisa.diem.se.drawingapp.command.DragCommand;
import unisa.diem.se.drawingapp.controller.SelectedShapeManager;
import unisa.diem.se.drawingapp.shape.EllipseShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class SelectionToolTest  {
    
    private SelectionTool selectionTool;
    private SelectedShapeManager selectedShapeManager;
    private Pane drawingPane;
    private EllipseTool ellipseTool;
    private ObjectProperty strokeColor;
    private ObjectProperty fillColor;
    
    @Before
    public void setUp() {
        this.selectionTool = new SelectionTool();
        this.selectedShapeManager = SelectedShapeManager.getInstance();
        this.selectedShapeManager.unselectShape();
        this.drawingPane = UtilityTest.createAndSetPane();
        this.strokeColor = new ReadOnlyObjectWrapper(UtilityTest.STROKE_COLOR);
        this.fillColor = new ReadOnlyObjectWrapper(UtilityTest.FILL_COLOR); 
        this.ellipseTool = new EllipseTool(this.strokeColor, this.fillColor);
    }
    
    /**
     * Utility method that allows to draw and select a Shape (in particular an ellipse).
     */
    private void createAndClickOnShape() {
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        UtilityTest.mouseEvent(this.ellipseTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        x = this.drawingPane.getWidth();
        y = this.drawingPane.getHeight();
        UtilityTest.mouseEvent(this.ellipseTool,this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        UtilityTest.mouseEvent(this.selectionTool,this.drawingPane, this.drawingPane.getChildren().get(0), x - 50, y - 50, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
    }
    
    /**
     * Utility method which allows to draw a Shape (in particular an ellipse) without selecting it.
     */
    private void createAndClickOutsideShape() {
        double x = this.drawingPane.getWidth() / 2, y = this.drawingPane.getHeight() / 2;
        UtilityTest.mouseEvent(this.ellipseTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        x = this.drawingPane.getWidth();
        y = this.drawingPane.getHeight();
        UtilityTest.mouseEvent(this.ellipseTool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        UtilityTest.mouseEvent(this.selectionTool, this.drawingPane, this.drawingPane, UtilityTest.TEST_DIM_PANE + 100, UtilityTest.TEST_DIM_PANE + 100, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
    }
    
    /**
     * Test of unselectShape method, of class SelectionTool.
     */
    @Test
    public void testComplete() {
        System.out.println("complete");
        
        //TEST: create and click on a shape: after complete method is execute , the shape must be unselected
        this.createAndClickOnShape();
        this.selectionTool.complete();
        assertNull(this.selectedShapeManager.getSelectedShape());
        assertFalse(this.selectedShapeManager.selectedProperty().getValue());
    }   
    
    /**
     * Test of onMouseDown method, of class SelectionTool.
     */
    @Test
    public void testOnMouseDownSelected() {
        System.out.println("onMouseDownSelected");
        
        //TEST create a shape and click on it: assert that the selected shape in selectedShapeManager and the original that was clicked are 
        //the same (same center coordinate)
        this.createAndClickOnShape();
        EllipseShape selectedEllipse = (EllipseShape) this.selectedShapeManager.getSelectedShape();
        for(Node item : this.drawingPane.getChildren()) {
            if(item instanceof Ellipse) {
                assertEquals(((Ellipse)item).getCenterX(), selectedEllipse.getShape().getCenterX(), UtilityTest.EPSILON);
                assertEquals(((Ellipse)item).getCenterY(), selectedEllipse.getShape().getCenterY(), UtilityTest.EPSILON);
            }
        }
    }
    
    /**
     * Test of onMouseDown method, of class SelectionTool.
     */
    @Test
    public void testOnMouseDownNotSelected() {
        System.out.println("onMouseDownNotSelected");
        
        //TEST create a shape and click outside it: assert that the selected shape in selectedShapeManager is null
        this.createAndClickOutsideShape();
        assertNull(this.selectedShapeManager.getSelectedShape());
        assertFalse(this.selectedShapeManager.selectedProperty().getValue());
    }
    
    /**
     * Test of onMouseDrag method, of class SelectionTool.
     */
    @Test
    public void testOnMouseDrag(){
        System.out.println("onMouseDrag");
        
        //TEST create a shape and click on it and drag: assert that the selected shape in selectedShapeManager and the original that was clicked are 
        //the same and the effect of drag is applied to the original shape
        this.createAndClickOnShape();
        EllipseShape selectedEllipse =  (EllipseShape) this.selectedShapeManager.getSelectedShape();
        UtilityTest.mouseEvent(this.selectionTool,this.drawingPane, this.drawingPane.getChildren().get(0), 50, 50, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        for(Node item : this.drawingPane.getChildren()) {
            if(item instanceof Ellipse) {
                assertEquals(((Ellipse)item).getTranslateX(), selectedEllipse.getShape().getTranslateX(), UtilityTest.EPSILON);
                assertEquals(((Ellipse)item).getTranslateY(), selectedEllipse.getShape().getTranslateY(), UtilityTest.EPSILON);
            }
        }
    }
    
    /**
     * Test of onMouseUp method, of class SelectionTool.
     */
    @Test
    public void testOnMouseUpAfterDrag(){
        System.out.println("onMouseUpAfterDrag");
        
        //TEST: create a shape, click on it, drag and release mouse: assert that the last command executed is a drag command
        this.createAndClickOnShape();
        UtilityTest.mouseEvent(this.selectionTool,this.drawingPane, this.drawingPane.getChildren().get(0), 50, 50, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        UtilityTest.mouseEvent(this.selectionTool,this.drawingPane, this.drawingPane.getChildren().get(0), 50, 50, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        assertTrue(CommandExecutor.getInstance().getCommandHistory().getLast() instanceof DragCommand);
    }
    
    /**
     * Test of onMouseUp method, of class SelectionTool.
     */
    @Test
    public void testOnMouseUpWithoutDrag(){
        System.out.println("onMouseUpWithoutDrag");
        
        //TEST: create a shape, click on it and release mouse: assert that the last command executed isn't a drag command
        this.createAndClickOnShape();
        UtilityTest.mouseEvent(this.selectionTool,this.drawingPane, this.drawingPane.getChildren().get(0), this.drawingPane.getWidth() - 50, this.drawingPane.getWidth() - 50, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        assertFalse(CommandExecutor.getInstance().getCommandHistory().getLast() instanceof DragCommand);
    }

}