// Assignment 3
// Written by: MIHIR VIPULBHAI PANCHAL [40366948]

import java.util.HashMap;

/**
 * ProductTree manages the complete product assembly
 * 
 * It holds the root node of the tree and search the tree and calculate raw material requirements for manufacturing
 *
 * The tree structure looks like
 * 
 * SCOOTER needs 
 * 
 * MOTOR -> ROTOR, STATOR
 * 
 * BATTERY -> CELL, CONTROLLER
 * 
 * FRAME 
 *
 * @author MIHIR VIPULBHAI PANCHAL
 */

public class ProductTree 
{

    // root node represents the Electric Scooter
    private ComponentNode root;

    /**
     * Constructor that sets the root of the assembly tree
     *
     * @param root the root ComponentNode representing the Electric Scooter
     */

    public ProductTree(ComponentNode root) 
    {
        this.root = root;
    }

    /**
     * Recursively searches the assembly tree for a component with the specified ID
     * Returns the matching node or null
     *
     * @param id   the component ID to search for
     * @param node the current node being examined
     * @return the matching ComponentNode or null 
     */

    public ComponentNode findComponent(String id, ComponentNode node) 
    {
        if (node == null) 
        {
            return null;
        }

        // check current node
        if (node.getId().equalsIgnoreCase(id)) 
        {
            return node;
        }

        // search each child
        for (ComponentNode child : node.getChildren()) 
        {
            ComponentNode found = findComponent(id, child);

            if (found != null) 
            {
                return found;
            }
        }

        // when not found in this branch
        return null;
    }

    /**
     * Calculates the total quantity of each raw material needed to manufacture the requested number of scooters
     * Uses recursive tree traversal via collectMaterials()
     *
     * @param quantity number of scooters to manufacture
     * @return HashMap with raw material ID as key
     */

    public HashMap<String, Integer> calculateRawMaterials(int quantity) 
    {
        HashMap<String, Integer> requirements = new HashMap<>();

        // start recursion at root with quantity as initial multiplier
        collectMaterials(root, quantity, requirements);

        return requirements;
    }

    /**
     * Recursive helper method that traverses the assembly tree and accumulates raw material quantities in the requirements map
     *
     * Logic:
     * 
     * If a node is a raw material then no children, add multiplier to map
     * If a node is an assembly, recurse into each child with an updated multiplier = current multiplier x child.quantityRequired
     *
     * This handles multi-level trees correctly
     * 
     * For example
     * 
     * 1 SCOOTER needs 1 BATTERY which needs 6 CELL
     * So 3 SCOOTERS need 3 x 1 x 6 = 18 CELL
     *
     * @param node         current node being processed
     * @param multiplier   total units of this node needed so far
     * @param requirements map accumulating raw material totals
     */

    public void collectMaterials(ComponentNode node, int multiplier, HashMap<String, Integer> requirements) 
    {
        // base case - node is a raw material, add to requirements
        if (node.isRawMaterial()) 
        {
            requirements.merge(node.getId(), multiplier, Integer::sum);
            return;
        }

        // assembly node - recurse into each child
        // multiply current requirement by child's quantityRequired

        for (ComponentNode child : node.getChildren()) 
        {
            collectMaterials(child, multiplier * child.getQuantityRequired(), requirements);
        }
    }

    /**
     * Displays the complete product assembly hierarchy starting from the root node
     * Shows all components with their IDs and quantities required
     */

    public void displayAssemblyTree() 
    {
        System.out.println("\n===== PRODUCT ASSEMBLY TREE =====");
        root.displayTree(0);
        System.out.println("==================================");
    }

    /**
     * Returns the root node of the assembly tree.
     *
     * @return root ComponentNode representing the finished product
     */

    public ComponentNode getRoot() 
    {
        return root;
    }
}