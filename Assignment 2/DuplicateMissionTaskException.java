// Assignment 2

// Written by: Mihir Vipulbhai Panchal (ID:40366948)

/*
 * This exception is thrown when someone tries to add a mission task that already exists in the active mission list.
 * Duplicate task IDs not allowed.
 */

public class DuplicateMissionTaskException extends Exception {

    // The default constructor
    public DuplicateMissionTaskException() 
    {
        super("Error: Mission task already exists in the mission plan.");
    }

    // This would be the Parameterized constructor
    public DuplicateMissionTaskException(String message) 
    {
        super(message);
    }
}