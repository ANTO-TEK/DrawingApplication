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
import unisa.diem.se.drawingapp.shape.PolygonShape;

/**
* This class specializes the ClosedShapeTool class and, in particular, represents the tool for inserting a polygon
*/
public class PolygonTool extends ClosedShapeTool {
    

    private PolygonShape toAdd;
    private Point2D origin;
    private final static double DELTA = 8.0;
    private final static double EPSILON = 1e-6;

    
    
    public PolygonTool(ObjectProperty strokePickerProperty, ObjectProperty fillPickerProperty) {
        super(strokePickerProperty, fillPickerProperty);
    }
    
    /**
     * Listener on the left click of mouse on the drawing surface, that allows to 
     * draw two linked point whose origin is the point corresponding to the mouse click
     * @param event event that has occurred
     */    
    @Override
    public void onMouseDown(MouseEvent event){
        
        if(toAdd == null){
            this.origin = new Point2D(event.getX(), event.getY());
            this.toAdd = new PolygonShape();
            this.toAdd.getShape().setStroke(super.getCurrentStrokeColor());
            this.toAdd.getShape().setFill(super.getCurrentFillColor());
            this.toAdd.getShape().setStrokeWidth(ShapeTool.STROKE_WIDTH);
            this.toAdd.addPoint(this.origin.getX(),this.origin.getY());
            CommandExecutor.getInstance().execute(new DrawCommand(toAdd));
            
        }
        
        this.toAdd.addPoint(event.getX(),event.getY());
            
        
    }
    
    /**
     * Listener on the left click dragging of mouse on the drawing surface, that allow to update 
     * the X and Y last point added to the polygon based on the mouse position
     * @param event event that has occurred
     */    
    @Override
    public void onMouseDrag(MouseEvent event){
        this.toAdd.setLast(event.getX(), event.getY());
    }

    /**
     * Listener on the left click release of mouse on the pane, that allows to finalize
     * the addition of the current point and check if the current drawed polygon is empty, only when there are only two
     * points and these are the same: in this case, it is removed from the pane; instead, when current point is setted near
     * starting point, the shape is automatically closed and so is possible to create another polygon: if the current point is not around 
     * the pre-set delta, then it is simply added as a point and the creation of current polygon continues.
     * @param event event that has occurred
     */    
    @Override
    public void onMouseUp(MouseEvent event){
       
        double x = event.getX();
        double y = event.getY();
        
        //If the polygon has only two points and are the same , it's empty -> delete it   
        if(this.isOnStartingPoint(x, y) && this.toAdd.getNumberOfVertices() == 2){
               CommandExecutor.getInstance().undo();
               this.toAdd = null;
        }
        else{
            
            if(this.isNearStartingPoint(x, y)){
                 this.toAdd.setLast(this.origin.getX(),this.origin.getY());
                 this.toAdd = null;
             }
             else
                 this.toAdd.setLast(x, y);

       }
        
    }
    
    
    /**
     * Complete method will close the actual drawed polygon, when the final point added is far from starting point 
     * and a different action than the one expected by the current tool is performed , completing work.
     * 
     */
    @Override
    public void complete(){
        if(this.toAdd != null && this.toAdd.isClosed() == false){
            this.toAdd.addPoint(this.origin.getX(), this.origin.getY());
            this.toAdd = null;
        }
    }
    
    /**
     * isOnStartingPoint method check if given coordinate and origin coordinate of current drawed polygon are the same
     * @param x : given x coordinate
     * @param y : given y coordinate
     * @return true when given coordinate are equals staring coordinate, false instead
     */
    private boolean isOnStartingPoint(double x, double y){
        return (Math.abs(this.origin.getX() - x) < PolygonTool.EPSILON) && (Math.abs(this.origin.getY() - y) < PolygonTool.EPSILON);
    }
    
    /**
     * isNearStartingPoint method check if given coordinate and origin coordinate of current drawed polygon
     * are close together, and this is true when they are around the pre-set delta.
     * @param x : given x coordinate
     * @param y : given y coordinate
     * @return true when given coordinate are equals to origin coordinate, false instead
     */    
    private boolean isNearStartingPoint(double x, double y){
        return (Math.abs(x-this.origin.getX()) <= PolygonTool.DELTA) && (Math.abs(y-this.origin.getY()) <= PolygonTool.DELTA);
    }

    
}
