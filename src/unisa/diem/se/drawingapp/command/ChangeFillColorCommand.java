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
 * Command to change fill color of a given shape.
 */
public class ChangeFillColorCommand implements Command {
    
    private final CustomShape shape;
    private final Paint newColor;
    private final Paint oldColor;
   
    public ChangeFillColorCommand(CustomShape shape, Paint newColor){
        this.shape = shape;
        this.oldColor = shape.getShape().getFill();
        this.newColor = newColor;
    }
    
    /**
     * Changes the fill color of the given shape into the new color.
     */
    @Override
    public void execute() {
        this.shape.getShape().setFill(this.newColor);
    }

    /**
     * Changes the fill color of the given shape with the original color.
     */
    @Override
    public void undo() {
        this.shape.getShape().setFill(this.oldColor);
    }

}