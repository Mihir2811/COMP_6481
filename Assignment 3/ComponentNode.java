// Assignment 3
// Written by: MIHIR VIPULBHAI PANCHAL [40366948]

import java.util.ArrayList;

/**
 * ComponentNode represents a single node component for product tree hierarchy
 * 
 * Nodes with no children are raw materials
 * 
 * Nodes with children are assemblies from those children
 *
 * @author MIHIR VIPULBHAI PANCHAL
 */

public class ComponentNode 
{

    // Uid
    private String id;

    // component name
    private String name;

    // required components
    private int quantityRequired;

    // node parent in the tree
    private ComponentNode parent;

    // sub components required to build
    private ArrayList<ComponentNode> children;


    /**
     * Parameterized constructor
     *
     * @param id                     unique component identifier
     * @param name                   component name
     * @param quantityRequired       required components
     */


    public ComponentNode(String id, String name, int quantityRequired) 
    {
        this.id = id;
        this.name = name;
        this.quantityRequired = quantityRequired;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    /**
     * This class adds a child component to this node and sets the parent reference back to this node
     *
     * @param child the ComponentNode to add as a child
     * 
     */

    public void addChild(ComponentNode child) 
    {
        child.parent = this;
        children.add(child);
    }

    /**
     * Returns true if this component is a raw material
     * 
     * A raw material is a node with no children in the tree
     * 
     * Assemblies always have at least one child
     *
     * @return true if this node has no children or false 
     */


    public boolean isRawMaterial() 
    {
        return children.isEmpty();
    }

    /**
     * Displays this component and all its sub components

     * Raw materials are labeled [RAW MATERIAL]

     * Assemblies are labeled [ASSEMBLY]
     *
     * @param depth current depth level in the tree from 0 to N
     */

    public void displayTree(int depth) 
    {
        // Create spaces based on the current level
        String indent = "    ".repeat(depth);

        // Add a branch symbol if it is not the main component
        String prefix;

        if (depth == 0)
        {
            prefix = "";
        }

        else
        {
            prefix = "+-- ";
        }

        // Check if the component is raw material or an assembly
        String typeLabel;

        if (isRawMaterial())
        {
            typeLabel = " [RAW MATERIAL]";
        }
        else
        {
            typeLabel = " [ASSEMBLY]";
        }

        // Print the component details
        System.out.println(indent + prefix + name + " [" + id + "] x" + quantityRequired + typeLabel);

        // Go through each child and display it at the next level
        for (ComponentNode child : children) 
        {
            child.displayTree(depth + 1);
        }
    }

    /**
     * Returns a readable string representating component including its ID, name, quantity required, and type
     *
     * @return formatted string with component details
     */

    public String toString() 
    {
        return "ID: " + id + " | Name: " + name + " | Quantity Required: " + quantityRequired + " | Type: " + (isRawMaterial() ? "Raw Material" : "Assembly");
    }

    // Getters

    /**
     * Returns the unique component identifier
     *
     * @return component ID as string 
     */

    public String getId() 
    {
        return id;
    }

    /**
     * Returns the component name
     *
     * @return component name string
     */

    public String getName() 
    {
        return name;
    }

    /**
     * Returns how many units of component are required
     *
     * @return quantity required by parent
     */

    public int getQuantityRequired() 
    {
        return quantityRequired;
    }

    /**
     * Returns the parent node of this component
     *
     * @return parent ComponentNode or null if this is the root
     */
    
    public ComponentNode getParent() 
    {
        return parent;
    }

    /**
     * Returns the list of child components
     *
     * @return ArrayList of child ComponentNode objects
     */

    public ArrayList<ComponentNode> getChildren() 
    {
        return children;
    }

    // Setters

    /**
     * Sets the parent reference for this component
     *
     * @param parent the parent ComponentNode
     */

    public void setParent(ComponentNode parent) 
    {
        this.parent = parent;
    }
}