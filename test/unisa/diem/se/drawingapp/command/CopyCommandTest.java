/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.command;

import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unisa.diem.se.drawingapp.io.Clipboard;
import unisa.diem.se.drawingapp.io.DWNGSaverAndLoader;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class CopyCommandTest {
    
    private Command command;
    private CustomShape toCopy;
    private Clipboard clipboard;
    
    @Before
    public void setUp(){
        UtilityTest.createAndSetPane();
        this.toCopy = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.toCopy.getShape().setFill(Color.BLACK);
        this.toCopy.getShape().setStroke(Color.BLACK);
        this.toCopy.draw();
        this.command = new CopyCommand(this.toCopy);
        this.clipboard = Clipboard.getApplicationClipboard();
        this.clipboard.clear();
    }
    
    /**
     * Test of execute method, of class CopyCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST: Assert that the system clipboard contains the copied shape in the dwng format
        byte[] encodedShape = DWNGSaverAndLoader.getShapeSerialized(this.toCopy);
        
        this.command.execute();
        
        byte[] clipboardContent = (byte[]) this.clipboard.getContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT);
        
        Assert.assertTrue(this.clipboard.hasContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT));
        Assert.assertArrayEquals(encodedShape, clipboardContent);
    }
    
    /**
     * Test of undo method, of class CopyCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        //TEST: Assert that the system clipboard doens't contains the copied shape in the dwng format
        this.command.execute();  
        this.command.undo();
        Assert.assertFalse(this.clipboard.hasContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT));
    }
}