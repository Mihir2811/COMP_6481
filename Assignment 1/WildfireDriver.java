// ---------------------------------------------------
// Assignment 1 Part 2 Problem B

// © Mihir Vipulbhai Panchal

// Written by Mihir Vipulbhai Panchal Student ID: 40366948
// ---------------------------------------------------

// Program

/*
 * This is a global wild fire monitoring system which will be used in java console
 * it stores wildfire records and allows user to add new incidents with password protection and also view previous incidents too
 * user can also update an existing record with password to do that
 * view incidents by containment level and country name
 * lockout method to prevent frauds
 */

// Approach

/*
 * I will use the Wildfire class because the WildfireDriver class depends on it.
 * I will put static final password in public class so it is accessible everywhere
 * plan methods for showing main menu, checking password for option 1 and 2
 * create logic for methods involving to create and update new wildfire incidents
 * create methods to view wildfire data from country and containment percentage for the stored information.
 */


import java.util.Scanner;

public class WildfireDriver {

    static Scanner scanner = new Scanner(System.in);

    // Setting default String password for the driver
    static final String PASSWORD = "fire2026";

    public static void main(String[] args) {

        // Default system print message
        System.out.println("Global Wildfire Monitoring System" + "\n");

        // Set maximum number of wildfires user can set
        int maxFires = 0;
        System.out.print("Enter maximum number of Wildfires for the system- ");
        maxFires = scanner.nextInt();
        scanner.nextLine();

        // Default database name - WildfireDatabase
        Wildfire[] WildfireDatabase = new Wildfire[maxFires];

        // total fire count
        int fireCount = 0;

        // this handles the amount of time user has entered the password
        int lockoutCount = 0;

        // Choise variable for user main menu selection
        int choice = 0;

        // Menu Selection
        // once user selects input between 1 and 5 proceed with password checking
        while (choice != 5) {

            choice = showMainMenu();

            if (choice == 1) {
                //checks password with method
                boolean success = checkPasswordOption1();

                if (success) 
                {
                    lockoutCount = 0;
                    // select option1 and proceed
                    fireCount = registerWildfires(WildfireDatabase, fireCount, maxFires);
                } 
                
                else 
                {
                    lockoutCount = lockoutCount + 1;
                    
                    // lockdown mode
                    if (lockoutCount >= 3) 
                    {
                        System.out.println("WARNING: Multiple unauthorized access attempts detected. Program terminating immediately!");
                        // exiting system
                        System.exit(0);
                    }
                }

            } 
            
            else if (choice == 2) 
            {

                boolean success = checkPasswordOption2();
                if (success) 
                {
                    // select option2 and proceed
                    updateWildfire(WildfireDatabase, fireCount);
                }

            } 
            
            // Display Wildfires by country.
            else if (choice == 3) 
            {

                System.out.print("\n Enter country name: ");
                String countryName = scanner.nextLine();

                // select option3 and proceed
                findWildfiresByCountry(WildfireDatabase, fireCount, countryName);

            } 
            
            // Display Wildfires by containment percentage.
            else if (choice == 4) 
            {

                searchByContainment(WildfireDatabase, fireCount);

            } 
            
            // Exit
            else if (choice == 5) 
            {
                System.out.println("\n Thank you for using the Global Wildfire Monitoring System.");
            }
        }

        scanner.close();
    }

    // Program methods starts here

    // displays main menu
    public static int showMainMenu() {

        int choice = 0;

        while (choice < 1 || choice > 5) 
        {
            System.out.println("\n What do you want to do?");
            System.out.println("1. Register new Wildfires (password is required).");
            System.out.println("2. Update an existing Wildfire (password is required).");
            System.out.println("3. Display Wildfires by country.");
            System.out.println("4. Display Wildfires by containment percentage.");
            System.out.println("5. Exit.");
            System.out.print("Please enter your choice > ");

            if (scanner.hasNextInt()) 
            {
                choice = scanner.nextInt();
                scanner.nextLine();
            } 
            
            else 
            {
                scanner.nextLine();
            }

            if (choice < 1 || choice > 5)
            {
                System.out.println("Please enter a number between 1 and 5.");
            }
        }

        return choice;
    }

    // Start with password check for option 1 and then proceed
    public static boolean checkPasswordOption1() 
    {

        int attempts = 0;

        while (attempts <= 3) 
        {
            System.out.print("Enter password: ");
            String input = scanner.nextLine();

            //Compare with final static password variable
            if (input.equals(PASSWORD)) 
            {
                return true;
            } 
            
            else 
            {
                attempts = attempts + 1;
                System.out.println("Your password is wrong.");
            }
        }

        System.out.println("Too many failed attempts. Returning to main menu.");
        return false;
    }

    // password check for option 2 and then proceed
    public static boolean checkPasswordOption2() 
    {
        int attempts = 0;

        while (attempts <= 3) 
        {
            System.out.print("Enter password: ");
            String input = scanner.nextLine();

            if (input.equals(PASSWORD)) 
            {
                return true;
            } 

            else 
            {
                attempts= attempts + 1;
                System.out.println("Your password is wrong.");
            }
        }

        System.out.println("Too many failed attempts.");
        return false;
    }

    // Option methods start here

    // option 1 - register for new wildfires
    public static int registerWildfires(Wildfire[] db, int fireCount, int maxFires) 
    {
        // Checking max space left in db
        int space = maxFires - fireCount;

        if (space == 0) 
        {
            System.out.println("The Wildfire Database is full.");
        }

        System.out.print("How many Wildfires would you like to add? ");

        // Entering how many wildfire counts user wants to add
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num > space) 
        {
            System.out.println("The Wildfire Database can only hold " + space + " more Wildfires.");
            num = space;
        }

        for (int i = 0; i < num; i++) 
        {
            System.out.println("\n Entering Wildfire " + (i + 1) + " of " + num);
            db[fireCount] = enterWildfire(db, fireCount);
            fireCount = fireCount + 1;
        }

        System.out.println(num + " Wildfire added.");
        return fireCount;
    }

    // collecting all information for a new wildfire from user
    public static Wildfire enterWildfire(Wildfire[] db, int fireCount) 
    {
        // fire id must be unique
        long id = 0;
        boolean validID = false;

        while (!validID) 
        {
            System.out.print("Enter Fire ID: ");
            
            if (scanner.hasNextLong()) 
            {
                id = scanner.nextLong();
                scanner.nextLine();

                if (id <= 0) 
                {
                    System.out.println("Fire ID must be a positive number.");
                } 
                
                else if (idAlreadyExists(db, fireCount, id)) 
                {
                    System.out.println("Fire ID already exists.");
                } 
                
                else 
                {
                    validID = true;
                }
            } 
            
            else 
            {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }

        System.out.print("Enter Fire Name- ");
        String name = scanner.nextLine();

        System.out.print("Enter Country- ");
        String country = scanner.nextLine();

        System.out.print("Enter Region- ");
        String region = scanner.nextLine();

        // condition check for negative area burned
        double area = -1;
        while (area < 0) 
        {
            System.out.print("Enter Area Burned: ");
            
            if (scanner.hasNextDouble()) 
            {
                area = scanner.nextDouble();
                scanner.nextLine();
                if (area < 0)
                    System.out.println("Area burned cannot be negative.");
            } 
            
            else 
            {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }

        // condition check for containment must be 0 to 100
        double containment = -1;
        while (containment < 0 || containment > 100) 
        {
            System.out.print("Enter Containment Percentage (0-100): ");

            if (scanner.hasNextDouble()) 
            {
                containment = scanner.nextDouble();
                scanner.nextLine();
                
                if (containment < 0 || containment > 100)
                {
                    System.out.println("Invalid input");
                }
            } 
            
            else 
            {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }

        // risk level must be one of the 4 valid options
        String risk = "";

        while (!Wildfire.isValidRiskLevel(risk)) 
        {
            System.out.print("Enter Risk Level (Low/Moderate/High/Extreme): ");
            risk = scanner.nextLine();
            
            if (!Wildfire.isValidRiskLevel(risk))
            {
                System.out.println("Invalid input");
            }
        }

        // evacuation status
        boolean evac = false;
        boolean validEvac = false;
       
        while (!validEvac) 
        {
            System.out.print("Evacuation Required? (true/false): ");
            String input = scanner.nextLine().trim();

            if (input.equals("true")) 
            {
                evac = true;
                validEvac = true;
            } 
            
            else if (input.equals("false")) 
            {
                evac = false;
                validEvac = true;
            } 
            
            else 
            {
                System.out.println("Invalid input");
            }
        }

        return new Wildfire(id, name, country, region, area, containment, risk, evac);
    }

    // option 2 - update an existing wildfire
    public static void updateWildfire(Wildfire[] db, int fireCount) 
    {

        if (fireCount == 0) 
        {
            System.out.println("No Wildfires in the database");
            return;
        }

        boolean searching = true;
        Wildfire target = null;
        int index = -1;

        while (searching) 
        {

            System.out.print("\n Enter the Fire ID of the Wildfire to update: ");
            long searchID = 0;

            if (scanner.hasNextLong()) 
            {
                searchID = scanner.nextLong();
                scanner.nextLine();
            } 
            
            else 
            {
                System.out.println("Invalid input");
                scanner.nextLine();
                continue;
            }

            // look for wildfire with that id
            for (int i = 0; i < fireCount; i++) 
            {
                if (db[i].getFireID() == searchID) 
                {
                    target = db[i];
                    index = i;
                    break;
                }
            }

            if (target != null) 
            {
                searching = false;
            } 
            
            else 
            {
                System.out.println("No Wildfire found with that ID");
                System.out.println("1 - Enter another Fire ID");
                System.out.println("2 - Return to the main menu");
                System.out.print("Enter choice: ");

                int pick = 0;
                while (pick != 1 && pick != 2) 
                {
                    if (scanner.hasNextInt()) 
                    {
                        pick = scanner.nextInt();
                        scanner.nextLine();
                    } 
                    
                    else 
                    {
                        scanner.nextLine();
                    }

                    if (pick != 1 && pick != 2)
                    {
                        System.out.print("Please enter 1 or 2: ");
                    }
                }

                if (pick == 2)
                {
                    return;
                }
            }
        }

        // display the found wildfire
        System.out.println("\n Wildfire: #" + index);
        System.out.println(target);

        // update loop
        int updateChoice = 0;

        while (updateChoice != 8) 
        {
            updateChoice = 0;

            while (updateChoice < 1 || updateChoice > 8) 
            {
                System.out.println("\n What information would you like to change?");
                System.out.println("1. Fire Name");
                System.out.println("2. Country");
                System.out.println("3. Region");
                System.out.println("4. Area Burned");
                System.out.println("5. Containment Percentage");
                System.out.println("6. Risk Level");
                System.out.println("7. Evacuation Status");
                System.out.println("8. Quit this operation");
                System.out.print("Please enter your choice (1-8)> ");

                if (scanner.hasNextInt()) 
                {
                    updateChoice = scanner.nextInt();
                    scanner.nextLine();
                } 
                
                else 
                {
                    scanner.nextLine();
                }

                if (updateChoice < 1 || updateChoice > 8)
                {
                    System.out.println("Please enter a number from 1 to 8.");
                }
            }

            if (updateChoice == 1) 
            {
                System.out.print("Enter new Fire Name: ");
                target.setFireName(scanner.nextLine());

            } 
            
            else if (updateChoice == 2) 
            {
                System.out.print("Enter new Country: ");
                target.setCountry(scanner.nextLine());

            } 
            
            else if (updateChoice == 3) 
            
            {
                System.out.print("Enter new Region: ");
                target.setRegion(scanner.nextLine());

            } 
            
            else if (updateChoice == 4) 
            
            {
                double newArea = -1;
                while (newArea < 0) 
                {
                    System.out.print("Enter new Area Burned: ");

                    if (scanner.hasNextDouble()) 
                    {
                        newArea = scanner.nextDouble();
                        scanner.nextLine();
                        if (newArea < 0)
                        {
                            System.out.println("Invalid input");
                        }
                    } 
                    
                    else 
                    {
                        System.out.println("Invalid input");
                        scanner.nextLine();
                    }
                }
                target.setAreaBurned(newArea);

            } 
            
            else if (updateChoice == 5) 
            {
                double newPct = -1;
                
                while (newPct < 0 || newPct > 100) 
                {
                    System.out.print("Enter new Containment Percentage (0-100): ");
                    if (scanner.hasNextDouble()) 
                    {
                        newPct = scanner.nextDouble();
                        scanner.nextLine();

                        if (newPct < 0 || newPct > 100)
                        {
                            System.out.println("Invalid input");
                        }
                    } 
                    
                    else 
                    {
                        System.out.println("Invalid input");
                        scanner.nextLine();
                    }
                }
                target.setContainmentPercentage(newPct);

            } 
            
            else if (updateChoice == 6) 
            
            {
                String newRisk = "";
                
                while (!Wildfire.isValidRiskLevel(newRisk)) 
                {
                    System.out.print("Enter new Risk Level (Low/Moderate/High/Extreme): ");
                    newRisk = scanner.nextLine();
                    
                    if (!Wildfire.isValidRiskLevel(newRisk))
                    {
                        System.out.println("Invalid input");
                    }
                }

                target.setRiskLevel(newRisk);

            } 
            
            else if (updateChoice == 7) 
            
            {
                boolean newEvac = false;
                boolean validInput = false;
                
                while (!validInput) 
                {
                    System.out.print("Enter new Evacuation Status (true/false): ");
                    String input = scanner.nextLine().trim();
                    
                    if (input.equals("true")) 
                    {
                        newEvac = true;
                        validInput = true;
                    } 
                    
                    else if (input.equals("false")) 
                    {
                        newEvac = false;
                        validInput = true;
                    } 
                    
                    else 
                    {
                        System.out.println("Invalid input");
                    }
                }
                target.setEvacuationRequired(newEvac);

            } 
            
            else if (updateChoice == 8) 
            
            {
                System.out.println("Returning to main menu.");
            }

            // show updated wildfire after each change
            if (updateChoice != 8) 
            {
                System.out.println("\n Updated Wildfire:");
                System.out.println("Wildfire: #" + index);
                System.out.println(target);
            }
        }
    }

    // option 3 - find wildfires by country (static as required)
    public static void findWildfiresByCountry(Wildfire[] db, int fireCount, String country) 
    {

        System.out.println("\n Searching for Wildfires in: " + country);

        int found = 0;

        for (int i = 0; i < fireCount; i++) 
        {
            if (db[i].getCountry().equalsIgnoreCase(country)) 
            {
                System.out.println("\n Wildfire: #" + i);
                System.out.println(db[i]);
                found = found + 1;
            }
        }

        if (found == 0)
        {
            System.out.println("No Wildfires found in " + country);
        }

        else
        {
            System.out.println("\n" + found + " Wildfire found.");
        }
    }

    // option 4 - display wildfires by containment percentage
    public static void searchByContainment(Wildfire[] db, int fireCount) 
    {

        double maxpercentage = -1;

        while (maxpercentage < 0 || maxpercentage > 100) 
        {
            System.out.print("\n Enter maximum containment percentage (0-100): ");
            
            if (scanner.hasNextDouble()) 
            {
                maxpercentage = scanner.nextDouble();
                scanner.nextLine();
                
                if (maxpercentage < 0 || maxpercentage > 100)
                    {
                    System.out.println("Invalid input");
                    }
            } 

            else 
            {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }

        int found = 0;

        for (int i = 0; i < fireCount; i++) 
        {
            if (db[i].getContainmentPercentage() <= maxpercentage) 
            {
                System.out.println("\n Wildfire: #" + i);
                System.out.println(db[i]);
                found = found + 1;
            }
        }

        if (found == 0)
        {
            System.out.println("No Wildfires found with containment percentage " + maxpercentage);
        }
        else
        {
            System.out.println("\n" + found + " Wildfire found.");
        }
    }

    // checks if a fire id is already in the database
    public static boolean idAlreadyExists(Wildfire[] db, int fireCount, long id) 
    {
        for (int i = 0; i < fireCount; i++) 
        {
            if (db[i].getFireID() == id)
                return true;
        }
        
        return false;
    }
}