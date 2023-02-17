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
import javafx.scene.shape.Ellipse;

/**
 * Concrete class that represents an Ellipse shape.
 * Adapter class in Adapter Object Design Pattern.
 */
public final class EllipseShape extends CustomShape {
    
    private transient Ellipse ellipse;
    
    public EllipseShape(double centerX, double centerY, double radiusX, double radiusY) {
        this.ellipse = new Ellipse(centerX, centerY, radiusX, radiusY);
        this.widthProperty().set(this.getWidth());
        this.heightProperty().set(this.getHeight());
    }
    
    /**
     * Getter method for the internal shape.
     * @return the effective ellipse shape
     */
    @Override
    public Ellipse getShape() {
        return this.ellipse;
    }
    
    /**
     * Getter method for the width of the shape.
     * @return the width of the ellipse
     */
    @Override
    public double getWidth() {
        return 2 * this.ellipse.getRadiusX();
    }
    
    /**
     * Getter method for the height of the shape.
     * @return the height of the ellipse
     */
    @Override
    public double getHeight() {
        return 2 * this.ellipse.getRadiusY();
    }
    
    /**
     * Setter method for the width of the shape.
     * @param newWidth 
     */
    @Override
    public void setWidth(double newWidth) {
        this.ellipse.setRadiusX(newWidth/2);
        this.widthProperty().set(newWidth);
    }
    
    /**
     * Setter method for the height of the shape.
     * @param newHeight
     */
    @Override
    public void setHeight(double newHeight) {
        this.ellipse.setRadiusY(newHeight/2);
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
        
        oos.writeDouble(this.ellipse.getTranslateX());
        oos.writeDouble(this.ellipse.getTranslateY());
        oos.writeDouble(this.ellipse.getLayoutX());
        oos.writeDouble(this.ellipse.getLayoutY());
        oos.writeDouble(this.ellipse.getRotate());
        oos.writeDouble(this.ellipse.getScaleX());
        oos.writeDouble(this.ellipse.getScaleY());
        oos.writeDouble(this.ellipse.getCenterX());
        oos.writeDouble(this.ellipse.getCenterY());
        oos.writeDouble(this.ellipse.getRadiusX());
        oos.writeDouble(this.ellipse.getRadiusY());
        oos.writeDouble(this.widthProperty().get());
        oos.writeDouble(this.heightProperty().get());        
        oos.writeDouble(this.ellipse.getStrokeWidth());
        oos.writeObject(this.ellipse.getStroke().toString());
        oos.writeObject(this.ellipse.getFill().toString());
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
        
        this.ellipse = new Ellipse();
        this.width = new SimpleDoubleProperty();
        this.height = new SimpleDoubleProperty();
        
        this.ellipse.setTranslateX(ois.readDouble());
        this.ellipse.setTranslateY(ois.readDouble());
        this.ellipse.setLayoutX(ois.readDouble());
        this.ellipse.setLayoutY(ois.readDouble());
        this.ellipse.setRotate(ois.readDouble());
        this.ellipse.setScaleX(ois.readDouble());
        this.ellipse.setScaleY(ois.readDouble());
        this.ellipse.setCenterX(ois.readDouble());
        this.ellipse.setCenterY(ois.readDouble());
        this.ellipse.setRadiusX(ois.readDouble());
        this.ellipse.setRadiusY(ois.readDouble());
        this.widthProperty().set(ois.readDouble());
        this.heightProperty().set(ois.readDouble());
        this.ellipse.setStrokeWidth(ois.readDouble());
        this.ellipse.setStroke(Color.web((String) ois.readObject()));
        this.ellipse.setFill(Color.web((String) ois.readObject()));
    }
    
}
