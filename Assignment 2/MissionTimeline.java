// Assignment 2
// Written by: Mihir Vipulbhai Panchal (ID:40366948)

/*
 * MissionTimeline is a custom linked list that stores
 * MissionTask objects in a chain of nodes.
 * Each node holds one task and a reference to the next node.
 * This does NOT use Java's built-in LinkedList.
 */

import java.util.NoSuchElementException;

public class MissionTimeline {

    // -------------------------------------------------------
    // Inner Class - MissionTaskNode
    // Each node is one link in the chain
    // -------------------------------------------------------

    public class MissionTaskNode {

        // What the node holds
        private MissionTask task;
        private MissionTaskNode next;

        // Default constructor - empty node
        public MissionTaskNode() {
            this.task = null;
            this.next = null;
        }

        // Parameterized constructor - node with a task
        public MissionTaskNode(MissionTask task) {
            this.task = task;
            this.next = null;
        }

        // Copy constructor - copies a node
        // Deep copies the task inside it
        public MissionTaskNode(MissionTaskNode original) {
            if (original.task != null) {
                this.task = original.task.clone();
            } else {
                this.task = null;
            }
            this.next = null; // next is NOT copied - caller handles chain
        }

        // clone() - returns independent copy of this node
        public MissionTaskNode clone() {
            return new MissionTaskNode(this);
        }

        // Getters
        public MissionTask getTask() {
            return task;
        }

        public MissionTaskNode getNext() {
            return next;
        }

        // Setters
        public void setTask(MissionTask task) {
            this.task = task;
        }

        public void setNext(MissionTaskNode next) {
            this.next = next;
        }

    } // end of MissionTaskNode inner class
    
    // -------------------------------------------------------
    // MissionTimeline attributes
    // -------------------------------------------------------

    private MissionTaskNode head;  // first node in the chain
    private int size;              // how many nodes exist

    // Constructor - starts as empty timeline
    public MissionTimeline() {
        this.head = null;
        this.size = 0;
    }
    
    /*
     * Adds a mission task to the beginning of the timeline.
     * The new node becomes the new head.
     */
    public void addToStart(MissionTask task) {

        MissionTaskNode newNode = new MissionTaskNode(task);

        // new node points to current head
        newNode.setNext(head);

        // new node becomes the new head
        head = newNode;

        size++;

        System.out.println("Mission task " + task.getTaskID() +
                           " added to the start of the timeline.");
    }
    
    /*
     * Inserts a mission task at the specified index.
     * Index 0 means the beginning.
     * Throws NoSuchElementException if index is out of range.
     */
    public void insertAtIndex(MissionTask task, int index) {

        // check if index is valid
        if (index < 0 || index > size) {
            throw new NoSuchElementException("Error: Invalid index " + index +
                                             ". Timeline size is " + size + ".");
        }

        // inserting at the start - use addToStart
        if (index == 0) {
            addToStart(task);
            return;
        }

        // create the new node
        MissionTaskNode newNode = new MissionTaskNode(task);

        // walk to the node just BEFORE where we want to insert
        MissionTaskNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        // new node points to what was after current
        newNode.setNext(current.getNext());

        // current now points to new node
        current.setNext(newNode);

        size++;

        System.out.println("Mission task " + task.getTaskID() +
                           " inserted at index " + index + ".");
    }
    
    /*
     * Removes the first mission task from the timeline.
     * Updates head to point to the second node.
     */
    public void deleteFromStart() {

        // nothing to delete
        if (head == null) {
            System.out.println("Timeline is empty. Nothing to delete.");
            return;
        }

        System.out.println("Removing task " + head.getTask().getTaskID() +
                           " from the start of the timeline.");

        // move head forward to next node
        head = head.getNext();

        size--;
    }
    
    /*
     * Deletes the mission task at the specified index.
     * Throws NoSuchElementException if index is invalid.
     */
    public void deleteFromIndex(int index) {

        // check if index is valid
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Error: Invalid index " + index +
                                             ". Timeline size is " + size + ".");
        }

        // deleting the first node
        if (index == 0) {
            deleteFromStart();
            return;
        }

        // walk to the node just BEFORE the one to delete
        MissionTaskNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        // skip over the node to delete
        // before: current → [target] → [next]
        // after:  current → [next]
        System.out.println("Removing task " +
                           current.getNext().getTask().getTaskID() +
                           " from index " + index + ".");

        current.setNext(current.getNext().getNext());

        size--;
    }
    
    /*
     * Replaces the mission task at the specified index
     * with the new task provided.
     */
    public void replaceAtIndex(MissionTask task, int index) {

        // check if index is valid
        if (index < 0 || index >= size) {
            throw new NoSuchElementException("Error: Invalid index " + index +
                                             ". Timeline size is " + size + ".");
        }

        // walk to the target node
        MissionTaskNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        System.out.println("Replacing task " +
                           current.getTask().getTaskID() +
                           " at index " + index +
                           " with " + task.getTaskID() + ".");

        // replace the task stored in that node
        current.setTask(task);
    }
    
    /*
     * Searches for a mission task by its ID.
     * Counts how many nodes were checked during the search.
     * Returns a deep copy of the found task.
     * Displays how many iterations were needed.
     */
    public MissionTask find(String taskID) {

        MissionTaskNode current = head;
        int iterations = 0;

        while (current != null) {
            iterations++;

            if (current.getTask().getTaskID().equalsIgnoreCase(taskID)) {
                System.out.println("Mission task " + taskID +
                                   " found after " + iterations + " iteration(s).");
                // return deep copy - not the original
                return current.getTask().clone();
            }

            current = current.getNext();
        }

        // not found
        System.out.println("Mission task " + taskID +
                           " not found in the timeline after " +
                           iterations + " iteration(s).");
        return null;
    }
    
    /*
     * Returns true if a task with the given ID exists
     * in the timeline. False otherwise.
     */
    public boolean contains(String taskID) {

        MissionTaskNode current = head;

        while (current != null) {
            if (current.getTask().getTaskID().equalsIgnoreCase(taskID)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }
    
    /*
     * Two timelines are equal if they contain the same
     * mission tasks in the same order.
     * Uses MissionTask equals() for each comparison.
     */
    public boolean equals(MissionTimeline other) {

        // different sizes means not equal
        if (this.size != other.size) {
            return false;
        }

        MissionTaskNode thisCurrent = this.head;
        MissionTaskNode otherCurrent = other.head;

        // walk both lists together comparing each node
        while (thisCurrent != null && otherCurrent != null) {

            if (!thisCurrent.getTask().equals(otherCurrent.getTask())) {
                return false;
            }

            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }

        return true;
    }
    
    /*
     * Returns the number of tasks in the timeline.
     */
    public int getSize() {
        return size;
    }

    /*
     * Displays all mission tasks in order from head to end.
     */
    public void displayTimeline() {

        if (head == null) {
            System.out.println("Timeline is empty.");
            return;
        }

        System.out.println("\n========= MISSION TIMELINE =========");

        MissionTaskNode current = head;
        int index = 0;

        while (current != null) {
            System.out.println("\n--- Position " + index + " ---");
            System.out.println(current.getTask());
            current = current.getNext();
            index++;
        }

        System.out.println("====================================");
        System.out.println("Total tasks in timeline: " + size);
    }

} // end of MissionTimeline class