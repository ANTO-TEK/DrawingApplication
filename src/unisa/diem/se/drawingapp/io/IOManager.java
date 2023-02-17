/** 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.io;

import java.io.IOException;
import java.util.Collection;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Class to manage input and output operations.
 */
public class IOManager {
    
    /**
     * Saves the list of data supplied in the file contained in the path,
     * if and only if the file has an accepted extension.
     * @param path
     * @param data 
     * @throws java.io.IOException 
     */
    public static void save(String path, Collection<CustomShape> data) throws IOException {
        FileExtension ext = IOManager.extensionTypeDetermination(path);
        ext.write(path, data);
    }
    
    /**
     * Loads in the supplied list of data, the content of the file specified by the path, 
     * if and only if the path contains a file with an accepted extension.
     * @param path
     * @param data 
     * @throws java.io.IOException 
     */
    public static void load(String path, Collection<CustomShape> data) throws IOException {
        FileExtension ext = IOManager.extensionTypeDetermination(path);
        ext.read(path, data);
    }
    
    /**
     * Given the name of a file or a complete path, if the file has an extension
     * accepted by the IO application, it returns an object of the FileExtension class 
     * associated with that extension, otherwise it throws an exception.
     * @param fileName
     * @return the object to read and write associated with the supported extension
     * @throws java.io.IOException when the extension is not supported
     */
    private static FileExtension extensionTypeDetermination(String fileName) throws IOException {
        if(fileName.endsWith(".dwng"))
            return new DWNGSaverAndLoader();
        //...may have more extensions
        throw new IOException("File extension not supported for saving and load."); //Unreachble because FileChooser limits the file extensions
    }

}
