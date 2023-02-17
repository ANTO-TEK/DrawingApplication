/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.view;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.TextArea;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;


/**
 * Class that represents the textbox where insert the text.
 */
public final class TextBox extends TextArea {
    
    private static final String TEXTBOX_CSS_ID = "textbox";
    private static final double EM_TO_PX_RATIO = 16; //Used to map the receveid px in em
    private final ReadOnlyObjectProperty<Integer> fontSizeProperty;
    
    public TextBox(ReadOnlyObjectProperty<Integer> fontSizeProperty){
        this(0,0,0,0, "", fontSizeProperty);
    }
    
    public TextBox(double x, double y, double width, double height, String prompt, ReadOnlyObjectProperty<Integer> fontSizeProperty){
        this.setId(TextBox.TEXTBOX_CSS_ID);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        this.setText(prompt);
        this.fontSizeProperty = fontSizeProperty;
        
        
        this.setWrapText(true); //Disabling the horizontal scroll bar
        
        //Low level binding
        //When the text size property changes, the text size of the textbox also changes
        this.styleProperty().bind(new StringBinding(){
            {
                this.bind(TextBox.this.fontSizeProperty);
            }
            @Override
            protected String computeValue() {               
                return "-fx-font: " + (fontSizeProperty.get()/EM_TO_PX_RATIO) + "em default;"; //16px:1em = fontsize : x -> x = fontsize/16px
            }
        });
        this.requestFocus();
    }

    /**
     * Draws the textbox in the drawing pane.
     */
    public void draw(){
        DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().add(this);
    }
    
    /**
     * Removes the textbox from drawing pane.
     */
    public void erase(){
        this.clear();
        DrawingSurfaceManager.getInstance().getDrawingPane().getChildren().remove(this);
    }
}
