// Assignment 3
// Written by: MIHIR VIPULBHAI PANCHAL [40366948]

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * FactoryManager manages all inventory, manufacturing operations, and production
 * It implements the Manufacturable interface also
 *
 * Maintains raw material inventory in a HashMap
 * Validates and processes manufacturing requests atomically
 * Tracks total production statistics
 * Generates inventory and production reports
 *
 * rules
 * ALL material checks must pass before ANY inventory is deducted
 * If any material is insufficient, inventory remains the same
 * 
 *
 * @author MIHIR VIPULBHAI PANCHAL [40366948]
 */

public class FactoryManager implements Manufacturable 
{
    // the complete product assembly tree
    private ProductTree productTree;

    // HashMap storing current raw material quantities
    // Key = material ID and Value = quantity available
    private HashMap<String, Integer> inventory;

    // total number of scooters manufactured
    private int totalProductsBuilt;

    // total individual raw material units consumed
    private int totalRawMaterialsUsed;

    /**
     * Constructor that initializes the factory with a product tree and starting inventory at zero.
     *
     * @param productTree the complete product assembly hierarchy
     * @param inventory   initial raw material quantities
     */

    public FactoryManager(ProductTree productTree, HashMap<String, Integer> inventory) 
    {
        this.productTree = productTree;
        this.inventory = inventory;
        this.totalProductsBuilt = 0;
        this.totalRawMaterialsUsed = 0;
    }

    /**
     * Adds the specified quantity to an existing raw material in the inventory
     * The material must exist in the assembly tree and must be a raw material and not an assembly
     *
     * @param materialId the ID of the raw material to replenish
     * @param quantity   the amount to add 
     * @throws InvalidMaterialException if ID is unknown, is an assembly, or quantity is not positive
    */
    public void addStock(String materialId, int quantity)

    throws InvalidMaterialException 
    {
        // quantity must be positive
        if (quantity <= 0) 
        {
            throw new InvalidMaterialException("Quantity must be a positive number");
        }

        String upperID = materialId.toUpperCase();

        // find component in tree
        ComponentNode component = productTree.findComponent(upperID, productTree.getRoot());

        if (component == null) 
        {
            throw new InvalidMaterialException("Material ID '" + upperID + "' does not exist in the assembly tree");
        }

        // must be a raw material, not an assembly
        if (!component.isRawMaterial()) 
        {
            throw new InvalidMaterialException("'" + upperID + "' is an assembly component not a raw material " + "Only raw materials can have stock added");
        }

        // update inventory
        int current = inventory.getOrDefault(upperID, 0);
        inventory.put(upperID, current + quantity);

        System.out.println("Stock updated success!");
        System.out.println(upperID + " new quantity: " + inventory.get(upperID));
    }

    /**
     * Determines whether the requested quantity of scooters can be manufactured with the current inventory
     *
     * @param quantity number of scooters to check
     * @return true if all required materials are available or false otherwise
     */

    public boolean canManufacture(int quantity) 
    {
        if (quantity <= 0) 
        {
            return false;
        }

        // if no materials are missing, manufacturing is possible
        HashMap<String, Integer> missing = getMissingMaterials(quantity);
        return missing.isEmpty();
    }

    /**
     * Returns a map of materials that are insufficient
     * 
     * Only materials with a shortfall appear in the returned map
     * Materials with sufficient stock are not included
     *
     * @param quantity number of scooters requested
     * @return HashMap with material ID as key, extra required amount as value
     */

    public HashMap<String, Integer> getMissingMaterials(int quantity) 
    {
        // calculate what is needed
        HashMap<String, Integer> required = productTree.calculateRawMaterials(quantity);

        HashMap<String, Integer> missing = new HashMap<>();

        // compare each requirement against current inventory
        for (Map.Entry<String, Integer> entry : required.entrySet()) 
        {
            String id = entry.getKey();
            int needed = entry.getValue();
            int available = inventory.getOrDefault(id, 0);

            if (available < needed) 
            {
                // shortfall = how many more units are needed
                missing.put(id, needed - available);
            }
        }

        return missing;
    }

    /**
     * Manufactures the requested number of scooters
     *
     * Steps
     * 
     * 1. Validate the requested quantity
     * 
     * 2. Calculate all raw material requirements
     * 
     * 3. Check whether EVERY required material is available
     * 
     * 4. If any material is insufficient, throw exception - no inventory change
     * 
     * 5. If all available, deduct from inventory
     * 
     * 6. Update production counters
     *
     * @param quantity number of scooters to manufacture
     * @throws InsufficientMaterialsException if any material is insufficient
     */

    public void manufacture(int quantity)
    throws InsufficientMaterialsException 
    {
        // step 1 - validate quantity
        if (quantity <= 0) 
        {
            throw new InsufficientMaterialsException("Quantity must be a positive number");
        }

        // step 2 - calculate all requirements
        HashMap<String, Integer> required = productTree.calculateRawMaterials(quantity);

        // step 3 - check ALL materials
        HashMap<String, Integer> missing = getMissingMaterials(quantity);

        // step 4 - if anything missing throwing exception
        if (!missing.isEmpty()) 
        {
            throw new InsufficientMaterialsException("Insufficient materials to manufacture " + quantity + " Electric Scooters");
        }

        // step 5 - all materials confirmed available
        int totalUsed = 0;

        for (Map.Entry<String, Integer> entry : required.entrySet()) 
        {
            String id = entry.getKey();
            int needed = entry.getValue();
            inventory.put(id, inventory.get(id) - needed);
            totalUsed += needed;
        }

        // step 6 - update production counters
        totalProductsBuilt += quantity;
        totalRawMaterialsUsed += totalUsed;

        System.out.println("Success! " + quantity + " Electric Scooters manufactured");

        System.out.println("Total scooters manufactured: " + totalProductsBuilt);
    }


    /**
     * Displays all raw materials and their current quantities sorted alphabetically by material ID
     * Materials with zero stock are also displayed
     */

    public void displayInventory() 
    {
        System.out.println("\nCurrent Inventory:");
        System.out.println("------------------------------");

        // TreeMap automatically sorts keys alphabetically
        TreeMap<String, Integer> sorted = new TreeMap<>(inventory);

        for (Map.Entry<String, Integer> entry : sorted.entrySet()) 
        {
            System.out.printf("%-15s %d%n", entry.getKey(), entry.getValue());
        }

        System.out.println("------------------------------");
    }

    /**
     * Displays the materials needed to fulfill a production request that cannot currently be completed
     * Shows material ID, name, and shortfall quantity
     * If no materials are missing, displays an appropriate message
     *
     * @param quantity number of scooters requested
     */

    public void displayMissingMaterials(int quantity) 
    {
        HashMap<String, Integer> missing = getMissingMaterials(quantity);

        if (missing.isEmpty()) 
        {
            System.out.println("Sufficient materials are available " + "to manufacture " + quantity + " scooters");
            return;
        }

        System.out.println("Missing materials:");

        // sort alphabetically before displaying
        TreeMap<String, Integer> sorted = new TreeMap<>(missing);

        for (Map.Entry<String, Integer> entry : sorted.entrySet()) 
        {
            // Find the component in the tree using its ID
            ComponentNode node = productTree.findComponent(entry.getKey(), productTree.getRoot());

            // Get the component name if it exists
            String name;

            if (node != null)
            {
                name = node.getName();
            }
            else
            {
                name = entry.getKey();
            }

            // Display the component ID, name and required quantity
            System.out.println(entry.getKey() + " (" + name + "): " + entry.getValue() + " units");
        }
    }

    /**
     * Generates a full production summary report showing
     * 
     * Total scooters manufactured
     * Total raw material units consumed
     * Number of distinct raw materials currently out of stock
     * Current inventory for all raw materials
     */

    public void summaryReport() {

        System.out.println("\n=====================================");
        System.out.println("         PRODUCTION SUMMARY");
        System.out.println("=====================================");

        System.out.println("Finished Product: Electric Scooter");

        System.out.printf("Total Scooters Manufactured : %d%n", totalProductsBuilt);

        System.out.printf("Total Raw Material Units Used: %d%n",totalRawMaterialsUsed);

        // count how many raw materials have zero stock
        int outOfStock = 0;
        for (int qty : inventory.values()) 
        {
            if (qty == 0) 
            {
                outOfStock++;
            }
        }

        System.out.printf("Materials Out of Stock : %d%n", outOfStock);

        // display current inventory sorted alphabetically
        displayInventory();

        System.out.println("=====================================");
    }


    // Getters


    /**
     * Returns the product assembly tree
     *
     * @return the ProductTree instance
     */

    public ProductTree getProductTree() 
    {
        return productTree;
    }

    /**
     * Returns the current inventory HashMap
     *
     * @return HashMap of raw material IDs to quantities
     */

    public HashMap<String, Integer> getInventory() 
    {
        return inventory;
    }

    /**
     * Returns the total number of scooters built so far
     *
     * @return total products built
     */

    public int getTotalProductsBuilt()
    {
        return totalProductsBuilt;
    }

    /**
     * Returns the total number of raw material units consumed
     *
     * @return total raw materials used
     */

    public int getTotalRawMaterialsUsed() 
    {
        return totalRawMaterialsUsed;
    }
}