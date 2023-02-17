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

import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Command to mirror the shape horizontally.
 */
public class HorizontalMirroringCommand implements Command {
    
    private final CustomShape shape;
    private final double originalScaleY;
    private final double originalAngle;
    
    public HorizontalMirroringCommand(CustomShape shape){
        this.shape = shape;
        this.originalScaleY = this.shape.getShape().getScaleY();
        this.originalAngle = this.shape.getShape().getRotate();
    }

    /**
     * Mirrors the given shape horizontally.
     */
    @Override
    public void execute() {
        this.shape.getShape().setScaleY(this.originalScaleY * -1);
        this.shape.getShape().setRotate(this.originalAngle * -1);
    }

    /**
     * Restore the previously scale factor to undoes the horizontally mirror effect.
     */
    @Override
    public void undo() {
        this.shape.getShape().setScaleY(this.originalScaleY);
        this.shape.getShape().setRotate(this.originalAngle);
    }
}
