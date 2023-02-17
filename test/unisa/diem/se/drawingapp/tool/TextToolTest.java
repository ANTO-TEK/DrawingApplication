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
import java.lang.reflect.Field;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class TextToolTest {

    private Pane drawingPane;
    private TextTool tool;
    private ObjectProperty fillProperty;
    private ObjectProperty strokeProperty;
    private ObjectProperty fontSizeProperty;
    private String testText;
    
    private static final double PREVIEW_MIN_HEIGHT = 15; //px
    private static final double PREVIEW_MIN_WIDTH = 15; //px
    
    @Before
    public void setUp() {
        this.drawingPane = UtilityTest.createAndSetPane();
        this.fillProperty = new SimpleObjectProperty(Color.WHITE);
        this.strokeProperty = new SimpleObjectProperty(Color.WHITE);
        this.fontSizeProperty = new SimpleObjectProperty(UtilityTest.FONT_SIZE);
        this.testText = "I Love Software Engineering";

        //Initialize GUI
        JFXPanel fxPanel = new JFXPanel();
        this.tool = new TextTool(this.fillProperty, this.fillProperty, this.fontSizeProperty);
    }

    /**
     * Test of onMouseDown method, of class TextTool.
     * Case of the area has been already used and contains some text.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testOnMouseDownAreaUsed() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("onMouseDownAreaUsed");
        //TEST: the area has been already used and contains some text, after the event the pane has to contains a new shape
        
        //Set up the area with some text
        Field areaField = this.tool.getClass().getDeclaredField("area");
        areaField.setAccessible(true);
        
        areaField.set(this.tool, new TextBox(this.fontSizeProperty));
        
        TextBox area = (TextBox) areaField.get(this.tool);
        area.setText(this.testText);
        
        //After the event I should have a text shape in the pane and a new preview with 0 sizes
        double x = 0, y = 0;
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        
        assertEquals(2, this.drawingPane.getChildren().size());
        
        //the first one is the new shape
        assertEquals(Text.class, this.drawingPane.getChildren().get(0).getClass());
        Text newText = (Text) this.drawingPane.getChildren().get(0);
        
        //the second one is the preview
        assertEquals(Rectangle.class, this.drawingPane.getChildren().get(1).getClass());
        Rectangle previewShape = (Rectangle) this.drawingPane.getChildren().get(1);
        
        //asserts on the text shape properties
        String expected = " -fx-font: " + Double.valueOf((Integer)this.fontSizeProperty.get()) + "px default;";
        assertEquals(expected, newText.styleProperty().get());
        assertEquals(this.fillProperty.get(), newText.getFill());
        assertEquals(this.strokeProperty.get(), newText.getStroke());
        assertEquals(this.testText, newText.getText());
        
        //asserts on the preview properties
        assertEquals(0, previewShape.getWidth(), UtilityTest.EPSILON);
        assertEquals(0, previewShape.getHeight(), UtilityTest.EPSILON);
        assertEquals(x, previewShape.getX(), UtilityTest.EPSILON);
        assertEquals(y, previewShape.getY(), UtilityTest.EPSILON);
    }
    
    /**
     * Test of onMouseDown method, of class TextTool.
     * Case of the area is null.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testOnMouseDownAreaUnused() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("onMouseDownAreaUnused");
        //TEST: the area hasn't been already used , after the event the pane has to contains only the preview with 0 sizes
        
        //Set up the area with some text
        Field areaField = this.tool.getClass().getDeclaredField("area");
        areaField.setAccessible(true);
        
        TextBox area = (TextBox) areaField.get(this.tool);
        
        assertEquals(null, area);
        
        //After the event I should have a new preview with 0 sizes
        double x = 0, y = 0;
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        
        assertEquals(1, this.drawingPane.getChildren().size());
        
        assertEquals(Rectangle.class, this.drawingPane.getChildren().get(0).getClass());
        Rectangle previewShape = (Rectangle) this.drawingPane.getChildren().get(0);
        
        //asserts on the preview properties
        assertEquals(0, previewShape.getWidth(), UtilityTest.EPSILON);
        assertEquals(0, previewShape.getHeight(), UtilityTest.EPSILON);
        assertEquals(x, previewShape.getX(), UtilityTest.EPSILON);
        assertEquals(y, previewShape.getY(), UtilityTest.EPSILON);
        
    }
    
    /**
     * Test of onMouseDown method, of class TextTool.
     * Case of the area is used but no text has been inserted into it.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testOnMouseDownAreaUsedEmptyText() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("onMouseDownAreaUsedEmptyText");
        
        //TEST: the area has been already used and doens't contains text, after the event the pane shouldn't contains a new text shape
        
        //Set up the area with some text
        Field areaField = this.tool.getClass().getDeclaredField("area");
        areaField.setAccessible(true);
        areaField.set(this.tool, new TextBox(this.fontSizeProperty));
        
        TextBox area = (TextBox) areaField.get(this.tool);
        area.setText("");
        
        //After the event I should have a new preview with 0 sizes
        double x = 0, y = 0;
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        
        assertEquals(1, this.drawingPane.getChildren().size());
        
        assertEquals(Rectangle.class, this.drawingPane.getChildren().get(0).getClass());
        Rectangle previewShape = (Rectangle) this.drawingPane.getChildren().get(0);
        
        //asserts on the preview properties
        assertEquals(0, previewShape.getWidth(), UtilityTest.EPSILON);
        assertEquals(0, previewShape.getHeight(), UtilityTest.EPSILON);
        assertEquals(x, previewShape.getX(), UtilityTest.EPSILON);
        assertEquals(y, previewShape.getY(), UtilityTest.EPSILON);
        
    }

    /**
     * Test of onMouseDrag method, of class TextTool.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testOnMouseDrag() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("onMouseDrag");
        
        //TEST: the preview sizes are changed from the sizes in MouseDown event
        Field field = this.tool.getClass().getDeclaredField("preview");
        field.setAccessible(true);
        
        double x = 0, y = 0;
        double deltaY = TextToolTest.PREVIEW_MIN_HEIGHT; 
        double deltaX = TextToolTest.PREVIEW_MIN_WIDTH; 
        
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x + deltaX, y + deltaY, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        
        RectangleShape preview = (RectangleShape) field.get(this.tool);
        
        //asserts on the preview properties and its presence on the pane
        assertEquals(preview.getHeight(), deltaY, UtilityTest.EPSILON);
        assertEquals(preview.getWidth(), deltaX, UtilityTest.EPSILON);
        assertEquals(preview.getShape().getX(), x, UtilityTest.EPSILON);
        assertEquals(preview.getShape().getY(), y, UtilityTest.EPSILON);
        assertTrue(this.drawingPane.getChildren().contains(preview.getShape()));
    }

    /**
     * Test of onMouseUp method, of class TextTool.
     * Case: the preview is big enough
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testOnMouseUpBigEnough() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("onMouseUpBigEnough");
        
        //TEST: on mouse up there is the box in the pane and preview has been removed
        Field fieldPreview = this.tool.getClass().getDeclaredField("preview");
        Field fieldBox = this.tool.getClass().getDeclaredField("area");
        fieldBox.setAccessible(true);
        fieldPreview.setAccessible(true);
        
        double x = 0, y = 0;
        double deltaY = TextToolTest.PREVIEW_MIN_HEIGHT; 
        double deltaX = TextToolTest.PREVIEW_MIN_WIDTH; 
        
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x + deltaX, y + deltaY, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        
        RectangleShape preview = (RectangleShape) fieldPreview.get(this.tool);
        TextBox textbox = (TextBox) fieldBox.get(this.tool);
        
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x + deltaX, y + deltaY, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        
        //asserts on the preview properties and its absence on the pane
        assertEquals(preview.getHeight(), deltaY, UtilityTest.EPSILON);
        assertEquals(preview.getWidth(), deltaX, UtilityTest.EPSILON);
        assertEquals(preview.getShape().getX(), x, UtilityTest.EPSILON);
        assertEquals(preview.getShape().getY(), y, UtilityTest.EPSILON);
        assertFalse(this.drawingPane.getChildren().contains(preview.getShape()));
        assertTrue(this.drawingPane.getChildren().contains(textbox));
        
    }
    
    /**
     * Test of onMouseUp method, of class TextTool.
     * Case: the preview is not big enough
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testOnMouseUpNotBigEnough() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("onMouseUp");
        
        //TEST: on mouse up there isn't the box in the pane and preview has been removed
        double x = 0, y = 0;
        double deltaY = TextToolTest.PREVIEW_MIN_HEIGHT - 1; 
        double deltaX = TextToolTest.PREVIEW_MIN_WIDTH - 1; 
        
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x, y, MouseButton.PRIMARY, MouseEvent.MOUSE_PRESSED);
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x + deltaX, y + deltaY, MouseButton.PRIMARY, MouseEvent.MOUSE_DRAGGED);
        
        UtilityTest.mouseEvent(this.tool, this.drawingPane, null, x + deltaX, y + deltaY, MouseButton.PRIMARY, MouseEvent.MOUSE_RELEASED);
        
        assertTrue(this.drawingPane.getChildren().isEmpty());        
    }
    
    /**
     * Test of complete method, of class TextTool.
     * Case: the textbox contains some text and is not null
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testCompleteTextNotNull() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("completeTextNotNull");
        
        //TEST: After the method the field "area" of the text tool has been removed from pane and text shape has been added
        Field fieldTextBox = this.tool.getClass().getDeclaredField("area");
        fieldTextBox.setAccessible(true);
        TextBox textBox = new TextBox(this.fontSizeProperty);
        textBox.setText(this.testText);
        fieldTextBox.set(this.tool, textBox);
        
        this.tool.complete();
        
        assertEquals(1, this.drawingPane.getChildren().size());
        assertEquals(Text.class, this.drawingPane.getChildren().get(0).getClass());
        
        Text newText = (Text) this.drawingPane.getChildren().get(0);
        System.out.println(this.drawingPane.getChildren());
        System.out.println(newText);
        //asserts on the text properties
        String expected = " -fx-font: " + Double.valueOf((Integer)this.fontSizeProperty.get()) + "px default;";
        assertEquals(expected, newText.styleProperty().get());
        assertEquals(this.fillProperty.get(), newText.getFill());
        assertEquals(this.strokeProperty.get(), newText.getStroke());
        assertEquals(this.testText, newText.getText());
       
    }
    
    /**
     * Test of complete method, of class TextTool.
     * Case: the textbox contains some text and is not null
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testCompleteTextNull() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("completeTextNull");
        
        //TEST: After the method the field "area" of the text tool has been removed from pane and text shape has not been added
        Field fieldTextBox = this.tool.getClass().getDeclaredField("area");
        fieldTextBox.setAccessible(true);
        fieldTextBox.set(this.tool, new TextBox(this.fontSizeProperty));
        
        this.tool.complete();
        
        assertEquals(0, this.drawingPane.getChildren().size());
       
    }    
    
    /**
     * Test of complete method, of class TextTool.
     * Case: the textbox is null
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testCompleteAreaNull() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("completeAreaNull");
        
        //TEST: After the method the drawing pane is immuted
        Field fieldTextBox = this.tool.getClass().getDeclaredField("area");
        fieldTextBox.setAccessible(true);
        fieldTextBox.set(this.tool, null);
        
        this.tool.complete();

        assertEquals(0, this.drawingPane.getChildren().size());
    }
}