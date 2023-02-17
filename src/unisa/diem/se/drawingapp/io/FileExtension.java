/** 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.io;

import java.util.Collection;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Interface representing an object to read and write data to a file having a specific extension.
 */
public interface FileExtension {
    
    /**
     * Reads CustomShape data from the given path, saves them in the given collection.
     * @param path where the data is contained
     * @param data collection to store the read data
     */
    void read(String path, Collection<CustomShape> data);
    
    /**
     * Writes the CustomShape data contained in the given collection into the file contained in the given path.
     * @param path
     * @param data 
     */
    void write(String path, Collection<CustomShape> data);
}
