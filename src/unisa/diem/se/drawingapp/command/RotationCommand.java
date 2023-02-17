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
 * Commando to rotate a shape.
 */
public class RotationCommand implements Command {
    
    private final double newValue;
    private final double oldValue;
    private final CustomShape shape;
    
    public RotationCommand(CustomShape shape, double newValue) {
        this.shape = shape;
        this.newValue = newValue;
        this.oldValue = shape.getShape().getRotate();
    }

    /**
     * Rotates the given shape at the given angle.
     */
    @Override
    public void execute() {
        this.shape.rotate(this.newValue);
    }

    /**
     * Restores the slope of the shape.
     */
    @Override
    public void undo() {
        this.shape.rotate(this.oldValue);
    }

}
