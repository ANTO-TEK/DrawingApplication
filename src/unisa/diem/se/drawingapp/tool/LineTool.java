/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.tool;

import javafx.beans.property.ObjectProperty;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import unisa.diem.se.drawingapp.command.CommandExecutor;
import unisa.diem.se.drawingapp.command.DrawCommand;
import unisa.diem.se.drawingapp.shape.LineShape;

/**
 * This class specializes the ShapeTool class and, in particular, represents the tool for inserting a line segment.
 */
public class LineTool extends ShapeTool {
    
    private Point2D origin;
    private LineShape line;

    public LineTool(ObjectProperty strokePickerProperty) {
        super(strokePickerProperty);
    }
    
    /**
     * Listener on the left click of mouse on the pane, that allows to starts drawing a line where 
     * the initial coordinates are the coordinates of the mouse pointer when the event is captured
     * @param event event that has occurred
     */
    @Override
    public void onMouseDown(MouseEvent event){
        
            this.origin = new Point2D(event.getX(), event.getY());

            this.line = new LineShape(this.origin.getX(), this.origin.getY(), this.origin.getX(), this.origin.getY());
            this.line.getShape().setStroke(super.getCurrentStrokeColor());
            this.line.getShape().setStrokeWidth(ShapeTool.STROKE_WIDTH);
            CommandExecutor.getInstance().execute(new DrawCommand(this.line));
    }
    
    /**
     * Listener on the left click dragging of mouse on the pane, that allows to change 
     * the dimension of the drawed line when the mouse is moved.
     * @param event event that has occurred
     */
    @Override
    public void onMouseDrag(MouseEvent event){
        this.line.getShape().setEndX(event.getX());
        this.line.getShape().setEndY(event.getY());
    }
    
    /**
     * Listener on the left click release of mouse on the pane, that allows to finalize
     * the creation of the drawed line and check if the drawed line is an empty line: in this 
     * case, it is removed from the pane.
     * @param event event that has occurred
     */
    @Override
    public void onMouseUp(MouseEvent event){
        if(this.line.getShape().getEndX() == this.origin.getX() && this.line.getShape().getEndY() == this.origin.getY())
            CommandExecutor.getInstance().undo();
    }
    
}
