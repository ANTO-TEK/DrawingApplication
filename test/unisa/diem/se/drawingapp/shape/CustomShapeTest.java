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

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class CustomShapeTest {
    
    private static final double TEST_ROTATION_ANGLE = 45;
    private static final double TEST_TRANSLATE_X = 20;
    private static final double TEST_TRANSLATE_Y = 30;
    
    private CustomShapeImpl customShapeImpl;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        UtilityTest.createAndSetPane();
        this.customShapeImpl = new CustomShapeImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of draw method, of class CustomShape.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        
        //TEST: Assert that after drawing the shape, it has been inserted both in the hashMap and in the drawing pane
        this.customShapeImpl.draw();
        
        assertEquals(1, DrawingSurfaceManager.getInstance().getShapes().size());
        assertNotNull(DrawingSurfaceManager.getInstance().getShapes().get(this.customShapeImpl.getShape()));
        
        assertEquals(1, DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().size());
        assertEquals(this.customShapeImpl.getShape(), DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().get(0));
    }

    /**
     * Test of erase method, of class CustomShape.
     */
    @Test
    public void testErase() {
        System.out.println("erase");
        
        //TEST: Assert that after drawing a shape, the delete operation removes it from both the hashMap and the drawing pane
        this.customShapeImpl.draw();
        this.customShapeImpl.erase();
        
        assertEquals(0, DrawingSurfaceManager.getInstance().getShapes().size());
        assertEquals(0, DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().size());
    }

    /**
     * Test of moveTo method, of class CustomShape.
     */
    @Test
    public void testMoveTo() {
        System.out.println("moveTo");
        
        //TEST: Assert that after moving a shape, it is actually in the new position
        this.customShapeImpl.moveTo(TEST_TRANSLATE_X, TEST_TRANSLATE_Y);
        
        assertEquals(TEST_TRANSLATE_X, this.customShapeImpl.getShape().getTranslateX(), UtilityTest.EPSILON);
        assertEquals(TEST_TRANSLATE_Y, this.customShapeImpl.getShape().getTranslateY(), UtilityTest.EPSILON);  
    }

    /**
     * Test of getShapeLevelOnPane method, of class CustomShape.
     */
    @Test
    public void testGetShapeLevelOnPane() {
        System.out.println("getShapeLevelOnPane");
        
        //TEST: Assert that after drawing a shape it is at a specific level and after 
        //removing it that its level is undefined (-1)
        this.customShapeImpl.draw();
        int result = this.customShapeImpl.getShapeLevelOnPane();
        assertEquals(0, result);
        
        this.customShapeImpl.erase();
        result = this.customShapeImpl.getShapeLevelOnPane();
        assertEquals(-1, result);
    }

    /**
     * Test of setShapeLevelOnPane method, of class CustomShape.
     */
    @Test
    public void testSetShapeLevelOnPane() {
        System.out.println("setShapeLevelOnPane");
        
        //TEST: Assert that after drawing a shape and changing its layer on the bread, it has actually changed
        EllipseShape ellipse = new EllipseShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        ellipse.draw();
        this.customShapeImpl.setShapeLevelOnPane(1);
        assertEquals(1, this.customShapeImpl.getShapeLevelOnPane());
    }
    
    /**
     * Test of rotate method, of class CustomShape.
     */
    @Test
    public void testRotate() {
        System.out.println("testRotate");
        
        //TEST: Assert that after drawing a shape and changing its angle of rotation, it has actually changed
        EllipseShape ellipse = new EllipseShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        ellipse.draw();
        double oldAngle = ellipse.getShape().getRotate();
        
        this.customShapeImpl.rotate(TEST_ROTATION_ANGLE);
        assertEquals(oldAngle + TEST_ROTATION_ANGLE, this.customShapeImpl.getShape().getRotate(), UtilityTest.EPSILON);
    }

    /**
     * Test of toString method, of class CustomShape.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        //TEST: Assert that the constructed string is the same as the one returned by the toString method
        String expResult = "Custom" + this.customShapeImpl.getShape().toString();
        String result = this.customShapeImpl.toString();
        assertEquals(expResult, result);
    }

    public class CustomShapeImpl extends CustomShape {
        
        private final Ellipse ellipse;
        
        public CustomShapeImpl(){
            this.ellipse = new Ellipse();
        }
        
        @Override
        public void resize(double width, double height) {
            
        }

        @Override
        public double getWidth() {
            return 0.0;
        }

        @Override
        public double getHeight() {
            return 0.0;
        }

        @Override
        public Ellipse getShape() {
            return this.ellipse;
        }

        @Override
        public void setWidth(double newWidth) {
            
        }

        @Override
        public void setHeight(double newHeight) {
            
        }

    }

}
