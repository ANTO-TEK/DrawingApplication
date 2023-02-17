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
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class RectangleShapeTest {
    
    private RectangleShape rectangleShape;

    @Before
    public void setUp() {
        this.rectangleShape = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
    }

    /**
     * Test of getShape method, of class RectangleShape.
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    @Test
    public void testGetShape() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("getRectangle");
        
        //TEST: Assert that the returned shape is the real shape
        final Field field = this.rectangleShape.getClass().getDeclaredField("rectangle");
        field.setAccessible(true);
        
        final Rectangle rectangleTest = new Rectangle();
        field.set(this.rectangleShape, rectangleTest);
        
        assertEquals(rectangleTest, this.rectangleShape.getShape());
    }

    /**
     * Test of getWidth method, of class RectangleShape.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        
        //TEST: Assert that the shape has the correct width
        assertEquals(UtilityTest.TEST_WIDTH_SHAPE, this.rectangleShape.getWidth(), UtilityTest.EPSILON);
    }

    /**
     * Test of getHeight method, of class RectangleShape.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        
        //TEST: Assert that the shape has the correct height
        assertEquals(UtilityTest.TEST_HEIGHT_SHAPE, this.rectangleShape.getHeight(), UtilityTest.EPSILON);
    }

    /**
     * Test of resize method, of class RectangleShape.
     */
    @Test
    public void testResize() {
        System.out.println("resize");
        
        //TEST: Assert that after changing the width and the height of the shape, it has actually changed
        this.rectangleShape.resize(UtilityTest.TEST_RESIZE_WIDTH, UtilityTest.TEST_RESIZE_HEIGHT);
        assertEquals(UtilityTest.TEST_RESIZE_WIDTH, this.rectangleShape.getWidth(), UtilityTest.EPSILON);
        assertEquals(UtilityTest.TEST_RESIZE_HEIGHT, this.rectangleShape.getHeight(), UtilityTest.EPSILON);
    }

}