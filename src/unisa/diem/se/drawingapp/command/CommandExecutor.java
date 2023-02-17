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

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;

/**
 * Represents an Invoker in Command Design Pattern, implemented as a Singleton.
 */
public class CommandExecutor {
    
    private final Deque<Command> commandHistory;  
    private static CommandExecutor uniqueInstance;
    
    private CommandExecutor(){
        this.commandHistory = new CommandsStack();
    }
    
    /**
     * Getter method for CommandExecutor instance.
     * @return CommandExecutor instance
     */
    public static CommandExecutor getInstance(){
        if(CommandExecutor.uniqueInstance == null){
            CommandExecutor.uniqueInstance = new CommandExecutor();
        }
        return CommandExecutor.uniqueInstance;
    }
    
    /**
     * Executes the received command and adds it to the command history collection.
     * @param command the command to execute
     */
    public void execute(Command command){
        this.commandHistory.addLast(command);
        command.execute();
    }
    
    /**
     * Undoes the last executed command, in other terms the object on the top of command history stack.
     */
    public void undo(){
        if(!this.commandHistory.isEmpty()){
            Command c = this.commandHistory.removeLast();
            c.undo();
        }
    }

    /**
     * Removes all the commands in the history.
     */
    public void clearHistory() {
        this.commandHistory.clear();
    }
    
    /**
     * Returns a stack containing all executed commands.
     * @return instance of CommandsStack class
     */
    public CommandsStack getCommandHistory(){
        return (CommandsStack) this.commandHistory;
    }
    
    /**
     * Nested class that represents the stack of executed commands.
     * Contains only Command objects and use a property for the emptiness. 
     * Follows LIFO policy.
     */
    public class CommandsStack extends ArrayDeque<Command>{
        
        private final BooleanProperty empty;
        
        public CommandsStack(){
            super();
            this.empty = new ReadOnlyBooleanWrapper();
            this.empty.set(true);
        }
        
        /**
         * Set the value of the empty property. 
         * Utility method.
         * @param value 
         */
        private void setEmpty(boolean value) {
            this.empty.set(value);
        }

        /**
         * Returns the empty property of the command stack. 
         * Useful for bindings.
         * @return empty BooleanProperty object.
         */
        public BooleanProperty emptyProperty() {
            return empty;
        }
        
        /**
         * Checks if the stack is empty.
         * @return a boolean that tells if the stack is empty
         */
        @Override
        public boolean isEmpty() {
            return empty.get();
        }

        /**
         * Removes the last inserted command object in the stack.
         * @return last executed command.
         */
        @Override
        public Command removeLast() {
            Command command = super.removeLast();
            if(super.isEmpty()){
                this.setEmpty(true);
            }
            return command;
        }
        
        /**
         * Adds a command object on top of the stack.
         * @param command
         */
        @Override
        public void addLast(Command command) {
            if(super.isEmpty()){
                this.setEmpty(false);
            }
            super.addLast(command);
        }
        
        /**
         * Removes all the Command objects contained in the stack.
         */
        @Override
        public void clear(){
            super.clear();
            this.setEmpty(true);
        }      
    }
}

