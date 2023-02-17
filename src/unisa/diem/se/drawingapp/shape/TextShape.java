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
import javafx.scene.text.Text;

/**
 * Concrete class that represents a Text shape.
 * Adapter class in Adapter Object Design Pattern.
 */
public final class TextShape extends CustomShape{
    
    private transient Text text;

    public TextShape(double x, double y, String text, double fontSize, double newLineSentinel){
        this.text = new Text(x,y,text);
        this.text.setStyle(" -fx-font: " + fontSize + "px default;");
        this.text.wrappingWidthProperty().set(newLineSentinel);
        this.widthProperty().set(this.getWidth());
        this.heightProperty().set(this.getHeight());
    }
    
    /**
     * Getter method for the internal shape.
     * @return the effective text object
     */
    @Override
    public Text getShape(){
        return this.text;
    }
    
    /**
     * Getter method for the width of the shape.
     * @return the width of the text
     */
    @Override
    public double getWidth() {
        return this.text.wrappingWidthProperty().get();
    }
    
    /**
     * Getter method for the height of the shape.
     * @return the width of the text
     */
    @Override
    public double getHeight() {
        return this.text.getBoundsInParent().getHeight();
    }
    
    /**
     * Setter method for the width of the shape.
     * @param newWidth 
     */
    @Override
    public void setWidth(double newWidth) {
        this.text.wrappingWidthProperty().set(newWidth);
        this.widthProperty().set(newWidth);
    }
    
    /**
     * Setter method for the height of the shape.
     * @param newHeight 
     */
    @Override
    public void setHeight(double newHeight) {
        // No operation performed
    }
    
    /**
     * Method that allows to change the dimensions of the shape.
     * @param width 
     * @param height 
     */
    @Override
    public void resize(double width, double height) {
        this.setWidth(width);
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
        
        oos.writeUTF(this.text.getText());
        oos.writeDouble(this.text.getTranslateX());
        oos.writeDouble(this.text.getTranslateY());
        oos.writeDouble(this.text.getLayoutX());
        oos.writeDouble(this.text.getLayoutY());
        oos.writeDouble(this.text.getX());
        oos.writeDouble(this.text.getY());
        oos.writeDouble(this.text.getRotate());
        oos.writeDouble(this.text.getScaleX());
        oos.writeDouble(this.text.getScaleY());
        oos.writeDouble(this.text.getFont().getSize());
        oos.writeUTF(this.text.getFont().getFamily());
        oos.writeDouble(this.text.wrappingWidthProperty().get());
        oos.writeDouble(this.widthProperty().get());
        oos.writeDouble(this.heightProperty().get());
        oos.writeDouble(this.text.getStrokeWidth());
        oos.writeUTF(this.text.getStroke().toString());
        oos.writeUTF(this.text.getFill().toString());
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
        
        this.text = new Text();
        this.width = new SimpleDoubleProperty();
        this.height = new SimpleDoubleProperty();
        
        this.text.setText(ois.readUTF());
        this.text.setTranslateX(ois.readDouble());
        this.text.setTranslateY(ois.readDouble());
        this.text.setLayoutX(ois.readDouble());
        this.text.setLayoutY(ois.readDouble());
        this.text.setX(ois.readDouble());
        this.text.setY(ois.readDouble());
        this.text.setRotate(ois.readDouble());
        this.text.setScaleX(ois.readDouble());
        this.text.setScaleY(ois.readDouble());
        this.text.setStyle(" -fx-font: " + ois.readDouble() + "px " + ois.readUTF() + ";}");
        this.text.wrappingWidthProperty().set(ois.readDouble());
        this.widthProperty().set(ois.readDouble());
        this.heightProperty().set(ois.readDouble());
        this.text.setStrokeWidth(ois.readDouble());
        this.text.setStroke(Color.web(ois.readUTF()));
        this.text.setFill(Color.web(ois.readUTF()));
    }
    
}
