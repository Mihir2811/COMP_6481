// Assignment 3
// Written by: MIHIR VIPULBHAI PANCHAL [40366948]

import java.util.HashMap;
import java.util.Scanner;

/**
 * FactoryMain is the main driver class 
 * This class builds the tree assembly, inventory and provides console menu interface for all operations. 
 *
 * Tree: (To build an electric scooter what we actually need)
 * Electric Scooter [SCOOTER]
 *   Motor Assembly [MOTOR] x1
 *     Rotor [ROTOR] x1
 *     Stator [STATOR] x2
 *   Battery Assembly [BATTERY] x1
 *     Battery Cell [CELL] x6
 *     Battery Controller [CONTROLLER] x1
 *   Frame [FRAME] x1
 *
 * Starting Inventory:
 * ROTOR=2, STATOR=4, CELL=10, CONTROLLER=2, FRAME=2
 *
 * @author MIHIR VIPULBHAI PANCHAL
 */

public class FactoryMain 
{
    static Scanner scanner = new Scanner(System.in);

    /**
     * Main entry point
     * Builds the tree, initializes inventory, and runs the main menu loop until user exits
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) 
    {

        System.out.println("Welcome to Smart Factory Assembly Manager");

//                                                /\      /\
//                                                ||______||
//                                                || ^  ^ ||
//                                                \| |  | |/
//                                                 |______|
//               __                                |  __  |
//              /  \       ________________________|_/  \_|__
//             / ^^ \     /=========================/ ^^ \===|
//            /  []  \   /=========================/  []  \==|
//           /________\ /=========================/________\=|
//        *  |        |/==========================|        |=|
//       *** | ^^  ^^ |---------------------------| ^^  ^^ |--
//      *****| []  [] |           _____           | []  [] | |
//     *******        |          /_____\          |      * | |
//    *********^^  ^^ |  ^^  ^^  |  |  |  ^^  ^^  |     ***| |
//   ***********]  [] |  []  []  |  |  |  []  []  | ===***** |
//  *************     |         @|__|__|@         |/ |*******|
// ***************   ***********--=====--**********| *********
// ***************___*********** |=====| **********|***********
//  *************     ********* /=======\ ******** | *********

        System.out.println(
        "         _\n" +
        "       D/_\n" +
        "       /(_`._,-.\n" +
        "_____(o) `--'(o)______"
        );

        System.out.println();

        // Build the assembly tree

        // root - finished product
        ComponentNode scooter = new ComponentNode("SCOOTER", "Electric Scooter", 1);

        // motor assembly and its raw materials
        ComponentNode motor = new ComponentNode("MOTOR", "Motor Assembly", 1);
        
        ComponentNode rotor = new ComponentNode("ROTOR", "Rotor", 1);
        
        ComponentNode stator = new ComponentNode("STATOR", "Stator", 2);
        
        motor.addChild(rotor);
        motor.addChild(stator);

        // battery assembly and its raw materials
        ComponentNode battery = new ComponentNode("BATTERY", "Battery Assembly", 1);
        
        ComponentNode cell = new ComponentNode("CELL", "Battery Cell", 6);
        
        ComponentNode controller = new ComponentNode("CONTROLLER", "Battery Controller", 1);
        
        battery.addChild(cell);
        battery.addChild(controller);

        // frame is a raw material directly on scooter
        ComponentNode frame = new ComponentNode("FRAME", "Frame", 1);

        // attach to root
        scooter.addChild(motor);
        scooter.addChild(battery);
        scooter.addChild(frame);

        ProductTree productTree = new ProductTree(scooter);


        // Initialize inventory with starting quantities

        HashMap<String, Integer> inventory = new HashMap<>();

        inventory.put("ROTOR", 2);
        inventory.put("STATOR", 4);
        inventory.put("CELL", 10);
        inventory.put("CONTROLLER", 2);
        inventory.put("FRAME", 2);

        FactoryManager manager = new FactoryManager(productTree, inventory);

        System.out.println("Product loaded: Electric Scooter");
        System.out.println("Factory inventory initialized successfully.");


        // Main menu loop
        // ---------------------

        int choice = 0;

        while (choice != 8) 
        {

            choice = showMainMenu();

            if (choice == 1) 
            {
                // view assembly tree
                productTree.displayAssemblyTree();

            } 
            
            else if (choice == 2) 
            {
                // add raw material stock
                handleAddStock(manager);

            } 
            
            else if (choice == 3) 
            {
                // check manufacturing feasibility
                handleCheckFeasibility(manager);

            } 
            
            else if (choice == 4) 
            {
                // manufacture scooters
                handleManufacture(manager);

            } 
            
            else if (choice == 5) 
            {
                // view inventory
                manager.displayInventory();

            } 
            
            else if (choice == 6) 
            {
                // view missing materials
                handleMissingMaterials(manager);

            } 
            
            else if (choice == 7) 
            {
                // production summary
                manager.summaryReport();

            } 
            
            else if (choice == 8) 
            {
                // exit
                System.out.println("\nExiting Smart Factory Assembly Manager");
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu and returns a valid choice between 1 and 8
     * Loops until valid input is entered
     *
     * @return validated menu choice
     */

    public static int showMainMenu() 
    {
        int choice = 0;

        while (choice < 1 || choice > 8)
        {

            System.out.println("\n=====================================");
            System.out.println("  SMART FACTORY ASSEMBLY MANAGER");
            System.out.println("=====================================");
            System.out.println();
            System.out.println("1. View Product Assembly Tree");
            System.out.println("2. Add Raw Material Stock");
            System.out.println("3. Check Manufacturing Feasibility");
            System.out.println("4. Manufacture Electric Scooter");
            System.out.println("5. View Inventory");
            System.out.println("6. View Missing Materials");
            System.out.println("7. Production Summary");
            System.out.println("8. Exit");
            System.out.println();
            System.out.println("=====================================");
            System.out.print("Enter your choice: ");

            try 
            {
                choice = Integer.parseInt(scanner.nextLine().trim());

                if (choice < 1 || choice > 8) 
                {
                    System.out.println("Please enter a number between 1 and 8");
                }
            } 
            
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input Please enter a number");
            }
        }

        return choice;
    }

    /**
     * Handles Option 2 - Add Raw Material Stock
     * Prompts for material ID and quantity, validates input, and calls addStock on the manager
     * Accepts lowercase or uppercase material IDs also!!
     *
     * @param manager the FactoryManager instance
     */

    public static void handleAddStock(FactoryManager manager) 
    {
        try 
        {
            System.out.print("Enter raw material ID: ");

            String id = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter quantity to add: ");

            int quantity = Integer.parseInt(scanner.nextLine().trim());

            if (quantity <= 0) 
            {
                System.out.println("Error Quantity must be a positive number");
                return;
            }

            manager.addStock(id, quantity);

        } 
        
        catch (NumberFormatException e) 
        {
            System.out.println("Error Please enter a valid number for quantity");
        } 
        
        catch (InvalidMaterialException e) 
        {
            System.out.println("Error " + e.getMessage());
        }
    }

    /**
     * Handles Option 3 - Check Manufacturing Feasibility
     * 
     * Prompts for quantity and checks if manufacturing is possible
     * Displays missing materials if not feasible.
     *
     * @param manager the FactoryManager instance
     */

    public static void handleCheckFeasibility(FactoryManager manager) 
    {

        try 
        {
            System.out.print("Enter number of scooters: ");

            int quantity = Integer.parseInt(scanner.nextLine().trim());

            if (quantity <= 0) 
            {
                System.out.println("Error Quantity must be a positive number");
                return;
            }

            if (manager.canManufacture(quantity)) 
            {
                System.out.println("Manufacturing is POSSIBLE for " + quantity + " scooters");

            } 
            
            else 
            {
                System.out.println("Manufacturing is NOT possible");
                manager.displayMissingMaterials(quantity);
            }

        } 
        
        catch (NumberFormatException e) 
        {
            System.out.println("Error Please enter a valid number");
        }
    }

    /**
     * Handles Option 4 - Manufacture Electric Scooter
     * Prompts for quantity, attempts manufacturing
     * On success, inventory is updated and totals are increased
     * On failure, displays error and missing materials
     * Inventory remains completely unchanged on failure
     *
     * @param manager the FactoryManager instance
     */

    public static void handleManufacture(FactoryManager manager) 
    {
        int quantity = 0;

        try 
        {
            System.out.print("Enter number of scooters: ");

            quantity = Integer.parseInt(scanner.nextLine().trim());

            if (quantity <= 0) 
            {
                System.out.println("Error Quantity must be a positive number");
                return;
            }

            manager.manufacture(quantity);

        } 
        
        catch (NumberFormatException e) 
        {
            System.out.println("Error Please enter a valid number");

        } 
        
        catch (InsufficientMaterialsException e) 
        {
            // display error from exception
            System.out.println("Error: " + e.getMessage());

            // display what is missing
            if (quantity > 0) 
            {
                manager.displayMissingMaterials(quantity);
            }

            System.out.println("Inventory is not changed");
        }
    }

    /**
     * Handles Option 6 - View Missing Materials
     * 
     * Prompts for a quantity and displays which materials are insufficient and by how much.
     *
     * @param manager the FactoryManager instance
     */

    public static void handleMissingMaterials(FactoryManager manager) 
    {

        try 
        {
            System.out.print("Enter number of scooters: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            if (quantity <= 0) 
            {
                System.out.println("Error Quantity must be a positive number");
                return;
            }

            manager.displayMissingMaterials(quantity);

        } 
        
        catch (NumberFormatException e) 
        {
            System.out.println("Error Please enter a valid number");
        }
    }
}