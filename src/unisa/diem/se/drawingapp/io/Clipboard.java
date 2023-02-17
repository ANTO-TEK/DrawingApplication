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

import java.util.HashMap;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.input.DataFormat;

/**
 * Class that represents the application Clipboard.
 * Implemented as a Singleton.
 */
public final class Clipboard {
    
    private static Clipboard uniqueInstance;
    private HashMap<DataFormat, Object> content;
    private final BooleanProperty empty;
    
    private Clipboard(){
        Clipboard.uniqueInstance = null;
        this.content = new HashMap();
        this.empty = new ReadOnlyBooleanWrapper();
        this.setEmpty(true);
    }
    
    /**
     * Returns the unique instance of the class.
     * @return the unique instance.
     */
    public static Clipboard getApplicationClipboard(){
        if(Clipboard.uniqueInstance == null)
            Clipboard.uniqueInstance = new Clipboard();
        return Clipboard.uniqueInstance;
    }
    
    /**
     * Puts the given content in the clipboard.
     * @param content, object of the class ClipboardContent 
     */
    public void setContent(HashMap<DataFormat, Object> content){
        this.content = content;
        this.setEmpty(false);
    }
    
    /**
     * Given a data format returns the object associated with that data format in the clipboard.
     * @param df
     * @return an object, if any, or null
     */
    public Object getContent(DataFormat df) {
        return this.content.get(df) ;
    }

    /**
     * Tells if there is an object associated with the given data format.
     * @param df
     * @return a boolean
     */
    public boolean hasContent(DataFormat df){
        return this.content.containsKey(df);
    }
    
    /**
     * Clears the content of application clipboard.
     */
    public void clear(){
        this.content.clear();
        this.setEmpty(true);
    }

    /**
     * Tells if the clipboard has any object.
     * @return a boolean value: True if the command history is empty
     */
    public boolean isEmpty() {
        return empty.get();
    }

    private void setEmpty(boolean value) {
        empty.set(value);
    }

    /**
     * Returns the empty property of the clipboard.
     * @return empty property.
     */
    public BooleanProperty emptyProperty() {
        return empty;
    }
    
    /**
     * Inner class that represents the contents of the clipboard.
     * Represented through a key-value map where it is possible to insert the various formats of a certain object.
     * key: DataFormat. Indicates the format of the associated value
     * value: Object. The object in the indicated format
     */
    public static class ClipboardContent extends HashMap<DataFormat, Object>{
        
    }
    
}
