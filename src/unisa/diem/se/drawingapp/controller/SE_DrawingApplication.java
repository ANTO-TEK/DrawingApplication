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

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class SE_DrawingApplication extends Application {

    /**
     * Set up the application set the title and icon, selected a FXMY sheet and
     * CSS stylesheet, intercept key event and call a specific handler, 
     * set up a controller and set the full screen on start
     * @param stage
     * @throws Exception: it can generate any type of Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unisa/diem/se/drawingapp/view/FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController();
        
        stage.getIcons().add(new Image("/unisa/diem/se/drawingapp/view/assets/drawingApplicationIcon.png"));
        stage.setTitle("SE Drawing Application");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/unisa/diem/se/drawingapp/view/application.css").toExternalForm());
        scene.setOnKeyPressed((KeyEvent event) -> controller.keyEventHandler(event));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    /**
     * The main methdos for the application, it launchs application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}