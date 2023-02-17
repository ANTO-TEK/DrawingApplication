/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.tool;

import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Paint;

/** 
 * Abstract class that represents a tool to manage shape objects
 */
public abstract class ShapeTool extends Tool {
    
    protected final static double STROKE_WIDTH = 2;    
    private final ObjectProperty strokePickerProperty;
    
    public ShapeTool(ObjectProperty strokePickerProperty){
        this.strokePickerProperty = strokePickerProperty;
    }
    
    /**
     * Getter method that returns the current stroke color selected in the picker
     * @return the Paint value contained in the property
     */
    public Paint getCurrentStrokeColor(){
        return (Paint) this.strokePickerProperty.getValue();
    }
    
    
    /**
     * Getter method that returns stroke width of a shape tool.
     * @return the default stroke width
     */
    protected static double getStrokeWidth() {
        return STROKE_WIDTH;
    }
    
}
