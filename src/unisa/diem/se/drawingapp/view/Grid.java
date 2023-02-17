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

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import unisa.diem.se.drawingapp.controller.DrawingSurfaceManager;

/**
 * Class responsible for creating and displaying the grid.
 */
public class Grid {
    
    private final static double STROKE_WIDTH = 0.15;
   
    private final Pane gridPane;
    private final Group wrapperGroup;
    private int gridDim;

    public Grid(Group wrapperGroup) {
        this.wrapperGroup = wrapperGroup;
        this.gridPane = new Pane();
        this.gridPane.setMouseTransparent(true);
        this.gridDim = 20;
    }
    
    /**
     * Draws the grid with the given scale parameter in order to manage the density of lines.
     * @param scale ratio factor to adjust the grid lines to the current panel zoom
     */
    public void drawGrid(double scale){

        this.eraseGrid();
        final double height = DrawingSurfaceManager.getInstance().getDrawingPane().getHeight()*scale;
        final double width = DrawingSurfaceManager.getInstance().getDrawingPane().getWidth()*scale;

        for(int i = 0; i < width; i+= this.gridDim * scale) {
            Line line = new Line(i, 0, i, height);
            line.setStrokeWidth(STROKE_WIDTH);
            this.gridPane.getChildren().add(line);
        }
        
        for(int i = (int) (this.gridDim * scale); i < height; i+= this.gridDim * scale){
            Line line = new Line(0, i, width, i);
            line.setStrokeWidth(STROKE_WIDTH);
            this.gridPane.getChildren().add(line);
        }
        this.wrapperGroup.getChildren().add(this.gridPane);
    }
    
    /**
     * Method that takes care of removing the grid.
     */
    public void eraseGrid() {
        this.gridPane.getChildren().clear();
        this.wrapperGroup.getChildren().remove(this.gridPane);
    }
    
    /**
     * Resizes the grid pane and draws the grid with the new scale factor.
     * @param newDim the px distance between two grid lines
     * @param scale ratio factor to adjust the grid lines to the current panel zoom
     */
    public void resizeGrid(Double newDim, double scale){
         this.gridDim = newDim.intValue();
         this.drawGrid(scale);
    }
    
}
