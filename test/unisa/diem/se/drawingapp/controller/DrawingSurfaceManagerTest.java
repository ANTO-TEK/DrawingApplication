/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.shape.LineShape;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.tool.SelectionTool;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class DrawingSurfaceManagerTest {
    
    private DrawingSurfaceManager drawingSurfaceManager;

    @Before
    public void setUp() {
        this.drawingSurfaceManager = DrawingSurfaceManager.getInstance();
    }

    /**
     * Test of getInstance method, of class DrawingSurfaceManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        //TEST: Assert that the method returns the same instance
        
        DrawingSurfaceManager dsm = DrawingSurfaceManager.getInstance();
        assertNotNull(this.drawingSurfaceManager);
        assertEquals(this.drawingSurfaceManager, dsm);
    }

    /**
     * Test of setDrawingPane method, of class DrawingSurfaceManager.
     */
    @Test
    public void testSetDrawingPane() {
        System.out.println("setDrawingPane");
        
        //TEST: Assert that the get method returns the value setted by the set method
        
        Pane drawingPane = UtilityTest.createAndSetPane();
        this.drawingSurfaceManager.setDrawingPane(drawingPane);
        assertNotNull(this.drawingSurfaceManager.getDrawingPane());
        assertEquals(drawingPane, this.drawingSurfaceManager.getDrawingPane());
    }

    /**
     * Test of getDrawingPane method, of class DrawingSurfaceManager.
     */
    @Test
    public void testGetDrawingPane() {
        System.out.println("getDrawingPane");
        
        //TEST: Assert that the get returns the value setted by the set method
        
        Pane drawingPane = UtilityTest.createAndSetPane();
        this.drawingSurfaceManager.setDrawingPane(drawingPane);
        assertNotNull(this.drawingSurfaceManager.getDrawingPane());
        assertEquals(drawingPane, this.drawingSurfaceManager.getDrawingPane());
    }

    /**
     * Test of getShapes method, of class DrawingSurfaceManager.
     */
    @Test
    public void testGetShapes() {
        System.out.println("getShapes");
        
        //TEST: Assert that the get method returns the correct map
        
        Pane drawingPane = UtilityTest.createAndSetPane();
        this.drawingSurfaceManager.setDrawingPane(drawingPane);
        
        double deltaX = 10;
        double deltaY = 10;
        LineShape line = new LineShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.POS + deltaX, UtilityTest.POS + deltaY);
        line.draw();
        RectangleShape rectangle = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_RESIZE_WIDTH, UtilityTest.TEST_RESIZE_WIDTH);
        rectangle.draw();
        
        HashMap<Shape, CustomShape> expResult = new HashMap<>();
        expResult.put(line.getShape(), line);
        expResult.put(rectangle.getShape(), rectangle);
        assertEquals(expResult, this.drawingSurfaceManager.getShapes());
    }

    /**
     * Test of useTool method, of class DrawingSurfaceManager.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testUseTool() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("useTool");
        
        //TEST: Assert that after the method the tool field is setted with the given input
        
        SelectionTool selectionTool = new SelectionTool();
        this.drawingSurfaceManager.useTool(selectionTool);
        
        final Field field = this.drawingSurfaceManager.getClass().getDeclaredField("currentTool");
        field.setAccessible(true);
 
        assertEquals(selectionTool, field.get(this.drawingSurfaceManager));
    }
}