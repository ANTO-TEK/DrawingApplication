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
 * Command to mirror the shape vertically.
 */
public class VerticalMirroringCommand implements Command{
    
    private final CustomShape shape;
    private final double originalScaleX;
    private final double originalAngle;

    
    public VerticalMirroringCommand(CustomShape shape){
        this.shape = shape;
        this.originalScaleX = this.shape.getShape().getScaleX();
        this.originalAngle = this.shape.getShape().getRotate();
    }
    
    /**
     * Mirrors the given shape vertically.
     */
    @Override
    public void execute() {
        this.shape.getShape().setScaleX(originalScaleX*-1);
        this.shape.getShape().setRotate(originalAngle*-1);
    }

    /**
     * Restores the previously scale factor to undoes the vertically mirror effect.
     */
    @Override
    public void undo() {
        this.shape.getShape().setScaleX(originalScaleX);
        this.shape.getShape().setRotate(originalAngle);
    }    
    
}
