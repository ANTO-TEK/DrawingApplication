/** 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.controller;

import java.util.HashMap;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.tool.Tool;

/**
 * Class that represents the manager of the drawing pane.
 * Implemented as a Singleton.
 */
public class DrawingSurfaceManager {
    
    private static DrawingSurfaceManager uniqueInstance;
    private Tool currentTool;
    private Pane drawingPane;
    private final HashMap<Shape, CustomShape> shapes = new HashMap<>();
    
    private DrawingSurfaceManager(){
        DrawingSurfaceManager.uniqueInstance = null;
    }
    
    /**
     * Getter method for ShapeManager class.
     * @return ShapeManager instance 
     */
    public static DrawingSurfaceManager getInstance(){
        if(DrawingSurfaceManager.uniqueInstance == null){
            DrawingSurfaceManager.uniqueInstance = new DrawingSurfaceManager();
        }
        return DrawingSurfaceManager.uniqueInstance;
    }
    
    /**
     * Setter method for attribute drawingPane.
     * @param drawingPane 
     */
    public void setDrawingPane(Pane drawingPane){
        this.drawingPane = drawingPane;
    }
    
    /**
     * Getter method for attribute drawingPane.
     * @return reference to the drawing pane
     */
    public Pane getDrawingPane(){
        return this.drawingPane;
    }
    
    /**
     * Returns the hashmap that collects the pairs Shape:CustomShape that are in the pane.
     * @return shapes give a collection of shapes
     */
    public HashMap<Shape, CustomShape> getShapes(){
        return this.shapes;
    }
    
    /**
     * Sets the current tool to the received tool
     * @param tool we wanna use
     */
    public void useTool(Tool tool){
        this.completeToolWork();
        this.currentTool = tool;
    }
    
    /**
     * Executes the action specified in the event on the pane.
     * @param event 
     */
    public void executeActionOnPane(MouseEvent event){
        EventType<? extends MouseEvent> eventType = event.getEventType();
        
        if(eventType.equals(MouseEvent.MOUSE_PRESSED)){
            this.currentTool.onMouseDown(event);
        }
        if(eventType.equals(MouseEvent.MOUSE_DRAGGED)){
            this.currentTool.onMouseDrag(event);
        }
        if(eventType.equals(MouseEvent.MOUSE_RELEASED)){
            this.currentTool.onMouseUp(event);
        }
    }    
    
    /**
     * Completes the work of the current tool.
     */
    public void completeToolWork(){
        if(this.currentTool != null)
            this.currentTool.complete();
    }
}
