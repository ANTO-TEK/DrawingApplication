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
 * Command to draw the shape in the drawing pane.
 */
public class DrawCommand implements Command{
    
    private final CustomShape shape;

    public DrawCommand(CustomShape shape) {
        this.shape = shape;       
    }
    
    /**
     * Draws the shape in the pane.
     */
    @Override
    public void execute() {
        this.shape.draw();
    }

    /**
     * Erase the previously drawn shape.
     */
    @Override
    public void undo() {
        this.shape.erase();
        DrawingSurfaceManager.getInstance().completeToolWork();
    }
}