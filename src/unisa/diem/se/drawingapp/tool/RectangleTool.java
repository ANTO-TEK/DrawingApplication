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
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import unisa.diem.se.drawingapp.command.CommandExecutor;
import unisa.diem.se.drawingapp.command.DrawCommand;
import unisa.diem.se.drawingapp.shape.RectangleShape;

/**
 * This class specializes the ClosedShapeTool class and, in particular, represents the tool for inserting a rectangle.
 */
public class RectangleTool extends ClosedShapeTool{
    
    private Point2D origin;
    private RectangleShape rectangle;
    
    public RectangleTool(ObjectProperty strokePickerProperty, ObjectProperty fillPickerProperty){
        super(strokePickerProperty, fillPickerProperty);      
    }
    
    /**
     * Listener on the left click of mouse on the pane, that allows to start drawing a rectangle with 
     * the left corner position set on the click point if this point.
     * @param event event that has occurred
     */
    @Override
    public void onMouseDown(MouseEvent event){
            
            this.origin = new Point2D(event.getX(), event.getY());

            this.rectangle = new RectangleShape(this.origin.getX(), this.origin.getY(), 0, 0);
            this.rectangle.getShape().setStroke(super.getCurrentStrokeColor());
            this.rectangle.getShape().setFill(super.getCurrentFillColor());
            this.rectangle.getShape().setStrokeWidth(ShapeTool.STROKE_WIDTH);
            CommandExecutor.getInstance().execute(new DrawCommand(this.rectangle));
    }
    
    /**
     * Listener on the left click dragging of mouse on the pane, that allows to change 
     * the dimension of the drawed rectangle when the mouse is moved.
     * @param event
     */
    @Override
    public void onMouseDrag(MouseEvent event){
            
        this.rectangle.getShape().setWidth(Math.abs(event.getX() - this.origin.getX()));
        this.rectangle.getShape().setHeight(Math.abs(event.getY() - this.origin.getY()));
        this.rectangle.getShape().relocate(Math.min(this.origin.getX(), event.getX()), Math.min(this.origin.getY(), event.getY()));
    
    }
    
    /**
     * Listener on the left click release of mouse on the pane, that allows to finalize
     * the creation of the drawed rectangle and check if the drawed rectangle is empty: in this 
     * case, it is removed from the pane.
     * @param event event that has occurred
     */
    @Override
    public void onMouseUp(MouseEvent event){
        if(this.rectangle.getWidth() == 0 && this.rectangle.getHeight() == 0)
            CommandExecutor.getInstance().undo();
    }
    
}
