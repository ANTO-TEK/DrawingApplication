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

import unisa.diem.se.drawingapp.controller.SelectedShapeManager;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Command to delete a shape.
 */
public class DeleteCommand implements Command {
    
    private final CustomShape shape;
    
    public DeleteCommand(CustomShape shape) {
        this.shape = shape;       
    }

    /**
     * Erases from the drawing pane the given shape.
     */
    @Override
    public void execute() {
        SelectedShapeManager.getInstance().unselectShape();
        this.shape.erase();
    }

    /**
     * Draws the shape previously erased.
     */
    @Override
    public void undo() {
        this.shape.draw();
    }
    
}
