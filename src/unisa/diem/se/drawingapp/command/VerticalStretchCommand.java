/** 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.command;

import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Command to stretch shapes vertically.
 */
public class VerticalStretchCommand implements Command {
    
    private final double newValue;
    private final double oldValue;
    private final CustomShape shape;
    
    public VerticalStretchCommand(CustomShape shape, double newValue) {
        this.shape = shape;
        this.newValue = newValue;
        this.oldValue = shape.getShape().getScaleY();
    }

    /**
     * Vertically stretchs the given figure according to the new scale value.
     */
    @Override
    public void execute() {
        this.shape.getShape().scaleYProperty().setValue(this.newValue);
    }

    /**
     * Restores the previous horizontal scale factor.
     */
    @Override
    public void undo() {
        this.shape.getShape().scaleYProperty().setValue(this.oldValue);
    }

}
