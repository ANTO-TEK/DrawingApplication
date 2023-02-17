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

import java.lang.reflect.Field;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import unisa.diem.se.drawingapp.io.Clipboard;
import unisa.diem.se.drawingapp.io.DWNGSaverAndLoader;
import unisa.diem.se.drawingapp.shape.EllipseShape;
import unisa.diem.se.drawingapp.shape.TextShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class SelectedShapeManagerTest {
    
    private SelectedShapeManager selectedShapeManager;
    private Pane drawingPane;
    private EllipseShape ellipse;


    @Before
    public void setUp() {
        //Initialize GUI
        JFXPanel fxPanel = new JFXPanel();
        
        this.selectedShapeManager = SelectedShapeManager.getInstance();
        this.drawingPane = UtilityTest.createAndSetPane();
        this.ellipse = new EllipseShape(0, 0, 10, 10);
        this.ellipse.getShape().setStroke(Color.BLACK);
        this.ellipse.getShape().setFill(Color.RED);
    }


    /**
     * Test of getInstance method, of class SelectedShapeManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        SelectedShapeManager ssm = SelectedShapeManager.getInstance();
        assertNotNull(this.selectedShapeManager);
        assertEquals(this.selectedShapeManager, ssm);
    }

    /**
     * Test of getSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testGetSelectedShape() {
        System.out.println("getSelectedShape");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        assertEquals(this.ellipse, this.selectedShapeManager.getSelectedShape());
    }

    /**
     * Test of setSelectedShape method, of class SelectedShapeManager.
     */
    @Test
    public void testSetSelectedShape() {
        System.out.println("setSelectedShape");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        assertEquals(this.ellipse, this.selectedShapeManager.getSelectedShape());
    }

    /**
     * Test of selectedProperty method, of class SelectedShapeManager.
     */
    @Test
    public void testSelectedProperty() {
        System.out.println("selectedProperty");
        
        this.selectedShapeManager.unselectShape();
        assertFalse(this.selectedShapeManager.selectedProperty().get());
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        assertTrue(this.selectedShapeManager.selectedProperty().get());
    }

    /**
     * Test of selectedShapeRotationProperty method, of class SelectedShapeManager.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSelectedShapeRotationProperty() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("selectedShapeRotationProperty");
        
        final Field field = this.selectedShapeManager.getClass().getDeclaredField("selectedShapeRotation");
        field.setAccessible(true);
        
        final SimpleDoubleProperty db = new SimpleDoubleProperty();
        field.set(this.selectedShapeManager, db);
        
        assertEquals(db, this.selectedShapeManager.selectedShapeRotationProperty());
    }

    /**
     * Test of selectedShapeHeightProperty method, of class SelectedShapeManager.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSelectedShapeHeightProperty() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("selectedShapeHeightProperty");
        
        final Field field = this.selectedShapeManager.getClass().getDeclaredField("selectedShapeHeight");
        field.setAccessible(true);
        
        final SimpleDoubleProperty db = new SimpleDoubleProperty();
        field.set(this.selectedShapeManager, db);
        
        assertEquals(db, this.selectedShapeManager.selectedShapeHeightProperty());
    }

    /**
     * Test of selectedShapeWidthProperty method, of class SelectedShapeManager.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testSelectedShapeWidthProperty() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("selectedShapeWidthProperty");
        
        final Field field = this.selectedShapeManager.getClass().getDeclaredField("selectedShapeWidth");
        field.setAccessible(true);
        
        final SimpleDoubleProperty db = new SimpleDoubleProperty();
        field.set(this.selectedShapeManager, db);
        
        assertEquals(db, this.selectedShapeManager.selectedShapeWidthProperty());
    }

    /**
     * Test of unselectShape method, of class SelectedShapeManager.
     */
    @Test
    public void testUnselectShape() {
        System.out.println("unselectShape");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.unselectShape();
        assertNull(this.selectedShapeManager.getSelectedShape());
        assertFalse(this.selectedShapeManager.selectedProperty().get());
    }

    /**
     * Test of updateFillColor method, of class SelectedShapeManager.
     */
    @Test
    public void testUpdateFillColor() {
        System.out.println("updateFillColor");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.updateFillColor(Color.RED);
        assertEquals(Color.RED, this.selectedShapeManager.getSelectedShape().getShape().getFill());
    }

    /**
     * Test of updateStrokeColor method, of class SelectedShapeManager.
     */
    @Test
    public void testUpdateStrokeColor() {
        System.out.println("updateStrokeColor");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.updateStrokeColor(Color.RED);
        assertEquals(Color.RED, this.selectedShapeManager.getSelectedShape().getShape().getStroke());
    }

    /**
     * Test of deleteShape method, of class SelectedShapeManager.
     */
    @Test
    public void testDeleteShape() {
        System.out.println("deleteShape");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.deleteShape();
        assertFalse(this.drawingPane.getChildren().contains(this.ellipse.getShape()));
        
    }

    /**
     * Test of rotateShape method, of class SelectedShapeManager.
     */
    @Test
    public void testRotateShape() {
        System.out.println("rotateShape");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.rotateShape(75.0);
        assertEquals(75.0, this.selectedShapeManager.getSelectedShape().getShape().getRotate(), UtilityTest.EPSILON);
    }

    /**
     * Test of copyShape method, of class SelectedShapeManager.
     */
    @Test
    public void testCopyShape() {
        System.out.println("copyShape");
      
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.copyShape();
        Clipboard clipboard = Clipboard.getApplicationClipboard();
        assertTrue(clipboard.hasContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT));
        this.selectedShapeManager.pasteShape();
        for(Node node : this.drawingPane.getChildren()){
            if(node instanceof Ellipse) {
                assertEquals(this.ellipse.getShape().getCenterX(), ((Ellipse)node).getCenterX(), UtilityTest.EPSILON);
                assertEquals(this.ellipse.getShape().getCenterY(), ((Ellipse)node).getCenterY(), UtilityTest.EPSILON);
                assertEquals(this.ellipse.getShape().getRadiusX(), ((Ellipse)node).getRadiusX(), UtilityTest.EPSILON);
                assertEquals(this.ellipse.getShape().getRadiusY(), ((Ellipse)node).getRadiusY(), UtilityTest.EPSILON);
            }
        }
    }

    /**
     * Test of pasteShape method, of class SelectedShapeManager.
     */
    @Test
    public void testPasteShape() {
        System.out.println("pasteShape");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.copyShape();
        Clipboard clipboard = Clipboard.getApplicationClipboard();
        assertTrue(clipboard.hasContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT));
        this.selectedShapeManager.pasteShape();
        for(Node node : this.drawingPane.getChildren()){
            if(node instanceof Ellipse) {
                assertEquals(this.ellipse.getShape().getCenterX(), ((Ellipse)node).getCenterX(), UtilityTest.EPSILON);
                assertEquals(this.ellipse.getShape().getCenterY(), ((Ellipse)node).getCenterY(), UtilityTest.EPSILON);
                assertEquals(this.ellipse.getShape().getRadiusX(), ((Ellipse)node).getRadiusX(), UtilityTest.EPSILON);
                assertEquals(this.ellipse.getShape().getRadiusY(), ((Ellipse)node).getRadiusY(), UtilityTest.EPSILON);
            }
        }
    }

    /**
     * Test of cutShape method, of class SelectedShapeManager.
     */
    @Test
    public void testCutShape() {
        System.out.println("cutShape");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.cutShape();
        Clipboard clipboard = Clipboard.getApplicationClipboard();
        assertTrue(clipboard.hasContent(DWNGSaverAndLoader.DWNG_DATA_FORMAT));
        assertFalse(this.drawingPane.getChildren().contains(this.ellipse.getShape()));
    }

    /**
     * Test of bringToFront method, of class SelectedShapeManager.
     */
    @Test
    public void testBringToFront() {
        System.out.println("bringToFront");
        
        this.ellipse.draw();
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        int beforeToFront = this.selectedShapeManager.getSelectedShape().getShapeLevelOnPane();
        this.selectedShapeManager.bringToFront();
        int afterToFront = this.selectedShapeManager.getSelectedShape().getShapeLevelOnPane();
        assertTrue(afterToFront > beforeToFront);
        
    }

    /**
     * Test of bringToBack method, of class SelectedShapeManager.
     */
    @Test
    public void testBringToBack() {
        System.out.println("bringToBack");
        
        this.ellipse.draw();
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.bringToFront();
        int beforeToFront = this.selectedShapeManager.getSelectedShape().getShapeLevelOnPane();
        this.selectedShapeManager.bringToBack();
        int afterToFront = this.selectedShapeManager.getSelectedShape().getShapeLevelOnPane();
        assertTrue(afterToFront < beforeToFront);
    }

    /**
     * Test of resizeShape method, of class SelectedShapeManager.
     */
    @Test
    public void testResizeShape() {
        System.out.println("resizeShape");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.resizeShape(100, 200);
        assertEquals(200, this.ellipse.getHeight(), UtilityTest.EPSILON);
        assertEquals(100, this.ellipse.getWidth(), UtilityTest.EPSILON);
    }

    /**
     * Test of selectedShapeScaleXProperty method, of class SelectedShapeManager.
     */
    @Test
    public void testSelectedShapeScaleXProperty() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException { 
        System.out.println("selectedShapeScaleXProperty");
        
        final Field field = this.selectedShapeManager.getClass().getDeclaredField("selectedShapeScaleX");
        field.setAccessible(true);
        
        final SimpleDoubleProperty db = new SimpleDoubleProperty();
        field.set(this.selectedShapeManager, db);
        
        assertEquals(db, this.selectedShapeManager.selectedShapeScaleXProperty());
    }

    /**
     * Test of selectedShapeScaleYProperty method, of class SelectedShapeManager.
     */
    @Test
    public void testSelectedShapeScaleYProperty() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("selectedShapeScaleYProperty");
        
        final Field field = this.selectedShapeManager.getClass().getDeclaredField("selectedShapeScaleY");
        field.setAccessible(true);
        
        final SimpleDoubleProperty db = new SimpleDoubleProperty();
        field.set(this.selectedShapeManager, db);
        
        assertEquals(db, this.selectedShapeManager.selectedShapeScaleYProperty());
    }

    /**
     * Test of horizontalStretch method, of class SelectedShapeManager.
     */
    @Test
    public void testHorizontalStretch() {
        System.out.println("horizontalStretch");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.horizontalStretch(300.0);
        assertEquals(300.0, this.selectedShapeManager.getSelectedShape().getShape().getScaleX(), UtilityTest.EPSILON);
    }

    /**
     * Test of verticalStretch method, of class SelectedShapeManager.
     */
    @Test
    public void testVerticalStretch() {
        System.out.println("verticalStretch");
        
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        this.selectedShapeManager.verticalStretch(125.0);
        assertEquals(125.0, this.selectedShapeManager.getSelectedShape().getShape().getScaleY(), UtilityTest.EPSILON);
    }

    /**
     * Test of textShapeIsSelectedProperty method, of class SelectedShapeManager.
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchFieldException
     */
    @Test
    public void testTextShapeIsSelectedProperty() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        System.out.println("textShapeIsSelectedProperty");
        
        //TEST: Modifying the related value of the property, the property returned has the modification
        final Field propertyField = this.selectedShapeManager.getClass().getDeclaredField("textShapeIsSelected");
        propertyField.setAccessible(true);
        
        assertEquals(propertyField.get(this.selectedShapeManager), this.selectedShapeManager.textShapeIsSelectedProperty());
    }

    /**
     * Test of changeTextSize method, of class SelectedShapeManager.
     */
    @Test
    public void testChangeTextSize() {
        System.out.println("changeTextSize");
        
        //TEST: Assert that after the call of the method, the selected text shape change the dimension by the given value
        double oldSize = 12;
        TextShape textShape = new TextShape(UtilityTest.POS, UtilityTest.POS, "", oldSize, 10);
        this.selectedShapeManager.setSelectedShape(textShape);
        
        double newSize = 20;
        this.selectedShapeManager.changeTextSize((int) newSize);
        
        String expected = "-fx-font: " + newSize/16 + "em System;";
        assertEquals( expected, textShape.getShape().styleProperty().get());
    }

    /**
     * Test of verticalMirroring method, of class SelectedShapeManager.
     */
    @Test
    public void testVerticalMirroring() {
        System.out.println("verticalMirroring");
        
        //TEST: Assert that after the method, the scale x property is multiplied by -1
        double oldScale = this.ellipse.getShape().getScaleX();
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        
        this.selectedShapeManager.verticalMirroring();
        
        assertEquals(oldScale * -1, this.ellipse.getShape().getScaleX(), UtilityTest.EPSILON);
    }

    /**
     * Test of horizontalMirroring method, of class SelectedShapeManager.
     */
    @Test
    public void testHorizontalMirroring() {
        System.out.println("horizontalMirroring");
        
        //TEST: Assert that after the method, the scale y property is multiplied by -1
        double oldScale = this.ellipse.getShape().getScaleY();
        this.selectedShapeManager.setSelectedShape(this.ellipse);
        
        this.selectedShapeManager.horizontalMirroring();
        
        assertEquals(oldScale * -1, this.ellipse.getShape().getScaleY(), UtilityTest.EPSILON);
    }

    /**
     * Test of getIncrement method, of class SelectedShapeManager.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException
     */
    @Test
    public void testGetIncrement() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("getIncrement");
        
        //TEST: ASsert that the get method returns the right value
        
        final Field incrementField = this.selectedShapeManager.getClass().getDeclaredField("incrementCopy");
        incrementField.setAccessible(true);
        
        assertEquals(incrementField.get(this.selectedShapeManager), this.selectedShapeManager.getIncrement());
    }

}