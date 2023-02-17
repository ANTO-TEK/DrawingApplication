/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.utility;

import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.junit.Ignore;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.tool.Tool;


@Ignore("Class used for utility methods")
public class UtilityTest {
    
    public static final double EPSILON = 1e-15;
    public static final double TEST_DIM_PANE = 200;
    public static final double TEST_WIDTH_SHAPE = 50;
    public static final double TEST_HEIGHT_SHAPE = 50;
    public static final double TEST_RESIZE_WIDTH = 6.0;
    public static final double TEST_RESIZE_HEIGHT = 8.0;
    public static final double POS = 10;
    public static final String FONT_FAMILY = "Verdana";
    public static final int FONT_SIZE = 12;
    public static final Color STROKE_COLOR = Color.BLACK;
    public static final Color FILL_COLOR = Color.TRANSPARENT;
    
    /**
     * Utility method for trigger mouse event in different ways.
     * 
     * @param tool used tool
     * @param source the source of the event.
     * @param target the target of the event
     * @param x The x with respect to the source.
     * @param y The y with respect to the source.
     * @param btn the mouse button used
     * @param op The type of the event.
     */
    public static void mouseEvent(Tool tool, Object source, EventTarget target, double x, double y, MouseButton btn, EventType op){
        double screenX = 0, screenY = 0;
        int clickCount = 1;
        boolean shiftDown = false, altDown = false, ctrlDown = false, metaDown = false;
        boolean primaryBtnDown, secondaryBtnDown, middleBtnDown;
        boolean synthesized = false, popupTrigger = false, stillSincePress = false;
        
        switch(btn){
            case PRIMARY: {
                primaryBtnDown = true;
                secondaryBtnDown = middleBtnDown = false;
                break;
            }
            case SECONDARY: {
                secondaryBtnDown = true;
                primaryBtnDown = middleBtnDown = false;
                break;
            }
            case MIDDLE: {
                middleBtnDown = true;
                primaryBtnDown = secondaryBtnDown = false;
                break;
            }
            default: {
                primaryBtnDown = true;
                secondaryBtnDown = middleBtnDown = false;
                break;
            }
        }

        MouseEvent evt = new MouseEvent(source, target, op, x, y, 
                screenX, screenY, btn, clickCount, shiftDown, ctrlDown, altDown, 
                metaDown, primaryBtnDown, middleBtnDown, secondaryBtnDown, synthesized, popupTrigger, stillSincePress, null);
        
        if(op.equals(MouseEvent.MOUSE_PRESSED))
            tool.onMouseDown(evt);


        if(op.equals(MouseEvent.MOUSE_DRAGGED))
            tool.onMouseDrag(evt);

        if(op.equals(MouseEvent.MOUSE_RELEASED))
            tool.onMouseUp(evt);
    }   
    
    /**
     * Utility method that allows you to create and set the drawing surface.
     * 
     * @return drawing surface created
     */
    public static Pane createAndSetPane() {
        
        Pane pane = new Pane(){
            @Override
            public void setPrefSize(double prefWidth, double prefHeight) {
                super.setWidth(prefWidth); 
                super.setHeight(prefHeight); 
            }
        };
        pane.setPrefSize(TEST_DIM_PANE, TEST_DIM_PANE);
        Scale scale = new Scale(1, 1);
        pane.getTransforms().add(scale);
        DrawingSurfaceManager.getInstance().setDrawingPane(pane);
        
        return pane;        
    }  
    
}
