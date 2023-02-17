/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.controller;

import unisa.diem.se.drawingapp.command.ChangeTextSizeCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import unisa.diem.se.drawingapp.command.*;
import unisa.diem.se.drawingapp.io.Clipboard;
import unisa.diem.se.drawingapp.io.DWNGSaverAndLoader;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.shape.TextShape;

/**
 * Class that manages all the operations performed on the pane and the contained shapes.
 * Implemented as a Singleton.
 */
public class SelectedShapeManager {
    
    private final static int COPY_INCREMENT = 10;
    private final static String SELECTED_SHAPE_CSS_CLASS = "selection_rectangle";
    
    private static SelectedShapeManager uniqueInstance;
    
    private CustomShape selectedShape;
    private SelectionBox selectionBox;
    
    private final BooleanProperty isSelected, textShapeIsSelected;
    private final DoubleProperty selectedShapeWidth, selectedShapeHeight, selectedShapeRotation, selectedShapeScaleX, selectedShapeScaleY;
    
    private int incrementCopy = 0;
    
    private SelectedShapeManager(){
        SelectedShapeManager.uniqueInstance = null;
        this.isSelected = new ReadOnlyBooleanWrapper(false);
        this.textShapeIsSelected = new ReadOnlyBooleanWrapper(false);
        this.selectedShapeWidth = new SimpleDoubleProperty();
        this.selectedShapeHeight = new SimpleDoubleProperty(); 
        this.selectedShapeRotation = new SimpleDoubleProperty();
        this.selectedShapeScaleX = new SimpleDoubleProperty();
        this.selectedShapeScaleY = new SimpleDoubleProperty();
    }
    
    /**
     * This method return an instance, that is unique because of singleton pattern, of the class if exists, otherwise create it.
     * @return istance of the class
     */
    public static SelectedShapeManager getInstance(){
        if(SelectedShapeManager.uniqueInstance == null)
            SelectedShapeManager.uniqueInstance = new SelectedShapeManager();
        return SelectedShapeManager.uniqueInstance; 
    }
    
    /**
     * Returns the selected shape of the selected tool.
     * @return the custum shape currently selected, if any.
     */
    public CustomShape getSelectedShape() {
        return this.selectedShape;
    }
    
    /**
     * Set a shape as a selected shape, it sets all property in the same way 
     * of the new selectedShape to allow the correct sync with GUI
     * @param selectedShape the shape that must became the new selected shape
     */
    public void setSelectedShape(CustomShape selectedShape) {
        this.selectedShape = selectedShape;
        this.selectionBox = new SelectionBox();
        this.isSelected.setValue(Boolean.TRUE);
        
        if(selectedShape instanceof TextShape)
            this.textShapeIsSelected.setValue(Boolean.TRUE);
        
        this.selectedShapeWidth.setValue(selectedShape.getWidth());
        this.selectedShape.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if(newValue != null && !newValue.equals(oldValue))
                selectedShapeWidth.set(newValue.doubleValue());
        });
        
        this.selectedShapeHeight.setValue(selectedShape.getHeight());
        this.selectedShape.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if(newValue != null && !newValue.equals(oldValue))
                selectedShapeHeight.set(newValue.doubleValue());
        });
        
        this.selectedShapeRotation.setValue(selectedShape.getShape().getRotate());
        this.selectedShape.getShape().rotateProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if(newValue != null && !newValue.equals(oldValue))
                selectedShapeRotation.set(newValue.doubleValue());
        });
        
        this.selectedShapeScaleX.setValue(selectedShape.getShape().getScaleX());
        this.selectedShape.getShape().scaleXProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if(newValue != null && !newValue.equals(oldValue))
                selectedShapeScaleX.set(newValue.doubleValue());
        });
        
        this.selectedShapeScaleY.setValue(selectedShape.getShape().getScaleY());
        this.selectedShape.getShape().scaleYProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if(newValue != null && !newValue.equals(oldValue))
                selectedShapeScaleY.set(newValue.doubleValue());
        });
    }
    
    /**
     * Returns the the selection property.
     * @return selected property.
     */
    public BooleanProperty selectedProperty() {
        return this.isSelected;
    }
    
    /**
     * Returns the text selection property.
     * @return text selected property.
     */
    public BooleanProperty textShapeIsSelectedProperty() {
        return this.textShapeIsSelected;
    }
    
    /**
     * Returns the rotation property of selection shape.
     * @return selected shape rotation property.
     */
    public DoubleProperty selectedShapeRotationProperty(){
        return this.selectedShapeRotation;
    }

    /**
     * Returns the height property of selection shape.
     * @return selected shape height property.
     */
    public DoubleProperty selectedShapeHeightProperty() {
        return this.selectedShapeHeight;
    }
    
    /**
     * Returns the width property of selection shape.
     * @return selected shape width property.
     */
    public DoubleProperty selectedShapeWidthProperty() {
        return this.selectedShapeWidth;
    }
    
    /**
     * Returns the scaleX property of selection shape.
     * @return selected shape scaleX property.
     */
    public DoubleProperty selectedShapeScaleXProperty() {
        return this.selectedShapeScaleX;
    }
    
    /**
     * Returns the scaleY property of selection shape.
     * @return selected shape scale Y property.
     */
    public DoubleProperty selectedShapeScaleYProperty() {
        return this.selectedShapeScaleY;
    }
    
    
    /**
     * Unselects the selected shape, if any.
     */
    public void unselectShape() {
        if(this.selectedShape != null){
            this.selectedShape = null;
            DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().remove(this.selectionBox);
            this.isSelected.setValue(Boolean.FALSE);
            this.textShapeIsSelected.setValue(Boolean.FALSE);
            this.incrementCopy = 0;
        }
    }
    
    /**
     * Changes the fill color of the current selected shape.
     * @param newColor
     */
    public void updateFillColor(Paint newColor){
        if(this.selectedShape != null)
            CommandExecutor.getInstance().execute(new ChangeFillColorCommand(this.selectedShape, newColor));
    }
    
    /**
     * Changes the stroke color of the current selected shape.
     * @param newColor
     */    
    public void updateStrokeColor(Paint newColor){
        if(this.selectedShape != null)
            CommandExecutor.getInstance().execute(new ChangeStrokeColorCommand(this.selectedShape, newColor));
    }
    
    /**
     * Deletes the current selected shape.
     */
    public void deleteShape(){
        if(this.selectedShape != null)
            CommandExecutor.getInstance().execute(new DeleteCommand(this.selectedShape));
    }
    
    /**
     * It can rotate the selected shape by angle ° deg
     * @param angle in deg
     */
    public void rotateShape(double angle){
        if(this.selectedShape != null)
            CommandExecutor.getInstance().execute(new RotationCommand(this.selectedShape, angle));
    }  
    
    /**
     * Performs horizontal stretch command if there is a selected shape.
     * @param factor 
     */
    public void horizontalStretch(double factor){
        if(this.selectedShape != null)
            CommandExecutor.getInstance().execute(new HorizontalStretchCommand(this.selectedShape, factor));
    }
    
    /**
     * Performs vertical stretch command if there is a selected shape.
     * @param factor 
     */
    public void verticalStretch(double factor){
        if(this.selectedShape != null)
            CommandExecutor.getInstance().execute(new VerticalStretchCommand(this.selectedShape, factor));
    }
    
    /**
     * Performs text size update on a selected shape of textshape class.
     * @param newTextSize
     */
    public void changeTextSize(int newTextSize){
        //Necessary downcast to check that the selected shape is a text
        //In real context this situation isn't verifiable cause the spinner is disabled 
        if(this.selectedShape != null && this.selectedShape instanceof TextShape)  
            CommandExecutor.getInstance().execute(new ChangeTextSizeCommand((TextShape) this.selectedShape, newTextSize));
    }
    
    /**
     * Copies a shape in clipboard.
     */
    public void copyShape(){
        if(this.selectedShape != null){
            CommandExecutor.getInstance().execute(new CopyCommand(this.selectedShape));
        }
    }
    
    /**
     * Pastes a previously copied shape in the given pane.
     */
    public void pasteShape(){
        if(Clipboard.getApplicationClipboard().hasContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT)){
            this.incrementCopy += COPY_INCREMENT;
            CommandExecutor.getInstance().execute(new PasteCommand());
        }
    }
    
    /**
     * Cuts a shape in clipboard.
     */
    public void cutShape(){
        if (this.selectedShape != null)
            CommandExecutor.getInstance().execute(new CutCommand(this.selectedShape));
    }
    
    /**
     * Brings a shape on top of the other shapes that are on the same point of the selected shape.
     */
    public void bringToFront(){            
        CommandExecutor.getInstance().execute(new ToFrontCommand(this.selectedShape));
    }
    
    /**
     * Brings a shape on bottom of the other shapes that are on the same point of the selected shape.
     */
    public void bringToBack(){
        CommandExecutor.getInstance().execute(new ToBackCommand(this.selectedShape));       
    }
    
    /**
     * Mirrors vertically the selected shape.
     */
    public void verticalMirroring(){
        CommandExecutor.getInstance().execute(new VerticalMirroringCommand(this.selectedShape));
    }
    
    /**
     * Mirrors horizontally the selected shape.
     */    
    public void horizontalMirroring(){
         CommandExecutor.getInstance().execute(new HorizontalMirroringCommand(this.selectedShape));
    }
    
    /**
     * Changes sizes of the actual selected shape.
     * @param width: is the new value for shape width
     * @param height: is the new value for shape height
     */
    public void resizeShape(double width, double height) {
        CommandExecutor.getInstance().execute(new ResizeCommand(this.selectedShape, width, height));
    }
    
    /**
     * Returns the increment of paste clipboard
     * @return 
     */
    public int getIncrement(){
        return this.incrementCopy;
    }
    
    /**
     * Class that represent the box around a selected shape.
     */
    private class SelectionBox extends Rectangle {
        
        private SelectionBox() { 
            setBounds(selectedShape.getShape().getBoundsInParent());
            this.setMouseTransparent(true);
            this.getStyleClass().add(SELECTED_SHAPE_CSS_CLASS);
            selectedShape.getShape().boundsInParentProperty().addListener((ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds newBounds) -> {
                setBounds(newBounds);
            });
            addSelectionBox();
        }
        
        /**
         * Method that given a selected shape, attachs the selection box. 
         */
        private void addSelectionBox(){          
            DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().add(this);
        }
        
        /**
         * Given a set of dimensions as an object of Bounds class, resizes the selection box.
         * @param newBounds 
         */
        private void setBounds(Bounds newBounds){                        
            this.setX(newBounds.getMinX());
            this.setY(newBounds.getMinY());
            this.setWidth(newBounds.getWidth());
            this.setHeight(newBounds.getHeight());
        }
        
    }

}
