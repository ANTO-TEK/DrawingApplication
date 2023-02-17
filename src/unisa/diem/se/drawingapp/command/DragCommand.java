/** 
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
 * Command to move a figure that is dragged by the mouse.
 */
public class DragCommand implements Command {
    
    private final CustomShape selectedShape;
    private final double prevX, prevY, newPosX, newPosY;
    
    public DragCommand(CustomShape selectedShape, double prevX, double prevY, double newPosX, double newPosY) {
        this.selectedShape = selectedShape;
        this.prevX = prevX;
        this.prevY = prevY;
        this.newPosX = newPosX;
        this.newPosY = newPosY;
    }

    /**
     * Moves the given shape in the given position.
     */
    @Override
    public void execute() {
        this.selectedShape.moveTo(this.newPosX, this.newPosY);
    }

    /**
     * Moves the shape in the previous position.
     */
    @Override
    public void undo() {
        this.selectedShape.moveTo(this.prevX, this.prevY);
        SelectedShapeManager.getInstance().unselectShape();
    }

}
