/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.view;

import java.lang.reflect.Field;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class GridTest  {
    
    private Group wrapperGroup;
    private Grid grid;
    private double scale;

    @Before
    public void setUp() {
        UtilityTest.createAndSetPane();
        this.wrapperGroup = new Group();
        this.scale = 100;
        this.grid = new Grid(this.wrapperGroup);
    }

    /**
     * Test of drawGrid method, of class Grid.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchFieldException
     */
    @Test
    public void testDrawGrid() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        System.out.println("drawGrid");
        
        //TEST: Assert that after the drawing of the grid, the wrapper group contains the new pane and the grid pane contains some lines
        
        this.grid.drawGrid(this.scale);
        
        assertEquals(1, this.wrapperGroup.getChildren().size());
        assertTrue(this.wrapperGroup.getChildren().get(0) instanceof Pane);
        
        Pane gridPane = (Pane)this.wrapperGroup.getChildren().get(0);
        assertFalse(gridPane.getChildren().isEmpty());
        
        for(Node node : gridPane.getChildren())
            assertTrue(node instanceof Line);
        
        final Field fieldGridDim = this.grid.getClass().getDeclaredField("gridDim");
        fieldGridDim.setAccessible(true);
        
        int width = (int) (UtilityTest.TEST_DIM_PANE * this.scale);
        int height = (int) (UtilityTest.TEST_DIM_PANE * this.scale);
        int gridDim = fieldGridDim.getInt(this.grid);
        int verticalLines = (int) (width/(gridDim * this.scale));
        int horizontalLines = (int) ((height - gridDim * this.scale)/(gridDim * this.scale)) ;
        
        assertEquals(gridPane.getChildren().size(), verticalLines + horizontalLines);
    }

    /**
     * Test of eraseGrid method, of class Grid.
     */
    @Test
    public void testEraseGrid() {
        System.out.println("eraseGrid");
        
        //TEST: Assert that after erasing the grid the wrapper group doesn't contains it
        
        this.grid.drawGrid(this.scale);
        this.grid.eraseGrid();
        assertFalse(this.wrapperGroup.getChildren().contains(this.grid));
    }

    /**
     * Test of resizeGrid method, of class Grid.
     */
    @Test
    public void testResizeGrid() {
        System.out.println("resizeGrid");
        
        //TEST: Assert that after the resize of the grid the number of lines changes
        
        this.grid.drawGrid(1.0);
        int prec = ((Pane)this.wrapperGroup.getChildren().get(0)).getChildren().size();
        this.grid.resizeGrid(40.0, 1.0);
        int post = ((Pane)this.wrapperGroup.getChildren().get(0)).getChildren().size();
        assertNotEquals(prec,post);
    }

}