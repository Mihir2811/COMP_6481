// Assignment 2

// Written by: Mihir Vipulbhai Panchal (ID:40366948)

public interface MissionCritical {

    /*
     * Returns true if the mission task has a priority
     * If anything returns between 8 and 10 inclusive that means it is a mission critical task.
     * Returns false for anything below 8.
     */
	
    public boolean isCritical();
}