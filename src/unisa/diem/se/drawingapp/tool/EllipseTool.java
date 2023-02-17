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
import unisa.diem.se.drawingapp.shape.EllipseShape;

/**
* This class specializes the ClosedShapeTool class and, in particular, represents the tool for inserting an ellipse.
*/
public class EllipseTool extends ClosedShapeTool {
    
    private Point2D origin; 
    private EllipseShape ellipse;
    
    public EllipseTool(ObjectProperty strokePickerProperty, ObjectProperty fillPickerProperty){
        super(strokePickerProperty, fillPickerProperty); 
    }
    
    /**
     * Listener on the left click of mouse on the drawing surface, that allows to draw an 
     * ellipse whose origin is the point corresponding to the mouse click.
     * @param event event that has occurred
     */
    @Override
    public void onMouseDown(MouseEvent event){
        
            this.origin = new Point2D(event.getX(), event.getY());
            
            this.ellipse = new EllipseShape(this.origin.getX(), this.origin.getY(), 0.0, 0.0);
            this.ellipse.getShape().setStroke(super.getCurrentStrokeColor());
            this.ellipse.getShape().setFill(super.getCurrentFillColor());
            this.ellipse.getShape().setStrokeWidth(ShapeTool.STROKE_WIDTH);
            
            CommandExecutor.getInstance().execute(new DrawCommand(this.ellipse));
    }
    
    /**
     * Listener on the left click dragging of mouse on the drawing surface, that allows to update 
     * the X and Y rays of the ellipse based on the mouse position.
     * @param event event that has occurred
     */
    @Override
    public void onMouseDrag(MouseEvent event){
        
        this.ellipse.getShape().setRadiusX(Math.abs(this.origin.getX() - event.getX()));
        this.ellipse.getShape().setRadiusY(Math.abs(this.origin.getY() - event.getY()));

    }    
    
    /**
     * Listener on the left click release of mouse on the pane, that allows to finalize
     * the creation of the drawed ellipse and check if the drawed ellipse is empty: in this 
     * case, it is removed from the pane.
     * @param event event that has occurred
     */
    @Override
    public void onMouseUp(MouseEvent event){
        if(this.ellipse.getShape().getRadiusX() == 0 && this.ellipse.getShape().getRadiusY() == 0)
            CommandExecutor.getInstance().undo();
    }

}
