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
 * Command to cut a shape.
 */
public class CutCommand implements Command{

    private final CustomShape toCut;
    private final ClipboardContent content;
    private final DeleteCommand delCmd;
    private final Clipboard clipboard;
    
    public CutCommand(CustomShape toCut){
        this.toCut = toCut;    
        this.content = new ClipboardContent();
        this.delCmd = new DeleteCommand(this.toCut);
        this.clipboard = Clipboard.getApplicationClipboard();
    }
    
    /**
     * Copies the serialized form of the given shape in application clipboard, with the custom dataformat DWNG, also delete the original shape.
     */
    @Override
    public void execute() {
        this.content.put(DWNGSaverAndLoader.DWNG_DATA_FORMAT, DWNGSaverAndLoader.getShapeSerialized(this.toCut)); //Content to paste in app
        this.content.put(DataFormat.PLAIN_TEXT, this.toCut.toString());
        
        this.clipboard.setContent(this.content);
        this.delCmd.execute();
    }

    /**
     * Undo the associated delete command and clears the content of application clipboard.
     */
    @Override
    public void undo() {
        this.clipboard.clear();
        this.delCmd.undo();
    }
    
}
