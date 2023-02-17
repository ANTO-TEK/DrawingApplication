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
 * Sends the selected shape behind other shapes on the same point relative to the z-axis.
 */
public class ToBackCommand implements Command {
    
    private final CustomShape shape;
    private int originalPosition;
    
    public ToBackCommand(CustomShape shape) {
        this.shape = shape;
        DrawingSurfaceManager.getInstance().getDrawingPane();
    }
    
    /**
     * Execute method that brings the selected shape back with respect to the others
     */
    @Override
    public void execute() {
        this.originalPosition = this.shape.getShapeLevelOnPane();
        this.shape.getShape().toBack();
    }

    /**
     * Undo method which brings the selected shape to its original position
     */
    @Override
    public void undo() {
        this.shape.erase();
        this.shape.setShapeLevelOnPane(this.originalPosition);
    }
}
