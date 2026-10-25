// Assignment 2

// Written by: Mihir Vipulbhai Panchal (ID:40366948)

/*
 * MissionTask will represent a single mission in the Artemis Lunar Mission Control System.
 * It will inherit MissionCritical class to determine if the task needs immediate attention based on its priority level.
 */

import java.util.ArrayList;

public class MissionTask implements MissionCritical {

    // private attributes
    private String taskID;
    private String taskName;
    private String category;
    private int priority;
    private String scheduledDate;
    private int estimatedDuration;
    private boolean isCompleted;
    private ArrayList<String> requiredSystems;
    


    /*
     * Parameterized constructor which will takes all the 8 values from user.
     * It will also validate every field before storing the result.
     * It will also Throw an InvalidMissionTaskException if anything is wrong.
     */

    public MissionTask(String taskID, String taskName, String category,
                       int priority, String scheduledDate,
                       int estimatedDuration, boolean isCompleted,
                       ArrayList<String> requiredSystems)
    
                       throws InvalidMissionTaskException {

        // the taskid cannot be null or empty
        if (taskID == null || taskID.trim().isEmpty()) 
        {
            throw new InvalidMissionTaskException("Error Task ID cannot be empty.");
        }

        // taskName cannot be null or empty
        if (taskName == null || taskName.trim().isEmpty()) 
        {
            throw new InvalidMissionTaskException("Error task name cannot be empty.");
        }

        // category cannot be null or empty
        if (category == null || category.trim().isEmpty()) 
        {
            throw new InvalidMissionTaskException("Error category cannot be empty.");
        }

        // priority must be between 1 and 10
        if (priority < 1 || priority > 10) 
        {
            throw new InvalidMissionTaskException("Error priority must be between 1 and 10.");
        }

        // scheduledDate cannot be null or empty
        if (scheduledDate == null || scheduledDate.trim().isEmpty()) 
        {
            throw new InvalidMissionTaskException("Error scheduled date cannot be empty.");
        }

        // duration must be positive
        if (estimatedDuration <= 0) 
        {
            throw new InvalidMissionTaskException("Error estimated duration must be greater than 0.");
        }

        // requiredSystems cannot be null
        if (requiredSystems == null) 
        {
            throw new InvalidMissionTaskException("Error required systems list cannot be null.");
        }

        // storing the values
        this.taskID = taskID;
        this.taskName = taskName;
        this.category = category;
        this.priority = priority;
        this.scheduledDate = scheduledDate;
        this.estimatedDuration = estimatedDuration;
        this.isCompleted = isCompleted;

        // deep copy to the system list
        this.requiredSystems = new ArrayList<>(requiredSystems);
    }
    


    /*
     * This is a copy constructor which will create a new MissionTask based on an existing one but assigns it a brand new unique task ID
     * The requiredSystems list is deep copied so both objects are independent.
     */

    public MissionTask(MissionTask original, String newTaskID)
    
    throws InvalidMissionTaskException {

        if (newTaskID == null || newTaskID.trim().isEmpty()) 
        {
            throw new InvalidMissionTaskException("Error New Task ID cannot be empty.");
        }

        // copying all fields from original
        this.taskID = newTaskID;
        this.taskName = original.taskName;
        this.category = original.category;
        this.priority = original.priority;
        this.scheduledDate = original.scheduledDate;
        this.estimatedDuration = original.estimatedDuration;
        this.isCompleted = original.isCompleted;

        // Deep copy the list
        this.requiredSystems = new ArrayList<>(original.requiredSystems);
    }
    


    /*
     * clone method will create and return a deep copy of the MissionTask.
     * The cloned object will have its own independent requiredSystems list.
     * A new task ID will be requested separately before adding to the system.
     */

    public MissionTask clone() {

        // new empty systems list
        ArrayList<String> systemsCopy = new ArrayList<>(this.requiredSystems);

        // new mission same values
        try 
        {
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

        } 
        
        catch (InvalidMissionTaskException e) 
        {
            System.out.println("Cloning mission task failed");
            return null;
        }
    }
    

     // Implements isCritical() from MissionCritical interface.
     // Returns true if priority is 8 9 or 10

    public boolean isCritical() 
    {
        return priority >= 8 && priority <= 10;
    }
    


    /*
     * Two mission tasks are equal if all fields match except taskID (overridding situation).
     */

    public boolean equals(Object obj) {

        if (this == obj) 
        {
            return true;
        }

        if (obj == null) 
        {
            return false;
        }

        if (!(obj instanceof MissionTask)) 
        {
            return false;
        }

        // casting and comparing all fields except taskID
        MissionTask other = (MissionTask) obj;

        return this.taskName.equalsIgnoreCase(other.taskName) &&
               this.category.equalsIgnoreCase(other.category) &&
               this.priority == other.priority &&
               this.scheduledDate.equals(other.scheduledDate) &&
               this.estimatedDuration == other.estimatedDuration &&
               this.isCompleted == other.isCompleted &&
               this.requiredSystems.equals(other.requiredSystems);
    }
    
    

    // custom tostring method
    
    public String toString() 
    {
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
    


    // Getters Methods

    public String getTaskID() 
    {
        return taskID;
    }

    public String getTaskName() 
    {
        return taskName;
    }

    public String getCategory() 
    {
        return category;
    }

    public int getPriority() 
    {
        return priority;
    }

    public String getScheduledDate() 
    {
        return scheduledDate;
    }

    public int getEstimatedDuration() 
    {
        return estimatedDuration;
    }

    public boolean isCompleted() 
    {
        return isCompleted;
    }

    // deep copy
    public ArrayList<String> getRequiredSystems() 
    {
        return new ArrayList<>(requiredSystems);
    }



    // Setters
    // except taskID

    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public void setCategory(String category) 
    {
        this.category = category;
    }

    public void setPriority(int priority) 
    {
        if (priority >= 1 && priority <= 10) 
        {
            this.priority = priority;
        } 

        else 
        {
            System.out.println("Priority must be between 1 and 10");
        }
    }

    public void setScheduledDate(String scheduledDate) 
    {
        this.scheduledDate = scheduledDate;
    }

    public void setEstimatedDuration(int estimatedDuration) 
    {
        if (estimatedDuration > 0) 
        {
            this.estimatedDuration = estimatedDuration;
        } 
        
        else 
        {
            System.out.println("Duration must be greater than 0");
        }
    }

    public void setCompleted(boolean isCompleted) 
    {
        this.isCompleted = isCompleted;
    }

    // deep copy
    public void setRequiredSystems(ArrayList<String> requiredSystems) 
    {
        this.requiredSystems = new ArrayList<>(requiredSystems);
    }

}
