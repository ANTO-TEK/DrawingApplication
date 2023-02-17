/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.io;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.shape.*;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class DWNGSaverAndLoaderTest {
    
    private static final String FILE_NAME = "ILoveSE.dwng";
    private RectangleShape testRectangle;
    private DWNGSaverAndLoader extensionManager;

    @Before
    public void setUp() {
        this.extensionManager = new DWNGSaverAndLoader();
        this.testRectangle = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE); 
        this.testRectangle.getShape().setFill(Color.BLACK); 
        this.testRectangle.getShape().setStroke(Color.BLACK);
    }

    /**
     * Test of read method, of class DWNGSaverAndLoader.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        
        //TEST: read from a the file and assert that the readed objects have the same properties of the writed ones
        ArrayList<CustomShape> toSave = new ArrayList<>();
        toSave.add(this.testRectangle);
        
        this.extensionManager.write(DWNGSaverAndLoaderTest.FILE_NAME, toSave);
        
        ArrayList<CustomShape> dataLoaded = new ArrayList<>();
        this.extensionManager.read(FILE_NAME, dataLoaded);
        
        assertEquals(1, dataLoaded.size());
        assertEquals(this.testRectangle.toString(), dataLoaded.get(0).toString());
    }

    /**
     * Test of write method, of class DWNGSaverAndLoader.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        
        //TEST: read from a the file and assert that the readed objects have the same properties of the writed ones
        ArrayList<CustomShape> toSave = new ArrayList<>();
        toSave.add(this.testRectangle);
        
        this.extensionManager.write(DWNGSaverAndLoaderTest.FILE_NAME, toSave);
        
        File file = new File(DWNGSaverAndLoaderTest.FILE_NAME);
        assertNotNull(file); //if null the file has not been created
        
        ArrayList<CustomShape> dataLoaded = new ArrayList<>();
        this.extensionManager.read(FILE_NAME, dataLoaded);
        
        assertEquals(1, dataLoaded.size());
        assertEquals(this.testRectangle.toString(), dataLoaded.get(0).toString());
    }

    /**
     * Test of getShapeSerialized and getShapeFromSerializedShape methods, of class DWNGSaverAndLoader.
     */
    @Test
    public void testGetShapeSerialized() {
        System.out.println("getShapeSerialized");
        
        //TEST: Given a shape, the reconstructed shape from the serialized form has the same properties
        byte[] serializedShape = DWNGSaverAndLoader.getShapeSerialized(this.testRectangle);
        
        assertNotNull(serializedShape);
        assertNotEquals(0, serializedShape.length);
        
        assertEquals(this.testRectangle.toString(), ((CustomShape)DWNGSaverAndLoader.getShapeFromSerializedShape(serializedShape)).toString());
    }

    /**
     * Test of getShapeFromSerializedShape method, of class DWNGSaverAndLoader.
     */
    @Test
    public void testGetShapeFromSerializedShape() {
        System.out.println("getShapeFromSerializedShape");
        
        //TEST: the reconstructed shape has the same properties of the encoded shape
        byte[] serializedShape = DWNGSaverAndLoader.getShapeSerialized(this.testRectangle);
        
        CustomShape decoded = DWNGSaverAndLoader.getShapeFromSerializedShape(serializedShape);
        
        assertEquals(this.testRectangle.toString(), decoded.toString());
    }

}