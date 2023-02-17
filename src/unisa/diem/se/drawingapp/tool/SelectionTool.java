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

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import unisa.diem.se.drawingapp.command.CommandExecutor;
import unisa.diem.se.drawingapp.command.DragCommand;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.controller.SelectedShapeManager;


/**
 * Concrete class that represents a tool to select any kind of shape. 
 */
public class SelectionTool extends Tool {
    
    private final SelectedShapeManager selectedShapeManager;
    private final DrawingSurfaceManager drawingSurfaceManager;
            
    private double anchorX, anchorY, prevX, prevY, scaleX, scaleY;
    
    public SelectionTool() {
        this.selectedShapeManager = SelectedShapeManager.getInstance();
        this.drawingSurfaceManager = DrawingSurfaceManager.getInstance();
    }
    
    /**
     * Unselects the selected shape, if any, completing work.
     */
    @Override
    public void complete() {
        this.selectedShapeManager.unselectShape();
    }
    
     /**
     * Listener on the left click of mouse on the pane, that allows to create a rectangle 
     * around the selected shape when if clicked, and shows the selected one. When a shape is selected
     * if the right click of the mouse is processed on the pane, the rectangle created is deleted, then if is 
     * selected another shape, the rectangle is deleted from the previous shape and added to the new.
     * @param event event that has occurred
     */
    @Override
    public void onMouseDown(MouseEvent event){
        
        this.complete();
        if(event.getTarget() instanceof Shape) {
            this.selectedShapeManager.setSelectedShape(this.drawingSurfaceManager.getShapes().get((Shape)event.getTarget()));

            this.scaleX = ((Scale)this.drawingSurfaceManager.getDrawingPane().getTransforms().get(0)).getX();
            this.scaleY = ((Scale)this.drawingSurfaceManager.getDrawingPane().getTransforms().get(0)).getY();
            this.prevX = this.selectedShapeManager.getSelectedShape().getShape().getTranslateX();
            this.prevY = this.selectedShapeManager.getSelectedShape().getShape().getTranslateY();
            this.anchorX = event.getSceneX()/this.scaleX - this.prevX;
            this.anchorY = event.getSceneY()/this.scaleY - this.prevY;
        }
    }
     
    /**
     * Listener on the left click drag of mouse on the pane, that allows to move the shape in the event position point.
     * @param event 
     */
    @Override
    public void onMouseDrag(MouseEvent event){
        if(this.selectedShapeManager.getSelectedShape() != null){ 
            this.selectedShapeManager.getSelectedShape().moveTo(event.getSceneX()/this.scaleX - this.anchorX, event.getSceneY()/this.scaleY - this.anchorY);
        }
    }
    
    /**
     * Lister on the left click mouse release that allows to, if there is a selected shape, execute the move command.
     * @param event 
     */
    @Override
    public void onMouseUp(MouseEvent event){
        if(this.selectedShapeManager.getSelectedShape() != null) {
            if(this.prevX != this.selectedShapeManager.getSelectedShape().getShape().getTranslateX() && this.prevY != this.selectedShapeManager.getSelectedShape().getShape().getTranslateY()){
                double newPosX =  event.getSceneX()/this.scaleX - this.anchorX;
                double newPosY = event.getSceneY()/this.scaleY - this.anchorY;
                CommandExecutor.getInstance().execute(new DragCommand(this.selectedShapeManager.getSelectedShape(), this.prevX, this.prevY, newPosX, newPosY));
            }
        }
    }

}
