/** 
 * Group members:
 *  ⋅ Amato Emilio
 *  ⋅ Apicella Salvatore
 *  ⋅ Bove Antonio
 *  ⋅ Cerasuolo Cristian
 */

package unisa.diem.se.drawingapp.tool;

import javafx.scene.input.MouseEvent;

/**
 * The abstract class Tool represents the current state of the Tool that allows 
 * to execute different operations depending on the state set in the FXMLDocumentController.
 */
public abstract class Tool {
    
    /**
     * This function provides an empty implementation. Classes that extend it 
     * can provide a concrete implementation by overriding the method.
     * @param evt event that has occurred
     */
    public void onMouseDown(MouseEvent evt){
        
    }
    
    /**
     * This function provides an empty implementation. Classes that extend it 
     * can provide a concrete implementation by overriding the method.
     * @param evt event that has occurred
     */
    public void onMouseDrag(MouseEvent evt){
        
    }
    
    /**
     * This function provides an empty implementation. Classes that extend it 
     * can provide a concrete implementation by overriding the method.
     * @param evt event that has occurred
     */
    public void onMouseUp(MouseEvent evt){
        
    }
    
    /**
     * Complete the work of the current tool. By completion we mean the finalization of the current 
     * operation, so that all the others available can be used correctly.
     */
    public void complete(){
        
    }
    
}
