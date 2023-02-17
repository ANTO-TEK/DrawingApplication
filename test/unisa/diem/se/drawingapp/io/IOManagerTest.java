/** 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class IOManagerTest {
    
    private static final String SUPPORTED_FILE_NAME = "ILoveSE.dwng";
    private static final String UNSUPPORTED_FILE_NAME = "ILoveSE.txt";
    
    private CustomShape testShape;
    
    @Before
    public void setUp(){
        UtilityTest.createAndSetPane();
        this.testShape = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.testShape.getShape().setFill(Color.BLACK);
        this.testShape.getShape().setStroke(Color.BLACK);
        this.testShape.draw();
    }
    
    @After
    public void tearDown() {
        File file = new File(IOManagerTest.SUPPORTED_FILE_NAME);
        file.delete();
        file = new File(IOManagerTest.UNSUPPORTED_FILE_NAME);
        file.delete();
    }
    
    /**
     * Test of save method, of class IOManager.Saving in a file with a supported extension.
     * @throws java.io.IOException
     */
    @Test
    public void testSaveSupportedExtension() throws IOException {
        System.out.println("saveSupportedExtension");
        
        //TEST: Saving file with supported extension, empty data array
        ArrayList<CustomShape> dataLoaded = new ArrayList<>();
        IOManager.save(IOManagerTest.SUPPORTED_FILE_NAME, new ArrayList<>(DrawingSurfaceManager.getInstance().getShapes().values()));
        
        File f = new File(IOManagerTest.SUPPORTED_FILE_NAME);
        
        assertNotNull(f); //if the save has been correctly performed then the file has been created and the pointer is not null

        IOManager.load(IOManagerTest.SUPPORTED_FILE_NAME, dataLoaded);
        
        assertNotNull(dataLoaded);
        assertEquals(DrawingSurfaceManager.getInstance().getShapes().size(), dataLoaded.size());
        assertEquals(this.testShape.toString(), dataLoaded.get(0).toString());
    }
    
    /**
     * Test of save method, of class IOManager.Saving in a file with an unsupported extension.
     * @throws java.io.IOException
     */
    @Test(expected = IOException.class)
    public void testSaveUnsupportedExtension() throws IOException {
        System.out.println("saveUnsupportedExtension");
        
        //TEST: Saving file with unsupported extension        
        IOManager.save(IOManagerTest.UNSUPPORTED_FILE_NAME, new ArrayList<>(DrawingSurfaceManager.getInstance().getShapes().values()));
    }

    /**
     * Test of load method, of class IOManager.Loading from a file with a supported extension
     * @throws java.io.IOException
     */
    @Test
    public void testLoadSupportedExtension() throws IOException {
        System.out.println("loadSupportedExtension");  
        
        //TEST: Load from a file with supported extension
        ArrayList<CustomShape> dataLoaded = new ArrayList<>();
        IOManager.save(IOManagerTest.SUPPORTED_FILE_NAME, new ArrayList<>(DrawingSurfaceManager.getInstance().getShapes().values()));
        
        IOManager.load(IOManagerTest.SUPPORTED_FILE_NAME, dataLoaded);
        
        assertNotNull(dataLoaded);
        assertEquals(DrawingSurfaceManager.getInstance().getShapes().size(), dataLoaded.size());
        assertEquals(this.testShape.toString(), dataLoaded.get(0).toString());
    }
    
    /**
     * Test of load method, of class IOManager.Loading from a file with an unsupported extension
     * @throws java.io.IOException
     */
    @Test(expected = IOException.class)
    public void testLoadUnsupportedExtension() throws IOException {
        System.out.println("loadUnsupportedExtension");  
        
        //TEST: Load from a file with unsupported extension      
        ArrayList<CustomShape> dataLoaded = new ArrayList<>();
        IOManager.load(IOManagerTest.UNSUPPORTED_FILE_NAME, dataLoaded);
    }
    
}
