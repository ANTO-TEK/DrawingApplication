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

import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Pane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class TextBoxTest {

    private Pane drawingPane;
    private TextBox textbox;
    private SimpleObjectProperty<Integer> fontSizeProperty;

    @Before
    public void setUp() {
        //Initialize GUI
        JFXPanel fxPanel = new JFXPanel();
        
        this.fontSizeProperty = new SimpleObjectProperty(UtilityTest.FONT_SIZE);
        this.textbox = new TextBox(this.fontSizeProperty);
        this.drawingPane = UtilityTest.createAndSetPane();
    }

    /**
     * Test of draw method, of class TextBox.
     */
    @Test
    public void testDraw() {
        System.out.println("draw");
        //TEST: After the drawing the instance is in the pane
        
        this.textbox.draw();
        
        assertTrue(this.drawingPane.getChildren().contains(this.textbox));        
    }

    /**
     * Test of erase method, of class TextBox.
     */
    @Test
    public void testErase() {
        System.out.println("erase");
        //TEST: After the erasing the instance is not in the pane
        this.textbox.draw();
        
        this.textbox.erase();
        
        assertFalse(this.drawingPane.getChildren().contains(this.textbox));  
    }

}