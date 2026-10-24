// Assignment 2

// Written by: Mihir Vipulbhai Panchal (ID:40366948)

/*
 * ArtemisMissionControlMain is the main driver class.
 * It displays the menu, collects user input and calls
 * the appropriate methods in MissionControlManager.
 * Handles all exceptions so the program never crashes.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class ArtemisMissionControlMain {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to the Artemis Lunar Mission Control System");
        System.out.println("Developed by: (your name)");

        // single manager instance - used throughout the program
        MissionControlManager manager = new MissionControlManager();

        // also create a MissionTimeline for option 13
        MissionTimeline timeline = new MissionTimeline();

        int choice = 0;

        while (choice != 14) 
            {

            choice = showMainMenu();

            if (choice == 1) 
            {
                handleAddMissionTask(manager);
            } 
            
            else if (choice == 2) 
            {
                handleUpdateMissionTask(manager);
            } 
            
            else if (choice == 3) 
            {
                handleCompleteMissionTask(manager);
            } 
            
            else if (choice == 4) 
            {
                handleDeleteMissionTask(manager);
            } 
            
            else if (choice == 5) 
            {
                handleViewActiveTasks(manager);
            } 
            
            else if (choice == 6) 
            {
                handleViewCompletedTasks(manager);
            }
             
            else if (choice == 7) 
            {
                manager.showCriticalTasks();
            } 
            
            else if (choice == 8) 
            {
                manager.showTopMissionTasks();
            } 
            
            else if (choice == 9) 
            {
                manager.showOverdueMissionTasks();
            } 
            
            else if (choice == 10) 
            {
                handleCloneMissionTask(manager);
            } 
            
            else if (choice == 11) 
            {
                handleSearchMissionTask(manager);
            } 
            
            else if (choice == 12) {
                manager.generateMissionSummary();

            } 
            
            else if (choice == 13) {
                handleTestTimeline(timeline);

            } 
            
            else if (choice == 14) {
                System.out.println("\nThank you for using the Artemis Mission Control System.");
                System.out.println("Safe travels and successful missions!");
            }
        }

        scanner.close();
    }

    // -------------------------------------------------------
    // Main Menu
    // -------------------------------------------------------

    public static int showMainMenu() {

        int choice = 0;

        while (choice < 1 || choice > 14) {

            System.out.println("\n========================================");
            System.out.println("        ARTEMIS MISSION CONTROL         ");
            System.out.println("========================================");
            System.out.println("1.  Add Mission Task");
            System.out.println("2.  Update Mission Task");
            System.out.println("3.  Complete Mission Task");
            System.out.println("4.  Delete Mission Task");
            System.out.println("5.  View Active Mission Tasks");
            System.out.println("6.  View Completed Mission Tasks");
            System.out.println("7.  View Critical Mission Tasks");
            System.out.println("8.  View Top 3 Mission Tasks");
            System.out.println("9.  View Overdue Mission Tasks");
            System.out.println("10. Clone Mission Task");
            System.out.println("11. Search Mission Task");
            System.out.println("12. Generate Mission Summary");
            System.out.println("13. Test Mission Timeline");
            System.out.println("14. Exit");
            System.out.print("Enter your selection: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice < 1 || choice > 14) {
                    System.out.println("Please enter a number between 1 and 14.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return choice;
    }

    // -------------------------------------------------------
    // Option 1 - Add Mission Task
    // -------------------------------------------------------

    public static void handleAddMissionTask(MissionControlManager manager) {

        try {
            System.out.print("Enter Mission Task ID: ");
            String taskID = scanner.nextLine().trim();

            System.out.print("Enter Task Name: ");
            String taskName = scanner.nextLine().trim();

            System.out.print("Enter Category: ");
            String category = scanner.nextLine().trim();

            System.out.print("Enter Priority (1-10): ");
            int priority = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Scheduled Date (YYYY-MM-DD): ");
            String scheduledDate = scanner.nextLine().trim();

            System.out.print("Enter Estimated Duration (minutes): ");
            int duration = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter number of required systems: ");
            int systemCount = Integer.parseInt(scanner.nextLine().trim());

            ArrayList<String> systems = new ArrayList<>();
            for (int i = 1; i <= systemCount; i++) {
                System.out.print("System " + i + ": ");
                systems.add(scanner.nextLine().trim());
            }

            // create the task - constructor validates all inputs
            MissionTask task = new MissionTask(
                taskID, taskName, category, priority,
                scheduledDate, duration, false, systems
            );

            // add to manager - checks for duplicates
            manager.addMissionTask(task);

        } catch (InvalidMissionTaskException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (DuplicateMissionTaskException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers for priority and duration.");
        }
    }

    // -------------------------------------------------------
    // Option 2 - Update Mission Task
    // -------------------------------------------------------

    public static void handleUpdateMissionTask(MissionControlManager manager) {

        try {
            System.out.print("Enter Task ID to update: ");
            String taskID = scanner.nextLine().trim();

            // find the task first
            MissionTask existing = manager.findMissionTask(taskID);
            if (existing == null) {
                return;
            }

            System.out.println("\nCurrent task details:");
            System.out.println(existing);

            System.out.println("\nEnter new values (press Enter to keep current value):");

            System.out.print("New Task Name [" + existing.getTaskName() + "]: ");
            String newName = scanner.nextLine().trim();

            System.out.print("New Category [" + existing.getCategory() + "]: ");
            String newCategory = scanner.nextLine().trim();

            System.out.print("New Priority 1-10 [" + existing.getPriority() + "]: ");
            String priorityInput = scanner.nextLine().trim();
            int newPriority = priorityInput.isEmpty() ? -1 :
                              Integer.parseInt(priorityInput);

            System.out.print("New Scheduled Date [" + existing.getScheduledDate() + "]: ");
            String newDate = scanner.nextLine().trim();

            System.out.print("New Duration (minutes) [" + existing.getEstimatedDuration() + "]: ");
            String durationInput = scanner.nextLine().trim();
            int newDuration = durationInput.isEmpty() ? -1 :
                              Integer.parseInt(durationInput);

            System.out.print("Update required systems? (yes/no): ");
            String updateSystems = scanner.nextLine().trim();

            ArrayList<String> newSystems = null;
            if (updateSystems.equalsIgnoreCase("yes")) {
                System.out.print("Enter number of systems: ");
                int count = Integer.parseInt(scanner.nextLine().trim());
                newSystems = new ArrayList<>();
                for (int i = 1; i <= count; i++) {
                    System.out.print("System " + i + ": ");
                    newSystems.add(scanner.nextLine().trim());
                }
            }

            manager.updateMissionTask(taskID, newName, newCategory,
                                      newPriority, newDate,
                                      newDuration, newSystems);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers.");
        }
    }

    // -------------------------------------------------------
    // Option 3 - Complete Mission Task
    // -------------------------------------------------------

    public static void handleCompleteMissionTask(MissionControlManager manager) {

        System.out.print("Enter Task ID to mark as complete: ");
        String taskID = scanner.nextLine().trim();
        manager.completeMissionTask(taskID);
    }

    // -------------------------------------------------------
    // Option 4 - Delete Mission Task
    // -------------------------------------------------------

    public static void handleDeleteMissionTask(MissionControlManager manager) {

        System.out.print("Enter Task ID to delete: ");
        String taskID = scanner.nextLine().trim();
        manager.deleteMissionTask(taskID);
    }

    // -------------------------------------------------------
    // Option 5 - View Active Tasks
    // -------------------------------------------------------

    public static void handleViewActiveTasks(MissionControlManager manager) {

        ArrayList<MissionTask> active = manager.getActiveTasks();

        if (active.isEmpty()) {
            System.out.println("No active mission tasks.");
            return;
        }

        System.out.println("\n========= ACTIVE MISSION TASKS =========");
        for (int i = 0; i < active.size(); i++) {
            System.out.println("\n--- Task " + (i + 1) + " ---");
            System.out.println(active.get(i));
        }
        System.out.println("\nTotal Active Tasks: " + active.size());
        System.out.println("=========================================");
    }

    // -------------------------------------------------------
    // Option 6 - View Completed Tasks
    // -------------------------------------------------------

    public static void handleViewCompletedTasks(MissionControlManager manager) {

        if (manager.getCompletedTasks().isEmpty()) {
            System.out.println("No completed mission tasks yet.");
            return;
        }

        System.out.println("\n========= COMPLETED MISSION TASKS =========");
        System.out.println("(Most recently completed shown first)");

        int count = 1;
        for (MissionTask task : manager.getCompletedTasks()) {
            System.out.println("\n--- Completed Task " + count + " ---");
            System.out.println(task);
            count++;
        }

        System.out.println("\nTotal Completed Tasks: " +
                           manager.getCompletedTasks().size());
        System.out.println("============================================");
    }

    // -------------------------------------------------------
    // Option 10 - Clone Mission Task
    // -------------------------------------------------------

    public static void handleCloneMissionTask(MissionControlManager manager) {

        try {
            System.out.print("Enter Task ID to clone: ");
            String taskID = scanner.nextLine().trim();

            System.out.print("Enter new unique Task ID for the clone: ");
            String newTaskID = scanner.nextLine().trim();

            manager.cloneMissionTask(taskID, newTaskID);

        } catch (DuplicateMissionTaskException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // Option 11 - Search Mission Task
    // -------------------------------------------------------

    public static void handleSearchMissionTask(MissionControlManager manager) {

        System.out.print("Enter Task ID to search: ");
        String taskID = scanner.nextLine().trim();

        MissionTask found = manager.findMissionTask(taskID);

        if (found != null) {
            System.out.println("\nTask Details:");
            System.out.println(found);
        }
    }

    // -------------------------------------------------------
    // Option 13 - Test Mission Timeline (custom linked list)
    // Demonstrates all required MissionTimeline operations
    // -------------------------------------------------------

    public static void handleTestTimeline(MissionTimeline timeline) {

        System.out.println("\n========= TESTING MISSION TIMELINE =========");

        try {
            // create some sample tasks for testing
            ArrayList<String> systems1 = new ArrayList<>();
            systems1.add("Navigation Computer");
            systems1.add("Orion Guidance System");

            ArrayList<String> systems2 = new ArrayList<>();
            systems2.add("Deep Space Antenna");

            ArrayList<String> systems3 = new ArrayList<>();
            systems3.add("Camera Array");
            systems3.add("Spectrometer");

            MissionTask task1 = new MissionTask("NAV-101",
                "Lunar Navigation Correction",
                "NAVIGATION", 9, "2027-05-15", 45, false, systems1);

            MissionTask task2 = new MissionTask("COM-102",
                "Deep Space Communication",
                "COMMUNICATION", 8, "2027-05-16", 30, false, systems2);

            MissionTask task3 = new MissionTask("SCI-103",
                "Lunar Surface Photography",
                "SCIENCE", 5, "2027-05-17", 60, false, systems3);

            // 1. addToStart - demonstrate adding to beginning
            System.out.println("\n--- Testing addToStart() ---");
            timeline.addToStart(task2);
            timeline.addToStart(task1);
            // timeline: NAV-101 → COM-102

            // 2. insertAtIndex - add at position 1
            System.out.println("\n--- Testing insertAtIndex() ---");
            timeline.insertAtIndex(task3, 1);
            // timeline: NAV-101 → SCI-103 → COM-102

            // 3. display the timeline
            System.out.println("\n--- Testing displayTimeline() ---");
            timeline.displayTimeline();

            // 4. contains - check if ID exists
            System.out.println("\n--- Testing contains() ---");
            System.out.println("Contains NAV-101: " + timeline.contains("NAV-101"));
            System.out.println("Contains XYZ-999: " + timeline.contains("XYZ-999"));

            // 5. find - search by ID and count iterations
            System.out.println("\n--- Testing find() ---");
            MissionTask found = timeline.find("COM-102");
            if (found != null) {
                System.out.println("Found task details:");
                System.out.println(found);
            }

            // 6. replaceAtIndex - replace task at position 1
            ArrayList<String> systems4 = new ArrayList<>();
            systems4.add("Radiation Sensor");

            MissionTask task4 = new MissionTask("RAD-104",
                "Radiation Measurement",
                "SCIENCE", 6, "2027-05-18", 90, false, systems4);

            System.out.println("\n--- Testing replaceAtIndex() ---");
            timeline.replaceAtIndex(task4, 1);
            timeline.displayTimeline();

            // 7. deleteFromIndex - remove at position 0
            System.out.println("\n--- Testing deleteFromIndex() ---");
            timeline.deleteFromIndex(0);
            timeline.displayTimeline();

            // 8. equals - compare two timelines
            System.out.println("\n--- Testing equals() ---");
            MissionTimeline timeline2 = new MissionTimeline();
            timeline2.addToStart(task2);
            System.out.println("Are timelines equal: " +
                               timeline.equals(timeline2));

            // 9. getSize
            System.out.println("\n--- Testing getSize() ---");
            System.out.println("Timeline size: " + timeline.getSize());

            // 10. deleteFromStart
            System.out.println("\n--- Testing deleteFromStart() ---");
            timeline.deleteFromStart();
            timeline.displayTimeline();

        } catch (InvalidMissionTaskException e) {
            System.out.println("Task error: " + e.getMessage());

        } catch (NoSuchElementException e) {
            System.out.println("Timeline error: " + e.getMessage());
        }

        System.out.println("\n========= TIMELINE TEST COMPLETE =========");
    }

} // end of ArtemisMissionControlMain