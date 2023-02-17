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
import javafx.scene.shape.Rectangle;

/**
 * Concrete class that represents a Rectangle shape.
 * Adapter class in Adapter Object Design Pattern.
 */
public final class RectangleShape extends CustomShape {
    
    private transient Rectangle rectangle;
    
    public RectangleShape(double x, double y, double width, double height){
        this.rectangle = new Rectangle(x, y, width, height);
        this.widthProperty().set(this.getWidth());
        this.heightProperty().set(this.getHeight());
    }
    
    public RectangleShape(){
        this(0,0,0,0);
    }
    
    /**
     * Getter method for the internal shape.
     * @return the effective rectangle shape
     */
    @Override
    public Rectangle getShape() {
        return this.rectangle;
    }
    
    /**
     * Getter method for the width of the shape
     * @return the width of the rectangle
     */
    @Override
    public double getWidth() {
        return this.rectangle.getWidth();
    }

    /**
     * Getter method for the height of the shape
     * @return the height of the rectangle
     */
    @Override
    public double getHeight() {
        return this.rectangle.getHeight();
    }
    
    /**
     * Setter method for the width of the shape
     * @param newWidth 
     */
    @Override
    public void setWidth(double newWidth){
        this.rectangle.setWidth(newWidth);
        this.widthProperty().set(newWidth);
    }
    
    /**
     * Setter method for the height of the shape
     * @param newHeight 
     */
    @Override
    public void setHeight(double newHeight){
        this.rectangle.setHeight(newHeight);
        this.heightProperty().set(newHeight);
    }
    
    /**
     * Method that allows to change the dimensions of the shape
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
        
        oos.writeDouble(this.rectangle.getTranslateX());
        oos.writeDouble(this.rectangle.getTranslateY());
        oos.writeDouble(this.rectangle.getLayoutX());
        oos.writeDouble(this.rectangle.getLayoutY());
        oos.writeDouble(this.rectangle.getX());
        oos.writeDouble(this.rectangle.getY());
        oos.writeDouble(this.rectangle.getRotate());
        oos.writeDouble(this.rectangle.getScaleX());
        oos.writeDouble(this.rectangle.getScaleY());
        oos.writeDouble(this.rectangle.getWidth());
        oos.writeDouble(this.rectangle.getHeight());
        oos.writeDouble(this.widthProperty().get());
        oos.writeDouble(this.heightProperty().get());
        oos.writeDouble(this.rectangle.getStrokeWidth());
        oos.writeObject(this.rectangle.getStroke().toString());
        oos.writeObject(this.rectangle.getFill().toString());
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
        
        this.rectangle = new Rectangle();
        this.width = new SimpleDoubleProperty();
        this.height = new SimpleDoubleProperty();
        
        this.rectangle.setTranslateX(ois.readDouble());
        this.rectangle.setTranslateY(ois.readDouble());
        this.rectangle.setLayoutX(ois.readDouble());
        this.rectangle.setLayoutY(ois.readDouble());
        this.rectangle.setX(ois.readDouble());
        this.rectangle.setY(ois.readDouble());
        this.rectangle.setRotate(ois.readDouble());
        this.rectangle.setScaleX(ois.readDouble());
        this.rectangle.setScaleY(ois.readDouble());
        this.rectangle.setWidth(ois.readDouble());
        this.rectangle.setHeight(ois.readDouble());
        this.widthProperty().set(ois.readDouble());
        this.heightProperty().set(ois.readDouble());
        this.rectangle.setStrokeWidth(ois.readDouble());
        this.rectangle.setStroke(Color.web((String) ois.readObject()));
        this.rectangle.setFill(Color.web((String) ois.readObject()));
    }

}
