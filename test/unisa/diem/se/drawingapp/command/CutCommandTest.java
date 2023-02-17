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
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.io.Clipboard;
import unisa.diem.se.drawingapp.io.DWNGSaverAndLoader;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class CutCommandTest {
    
    private Command command;
    private CustomShape toCut;
    private Clipboard clipboard;
    
    @Before
    public void setUp(){
        UtilityTest.createAndSetPane();
        this.toCut = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.toCut.getShape().setFill(Color.BLACK);
        this.toCut.getShape().setStroke(Color.BLACK);
        this.toCut.draw();
        this.command = new CutCommand(this.toCut);
        this.clipboard = Clipboard.getApplicationClipboard();
        this.clipboard.clear();
    }
    

    /**
     * Test of execute method, of class CutCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST: Assert that the system clipboard contains the cut shape in the dwng format and the pane doesn't contains it
        byte[] encodedShape = DWNGSaverAndLoader.getShapeSerialized(this.toCut);
        
        this.command.execute();
        Assert.assertTrue(this.clipboard.hasContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT));
        
        byte[] clipboardContent = (byte[]) this.clipboard.getContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT);
        
        Assert.assertFalse(DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().contains(this.toCut.getShape()));
        Assert.assertArrayEquals(encodedShape, clipboardContent);
    }

    /**
     * Test of undo method, of class CutCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        //TEST: Assert that the system clipboard doens't contains the cut shape in the dwng format and the shape is in the pane
        this.command.execute();
        this.command.undo();
        
        Assert.assertFalse(this.clipboard.hasContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT));
        Assert.assertTrue(DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().contains(this.toCut.getShape()));    
    }
    
}
