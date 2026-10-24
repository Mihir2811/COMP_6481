// Assignment 2
// Written by: (your name and student id)

/*
 * MissionTask represents a single mission operation in the
 * Artemis Lunar Mission Control System.
 * It implements MissionCritical to determine if the task
 * needs immediate attention based on its priority level.
 */

import java.util.ArrayList;

public class MissionTask implements MissionCritical {

    // All attributes are private - proper encapsulation
    private String taskID;
    private String taskName;
    private String category;
    private int priority;
    private String scheduledDate;
    private int estimatedDuration;
    private boolean isCompleted;
    private ArrayList<String> requiredSystems;
    
    /*
     * Parameterized constructor - takes all 8 values from user.
     * Validates every field before storing it.
     * Throws InvalidMissionTaskException if anything is wrong.
     */
    public MissionTask(String taskID, String taskName, String category,
                       int priority, String scheduledDate,
                       int estimatedDuration, boolean isCompleted,
                       ArrayList<String> requiredSystems)
    throws InvalidMissionTaskException {

        // taskID cannot be null or empty
        if (taskID == null || taskID.trim().isEmpty()) {
            throw new InvalidMissionTaskException("Error: Task ID cannot be empty.");
        }

        // taskName cannot be null or empty
        if (taskName == null || taskName.trim().isEmpty()) {
            throw new InvalidMissionTaskException("Error: Task name cannot be empty.");
        }

        // category cannot be null or empty
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidMissionTaskException("Error: Category cannot be empty.");
        }

        // priority must be between 1 and 10
        if (priority < 1 || priority > 10) {
            throw new InvalidMissionTaskException("Error: Priority must be between 1 and 10.");
        }

        // scheduledDate cannot be null or empty
        if (scheduledDate == null || scheduledDate.trim().isEmpty()) {
            throw new InvalidMissionTaskException("Error: Scheduled date cannot be empty.");
        }

        // duration must be positive
        if (estimatedDuration <= 0) {
            throw new InvalidMissionTaskException("Error: Estimated duration must be greater than zero.");
        }

        // requiredSystems cannot be null
        if (requiredSystems == null) {
            throw new InvalidMissionTaskException("Error: Required systems list cannot be null.");
        }

        // all valid - store the values
        this.taskID = taskID;
        this.taskName = taskName;
        this.category = category;
        this.priority = priority;
        this.scheduledDate = scheduledDate;
        this.estimatedDuration = estimatedDuration;
        this.isCompleted = isCompleted;

        // Deep copy the systems list so outside changes don't affect this object
        this.requiredSystems = new ArrayList<>(requiredSystems);
    }
    
    /*
     * Copy constructor - creates a new MissionTask based on an existing one
     * but assigns it a brand new unique task ID.
     * The requiredSystems list is deep copied so both objects are independent.
     */
    public MissionTask(MissionTask original, String newTaskID)
    throws InvalidMissionTaskException {

        // validate the new ID
        if (newTaskID == null || newTaskID.trim().isEmpty()) {
            throw new InvalidMissionTaskException("Error: New Task ID cannot be empty.");
        }

        // copy all fields from original
        this.taskID = newTaskID;               // new ID given by user
        this.taskName = original.taskName;
        this.category = original.category;
        this.priority = original.priority;
        this.scheduledDate = original.scheduledDate;
        this.estimatedDuration = original.estimatedDuration;
        this.isCompleted = original.isCompleted;

        // Deep copy the list - not just copying the reference
        this.requiredSystems = new ArrayList<>(original.requiredSystems);
    }
    
    /*
     * clone() creates and returns a deep copy of this MissionTask.
     * The cloned object has its own independent requiredSystems list.
     * A new task ID will be requested separately before adding to the system.
     */
    public MissionTask clone() {

        // create a new empty systems list
        ArrayList<String> systemsCopy = new ArrayList<>(this.requiredSystems);

        // try to build a new MissionTask with same values
        try {
            return new MissionTask(
                this.taskID,
                this.taskName,
                this.category,
                this.priority,
                this.scheduledDate,
                this.estimatedDuration,
                this.isCompleted,
                systemsCopy
            );
        } catch (InvalidMissionTaskException e) {
            // this should never happen since we are copying valid data
            System.out.println("Clone failed: " + e.getMessage());
            return null;
        }
    }
    
    /*
     * Implements isCritical() from MissionCritical interface.
     * Returns true if priority is 8, 9, or 10.
     * These tasks need immediate attention.
     */
    public boolean isCritical() {
        return priority >= 8 && priority <= 10;
    }
    
    /*
     * Overrides equals() from Object class.
     * Two mission tasks are equal if all fields match except taskID.
     * This is used to detect duplicate tasks in the system.
     */
    public boolean equals(Object obj) {

        // same object in memory
        if (this == obj) {
            return true;
        }

        // null check
        if (obj == null) {
            return false;
        }

        // must be a MissionTask
        if (!(obj instanceof MissionTask)) {
            return false;
        }

        // cast and compare all fields except taskID
        MissionTask other = (MissionTask) obj;

        return this.taskName.equalsIgnoreCase(other.taskName) &&
               this.category.equalsIgnoreCase(other.category) &&
               this.priority == other.priority &&
               this.scheduledDate.equals(other.scheduledDate) &&
               this.estimatedDuration == other.estimatedDuration &&
               this.isCompleted == other.isCompleted &&
               this.requiredSystems.equals(other.requiredSystems);
    }
    
    /*
     * toString() displays all mission task information clearly.
     * Called automatically when you do System.out.println(task)
     */
    public String toString() {
        return "Task ID: " + taskID + "\n" +
               "Task Name: " + taskName + "\n" +
               "Category: " + category + "\n" +
               "Priority: " + priority + "\n" +
               "Scheduled Date: " + scheduledDate + "\n" +
               "Estimated Duration: " + estimatedDuration + " minutes" + "\n" +
               "Completed: " + isCompleted + "\n" +
               "Required Systems: " + requiredSystems + "\n" +
               "Mission Critical: " + isCritical();
    }
    
    // -------------------------
    // Getters
    // -------------------------

    public String getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getCategory() {
        return category;
    }

    public int getPriority() {
        return priority;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public ArrayList<String> getRequiredSystems() {
        // return deep copy so outside code cannot modify internal list
        return new ArrayList<>(requiredSystems);
    }

    // -------------------------
    // Setters - no setter for taskID as it must stay unique
    // -------------------------

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPriority(int priority) {
        if (priority >= 1 && priority <= 10) {
            this.priority = priority;
        } else {
            System.out.println("Priority must be between 1 and 10.");
        }
    }

    public void setScheduledDate(String scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        if (estimatedDuration > 0) {
            this.estimatedDuration = estimatedDuration;
        } else {
            System.out.println("Duration must be greater than zero.");
        }
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void setRequiredSystems(ArrayList<String> requiredSystems) {
        // deep copy incoming list
        this.requiredSystems = new ArrayList<>(requiredSystems);
    }

} // end of MissionTask class