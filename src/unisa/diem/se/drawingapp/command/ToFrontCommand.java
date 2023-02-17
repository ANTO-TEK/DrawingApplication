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

import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Command to set a shape on top of shapes in the same place.
 */
public class ToFrontCommand implements Command {

    private final CustomShape shape;
    private int originalPosition;
    
    public ToFrontCommand(CustomShape shape) {
        this.shape = shape; 
        DrawingSurfaceManager.getInstance().getDrawingPane();
    }
    
    /**
     * Brings the given shape in front of the others.
     */
    @Override
    public void execute() {
        this.originalPosition = this.shape.getShapeLevelOnPane();
        this.shape.getShape().toFront();
    }

    /**
     * Brings the given shape back to its original position.
     */
    @Override
    public void undo() {
        this.shape.erase();
        this.shape.setShapeLevelOnPane(this.originalPosition);
        
    }
    
}
