/** 
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
 * Abstract class that represents a tool to manage closed shape objects.
 */
public abstract class ClosedShapeTool extends ShapeTool {

    private final ObjectProperty fillPickerProperty ;

    public ClosedShapeTool(ObjectProperty strokePickerProperty, ObjectProperty fillPickerProperty) {
        super(strokePickerProperty);
        this.fillPickerProperty = fillPickerProperty;
    }
    
    /**
     * Getter method that returns the current fill color selected in the picker
     * @return the Paint value contained in the property
     */
    public Paint getCurrentFillColor() {
        return (Paint) this.fillPickerProperty.getValue();
    }
}
