// Assignment 2

// Written by: Mihir Vipulbhai Panchal (ID:40366948)

/*
 * This is a custom exception class which is thrown when a mission task has missing or invalid information
 * such as empty task ID, invalid priority, negative duration etc.
 */

public class InvalidMissionTaskException extends Exception {

    // The default constructor
    public InvalidMissionTaskException() 
    {
        super("Error: Invalid or missing mission task information.");
    }

    // This would be the Parameterized constructor
    public InvalidMissionTaskException(String message) 
    {
        super(message);
    }
}