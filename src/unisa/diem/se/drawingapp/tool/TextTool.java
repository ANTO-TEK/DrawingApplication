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

import unisa.diem.se.drawingapp.view.TextBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import unisa.diem.se.drawingapp.command.CommandExecutor;
import unisa.diem.se.drawingapp.command.DrawCommand;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.shape.TextShape;

/**
 * This class specializes the ClosedShapeTool class and represents a tool to insert text in the drawing.
 */
public class TextTool extends ClosedShapeTool{

    private Point2D origin;
    private TextBox area;
    private RectangleShape preview;
    private final ReadOnlyObjectProperty<Integer> fontSizeProperty;
    
    private final static String PREVIEW_CSS_ID = "selection_rectangle";
    private static final double PREVIEW_MIN_HEIGHT = 15; //px
    private static final double PREVIEW_MIN_WIDTH = 15; //px

    public TextTool(ObjectProperty strokePickerProperty, ObjectProperty fillPickerProperty, ReadOnlyObjectProperty<Integer> fontSizeProperty) {
        super(strokePickerProperty, fillPickerProperty);
        this.fontSizeProperty = fontSizeProperty;   
    }

    /**
     * Listener on the left click of mouse on the drawing surface, that allows to draws the preview of the textbox: if some 
     * area has already been drawed, then draw the associated text shape, removes the previously used preview.
     * @param event 
     */
    @Override
    public void onMouseDown(MouseEvent event) {
        //Erase eventually previous used textboxes
        this.complete();
        
        //Define the textbox where the text will be inserted
        this.area = new TextBox(this.fontSizeProperty);
        
        //Set up the preview of the textbox
        this.preview = new RectangleShape();
        this.preview.getShape().getStyleClass().add(TextTool.PREVIEW_CSS_ID);
        
        this.origin = new Point2D(event.getX(), event.getY());
        
        this.preview.getShape().setX(this.origin.getX());
        this.preview.getShape().setY(this.origin.getY());

        //Draws the preview
        this.preview.draw();
    }
    
    /**
     * Listener on the left click dragging of mouse on the drawing surface that allows to adjusts dimension of preview textbox.
     * @param event 
     */
    @Override
    public void onMouseDrag(MouseEvent event){
        //Modifies the preview
        this.preview.getShape().setWidth(Math.abs(event.getX() - this.origin.getX()));
        this.preview.getShape().setHeight(Math.abs(event.getY() - this.origin.getY()));

        this.preview.getShape().setX(Math.min(this.origin.getX(), event.getX()));
        this.preview.getShape().setY(Math.min(this.origin.getY(), event.getY()));
    }
    
    /**
     * Listener on the left click release of mouse on the drawing surface that allows to
     * setting up the textbox and draw it on the drawing pane.
     * @param event 
     */
    @Override
    public void onMouseUp(MouseEvent event) {
        //If the user draws a preview with minimum sizes then shows the textbox
        if(this.preview.getWidth() >= TextTool.PREVIEW_MIN_WIDTH && this.preview.getHeight() >= TextTool.PREVIEW_MIN_HEIGHT){
            //Setting up the textbox
            this.area.setPrefWidth(this.preview.getWidth() != 0 ? this.preview.getWidth() : this.preview.getHeight());
            this.area.setPrefHeight(this.preview.getHeight());
            this.area.setLayoutX(this.preview.getShape().getX());
            this.area.setLayoutY(this.preview.getShape().getY());

            //Show textbox
            this.area.draw();
        }
        
        //Remove preview
        this.preview.erase();
    }
    
    /**
     * complete method remove the textbox from the area if any when a different action 
     * than the one expected by the current tool is performed.
     */
    @Override
    public void complete(){
        if(this.area != null){
            String containedText = this.area.textProperty().get().trim();
            //Draws the shape if some text has been written
            if(!containedText.isEmpty()){
                double x1 = this.area.getLayoutX();
                double y1 = this.area.getLayoutY();

                //Creating new object of textshape
                TextShape newTextShape = new TextShape(x1, y1 + this.fontSizeProperty.get(), containedText, this.fontSizeProperty.get(), this.area.getWidth());
                newTextShape.getShape().setFill(super.getCurrentFillColor());
                newTextShape.getShape().setStroke(super.getCurrentStrokeColor());

                CommandExecutor.getInstance().execute(new DrawCommand(newTextShape)); //Need to sum fontSize because (x1,y1) is the left-top corner of textarea but the left-bottom of textshape

            }
            
            //Delete textbox from the pane after (if necessary) adding new shape
            this.area.erase();
        }
    }
}
