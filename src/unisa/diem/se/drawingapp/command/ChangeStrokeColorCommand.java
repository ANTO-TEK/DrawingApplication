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

import javafx.scene.paint.Paint;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Command to change stroke color of a given shape.
 */
public class ChangeStrokeColorCommand implements Command {
    
    private final CustomShape shape;
    private final Paint newColor;
    private final Paint oldColor;

    public ChangeStrokeColorCommand(CustomShape shape, Paint newColor){
        this.shape = shape;
        this.oldColor = shape.getShape().getStroke();
        this.newColor = newColor;
    }
    
    /**
     * Changes the stroke color of the given shape into the new color.
     */
    @Override
    public void execute() {
        this.shape.getShape().setStroke(this.newColor);
    }

    /**
     * Changes the stroke color of the given shape with the original color.
     */
    @Override
    public void undo() {
        this.shape.getShape().setStroke(this.oldColor);
    }

}
