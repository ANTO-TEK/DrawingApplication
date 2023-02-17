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
 * Command to resize a shape.
 */
public class ResizeCommand implements Command {

    private final CustomShape toResize;
    private final double newWidth;
    private final double newHeight;
    private final double oldWidth;
    private final double oldHeight;

    public ResizeCommand(CustomShape toResize, double newWidth, double newHeight){
        this.toResize = toResize;
        this.newWidth = newWidth;
        this.newHeight = newHeight;
        this.oldWidth = this.toResize.getWidth();
        this.oldHeight = this.toResize.getHeight();
    }
    
    /**
     * Resizes the given shape with the new width and new height values.
     */
    @Override
    public void execute() {
        this.toResize.resize(this.newWidth, this.newHeight);
    }

    /**
     * Undoes the previously executed resize operation.
     */
    @Override
    public void undo() {
        this.toResize.resize(this.oldWidth, this.oldHeight);
    }

}
