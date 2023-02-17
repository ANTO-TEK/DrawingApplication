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

import unisa.diem.se.drawingapp.controller.SelectedShapeManager;
import unisa.diem.se.drawingapp.io.Clipboard;
import unisa.diem.se.drawingapp.io.DWNGSaverAndLoader;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Command for paste a shape.
 */
public class PasteCommand implements Command{
    
    private CustomShape toPaste;
    
    public PasteCommand(){
        this.toPaste = null;
    }
    
    /**
     * Pastes a shape on the given drawing pane from the application clipboard.
     * A prerequisite is that the clipboard has a shape in serialized form corresponding in a content of DWNG_DATA_FORMAT.
     */
    @Override
    public void execute() {
        Clipboard clipboard = Clipboard.getApplicationClipboard();
        //assume that the check has been performed
        byte[] serializedShape = (byte[]) clipboard.getContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT);

        //Get the original shape from the serialized one
        this.toPaste = DWNGSaverAndLoader.getShapeFromSerializedShape(serializedShape);
        this.toPaste.getShape().getBoundsInParent().getMinX();
        //Move the shape in a prefixed point in the drawing pane and draws it
        
        this.toPaste.getShape().setLayoutX(SelectedShapeManager.getInstance().getIncrement());
        this.toPaste.getShape().setLayoutY(SelectedShapeManager.getInstance().getIncrement());
 
        this.toPaste.draw();
    }

    /**
     * Undoes the insertion of the pasted figure.
     */
    @Override
    public void undo() {
        this.toPaste.erase();
    }
}
