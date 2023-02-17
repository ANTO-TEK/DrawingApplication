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

import java.lang.reflect.Field;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import unisa.diem.se.drawingapp.command.CommandExecutor.CommandsStack;
import unisa.diem.se.drawingapp.shape.RectangleShape;
import unisa.diem.se.drawingapp.utility.UtilityTest;

public class CommandExecutorTest {
    
    private CommandExecutor commandExecutor;
    private DrawCommand command; 
    private RectangleShape addedShape;
    
    @Before
    public void setUp(){
        this.commandExecutor = CommandExecutor.getInstance();
        UtilityTest.createAndSetPane();
        this.addedShape = new RectangleShape(UtilityTest.POS, UtilityTest.POS, UtilityTest.TEST_WIDTH_SHAPE, UtilityTest.TEST_HEIGHT_SHAPE);
        this.command = new DrawCommand(this.addedShape); 
    }

    /**
     * Test of getInstance method, of class CommandExecutor.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        //TEST: Assert that the method always returns the same object's instance.
        assertEquals(this.commandExecutor, CommandExecutor.getInstance());
    }

    /**
     * Test of execute method, of class CommandExecutor.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        
        //TEST: Assert that the command has been executed, and has been inserted in the commandHistory
        int oldSize = this.commandExecutor.getCommandHistory().size();
        
        this.commandExecutor.execute(this.command);
        
        assertEquals(oldSize + 1, this.commandExecutor.getCommandHistory().size());
        assertEquals(this.command, this.commandExecutor.getCommandHistory().getLast());
    }

    /**
     * Test of undo method, of class CommandExecutor.
     */
    @Test
    public void testUndoInAnEmptyStack() {
        System.out.println("undoInAnEmptyStack");
       
        //TEST: undo a command in an empty stack
        int oldSize = this.commandExecutor.getCommandHistory().size();
        this.commandExecutor.undo();
        
        assertEquals(oldSize - 1, this.commandExecutor.getCommandHistory().size());
    }
    
    @Test
    public void testUndoInANotEmptyStack() {
        System.out.println("undoInANotEmptyStack");
        
        //TEST: undo a command in a not empty stack
        this.commandExecutor.execute(this.command);
        int oldSize = this.commandExecutor.getCommandHistory().size();
        
        this.commandExecutor.undo();
        
        assertEquals(oldSize - 1, this.commandExecutor.getCommandHistory().size());
    }
    
    
    /**
     * Test of clearHistory method, of class CommandExecutor.
     */
    @Test
    public void testClearHistory() {
        System.out.println("clearHistory");
        
        //TEST: assert that the size is zero after the execution
        this.commandExecutor.execute(this.command); // Make the size > 0
        
        this.commandExecutor.clearHistory();
        
        assertEquals(0, this.commandExecutor.getCommandHistory().size());
    }

    /**
     * Test of getCommandHistory method, of class CommandExecutor.
     * @throws java.lang.NoSuchFieldException
     * @throws java.lang.IllegalAccessException     
     */
    @Test
    public void testGetCommandHistory() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("getHistory");
        
        //TEST: Assert that the returned history is the real history
        final Field field = this.commandExecutor.getClass().getDeclaredField("commandHistory");
        field.setAccessible(true);

        final CommandsStack stack = this.commandExecutor.new CommandsStack();
        field.set(this.commandExecutor, stack);
        
        assertEquals(stack, this.commandExecutor.getCommandHistory());
    }

}