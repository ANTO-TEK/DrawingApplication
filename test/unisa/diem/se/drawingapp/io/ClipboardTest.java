/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.io;

import java.lang.reflect.Field;
import javafx.scene.input.DataFormat;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import unisa.diem.se.drawingapp.io.Clipboard.ClipboardContent;

public class ClipboardTest {

    private static final String SAMPLE_TEXT = "I love Software Engineering.";
    private Clipboard clipboard;
    
    @Before
    public void setUp() {
        this.clipboard = Clipboard.getApplicationClipboard();
        this.clipboard.clear();
    }

    /**
     * Test of getApplicationClipboard method, of class Clipboard.
     */
    @Test
    public void testGetApplicationClipboard() {
        System.out.println("getApplicationClipboard");
        
        //TEST: Called twice it returns the same object
        assertEquals(this.clipboard, Clipboard.getApplicationClipboard());

    }

    /**
     * Test of setContent and getContent methods, of class Clipboard.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchFieldException
     */
    @Test
    public void testSetContent() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        System.out.println("setContent");
        
        //TEST: test that after the setting of a content I can get the setted content
        ClipboardContent content = new ClipboardContent();
        content.put(DataFormat.PLAIN_TEXT, ClipboardTest.SAMPLE_TEXT);
        this.clipboard.setContent(content);
        
        final Field contentField = this.clipboard.getClass().getDeclaredField("content");
        contentField.setAccessible(true);
        
        assertEquals(content, contentField.get(this.clipboard));
        assertEquals(ClipboardTest.SAMPLE_TEXT, (String) this.clipboard.getContent(DataFormat.PLAIN_TEXT));
    }
    
    /**
     * Test of hasContent method, of class Clipboard.
     */
    @Test
    public void testHasContent() {
        System.out.println("hasContent");
        
        //TEST: assert that when no content has been inserted returns false, true in the other case
        assertFalse(this.clipboard.hasContent(DataFormat.RTF));
        
        ClipboardContent content = new ClipboardContent();
        content.put(DataFormat.PLAIN_TEXT, ClipboardTest.SAMPLE_TEXT);
        this.clipboard.setContent(content);
        
        assertTrue(this.clipboard.hasContent(DataFormat.PLAIN_TEXT));
    }

    /**
     * Test of clear method, of class Clipboard.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        
        //TEST: Test if is empty after clear methods
        ClipboardContent content = new ClipboardContent();
        content.put(DataFormat.PLAIN_TEXT, ClipboardTest.SAMPLE_TEXT);
        this.clipboard.setContent(content);
        
        this.clipboard.clear();
        assertTrue(this.clipboard.isEmpty());
    }

    /**
     * Test of isEmpty method, of class Clipboard.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        
        //TEST: assert that if no content has been inserted then returns true, otherwise false
        assertTrue(this.clipboard.isEmpty());
        
        ClipboardContent content = new ClipboardContent();
        content.put(DataFormat.PLAIN_TEXT, ClipboardTest.SAMPLE_TEXT);
        this.clipboard.setContent(content);
        
        assertFalse(this.clipboard.isEmpty());
    }

    /**
     * Test of emptyProperty method, of class Clipboard.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testEmptyProperty() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("emptyProperty");
        
        //TEST: ASser that the value contained in the field is equals to the value returned by the method
        final Field emptyField = this.clipboard.getClass().getDeclaredField("empty");
        emptyField.setAccessible(true);
        
        assertEquals(emptyField.get(this.clipboard), this.clipboard.emptyProperty());
    }

    /**
     * Test of getContent method, of class Clipboard.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        
        //TEST: Assert that the content returned is the some of the content setted
        ClipboardContent content = new ClipboardContent();
        content.put(DataFormat.PLAIN_TEXT, ClipboardTest.SAMPLE_TEXT);
        this.clipboard.setContent(content);
        
        assertEquals(content.get(DataFormat.PLAIN_TEXT), this.clipboard.getContent(DataFormat.PLAIN_TEXT));

    }

}