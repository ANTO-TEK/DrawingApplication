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

import javafx.scene.input.DataFormat;
import unisa.diem.se.drawingapp.io.Clipboard;
import unisa.diem.se.drawingapp.io.Clipboard.ClipboardContent;
import unisa.diem.se.drawingapp.io.DWNGSaverAndLoader;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Command to copy a shape.
 */
public class CopyCommand implements Command {

    private ClipboardContent content;
    private final CustomShape toCopy;
    private final Clipboard clipboard;
    
    public CopyCommand(CustomShape toCopy){
        this.toCopy = toCopy;
        this.clipboard = Clipboard.getApplicationClipboard();
    }
    
    /**
     * Copies the serialized form of the given shape in application clipboard, with the custom dataformat DWNG.
    */
    @Override
    public void execute() {
        this.content = new ClipboardContent();
        
        this.content.put(DWNGSaverAndLoader.DWNG_DATA_FORMAT, DWNGSaverAndLoader.getShapeSerialized(this.toCopy)); //Content to paste in app
        this.content.put(DataFormat.PLAIN_TEXT, this.toCopy.toString());
        
        this.clipboard.setContent(this.content);
    }

    /**
     * Clears the content of the application clipboard.
     */
    @Override
    public void undo() {
        this.clipboard.clear();
    }

}
