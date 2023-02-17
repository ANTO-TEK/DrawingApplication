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

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.io.Clipboard;
import unisa.diem.se.drawingapp.io.Clipboard.ClipboardContent;
import unisa.diem.se.drawingapp.io.DWNGSaverAndLoader;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class PasteCommandTest {
    
    private Command command;
    private CustomShape toPaste;
    private Pane drawingPane;
    private Clipboard clipboard;
    private ClipboardContent content;
    
    @Before
    public void setUp(){
        this.drawingPane = UtilityTest.createAndSetPane();
        this.toPaste = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.toPaste.getShape().setFill(Color.BLACK);
        this.toPaste.getShape().setStroke(Color.BLACK);
        this.toPaste.draw();
        this.command = new PasteCommand();
        this.content = new ClipboardContent();
        this.clipboard = Clipboard.getApplicationClipboard();
        this.clipboard.clear();
    }

    /**
     * Test of execute method, of class PasteCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST: Assert after the execute the pane has the shape
        this.content.put(DWNGSaverAndLoader.DWNG_DATA_FORMAT, DWNGSaverAndLoader.getShapeSerialized(this.toPaste));
        this.clipboard.setContent(content);
        this.command.execute();
        
        CustomShape takenFromClipboard = (CustomShape) DWNGSaverAndLoader.getShapeFromSerializedShape((byte[]) this.clipboard.getContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT));
        
        assertEquals(this.toPaste.toString(), takenFromClipboard.toString());
        assertEquals(2, this.drawingPane.getChildren().size());
        
        Shape copiedShape = (Shape) this.drawingPane.getChildren().get(1);
        assertEquals(this.toPaste.getShape().toString(), copiedShape.toString());
    }

    /**
     * Test of undo method, of class PasteCommand.
     */
    @Test
    public void testUndo() {
        System.out.println("undo");
        
        //TEST: Assert after the execute the pane has not the shape
        this.content.put(DWNGSaverAndLoader.DWNG_DATA_FORMAT, DWNGSaverAndLoader.getShapeSerialized(this.toPaste));
        this.clipboard.setContent(content);
        CustomShape takenFromClipboard = (CustomShape) DWNGSaverAndLoader.getShapeFromSerializedShape((byte[]) Clipboard.getApplicationClipboard().getContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT));
        this.command.execute();
        this.command.undo();
        
        Assert.assertFalse(DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().contains(takenFromClipboard.getShape()));
    }
    
}
