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

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Concrete class that represents a Line shape.
 * Adapter class in Adapter Object Design Pattern.
 */
public final class LineShape extends CustomShape {
    
    private transient Line line;
    
    public LineShape(double startX, double startY, double endX, double endY) {
        this.line = new Line(startX, startY, endX, endY);
        this.widthProperty().set(this.getWidth());
        this.heightProperty().set(this.getHeight());
    }
    
    /**
     * Getter method for the internal shape.
     * @return the effective line shape
     */
    @Override
    public Line getShape() {
        return this.line;
    }
    
    /**
     * Getter method for the width of the shape.
     * @return the width of the line
     */
    @Override
    public double getWidth() {
        return Math.abs(this.line.getEndX() - this.line.getStartX());
    }
    
    /**
     * Getter method for the height of the shape.
     * @return the height of the line
     */
    @Override
    public double getHeight() {
        return Math.abs(this.line.getEndY() - this.line.getStartY());
    }
    
    /**
     * Setter method for the width of the shape.
     * @param newWidth 
     */
    @Override
    public void setWidth(double newWidth) {
        if(Math.min(this.line.getEndX(), this.line.getStartX()) == (this.line.getStartX()))
            // If the x component of the start point is lower than that of the end point
            this.line.setEndX(newWidth + this.line.getStartX());
        else
            this.line.setStartX(newWidth + this.line.getEndX());
        
        this.widthProperty().set(newWidth);
    }
    
    /**
     * Setter method for the height of the shape.
     * @param newHeight
     */
    @Override
    public void setHeight(double newHeight) {
        if(Math.min(this.line.getEndY(), this.line.getStartY()) == (this.line.getStartY()))
            // If the y component of the start point is lower than that of the end point
            this.line.setEndY(newHeight + this.line.getStartY());
        else 
            this.line.setStartY(newHeight + this.line.getEndY());
        
        this.heightProperty().set(newHeight);
    }
    
    /**
     * Method that allows to change the dimensions of the shape.
     * @param width 
     * @param height 
     */
    @Override
    public void resize(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }
    
    /**
     * Write the specified object to the ObjectOutputStream.The class of the object, 
     * the signature of the class, and the values of the non-transient and non-static 
     * fields of the class and all of its supertypes are written. 
     * Used to implement custom serialization.
     * @param oos
     * @throws Exception 
     */
    private void writeObject(ObjectOutputStream oos) throws Exception {
        oos.defaultWriteObject();
        
        oos.writeDouble(this.line.getTranslateX());
        oos.writeDouble(this.line.getTranslateY());
        oos.writeDouble(this.line.getLayoutX());
        oos.writeDouble(this.line.getLayoutY());
        oos.writeDouble(this.line.getStartX());
        oos.writeDouble(this.line.getStartY());
        oos.writeDouble(this.line.getEndX());
        oos.writeDouble(this.line.getEndY());
        oos.writeDouble(this.line.getRotate());
        oos.writeDouble(this.line.getScaleX());
        oos.writeDouble(this.line.getScaleY());
        oos.writeDouble(this.widthProperty().get());
        oos.writeDouble(this.heightProperty().get());
        oos.writeDouble(this.line.getStrokeWidth());
        oos.writeObject(this.line.getStroke().toString());
    }
    
    /**
     * Read an object from the ObjectInputStream.The class of the object, the 
     * signature of the class, and the values of the non-transient and non-static 
     * fields of the class and all of its supertypes are read.
     * Used to implement custom deserialization.
     * @param ois
     * @throws Exception 
     */
    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
        
        this.line = new Line();
        this.width = new SimpleDoubleProperty();
        this.height = new SimpleDoubleProperty();
        
        this.line.setTranslateX(ois.readDouble());
        this.line.setTranslateY(ois.readDouble());
        this.line.setLayoutX(ois.readDouble());
        this.line.setLayoutY(ois.readDouble());
        this.line.setStartX(ois.readDouble());
        this.line.setStartY(ois.readDouble());
        this.line.setEndX(ois.readDouble());
        this.line.setEndY(ois.readDouble());
        this.line.setRotate(ois.readDouble());
        this.line.setScaleX(ois.readDouble());
        this.line.setScaleY(ois.readDouble());
        this.widthProperty().set(ois.readDouble());
        this.heightProperty().set(ois.readDouble());
        this.line.setStrokeWidth(ois.readDouble());
        this.line.setStroke(Color.web((String) ois.readObject()));
    }    

}
