// Assignment 2

// Written by: Mihir Vipulbhai Panchal (ID:40366948)

/*
 * This MissionControlManager class manages all the active and completed mission tasks.
 * I will store all the active tasks in an ArrayList.
 * All the Completed tasks will be stored in a LinkedList with most recent task as first.
 * This class will handle operations of adding, updating, completing, deleting, searching, cloning and summarizing mission tasks.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;

public class MissionControlManager {

    // Using ArrayList for all current and upcoming tasks
    private ArrayList<MissionTask> activeTasks;

    // Using LinkedList for completed tasks and I will keep the most recent task at the front
    private LinkedList<MissionTask> completedTasks;

    // Empty Constructor which will initialize the arraylist and linkedlist
    public MissionControlManager() 
    {
        activeTasks = new ArrayList<>();
        completedTasks = new LinkedList<>();
    }

    // 1. addMissionTask
    // This will adds a new task to activeTasks and validate by checking for duplicates
    // Throws DuplicateMissionTaskException if task already exists.

    public void addMissionTask(MissionTask task)
    throws DuplicateMissionTaskException {

        for (MissionTask existing : activeTasks) 
        {
            if (existing.getTaskID().equalsIgnoreCase(task.getTaskID())) 
            {
                throw new DuplicateMissionTaskException("Mission task [" + task.getTaskID() + "] already exists Duplicate entries are not allowed");
            }
        }

        activeTasks.add(task);

        System.out.println("Mission task [" + task.getTaskID() + "] successfully added to the active mission list");
    }

    // 2. updateMissionTask
    // Finds task by ID and lets user modify its fields

    public void updateMissionTask(String taskID, String newTaskName, String newCategory, int newPriority,
                                   String newScheduledDate, int newEstimatedDuration, ArrayList<String> newSystems) {

        for (MissionTask task : activeTasks) 
        {

            if (task.getTaskID().equalsIgnoreCase(taskID)) 
            {
                // update only if new value is not empty

                if (newTaskName != null && !newTaskName.trim().isEmpty()) 
                {
                    task.setTaskName(newTaskName);
                }

                if (newCategory != null && !newCategory.trim().isEmpty()) 
                {
                    task.setCategory(newCategory);
                }

                if (newPriority >= 1 && newPriority <= 10) 
                {
                    task.setPriority(newPriority);
                }

                if (newScheduledDate != null && !newScheduledDate.trim().isEmpty()) 
                {
                    task.setScheduledDate(newScheduledDate);
                }

                if (newEstimatedDuration > 0) 
                {
                    task.setEstimatedDuration(newEstimatedDuration);
                }

                if (newSystems != null && !newSystems.isEmpty()) 
                {
                    task.setRequiredSystems(newSystems);
                }

                System.out.println("Mission task: " + taskID + " has been updated successfully");
                System.out.println(task);
                return;
            }
        }

        System.out.println("Mission task: " + taskID + " not found in active tasks");
    }

    // 3. completeMissionTask
    // Moves task from activeTasks to front of completedTasks

    public void completeMissionTask(String taskID) {

        for (int i = 0; i < activeTasks.size(); i++) 
        {
            if (activeTasks.get(i).getTaskID().equalsIgnoreCase(taskID)) 
            {

                MissionTask task = activeTasks.get(i);

                task.setCompleted(true);

                activeTasks.remove(i);

                completedTasks.addFirst(task);

                System.out.println("Mission task: " + taskID + " completed and moved to the mission history");
                System.out.println("Most Recently Completed Task:");
                System.out.println(completedTasks.getFirst().getTaskID() +
                                   " - " +
                                   completedTasks.getFirst().getTaskName());
                return;
            }
        }

        System.out.println("Mission task: " + taskID + " not found in active tasks");
    }

    // 4. deleteMissionTask
    // Removes task from either activeTasks or completedTasks

    public void deleteMissionTask(String taskID) {

        // search activeTasks first
        for (int i = 0; i < activeTasks.size(); i++) 
        {
            if (activeTasks.get(i).getTaskID().equalsIgnoreCase(taskID)) 
            {
                activeTasks.remove(i);
                System.out.println("Mission task: " + taskID + " removed from the active mission plan");
                return;
            }
        }

        // search completedTasks if not in active
        for (int i = 0; i < completedTasks.size(); i++) 
        {
            if (completedTasks.get(i).getTaskID().equalsIgnoreCase(taskID)) 
            {
                completedTasks.remove(i);
                System.out.println("Mission task: " + taskID +" removed from the mission history");
                return;
            }
        }

        System.out.println("Mission task: " + taskID + " not found in any list");
    }

    // 5. showCriticalTasks
    // Displays all active tasks where isCritical() is true

    public void showCriticalTasks() {

        System.out.println("\n..·´¯`·...·´¯`·...·´¯`·. CRITICAL MISSION OPERATIONS ..·´¯`·...·´¯`·...·´¯`·.");

        int count = 0;

        for (MissionTask task : activeTasks) 
        {
            if (task.isCritical()) 
            {
                System.out.println("\nTask ID: " + task.getTaskID());
                System.out.println("Task Name: " + task.getTaskName());
                System.out.println("Priority: " + task.getPriority());
                System.out.println("CRITICAL: " + task.getTaskName() + " requires immediate attention");
                count = count + 1;
            }
        }

        if (count == 0) 
        {
            System.out.println("No critical mission tasks found");
        } 
        
        else 
        {
            System.out.println("\nTotal Critical Operations: " + count);
        }

        System.out.println("===============================================");
    }

    // 6. showTopMissionTasks
    // Displays top 3 tasks by highest priority

    public void showTopMissionTasks() {

        if (activeTasks.isEmpty()) 
        {
            System.out.println("No active mission tasks available");
            return;
        }

        // temporary copy 
        ArrayList<MissionTask> tempList = new ArrayList<>(activeTasks);

        // sort by priority 
        Collections.sort(tempList, new Comparator<MissionTask>() 
        {
            public int compare(MissionTask a, MissionTask b) 
            {
                return b.getPriority() - a.getPriority();
            }
        });

        System.out.println("\n..·´¯`·...·´¯`·...·´¯`·. TOP 3 MISSION TASKS ..·´¯`·...·´¯`·...·´¯`·.");

        System.out.println("The top 3 mission tasks based on priority are displayed below.");

        int limit = Math.min(3, tempList.size());

        for (int i = 0; i < limit; i++) 
        {
            System.out.println("\n--- Rank " + (i + 1) + " ---");
            System.out.println(tempList.get(i));
        }

        System.out.println("========================================");
    }

    // 7. showOverdueMissionTasks
    // Displays tasks whose scheduled date has already passed

    public void showOverdueMissionTasks() {

        LocalDate today = LocalDate.now();

        System.out.println("\n..·´¯`·...·´¯`·...·´¯`·. OVERDUE MISSION TASKS ..·´¯`·...·´¯`·...·´¯`·.");

        System.out.println("Today's Date: " + today);

        int count = 0;

        for (MissionTask task : activeTasks) 
        {
            LocalDate taskDate = LocalDate.parse(task.getScheduledDate());

            if (taskDate.isBefore(today) && !task.isCompleted()) {
                System.out.println("\nTask ID: " + task.getTaskID());
                System.out.println("Task Name: " + task.getTaskName());
                System.out.println("Scheduled Date: " + task.getScheduledDate());
                System.out.println("Priority: " + task.getPriority());
                count = count + 1;
            }
        }

        if (count == 0) 
        {
            System.out.println("No overdue mission tasks found");
        } 
        else 
        {
            System.out.println("\nWarning: " + count + " mission task(s) have passed their scheduled execution time");
        }

        System.out.println("==========================================");
    }

    // 8. cloneMissionTask
    // Deep copies a task and adds it with a new ID

    public void cloneMissionTask(String taskID, String newTaskID)
    throws DuplicateMissionTaskException 
    {

        MissionTask original = findMissionTask(taskID);

        if (original == null) 
        {
            System.out.println("Mission task: " + taskID + " not found.");
            return;
        }

        MissionTask cloned = original.clone();

        try 
        {
            MissionTask clonedWithNewID = new MissionTask(cloned, newTaskID);

            addMissionTask(clonedWithNewID);

            System.out.println("Mission task: " + taskID + " cloned successfully as " + newTaskID);

        } 
        
        catch (InvalidMissionTaskException e) 
        {
            System.out.println("Clone failed: " + e.getMessage());
        }
    }

    // 9. findMissionTask
    // Searches both lists and returns the task if found

    public MissionTask findMissionTask(String taskID) {

        for (MissionTask task : activeTasks) 
        {
            if (task.getTaskID().equalsIgnoreCase(taskID)) 
            {
                System.out.println("Mission task: " + taskID + " found in active tasks.");
                return task;
            }
        }

        for (MissionTask task : completedTasks) 
        {
            if (task.getTaskID().equalsIgnoreCase(taskID)) 
            {
                System.out.println("Mission task " + taskID +" found in completed tasks.");
                return task;
            }
        }

        System.out.println("Mission task " + taskID + " not found.");
        return null;
    }

    // 10. generateMissionSummary
    // Prints a full summary report of the mission

    public void generateMissionSummary() {

        System.out.println("\n..·´¯`·...·´¯`·...·´¯`·. ARTEMIS MISSION SUMMARY ..·´¯`·...·´¯`·...·´¯`·.");

        System.out.println("Active Mission Tasks: " + activeTasks.size());
        System.out.println("Completed Mission Tasks: " + completedTasks.size());

        int criticalCount = 0;
        for (MissionTask task : activeTasks) 
            {
            if (task.isCritical()) 
            {
                criticalCount = criticalCount + 1;
            }
        }

        System.out.println("Critical Active Tasks: " + criticalCount);

        LocalDate today = LocalDate.now();
        int overdueCount = 0;

        for (MissionTask task : activeTasks) 
        {
            LocalDate taskDate = LocalDate.parse(task.getScheduledDate());

            if (taskDate.isBefore(today) && !task.isCompleted()) 
            {
                overdueCount = overdueCount + 1;
            }
        }

        System.out.println("Overdue Tasks: " + overdueCount);

        if (!activeTasks.isEmpty()) 
        {
            double total = 0;
            
            for (MissionTask task : activeTasks) 
            {
                total += task.getEstimatedDuration();
            }

            double average = total / activeTasks.size();
            System.out.printf("Average Estimated Duration: %.1f minutes%n", average);

        } 

        else 
        {
            System.out.println("Average Estimated Duration: N/A");
        }

        if (!activeTasks.isEmpty()) {
            MissionTask highest = activeTasks.get(0);
            for (MissionTask task : activeTasks) 
            {
                if (task.getPriority() > highest.getPriority()) 
                {
                    highest = task;
                }
            }

            System.out.println("Highest Priority Operation:");
            System.out.println(highest.getTaskID() + " - " + highest.getTaskName());
        }

        if (!completedTasks.isEmpty()) 
        {
            System.out.println("Most Recently Completed Operation:");
            System.out.println(completedTasks.getFirst().getTaskID() + " - " + completedTasks.getFirst().getTaskName());
        }

        System.out.println("===========================================");
        System.out.println("Mission summary successfully generated");
    }

    // Getters for the lists - used by main driver

    public ArrayList<MissionTask> getActiveTasks() 
    {
        return activeTasks;
    }

    public LinkedList<MissionTask> getCompletedTasks() 
    {
        return completedTasks;
    }

} 