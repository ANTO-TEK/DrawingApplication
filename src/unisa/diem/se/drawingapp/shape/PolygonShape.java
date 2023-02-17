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
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

/**
 * Concrete class that represents a Polygon shape.
 * Adapter class in Adapter Object Design Pattern.
 */
public final class PolygonShape extends CustomShape {

    private transient Polyline polyline;
    
    public PolygonShape() {
        this.polyline = new Polyline();
    }
    
    /**
     * Getter method for the internal shape.
     * @return the effective polygon shape
     */
    @Override
    public Polyline getShape(){
        return this.polyline;
    }
    
    /**
     * Setter method for the width of the shape.
     * @param newWidth 
     */
    @Override
    public void setWidth(double newWidth) {
        
        double xReference = this.getMinX();
        double currentWidth = this.getWidth();
        double xCoordinate;
        
        ObservableList<Double> points = this.polyline.getPoints();
        
        for(int i = 0; i < points.size(); i+=2){
            xCoordinate = this.getPoint(i);
            
            if(xCoordinate != xReference) // Because of the top-left x-point is blocked
                points.set(i, ((newWidth/currentWidth)*(xCoordinate-xReference)+xReference));
        }
        
        this.widthProperty().set(newWidth);
    }
    
    /**
     * Setter method for the height of the shape.
     * @param newHeight 
     */
    @Override
    public void setHeight(double newHeight) {
        
        double yReference = this.getMinY();    
        double currentHeight = this.getHeight();
        double yCoordinate;  
        
        ObservableList<Double> points = this.polyline.getPoints();        
        
        for(int i = 1; i < points.size(); i+=2){
            yCoordinate = this.getPoint(i);

            if(yCoordinate != yReference)         //Because of the top-left y-point is blocked
                points.set(i, ((newHeight/currentHeight)*(yCoordinate-yReference)+yReference));
        }        
        
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
     * Getter method for the width of the shape.
     * @return the width of the polygon
     */
    @Override
    public double getWidth() {
       return Math.abs(this.getMaxX()-this.getMinX());
    }

    /**
     * Getter method for the height of the shape.
     * @return the height of the polygon
     */
    @Override
    public double getHeight() {
        return Math.abs(this.getMaxY()-this.getMinY());
    }
    
    /**
     * Method that add a point, made up by x and y coordinate, to the observable 
     * list of polygon's point.
     * @param valx x coordinate 
     * @param valy y coordinate 
     */
    public void addPoint(double valx, double valy){
        this.polyline.getPoints().addAll(valx,valy);
        this.widthProperty().set(this.getWidth());
        this.heightProperty().set(this.getHeight());
    }
    
    /**
     * Method that set a point in the last position, made up by x and y coordinate, 
     * of the observable list of polygon's point.
     * @param valx x coordinate
     * @param valy y coordinate
     */
    public void setLast(double valx, double valy){
        ObservableList<Double> points = this.polyline.getPoints();
        points.set(points.size() - 2, valx);
        points.set(points.size() - 1, valy);
    }
    
    /**
     * Checks if the shape is closed.
     * @return true if the shape is closed, otherwise false
     */
    public boolean isClosed(){ 
        double epsilon = 0.000001d;
        ObservableList<Double> points = this.polyline.getPoints();
        return (Math.abs(points.get(points.size()-2) - points.get(0)) < epsilon)  && (Math.abs(points.get(points.size()-1) - points.get(1)) < epsilon);
    }
    
    /**
     * Returns the specific coordinate setted at given index.
     * @param index specific position in polygon's point observable list 
     * @return the coordinate at the specified index
     */
    public double getPoint(int index){
        return this.polyline.getPoints().get(index);
    }
    
    /**
     * Returns the number of polygon's vertices.
     * @return number of polygon's vertices
     */
    public int getNumberOfVertices(){
        return this.polyline.getPoints().size()/2;
    }
    
    /**
     * Returns the lowest value of x coordinate in polygon's point observable list.
     * @return minimum x coordinate
     */
    private double getMinX(){
        
        ObservableList<Double> points = this.polyline.getPoints();
        double minX = points.get(0);
        
        for(int i = 0; i < points.size(); i+=2)
            if(points.get(i) < minX)
                minX = points.get(i);
            
        return minX;
    }
    
    /**
     * Returns the lowest value of y coordinate in polygon's point observable list.
     * @return minimum y coordinate
     */
    private double getMinY(){
        
        ObservableList<Double> points = this.polyline.getPoints();
        double minY = points.get(1);
        
        for(int i = 1; i < points.size(); i+=2)
            if(points.get(i) < minY)
                minY = points.get(i);
            
        return minY;        
    }
    
    /**
     * Returns the highest value of x coordinate in polygon's point observable list.   
     * @return maximum x coordinate
     */
    private double getMaxX(){
        
        ObservableList<Double> points = this.polyline.getPoints();
        double maxX = points.get(0);
        
        for(int i = 0; i < points.size(); i+=2)
            if(points.get(i) > maxX)
                maxX = points.get(i);
            
        return maxX;
    }
    
    /**
     * Returns the highest value of y coordinate in polygon's point observable list. 
     * @return maximum y coordinate
     */
    private double getMaxY(){
        
        ObservableList<Double> points = this.polyline.getPoints();
        double maxY = points.get(1);
        
        for(int i = 1; i < points.size(); i+=2)
            if(points.get(i) > maxY)
                maxY = points.get(i);
            
        return maxY;
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
        
        oos.writeDouble(this.polyline.getTranslateX());
        oos.writeDouble(this.polyline.getTranslateY());
        oos.writeDouble(this.polyline.getLayoutX());
        oos.writeDouble(this.polyline.getLayoutY());
        oos.writeDouble(this.polyline.getScaleX());
        oos.writeDouble(this.polyline.getScaleY());
        oos.writeDouble(this.polyline.getRotate());
        oos.writeObject(new ArrayList(this.polyline.getPoints()));
        oos.writeDouble(this.widthProperty().get());
        oos.writeDouble(this.heightProperty().get());
        oos.writeDouble(this.polyline.getStrokeWidth());
        oos.writeObject(this.polyline.getStroke().toString());
        oos.writeObject(this.polyline.getFill().toString());
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
        
        this.polyline = new Polyline();
        this.width = new SimpleDoubleProperty();
        this.height = new SimpleDoubleProperty();
        
        this.polyline.setTranslateX(ois.readDouble());
        this.polyline.setTranslateY(ois.readDouble());
        this.polyline.setLayoutX(ois.readDouble());
        this.polyline.setLayoutY(ois.readDouble());
        this.polyline.setScaleX(ois.readDouble());
        this.polyline.setScaleY(ois.readDouble());
        this.polyline.setRotate(ois.readDouble());
        this.polyline.getPoints().addAll((ArrayList)ois.readObject());
        this.widthProperty().set(ois.readDouble());
        this.heightProperty().set(ois.readDouble());
        this.polyline.setStrokeWidth(ois.readDouble());
        this.polyline.setStroke(Color.web((String) ois.readObject()));
        this.polyline.setFill(Color.web((String) ois.readObject()));
    }
}
