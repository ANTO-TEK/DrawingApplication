/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.shape;

import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Shape;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;

/**
 * Abstract class that represents a serialized shape. 
 * Represents the Target interface in Adapter Object Design Pattern.
 */
public abstract class CustomShape implements Serializable {

    protected transient DoubleProperty width;
    protected transient DoubleProperty height;

    public CustomShape(){
        this.width = new SimpleDoubleProperty();
        this.height = new SimpleDoubleProperty();
    }
    
    /**
     * Defines a shape width.
     * @return a DoubleProperty that represents the width of the shape
     */
    public DoubleProperty widthProperty() {
        return width;
    }
    
    /**
     * Defines a shape height.
     * @return a DoubleProperty that represents the height of the shape
     */
    public DoubleProperty heightProperty() {
        return height;
    }
   
    /**
     * Draw the shape on the drawing surface by adding it both to the hashmap 
     * containing all the shapes displayed, and to the drawing pane.
     */
    public void draw() {
        DrawingSurfaceManager.getInstance().getShapes().put(this.getShape(), this);
        DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().add(this.getShape());
    }
    
    /**
     * Delete the shape from the drawing surface by deleting it both from the hashmap 
     * containing all the shapes displayed and from the drawing pane.
     */
    public void erase() {
        DrawingSurfaceManager.getInstance().getShapes().remove(this.getShape());
        DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().remove(this.getShape());
    }
        
    /**
     * Moves the shape to the given position.
     * @param x coordinate of the translation
     * @param y coordinate of the translation
     */
    public void moveTo(double x, double y) {
        this.getShape().setTranslateX(x);
        this.getShape().setTranslateY(y);
    }
    
    /**
     * Rotates the shape of a specified angle.
     * @param angle angle of rotation measured in degrees
     */
    public void rotate(double angle) {
        this.getShape().setRotate(angle);
    }
    
    /**
     * Method for getting the shape level with respect to the others on the drawing pane.
     * @return the index of the shape in the drawing pane shape ObservableList
     */
    public int getShapeLevelOnPane(){
        return DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().indexOf(this.getShape());
    }
    
    /**
     * Method for setting the shape level with respect to the others on the drawing pane.
     * @param index the index of the shape in the shape ObservableList drawing box where it has to be added
     */
    public void setShapeLevelOnPane(int index){
        DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().add(index, this.getShape());
    }
    
    /**
     * @return a string representation for the CustomShape.
     */
    @Override
    public String toString(){
        return "Custom" + this.getShape().toString();
    }
    
    /**
     * Method that allows to change the dimensions of the shape
     * @param width 
     * @param height 
     */
    public abstract void resize(double width, double height);
    
    /**
     * Getter method for the width of the shape
     * @return the width of the shape
     */
    public abstract double getWidth();
    
    /**
     * Getter method for the height of the shape
     * @return the height of the shape
     */
    public abstract double getHeight();
    
    /**
     * Setter method for the width of the shape
     * @param newWidth 
     */
    public abstract void setWidth(double newWidth);
    
    /**
     * Setter method for the height of the shape
     * @param newHeight 
     */
    public abstract void setHeight(double newHeight);
    
    /**
     * Getter method for the internal shape.
     * @return the effective shape
     */
    public abstract Shape getShape();
    
}
