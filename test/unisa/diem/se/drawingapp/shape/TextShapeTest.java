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
import javafx.embed.swing.JFXPanel;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class TextShapeTest {

    private TextShape textShape;
    
    @Before
    public void setUp() {
        //Initialize GUI
        JFXPanel jfxPanel = new JFXPanel();
        this.textShape = new TextShape(UtilityTest.TEST_WIDTH_SHAPE,UtilityTest.TEST_HEIGHT_SHAPE,"", UtilityTest.FONT_SIZE, 0);
    }

    /**
     * Test of resize method, of class TextShape.
     */
    @Test
    public void testResize() {
        System.out.println("resize");
        
        //TEST: After the resize the wrapping property of the text has changed
        double newWrappingWidth = 1000;
        this.textShape.resize(newWrappingWidth, 0); //The height resize doesnt't perform any action
        
        assertEquals(this.textShape.getShape().getWrappingWidth(), newWrappingWidth, UtilityTest.EPSILON);
    }

    /**
     * Test of getWidth method, of class TextShape.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        
        //TEST: Assert the equality of the property of the adapted shape and the getter return value
        double adaptedValue = this.textShape.getShape().getWrappingWidth();
        
        assertEquals(adaptedValue, this.textShape.getWidth(), UtilityTest.EPSILON);
    }

    /**
     * Test of getHeight method, of class TextShape.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        
        //TEST: assert te equality of the property of the adapted shape and the getter return value
        double adaptedValue = this.textShape.getShape().getBoundsInParent().getHeight();
        
        assertEquals(adaptedValue, this.textShape.getHeight(), UtilityTest.EPSILON);
    }

    /**
     * Test of getShape method, of class TextShape.
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    @Test
    public void testGetShape() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("getText");
        
        //TEST: Assert that the returned shape is the real shape
        final Field field = this.textShape.getClass().getDeclaredField("text");
        field.setAccessible(true);
        
        final Text textTest = new Text();
        field.set(this.textShape, textTest);
        
        assertEquals(textTest, this.textShape.getShape());
    }

}
