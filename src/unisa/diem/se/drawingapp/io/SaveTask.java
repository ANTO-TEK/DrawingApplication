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

import java.io.File;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;
import unisa.diem.se.drawingapp.shape.CustomShape;

/**
 * Class of a task that takes care of saving to file.
 */
public class SaveTask extends Task<Void> {

    private final ArrayList<Node> shapes;
    private final File file;

    public SaveTask(ArrayList<Node> shapes, File file) {
        this.shapes = shapes;
        this.file = file;
    }
    
    /**
     * Save the given collection of custom shape into the file.
     * @return nothing
     * @throws Exception if the extensions is not supported by the application for saving shapes 
     */
    @Override
    protected Void call() throws Exception {
        
        ArrayList<CustomShape> list = new ArrayList<>();

        for(Node shape : shapes)
            list.add(DrawingSurfaceManager.getInstance().getShapes().get((Shape)shape));

        IOManager.save(file.getPath(), list);

        return null;

    }


    

    
}
