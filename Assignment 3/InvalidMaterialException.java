// Assignment 3
// Written by: MIHIR VIPULBHAI PANCHAL [40366948]

/**
 * This exception is thrown when a user attempts to add stock using an ID that does not exist in the assembly tree
 *
 * @author MIHIR VIPULBHAI PANCHAL
 */

public class InvalidMaterialException extends Exception 
{
    /**
     * Default constructor with a static error message
     */

    public InvalidMaterialException() 
    {
        super("Error: Invalid material ID");
    }

    /**
     * Parameterized constructor with a custom error message
     *
     * @param message the specific error description
     */
    
    public InvalidMaterialException(String message) 
    {
        super(message);
    }
}