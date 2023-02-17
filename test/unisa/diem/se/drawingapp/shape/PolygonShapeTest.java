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
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Polyline;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import unisa.diem.se.drawingapp.utility.UtilityTest;


public class PolygonShapeTest {
    
    private final static double TEST_NEW_WIDTH = 20;
    private final static double TEST_NEW_HEIGHT = 30;

    private PolygonShape polygonShape;
    private List<Double> coordinates;
    private final int NUM_POINTS = 8;
    private final int OFFSET = 5;
    private double width;
    private double height;
    

    @Before
    public void setUp() {
        this.polygonShape = new PolygonShape();
        this.coordinates = new ArrayList<>();
        
        //POLYGON OF POINTS: (10,10), (10,20), (20,20), (20,10) 
        for(int i = 0; i < this.NUM_POINTS; i++){
            if(i < 3 || i == 7)
                this.coordinates.add(10.0);
            else
                this.coordinates.add(20.0);
        }
        
        this.polygonShape.getShape().getPoints().addAll(coordinates);
        this.width = 10.0;     //MaxX - MinX = 20 -10
        this.height = 10.0;    //MaxY - MinY = 20 -10
    }


    /**
     * Test of addPoint method, of class PolygonShape.
     */
    @Test
    public void testAddPoint() {
        System.out.println("addPoint");
        
        //TEST: add a point with addPoint method to polygonShape's point observable list -> expected that this are the same as the setted point
        this.polygonShape.addPoint(this.coordinates.get(0), this.coordinates.get(1));
        
        assertEquals(this.coordinates.get(0), this.polygonShape.getShape().getPoints().get(this.polygonShape.getShape().getPoints().size()-2), UtilityTest.EPSILON);
        assertEquals(this.coordinates.get(1), this.polygonShape.getShape().getPoints().get(this.polygonShape.getShape().getPoints().size()-1), UtilityTest.EPSILON);
    }

    /**
     * Test of setLast method, of class PolygonShape.
     */
    @Test
    public void testSetLast() {
        System.out.println("setLast");

        //TEST: set the original last point: (20,10) -> expected new last poitn (10,10)
        this.polygonShape.setLast(this.coordinates.get(0), this.coordinates.get(1));
        
        assertEquals(this.coordinates.get(0), this.polygonShape.getShape().getPoints().get(this.polygonShape.getShape().getPoints().size()-2), UtilityTest.EPSILON);
        assertEquals(this.coordinates.get(1), this.polygonShape.getShape().getPoints().get(this.polygonShape.getShape().getPoints().size()-1), UtilityTest.EPSILON);
    }

    /**
     * Test of isClosed method when the shape is closed, of class PolygonShape.
     */
    @Test
    public void testIsClosed() {
        System.out.println("isClosed");
        
        double toAddX = this.coordinates.get(0);
        double toAddY = this.coordinates.get(1);
        
        //TEST: add last point as the first point -> the shape is closed
        this.polygonShape.getShape().getPoints().add(toAddX);
        this.polygonShape.getShape().getPoints().add(toAddY);
        
        assertTrue(this.polygonShape.isClosed());
    }
    

    /**
     * Test of isClosed method when the shape is open, of class PolygonShape.
     */    
    @Test
    public void testIsOpen(){
        
        double toAddX = this.coordinates.get(0);
        double toAddY = this.coordinates.get(1);
        
        //TEST: Add last point differently from first point -> the shape is open
        this.polygonShape.getShape().getPoints().set(this.polygonShape.getShape().getPoints().size()-2,toAddX-this.OFFSET);
        this.polygonShape.getShape().getPoints().set(this.polygonShape.getShape().getPoints().size()-1,toAddY-this.OFFSET);
        
        assertFalse(this.polygonShape.isClosed());
    }

    /**
     * Test of getPoint method, of class PolygonShape.
     */
    @Test
    public void testGetPoint() {
        System.out.println("getPoint");
        
        //TEST: Setted point at specific index are the same as those returned by the getPoint method at the same indexes
        assertEquals(this.coordinates.get(0), this.polygonShape.getPoint(0), UtilityTest.EPSILON);
        assertEquals(this.coordinates.get(1), this.polygonShape.getPoint(1), UtilityTest.EPSILON);
    }

    /**
     * Test of getNumberOfPoints method, of class PolygonShape.
     */
    @Test
    public void testGetNumberOfPoints() {
        System.out.println("getNumberOfPoints");
        
        //TEST: One vertice is composed by two point -> expected that number of vertices = number of point / 2;
        assertEquals(this.NUM_POINTS/2, this.polygonShape.getNumberOfVertices());
    }

    /**
     * Test of resize method, of class PolygonShape.
     */
    @Test
    public void testResize() {
        System.out.println("resize");
        
        //TEST: Resizing polygonShape with new width and height given by the original one + offset
        this.polygonShape.resize(this.width+this.OFFSET, this.height+this.OFFSET);
        
        assertEquals(this.width+this.OFFSET, this.polygonShape.getWidth(), UtilityTest.EPSILON);
        assertEquals(this.height+this.OFFSET, this.polygonShape.getHeight(), UtilityTest.EPSILON);
    }

    /**
     * Test of getWidth method, of class PolygonShape.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");

        //TEST: Assert that the shape has the correct width     
        assertEquals(this.width, this.polygonShape.getWidth(), UtilityTest.EPSILON);
    }

    /**
     * Test of getHeight method, of class PolygonShape.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        
        //TEST: Assert that the shape has the correct height
        assertEquals(this.height, this.polygonShape.getHeight(), UtilityTest.EPSILON);
    }
    
    /**
     * Test of setWidth method, of class PolygonShape.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        
        //TEST: Expected that setted width and current width by get width method are the same
        this.polygonShape.setWidth(TEST_NEW_WIDTH);        
        assertEquals(TEST_NEW_WIDTH, this.polygonShape.getWidth(), UtilityTest.EPSILON);
    }

    /**
     * Test of getHeight method, of class PolygonShape.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        
        //TEST: Expected that setted height and current height by get height method are the same
        this.polygonShape.setHeight(TEST_NEW_HEIGHT);        
        assertEquals(TEST_NEW_HEIGHT, this.polygonShape.getHeight(), UtilityTest.EPSILON);
    }    
    
    /**
     * Test of getShape method, of class PolygonShape.
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    @Test
    public void testGetShape() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("getPolygon");

        //TEST: Assert that the returned shape is the real shape
        final Field field = this.polygonShape.getClass().getDeclaredField("polyline");
        field.setAccessible(true);
        
        final Polyline polygonTest = new Polyline();
        field.set(this.polygonShape, polygonTest);
        
        assertEquals(polygonTest, this.polygonShape.getShape());
    }

}