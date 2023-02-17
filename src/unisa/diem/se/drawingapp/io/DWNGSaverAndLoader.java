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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.DataFormat;
import unisa.diem.se.drawingapp.controller.FXMLDocumentController;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Class that represents a file extension saver and loader for a custom extension .dwng. I
 * .dwng is the extension of a file that containts the serialized custom shapes.
 */
public class DWNGSaverAndLoader implements FileExtension {
    
    public static final DataFormat DWNG_DATA_FORMAT = new DataFormat("application/octet-stream");
    private static final String SERIALIZATION_MSG_ERROR = "Errors occurred during deserialization process.";
    private static final String DESERIALIZATION_MSG_ERROR = "Errors occurred during serialization process.";

    /**
     * Reads from the .dwng file contained in path a collection of data and saves them in the received collection object.
     * @param path where the file is located
     * @param data reading container
     */
    @Override
    public void read(String path, Collection<CustomShape> data) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            data.clear();
            data.addAll((Collection<CustomShape>) ois.readObject());
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, DWNGSaverAndLoader.DESERIALIZATION_MSG_ERROR, ex);
        }
    }

    /**
     * Writes on the .dwng file at the given path, the data contained in the received list.
     * @param path where the file is located
     * @param data writing container
     */
    @Override
    public void write(String path, Collection<CustomShape> data) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(data); 
        } catch (IOException ex) {
            Logger.getLogger(DWNGSaverAndLoader.class.getName()).log(Level.SEVERE, DWNGSaverAndLoader.SERIALIZATION_MSG_ERROR, ex);
        }
    }
    
     /**
     * Given a shape returns a bytearray that contains the serialized form of that shape.
     * Utility method for copy and cut method.
     * @param shape to serialize
     * @return bytes of the serialized shape
     */
    public static byte[] getShapeSerialized(CustomShape shape){
        byte[] buf = null;
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos);){
            oos.writeObject(shape);
            buf = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(DWNGSaverAndLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buf;
    }
    
    /**
     * Given a byte array that represents a serialized shape, returns a clone of the shape.
     * Utility method for paste.
     * @param serializedShape bytes that contains the properties of the shape
     * @return a shape constructed with the decoded properties.
     */
    public static CustomShape getShapeFromSerializedShape(byte[] serializedShape){
        
        CustomShape shape = null;
        try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(serializedShape))){
            shape = (CustomShape) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(DWNGSaverAndLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return shape;
    }
    
}
