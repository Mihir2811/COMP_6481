// Assignment 3
// Written by: MIHIR VIPULBHAI PANCHAL [40366948]


/**
 * 
 * This exception is thrown when a manufacturing request cannot be processed because of not having sufficient quantity of inventory.
 *
 * @author MIHIR VIPULBHAI PANCHAL
 */


public class InsufficientMaterialsException extends Exception 
{

    /**
     * Default constructor with a static error message
     */

    public InsufficientMaterialsException() {
        super("Error: Insufficient materials to complete manufacturing.");
    }



    /**
     * Parameterized constructor with custom error message
     *
     * 
     * @param message specific error description
     */

    public InsufficientMaterialsException(String message) 
    {
        super(message);
    }
}