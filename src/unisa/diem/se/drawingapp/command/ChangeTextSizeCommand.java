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

import unisa.diem.se.drawingapp.shape.TextShape;

/**
 * Command to change text size of a given shape.
 */
public class ChangeTextSizeCommand implements Command{

    private final TextShape textShape;
    private final int oldTextSize;
    private final int newTextSize;
    
    private static final double EM_TO_PX_RATIO = 16; //Used to map the receveid px in em 
    
    public ChangeTextSizeCommand(TextShape textShape, int newTextSize){
        //I'm sure that the shape is a text
        this.textShape = textShape;
        
        this.oldTextSize = (int) this.textShape.getShape().getFont().getSize();
        this.newTextSize = newTextSize;
    }
    
    /**
     * Changes the text size of the provided TextShape object.
     */
    @Override
    public void execute() {
        this.textShape.getShape().setStyle("-fx-font: " + (this.newTextSize/EM_TO_PX_RATIO) + "em " + this.textShape.getShape().getFont().getFamily() + ";"); //16px:1em = fontsize : x -> x = fontsize/16px
    }

    /**
     * Changes the text size of the given picture to the value before the execute() command.
     */
    @Override
    public void undo() {
        this.textShape.getShape().setStyle("-fx-font: " + (this.oldTextSize/EM_TO_PX_RATIO) + "em " + this.textShape.getShape().getFont().getFamily() + ";"); //16px:1em = fontsize : x -> x = fontsize/16px
    }
    
}
