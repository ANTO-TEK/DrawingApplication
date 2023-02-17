/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.shape;

import java.lang.reflect.Field;
import javafx.scene.shape.Line;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class LineShapeTest {
    
    private LineShape lineShape;

    @Before
    public void setUp() {
        this.lineShape = new LineShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
    }

    /**
     * Test of getShape method, of class LineShape.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetShape() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("getLine");
        
        //TEST: Assert that the returned shape is the real shape
        final Field field = this.lineShape.getClass().getDeclaredField("line");
        field.setAccessible(true);
        
        final Line lineTest = new Line();
        field.set(this.lineShape, lineTest);
        
        assertEquals(lineTest, this.lineShape.getShape());
    }

    /**
     * Test of getWidth method, of class LineShape.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        
        //TEST: Assert that the shape has the correct width
        assertEquals(UtilityTest.TEST_WIDTH_SHAPE - UtilityTest.POS, this.lineShape.getWidth(), UtilityTest.EPSILON);
    }

    /**
     * Test of getHeight method, of class LineShape.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        
        //TEST: Assert that the shape has the correct height
        assertEquals(UtilityTest.TEST_WIDTH_SHAPE - UtilityTest.POS, this.lineShape.getHeight(), UtilityTest.EPSILON);
    }

    /**
     * Test of resize method, of class LineShape.
     */
    @Test
    public void testResize() {
        System.out.println("resize");
        
        //TEST: Assert that after changing the width and the height of the shape, it has actually changed
        this.lineShape.resize(UtilityTest.TEST_RESIZE_WIDTH, UtilityTest.TEST_RESIZE_HEIGHT);
        assertEquals(UtilityTest.TEST_RESIZE_WIDTH/2, this.lineShape.getWidth()/2, UtilityTest.EPSILON);
        assertEquals(UtilityTest.TEST_RESIZE_HEIGHT/2, this.lineShape.getHeight()/2, UtilityTest.EPSILON);
    }

}