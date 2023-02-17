/**
 * SE_DrawingApplication
 * 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.command;

/**
 * Interface that represents a generic undoable and executable command.
 */
public interface Command {
    
    /**
     * Runs the command.
     */
    public void execute();
    
    /**
     * Undoes the previously executed command.
     */
    public void undo();
}
