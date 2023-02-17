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
import javafx.concurrent.Task;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Class of a task that takes care of loadgin shapes from file.
 */
public class LoadTask extends Task<ArrayList<CustomShape>> {
    
    private File file;
    
    public LoadTask(File file){
        this.file = file;
    }
    
    /**
     * Reads the custom shape saved into the given file.
     * @return a list of the custom shape read
     * @throws Exception when the extension is not supported by the application for loading file
     */
    @Override
    protected ArrayList<CustomShape> call() throws Exception {        
        //Reads from the file
        ArrayList<CustomShape> read = new ArrayList<>();
        IOManager.load(file.getPath(), read);
       
        
        return read;

    }

}
