// Assignment 2

// Written by: Mihir Vipulbhai Panchal (ID:40366948)

/*
 * MissionTimeline is a linked list that stores MissionTask objects in a chain of nodes.
 * Each node will hold one task and a reference to the next node.
 */

import java.util.NoSuchElementException;

public class MissionTimeline {

    // the Inner Class

    public class MissionTaskNode {

        private MissionTask task;
        private MissionTaskNode next;


        // Default constructor
        public MissionTaskNode() 
        {
            this.task = null;
            this.next = null;
        }


        // Parameterized constructor
        public MissionTaskNode(MissionTask task) 
        {
            this.task = task;
            this.next = null;
        }


        // Copy constructor 
        public MissionTaskNode(MissionTaskNode original) 
        {

            if (original.task != null) 
            {
                this.task = original.task.clone();
            } 
            
            else 
            {
                this.task = null;
            }

            this.next = null; 
        }


        // clone method
        public MissionTaskNode clone() 
        {
            return new MissionTaskNode(this);
        }


        // Getter methods
        public MissionTask getTask() 
        {
            return task;
        }

        public MissionTaskNode getNext() 
        {
            return next;
        }


        // Setter methods
        public void setTask(MissionTask task) 
        {
            this.task = task;
        }

        public void setNext(MissionTaskNode next) 
        {
            this.next = next;
        }

    } 
    
    // end of inner class


    // MissionTimeline attributes
    private MissionTaskNode head;  
    private int size;          

    // empty constructor
    public MissionTimeline() 
    {
        this.head = null;
        this.size = 0;
    }
    
    
    // Adds a mission task to the beginning of the timeline.
    public void addToStart(MissionTask task) 
    {
        MissionTaskNode newNode = new MissionTaskNode(task);

        newNode.setNext(head);
        head = newNode;
        size = size + 1;

        System.out.println("Mission task " + task.getTaskID() + " is added to the start of the timeline");
    }
    

    // Inserts a mission task at the specified index
    public void insertAtIndex(MissionTask task, int index) 
    {
        if (index < 0 || index > size) 
        {
            throw new NoSuchElementException("Error Invalid index " + index + " Timeline size is " + size);
        }

        if (index == 0) 
        {
            addToStart(task);
            return;
        }

        MissionTaskNode newNode = new MissionTaskNode(task);

        MissionTaskNode current = head;

        for (int i = 0; i < index - 1; i++) 
        {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());

        current.setNext(newNode);

        size = size + 1;

        System.out.println("Mission task " + task.getTaskID() + " is inserted at index " + index);
    }
    


    // Removes the first mission task from the timeline.
     
    public void deleteFromStart() 
    {

        if (head == null) 
        {
            System.out.println("Timeline is empty");
            return;
        }

        System.out.println("Removing task " + head.getTask().getTaskID() + " from the start of the timeline.");

        head = head.getNext();
        size = size - 1;
    }
    


     //Deletes the mission task at the specified index
    public void deleteFromIndex(int index) {

        if (index < 0 || index >= size) 
        {
            throw new NoSuchElementException("Error Invalid index " + index + " Timeline size is " + size);
        }

        if (index == 0) 
        {
            deleteFromStart();
            return;
        }

        MissionTaskNode current = head;

        for (int i = 0; i < index - 1; i++) 
        {
            current = current.getNext();
        }

        // skiping over the node to delete
        // before: current to target to next
        // after:  current to next

        System.out.println("Removing task " + current.getNext().getTask().getTaskID() + " from index " + index);

        current.setNext(current.getNext().getNext());

        size = size - 1;
    }
    


    // Replaces the mission task at the specified index
    public void replaceAtIndex(MissionTask task, int index) 
    {
        if (index < 0 || index >= size) 
        {
            throw new NoSuchElementException("Error Invalid index " + index + " Timeline size is " + size);
        }

        MissionTaskNode current = head;
        
        for (int i = 0; i < index; i++) 
        {
            current = current.getNext();
        }

        System.out.println("Replacing task " + current.getTask().getTaskID() + " at index " + index + " with " + task.getTaskID());
        current.setTask(task);
    }
    


    /*
     * This method Searches for a mission task by its ID
     * Counts how many nodes were checked during the search.
     * Returns a deep copy of the found task.
     * Displays how many iterations were needed.
     */

    public MissionTask find(String taskID) {

        MissionTaskNode current = head;
        int iterations = 0;

        while (current != null) 
        {
            iterations = iterations + 1;

            if (current.getTask().getTaskID().equalsIgnoreCase(taskID)) 
            {
                System.out.println("Mission task " + taskID + " found after " + iterations + " iterations");
                return current.getTask().clone();
            }

            current = current.getNext();
        }

        System.out.println("Mission task " + taskID + " not found in the timeline after " + iterations + " iterations");
        return null;
    }
    


    // Returns true if a task with the given ID exists False otherwise.
    public boolean contains(String taskID) {

        MissionTaskNode current = head;

        while (current != null) 
        {
            if (current.getTask().getTaskID().equalsIgnoreCase(taskID)) 
            {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }
    


    // Two timelines are equal if they contain the same mission tasks in the same order
    public boolean equals(MissionTimeline other) {

        if (this.size != other.size) 
        {
            return false;
        }

        MissionTaskNode thisCurrent = this.head;
        MissionTaskNode otherCurrent = other.head;


        while (thisCurrent != null && otherCurrent != null) 
        {
            if (!thisCurrent.getTask().equals(otherCurrent.getTask())) 
            {
                return false;
            }

            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }
        return true;
    }
    


    // Returns the number of tasks
    public int getSize() 
    {
        return size;
    }



    // Displays all mission tasks in order
    public void displayTimeline() 
    {
        if (head == null) 
        {
            System.out.println("Timeline is empty");
            return;
        }

        System.out.println("\n--------- MISSION TIMELINE ---------");

        MissionTaskNode current = head;
        
        int index = 0;

        while (current != null) 
        {
            System.out.println("\n--- Position " + index + " ---");
            System.out.println(current.getTask());

            current = current.getNext();
            index = index + 1;
        }

        System.out.println("---------------------------------------------");
        System.out.println("Total tasks in timeline " + size);
    }

}