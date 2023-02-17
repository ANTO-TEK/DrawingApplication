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

import unisa.diem.se.drawingapp.io.SaveTask;
import unisa.diem.se.drawingapp.io.Clipboard;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import unisa.diem.se.drawingapp.command.CommandExecutor;
import javafx.util.converter.NumberStringConverter;
import unisa.diem.se.drawingapp.io.LoadTask;
import unisa.diem.se.drawingapp.shape.CustomShape;
import unisa.diem.se.drawingapp.shape.TextShape;
import unisa.diem.se.drawingapp.tool.*;
import unisa.diem.se.drawingapp.view.Grid;

/**
 * Class that implements the code behind the object hierarchy defined by the FXMLDocument.
 */

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ToggleButton lineToolButton;

    @FXML
    private ColorPicker fillColorPicker;
    
    @FXML
    private ColorPicker strokeColorPicker;    
    
    @FXML
    private Button undoButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private MenuItem itemNew;
    
    @FXML
    private ToolBar toolBarItems;
        
    @FXML
    private ToggleButton gridButton;
    
    @FXML
    private ScrollPane propertiesSection;
    
    @FXML
    private TextField widthTextField;
    
    @FXML
    private TextField heightTextField;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private Pane drawingPane;
    
    @FXML
    private CheckBox lockProportionsCB;
    
    @FXML
    private Group wrapperGroup;
    
    @FXML
    private Slider zoomSlider;
    
    @FXML
    private Label zoomLabel;
    
    @FXML
    private Spinner<Integer> gridDensitySpinner;
    
    @FXML
    private Spinner<Double> rotationSpinner;
    
    @FXML
    private ScrollPane mainScrollPane;
        
    @FXML
    private Spinner<Integer> sizeTextSpinner;
    
    @FXML
    private Spinner<Double> horizontalStretchSpinner;
    
    @FXML
    private Spinner<Double> verticalStretchSpinner;
    
    @FXML
    private RadioButton textRadioButton;
        
    @FXML
    private VBox textPropertiesSection;  
    
    @FXML
    private RadioButton selectToolButton;
    
    @FXML
    private RadioButton rectangleToolButton;
    
    @FXML
    private RadioButton ellipseToolButton;
    
    @FXML
    private RadioButton polylgonToolButton;

    //Context menu items
    private ContextMenu contextMenu;
    private MenuItem toFrontItem;
    private MenuItem toBackItem;
    private MenuItem copyItem;
    private MenuItem cutItem;
    private MenuItem pasteItem;
    private MenuItem verticalMirroringItem;
    private MenuItem horizontalMirroringItem;
    
    //Constant elements in the file chooser
    public static final String FILECHOOSER_DESCRIPTION = "Drawing App files (*.dwng)";
    public static final String[] FILECHOOSER_SUPPORTED_EXTENSION = {"*.dwng"};   
    
    //Regex
    public static final String POSITIVE_INTEGERS_REGEX = "^[0-9]+$";
    public static final String INTEGERS_REGEX = "^-?[0-9]+$";
    public static final String REAL_NUMBERS_REGEX = "^-?([0-9]*)?\\.?[0-9]?";
    
    //CSS IDs and CLASSes
    private static final String MAIN_SCROLL_PANE_CSS_ID = "main-scroll-pane";
    private static final String BUTTON_CSS_CLASS = "custom-button";
    
    //Grid spinner bounds 
    private static final int MIN_GRID = 4;
    private static final int MAX_GRID = 840;
    private static final int DEFAULT_GRID = 20;
    
    //Text spinner bounds 
    private static final int MIN_TEXT_SIZE = 1;
    private static final int MAX_TEXT_SIZE = 300;
    private static final int DEFAULT_SIZE = 12;
    private static final int INCREMENT_TEXT_SIZE = 1;
    
    //Stretch spinner bounds
    private static final double MIN_STRETCH = -10;
    private static final double MAX_STRETCH = 10;
    private static final double INCREMENT_STRETCH = 0.1;
    
    //Rotation spinner bounds
    private static final double MIN_ROTATION = -360;
    private static final double MAX_ROTATION = 360;
    private static final double INCREMENT_ROTATION = 0.5;
    
    //Width and height tf bound
    private static final double MAX_RESIZE = 5000;
    private static final double MIN_RESIZE = 1;
    
    //Grid instance
    private Grid grid;
    
    //Zoom
    private DoubleProperty zoomProperty;
    double[] zoomLevels = {25, 50, 75, 100, 200, 300, 400, 500};
    
    //Drawing Surface Manager instance
    private DrawingSurfaceManager drawingSurfaceManager;
    
    //Selected Shape Manager instance
    private SelectedShapeManager selectedShapeManager;

    //Text Formatter: Resize Textfield
    private UnaryOperator<TextFormatter.Change> sizesFormatter;
    
    //Text Formatter: Rotation editor spinner
    private UnaryOperator<TextFormatter.Change> rotationFormatter;

    //Text Formatter: Stretch formatter
    private UnaryOperator<TextFormatter.Change> stretchFormatter;
    
    /**
     * This method allows the implementing class to perform any 
     * necessary post-processing on the content ,use other setUp 
     * methods for inizialize: Pane, Buttons, ContextMenu, ToolButton,
     * InspectorSection, Zoom, GridSpinner, RotateSection, StretchSection,
     * TextPropertiesSection, Defaults
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Locale.setDefault(Locale.US);
        
        this.selectedShapeManager = SelectedShapeManager.getInstance();
        this.drawingSurfaceManager = DrawingSurfaceManager.getInstance();
        
        this.setUpPane();
        this.setUpDefaults();
        this.setUpButtons();
        this.setUpContextMenu();
        this.setUpToolButton();
        this.setUpInspectorSection();
        this.setUpZoom();
        this.setUpGridSpinner();
        this.setUpRotationSection();
        this.setUpStretchSection();
        this.setUpTextPropertiesSection();
    }
    
    /**
     * Handler that manages the pressing of line tool radio button.
     * Tells to the drawing surface manager to use the corresponding tool.
     * @param event (MOUSE_RELEASED)
     */
    @FXML
    private void lineToolHandler(ActionEvent event) {
        this.drawingSurfaceManager.useTool(new LineTool(this.strokeColorPicker.valueProperty()));
    }
    
    /**
     * Handler that manages the pressing of rectangle tool radio button.
     * Tells to the drawing surface manager to use the corresponding tool.
     * @param event (MOUSE_RELEASED)
     */
    @FXML
    private void rectangleToolHandler(ActionEvent event) {
        this.drawingSurfaceManager.useTool(new RectangleTool(this.strokeColorPicker.valueProperty(), this.fillColorPicker.valueProperty()));
    }
    
    /**
     * Handler that manages the pressing of ellipse tool radio button.
     * Tells to the drawing surface manager to use the corresponding tool.
     * @param event (MOUSE_RELEASED)
     */
    @FXML
    private void ellipseToolHandler(ActionEvent event) {
        this.drawingSurfaceManager.useTool(new EllipseTool(this.strokeColorPicker.valueProperty(), this.fillColorPicker.valueProperty()));
    }
    
    /**
     * WHandler that manages the pressing of selection tool radio button.
     * Tells to the drawing surface manager to use the corresponding tool.
     * @param event (MOUSE_RELEASED)
     */
    @FXML
    private void selectionToolHandler(ActionEvent event){
        this.drawingSurfaceManager.useTool(new SelectionTool());
    }
    
    /**
     * Handler that manages the pressing of text tool radio button.
     * Tells to the drawing surface manager to use the corresponding tool.
     * @param event (MOUSE_RELEASED)
     */
    @FXML
    private void textToolHandler(ActionEvent event) {
        this.drawingSurfaceManager.useTool(new TextTool(this.strokeColorPicker.valueProperty(), this.fillColorPicker.valueProperty(), this.sizeTextSpinner.valueProperty()));
    }
    
    /**
     * Handler that manages the pressing of polygon tool radio button.
     * Tells to the drawing surface manager to use the corresponding tool.
     * @param event 
     */
    @FXML
    private void polygonToolHandler(ActionEvent event) {
        this.drawingSurfaceManager.useTool(new PolygonTool(this.strokeColorPicker.valueProperty(), this.fillColorPicker.valueProperty()));
    }
    
    /**
     * Handler that manages the releasing mouse event on the drawing pane.
     * Tells to the manager to execute the corresponding action.
     * @param event (MOUSE_RELEASED)
     */
    @FXML
    private void mouseReleasedOnPane(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY))
            this.drawingSurfaceManager.executeActionOnPane(event);
    }
    
    /**
     * Handler that manages the pressing mouse event on the drawing pane.
     * Tells to the manager to execute the corresponding action.
     * @param event (MOUSE_PRESSED)
     */
    @FXML
    private void mousePressedOnPane(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            this.contextMenu.hide();
            this.drawingSurfaceManager.executeActionOnPane(event);
        }
    }
    
    /**
     * Handler that manages the dragging mouse event on the drawing pane.
     * Tells to the manager to execute the corresponding action. 
     * @param event (MOUSE_DRAG)
     */
    @FXML
    private void mouseDraggedOnPane(MouseEvent event) {
        if(event.getButton().equals(MouseButton.PRIMARY))
            this.drawingSurfaceManager.executeActionOnPane(event);
    }

    /**
     * Handler that manages the action associated with the menu item "Save".
     * When the user clic on Save to file, file chooser will be open and the user
     * can select a path to save the current drawing
     * @param event (MOUSE_RELEASED)
     */
    @FXML
    private void saveToFileHandler(ActionEvent event) {
        this.drawingSurfaceManager.completeToolWork();
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(FXMLDocumentController.FILECHOOSER_DESCRIPTION, FXMLDocumentController.FILECHOOSER_SUPPORTED_EXTENSION);
        fileChooser.getExtensionFilters().add(extFilter);
 
        //Show save file dialog
        fileChooser.setTitle("Save with name");
        File file = fileChooser.showSaveDialog(this.borderPane.getScene().getWindow());
        
            if (file != null) {
                SaveTask saveTask = new SaveTask(new ArrayList<>(this.drawingPane.getChildren()), file);
                Thread saveThread = new Thread(saveTask);
                saveThread.start();

                saveTask.setOnFailed(failEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Stack Trace:\n" + saveTask.getException().getMessage());
                    alert.setHeaderText("Saving Error");
                    alert.showAndWait();

                    saveTask.cancel();
                });
        }
    }
    
    /**
     * Handler that manages the action associated with the menu item "Load".
     * When the user clic on Load to file, file chooser will be open and the user
     * can select the that contains a previously saved drawing.
     * @param event 
     */
    @FXML
    private void loadFromFileHandler(ActionEvent event) {
        this.drawingSurfaceManager.completeToolWork();
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(FXMLDocumentController.FILECHOOSER_DESCRIPTION, FXMLDocumentController.FILECHOOSER_SUPPORTED_EXTENSION);
        fileChooser.getExtensionFilters().add(extFilter);
        
        fileChooser.setTitle("Open");
        File file = fileChooser.showOpenDialog(this.borderPane.getScene().getWindow());
        
            if(file != null){
                //Clear the pairs shape-customshape before reading from file
                this.drawingSurfaceManager.getShapes().clear(); 

                //Clears command history to reset the drawing
                CommandExecutor.getInstance().clearHistory();

                //before drawing clears the content of the drawing pane
                this.drawingPane.getChildren().clear();

                LoadTask loadTask = new LoadTask(file);
                Thread loaderThread = new Thread(loadTask); //A thread is automatically destroyed when the run() method has completed
                loaderThread.start();

                loadTask.setOnSucceeded(loadEvent -> {

                    ArrayList<CustomShape> readShapesList = loadTask.getValue();

                    //for each customshape readed by file I have to draw on drawing pane
                    for(CustomShape shape : readShapesList)
                        shape.draw();

                    loadTask.cancel();
                });

                loadTask.setOnFailed(failEvent -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Stack Trace:\n" + loadTask.getException().getMessage());
                    alert.setHeaderText("Loading Error");
                    alert.showAndWait();
                });
        }
    }

    /**
     * Handler associated to the releasing of undo button.
     * Perform an operation of undo in the application.
     * @param event 
     */
    @FXML
    private void undoHandler(ActionEvent event) {
        CommandExecutor.getInstance().undo();
    }

    /**
     * Handler associated to the releasing of delete button.
     * Tells to selected shape manager to delete the currently selected shape.
     * @param event 
     */
    @FXML
    private void deleteHandler(ActionEvent event) {
        SelectedShapeManager.getInstance().deleteShape();
    }

    /**
     * Handler to manage the change of the stroke color picker.
     * If a shape is selected, it mutates it's stroke color.
     * @param event 
     */
    @FXML
    private void strokeColorPickerHandler(ActionEvent event) {
        SelectedShapeManager.getInstance().updateStrokeColor(strokeColorPicker.getValue());
    }

    /**
     * Handler to manage the change of the fill color picker.
     * If a shape is selected, it mutates it's fill color.
     * @param event 
     */
    @FXML
    private void fillColorPickerHandler(ActionEvent event) {
        SelectedShapeManager.getInstance().updateFillColor(fillColorPicker.getValue());
    }
    
    /**
     * Handler associated to "Clear all" menu item.
     * Clears the pane if the result of the alert is YES.
     * @param event 
     */
    @FXML
    private void newHandler(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.WARNING, "Do you want to clear all the shapes?", ButtonType.YES,ButtonType.CANCEL);
        alert.setHeaderText("Warning");
        alert.setTitle("");
        alert.showAndWait();

        CommandExecutor.getInstance().clearHistory(); //Clear the commands history
        
        if(alert.getResult() == ButtonType.YES)
            this.drawingPane.getChildren().clear();
    }
    
    /**
     * Handler associated to the key pressing in the textfields contained in Resize section in inspector.
     * Performs the request for size changing when on the sizes textfields the enter key is pressed.
     * @param event 
     */
    @FXML
    private void updateSizes(KeyEvent event) {
        
        if(event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.TAB)){
            if(this.lockProportionsCB.isSelected()){
                //If the user checked the CB the when i update a value then i have to update the other also
                double newWidth = Double.parseDouble(this.widthTextField.getText());
                double newHeight = Double.parseDouble(this.heightTextField.getText());
                double oldWidth = this.selectedShapeManager.getSelectedShape().getWidth();
                double oldHeight = this.selectedShapeManager.getSelectedShape().getHeight();

                if(newWidth != oldWidth){
                    //If i change the width
                    double ratio = newWidth / oldWidth;
                    String newStringHeight = String.valueOf((int)(oldHeight * ratio));
                    this.heightTextField.setText(newStringHeight);
                }else if(newHeight != oldHeight){
                    //If i change the height
                    double ratio = newHeight / oldHeight;
                    String newStringWidth = String.valueOf((int)(oldWidth * ratio));
                    this.widthTextField.setText(newStringWidth);
                }
            }
            
            this.selectedShapeManager.resizeShape(Double.parseDouble(this.widthTextField.getText()), Double.parseDouble(this.heightTextField.getText()));
            this.toolBarItems.requestFocus(); //Remove focus from the TF after the execution of resize
        }
    }
    
    /**
     * Handler to manage the pressing of the gridButton.
     * Make the grid visible or not.
     * @param event 
     */
    @FXML
    private void gridHandler(ActionEvent event) {
        if(this.gridButton.isSelected())
            this.grid.drawGrid(this.zoomProperty.getValue());
        else
            this.grid.eraseGrid();
    }
    
    /**
     * This methods was triggered when a key or a combination of keys is pressed
     * select the righ operation to execute
     * @param event 
     */
    void keyEventHandler(KeyEvent event){
        ActionEvent action = new ActionEvent();
        if(event.isShortcutDown()){
            switch (event.getCode()) {
                case V: SelectedShapeManager.getInstance().pasteShape(); break;
                case X: SelectedShapeManager.getInstance().cutShape(); break;
                case C: SelectedShapeManager.getInstance().copyShape(); break;
                case Z: CommandExecutor.getInstance().undo(); break;
            }
        }else if(event.getCode().equals(KeyCode.DELETE))  
            this.deleteHandler(action);
    }
    
    /**
     * Handler associated to the action of "Exit" menu item.
     * Close the application.
     * @param event 
     */
    @FXML
    private void exitHandler(ActionEvent event) {
        Platform.exit();
    }
    
    /**
     * Setting up dimensions, and other stuff referred to the panes.
     */
    private void setUpPane(){
        this.drawingSurfaceManager.setDrawingPane(this.drawingPane);
        this.drawingPane.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.drawingPane.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        this.drawingPane.setClip(new Rectangle (0,0, this.drawingPane.getPrefWidth(),this.drawingPane.getPrefHeight()));
        this.grid = new Grid(this.wrapperGroup);
        this.mainScrollPane.setId(MAIN_SCROLL_PANE_CSS_ID);
    }
    
    /**
     * Setting up the element of the context menu, the associated bindings and listeners.
     */
    private void setUpContextMenu(){
        //Initialize the objects
        this.contextMenu = new ContextMenu();
        this.toFrontItem = new MenuItem("To Front");
        this.toBackItem = new MenuItem("To Back");
        this.copyItem = new MenuItem("Copy (CTRL+C)");
        this.cutItem = new MenuItem("Cut (CTRL+X)");
        this.pasteItem = new MenuItem("Paste (CTRL+V)");
        this.verticalMirroringItem = new MenuItem("Vertical Mirroring");
        this.horizontalMirroringItem = new MenuItem("Horizontal Mirroring");
        
        //Add menu items to context menu associated on the Pane
        contextMenu.getItems().addAll(copyItem,cutItem,pasteItem,toFrontItem, toBackItem, verticalMirroringItem, horizontalMirroringItem);
        
        //Set the apperaence of Context menu on context menu requested (right-click of mouse on pane)
        this.drawingPane.setOnContextMenuRequested(e -> this.contextMenu.show(this.drawingPane, e.getScreenX(), e.getScreenY()));
        
        //Bindings on menu item
        this.toFrontItem.disableProperty().bind(this.selectedShapeManager.selectedProperty().not());
        this.toBackItem.disableProperty().bind(this.selectedShapeManager.selectedProperty().not());
        this.copyItem.disableProperty().bind(this.selectedShapeManager.selectedProperty().not());
        this.cutItem.disableProperty().bind(this.selectedShapeManager.selectedProperty().not());
        this.verticalMirroringItem.disableProperty().bind(this.selectedShapeManager.selectedProperty().not());
        this.horizontalMirroringItem.disableProperty().bind(this.selectedShapeManager.selectedProperty().not());
        this.pasteItem.disableProperty().bind(Clipboard.getApplicationClipboard().emptyProperty());
        
        
        //Set up the listeners on the context menu items
        this.toFrontItem.setOnAction((ActionEvent event) -> {
            SelectedShapeManager.getInstance().bringToFront();
        });
        
        this.toBackItem.setOnAction((ActionEvent event) -> {
            SelectedShapeManager.getInstance().bringToBack();
        });
        
        this.copyItem.setOnAction((ActionEvent event) -> {
            SelectedShapeManager.getInstance().copyShape();
        });
        
        this.cutItem.setOnAction((ActionEvent event) -> {
            SelectedShapeManager.getInstance().cutShape();
        });
        
        this.pasteItem.setOnAction((ActionEvent event) -> {
            SelectedShapeManager.getInstance().pasteShape();
        });
        
        this.verticalMirroringItem.setOnAction((ActionEvent event) -> {
            SelectedShapeManager.getInstance().verticalMirroring();
        });

        this.horizontalMirroringItem.setOnAction((ActionEvent event) -> {
            SelectedShapeManager.getInstance().horizontalMirroring();
        });                
        
        this.itemNew.disableProperty().bind(Bindings.size(this.drawingPane.getChildren()).isEqualTo(0));
        
    }
    
    /**
     * Setting up the default button pressed and tool set at the start of the application. 
     */
    private void setUpDefaults(){
        //Setting default button pressed initialization
        this.lineToolButton.setSelected(true);
        //Shape Manager initialization: set a default tool
        this.drawingSurfaceManager.useTool(new LineTool(this.strokeColorPicker.valueProperty()));
        //Setting default color in color pickers
        this.fillColorPicker.setValue(Color.TRANSPARENT);
        this.strokeColorPicker.setValue(Color.BLACK);
    }
    
    /**
     * Setting up some bindings to disable command buttons.
     */
    private void setUpButtons(){
        //Setting a bind to disable undoButton and deleteButton
        undoButton.disableProperty().bind(CommandExecutor.getInstance().getCommandHistory().emptyProperty());
        deleteButton.disableProperty().bind(this.selectedShapeManager.selectedProperty().not());
    }

    
    /**
     * Setting up of the css for the elements in the toolbar.
     */
    private void setUpToolButton(){
        this.lineToolButton.getStyleClass().remove("radio-button");
        this.lineToolButton.getStyleClass().add(BUTTON_CSS_CLASS);
        this.rectangleToolButton.getStyleClass().remove("radio-button");
        this.rectangleToolButton.getStyleClass().add(BUTTON_CSS_CLASS);
        this.ellipseToolButton.getStyleClass().remove("radio-button");
        this.ellipseToolButton.getStyleClass().add(BUTTON_CSS_CLASS);
        this.textRadioButton.getStyleClass().remove("radio-button");
        this.textRadioButton.getStyleClass().add(BUTTON_CSS_CLASS);
        this.selectToolButton.getStyleClass().remove("radio-button");
        this.selectToolButton.getStyleClass().add(BUTTON_CSS_CLASS);
        this.polylgonToolButton.getStyleClass().remove("radio-button");
        this.polylgonToolButton.getStyleClass().add(BUTTON_CSS_CLASS);
    }
   
    
    /**
     * Setting up bindings and controls for the elements in the inspector.
     */
    private void setUpInspectorSection(){
        //Visible only if a shape has been selected
        this.propertiesSection.visibleProperty().bind(this.selectedShapeManager.selectedProperty());
        this.propertiesSection.managedProperty().bind(this.selectedShapeManager.selectedProperty());
        
        DecimalFormat df = new DecimalFormat("##,####,####");
        df.setGroupingUsed(true);
        df.setDecimalSeparatorAlwaysShown(false);
        
        Bindings.bindBidirectional(this.widthTextField.textProperty(), this.selectedShapeManager.selectedShapeWidthProperty(), new NumberStringConverter(df));
        Bindings.bindBidirectional(this.heightTextField.textProperty(), this.selectedShapeManager.selectedShapeHeightProperty(), new NumberStringConverter(df));
        
        //Setting up the formatter
        this.sizesFormatter = change -> {
            String newText = change.getControlNewText();
            
            if (!newText.isEmpty() && newText.matches(FXMLDocumentController.POSITIVE_INTEGERS_REGEX) && Double.valueOf(newText).compareTo(MIN_RESIZE)>=0 && Double.valueOf(newText).compareTo(MAX_RESIZE)<=0) {
                return change;
            }
            return null;
        };
        
        //Set up the formatter previously defined -> Text Field of width and height can only accept positive
        this.heightTextField.setTextFormatter(new TextFormatter(this.sizesFormatter));
        this.widthTextField.setTextFormatter(new TextFormatter(this.sizesFormatter)); //Same TextFormatter can't be associated to different controls
        
        //Set up the disabling of a textfield when the other is concurrently modified and the CB is selected
        this.widthTextField.disableProperty().bind(Bindings.and(this.lockProportionsCB.selectedProperty(), this.heightTextField.focusedProperty()));
        this.heightTextField.disableProperty().bind(Bindings.and(this.lockProportionsCB.selectedProperty(), this.widthTextField.focusedProperty()).or(this.selectedShapeManager.textShapeIsSelectedProperty()));     
        
        this.lockProportionsCB.disableProperty().bind(this.selectedShapeManager.textShapeIsSelectedProperty());
   }
    
    /**
     * Setting up all attributes of rotation section in inspector.
     */
    private void setUpRotationSection() {
        //Setting up the formatter
        this.rotationFormatter = change -> {
            String newText = change.getControlNewText();
            if(!newText.isEmpty())
                if (!newText.equals("-") && newText.matches(FXMLDocumentController.REAL_NUMBERS_REGEX) && Double.valueOf(newText).compareTo(MIN_ROTATION)>=0 && Double.valueOf(newText).compareTo(MAX_ROTATION)<=0) {
                    return change;
                }
            return null;
        };
             
        this.rotationSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory.DoubleSpinnerValueFactory(MIN_ROTATION, MAX_ROTATION, 0, INCREMENT_ROTATION));
        
        this.rotationSpinner.getEditor().textProperty().bindBidirectional(this.selectedShapeManager.selectedShapeRotationProperty(), new NumberStringConverter());
        this.rotationSpinner.valueProperty().addListener((ObservableValue<? extends Double> observable, Double oldValue, Double newValue) -> {
            if(newValue != null) selectedShapeManager.rotateShape(newValue);
            this.toolBarItems.requestFocus(); //Remove focus from the TF after the execution of resize
        });
        
        this.rotationSpinner.getEditor().setTextFormatter(new TextFormatter(this.rotationFormatter));
    }
    
    /**
     * Setting Up all attributes of scale factor section in inspector to perform the stretch.
     */
    private void setUpStretchSection() {
        //Setting up the formatter
        this.stretchFormatter = change -> {
            String newText = change.getControlNewText();
            if(!newText.isEmpty())
                if (!newText.equals("-") && newText.matches(FXMLDocumentController.REAL_NUMBERS_REGEX) && Double.valueOf(newText).compareTo(MIN_STRETCH)>=0 && Double.valueOf(newText).compareTo(MAX_STRETCH)<=0) {
                    return change;
                }
            return null;
        };
        
        //Horizontal scale factor spinner   
        this.horizontalStretchSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory.DoubleSpinnerValueFactory(MIN_STRETCH, MAX_STRETCH, 1, INCREMENT_STRETCH));

        this.horizontalStretchSpinner.getEditor().textProperty().bindBidirectional(this.selectedShapeManager.selectedShapeScaleXProperty(), new NumberStringConverter());
        this.horizontalStretchSpinner.valueProperty().addListener((ObservableValue<? extends Double> observable, Double oldValue, Double newValue) -> {
            if(newValue != null) SelectedShapeManager.getInstance().horizontalStretch(newValue);
            this.toolBarItems.requestFocus(); //Remove focus from the TF after the execution of resize
        });
        
        this.horizontalStretchSpinner.getEditor().setTextFormatter(new TextFormatter(this.stretchFormatter));
        
        //Vertical scale factor spinner    
        this.verticalStretchSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory.DoubleSpinnerValueFactory(MIN_STRETCH, MAX_STRETCH, 1, INCREMENT_STRETCH));

        this.verticalStretchSpinner.getEditor().textProperty().bindBidirectional(this.selectedShapeManager.selectedShapeScaleYProperty(), new NumberStringConverter());
        this.verticalStretchSpinner.valueProperty().addListener((ObservableValue<? extends Double> observable, Double oldValue, Double newValue) -> {
            if(newValue != null) SelectedShapeManager.getInstance().verticalStretch(newValue);
            this.toolBarItems.requestFocus(); //Remove focus from the TF after the execution of resize
        });
        
        this.verticalStretchSpinner.getEditor().setTextFormatter(new TextFormatter(this.stretchFormatter));
        
    }
    
    /**
     * Setting up all attributes of Text section in toolbar.
     */
    private void setUpTextPropertiesSection() {

        this.sizeTextSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_TEXT_SIZE, MAX_TEXT_SIZE, DEFAULT_SIZE, INCREMENT_TEXT_SIZE));
     
        //Setting up the spinner editor to accept only integer positive
        this.sizeTextSpinner.getEditor().setTextFormatter(new TextFormatter(sizesFormatter));
        this.sizeTextSpinner.setEditable(true);
        
        this.sizeTextSpinner.valueProperty().addListener((ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) -> {
            if(newValue != null) selectedShapeManager.changeTextSize(sizeTextSpinner.getValue());
        });
        
        //Bind the section with the text button and the selection of a text shape
        this.textPropertiesSection.visibleProperty().bind(this.textRadioButton.selectedProperty().or(this.selectedShapeManager.textShapeIsSelectedProperty()));
    }
    
    /**
     * Setting up all attributes of zoom section.
     */
    private void setUpZoom() {
        
        this.zoomSlider.setMin(0);
        this.zoomSlider.setMax(this.zoomLevels.length - 1);
        
        this.zoomProperty = new  SimpleDoubleProperty();
        
        this.zoomSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.zoomLabel.setText(String.valueOf(this.zoomLevels[newValue.intValue()]) + " %");
            this.zoomProperty.setValue(this.zoomLevels[newValue.intValue()]/100);
        });
        
        this.zoomSlider.setValue((this.zoomLevels.length - 1)/2);
        
        Scale scale = new Scale(1, 1);
        this.drawingPane.getTransforms().add(scale);
        this.zoomProperty.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            double scaleVal = newValue.doubleValue();
            scale.setX(scaleVal);
            scale.setY(scaleVal);
            if(gridButton.isSelected())
                grid.drawGrid(scaleVal);
        });   
    }
    
    /**
     * Setting up the grid spinner in toolbar.
     */
    private void setUpGridSpinner(){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory.IntegerSpinnerValueFactory(MIN_GRID, MAX_GRID, DEFAULT_GRID);
        this.gridDensitySpinner.setValueFactory(valueFactory);
        this.gridDensitySpinner.getEditor().setTextFormatter(new TextFormatter(this.sizesFormatter));
        this.gridDensitySpinner.valueProperty().addListener((ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) -> {
            grid.resizeGrid(newValue.doubleValue(), this.zoomProperty.getValue());
        });
        this.gridDensitySpinner.disableProperty().bind(this.gridButton.selectedProperty().not());
    }    
}
