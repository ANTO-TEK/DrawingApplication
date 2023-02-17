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
import javafx.scene.shape.Ellipse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class EllipseShapeTest {
    
    private EllipseShape ellipseShape;

    @Before
    public void setUp() {
        this.ellipseShape = new EllipseShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
    }

    /**
     * Test of getShape method, of class EllipseShape.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetShape() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("getEllipse");
        
        //TEST: Assert that the returned shape is the real shape
        final Field field = this.ellipseShape.getClass().getDeclaredField("ellipse");
        field.setAccessible(true);
        
        final Ellipse ellipseTest = new Ellipse();
        field.set(this.ellipseShape, ellipseTest);
        
        assertEquals(ellipseTest, this.ellipseShape.getShape());
    }

    /**
     * Test of getWidth method, of class EllipseShape.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        
        //TEST: Assert that the shape has the correct width
        assertEquals(UtilityTest.TEST_WIDTH_SHAPE * 2, this.ellipseShape.getWidth(), UtilityTest.EPSILON);
    }

    /**
     * Test of getHeight method, of class EllipseShape.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        
        //TEST: Assert that the shape has the correct height
        assertEquals(UtilityTest.TEST_HEIGHT_SHAPE * 2, this.ellipseShape.getHeight(), UtilityTest.EPSILON);
    }

    /**
     * Test of resize method, of class EllipseShape.
     */
    @Test
    public void testResize() {
        System.out.println("resize");
        
        //TEST: Assert that after changing the width and the height of the shape, it has actually changed
        this.ellipseShape.resize(UtilityTest.TEST_RESIZE_WIDTH, UtilityTest.TEST_RESIZE_HEIGHT);
        assertEquals(UtilityTest.TEST_RESIZE_WIDTH/2, this.ellipseShape.getWidth()/2, UtilityTest.EPSILON);
        assertEquals(UtilityTest.TEST_RESIZE_HEIGHT/2, this.ellipseShape.getHeight()/2, UtilityTest.EPSILON);
    }

}