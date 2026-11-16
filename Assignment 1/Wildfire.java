// ---------------------------------------------------
// Assignment 1 Part 2 Problem A

// © Mihir Vipulbhai Panchal

// Written by Mihir Vipulbhai Panchal Student ID: 40366948
// ---------------------------------------------------

// Problem

/*
 * Question: Design and implement a class called Wildfire according to the following requirements: 

	A Wildfire object has eight attributes, namely,  
		fireID (long)
		fireName (String)
		country (String)
		region (String)
		areaBurned (double) 
		containmentPercentage (double)
		riskLevel (String)
		evacuationRequired (boolean)

	Upon creation of a Wildfire object, the object must immediately be initialized with valid values for all its attributes. The design should provide different ways of creating a Wildfire object, including creating one with appropriate default values. (Hint: use constructors.) 
	The design must allow enough flexibility so that the value of any of these attributes can be modified later except for fireID. For example, the containment percentage of a Wildfire may increase as firefighters make progress, or its risk level may change as conditions evolve. The design must also allow a user to obtain the value of any attribute. (Hint: use accessors and mutators.)
	The design must allow all attributes of a Wildfire object to be displayed at once using the System.out.print() or System.out.println(). (Hint: use the toString() method.)
	It is required to know how many Wildfire objects have been created at any point during the execution of the program. For this purpose, the Wildfire class must have a method called getNumberOfWildfires(). This method must return the number of Wildfire objects that have been created prior to the time the method is invoked. The method returns 0 if no Wildfire objects have been created when it is called. (Hint: use a static attribute and/or static method. You may add other attributes to the class as required.).
	It is required to compare two Wildfire objects for equality. Two Wildfire objects are considered equal if they have the same fire name, country, and region. The comparison of these attributes should not be affected by differences in uppercase and lowercase letters. (Hint: use the equals() method.)

 */

// Problem Approach

/*
 * store information of wildfire such as ID, name, location, area burned, containment, risk level, and evacuation status.
 * constructor class to create objects for wildfire. A counter to maintain ledger for total number of wildfires created.
 * condition validation to prevent invalid object values
 * getter and setter values for required objects
 * compare same wildfire instances
 * and finally print all the wildfire information
 */

public class Wildfire {

    // Here I will set a integer static variable to count the number of wildfires as a counter.
    private static int numberOfWildfires = 0;

    // Here I will set 8 instance variables required
    private final long fireID;
    private String fireName;
    private String country;
    private String region;
    private double areaBurned;
    private double containmentPercentage;
    private String riskLevel;
    private boolean evacuationRequired;

    // The default constructor with all 8 features of wildfire.
    public Wildfire() {
        this.fireID = 0;
        this.fireName = "Unknown";
        this.country = "Unknown";
        this.region = "Unknown";
        this.areaBurned = 0.0;
        this.containmentPercentage = 0.0;
        this.riskLevel = "Low";
        this.evacuationRequired = false;
        numberOfWildfires++;
    }

    // The parameterized constructor
    public Wildfire(long fireID, String fireName, String country, String region, double areaBurned, double containmentPercentage, String riskLevel, boolean evacuationRequired) 
    
    {
    	// Collecting feature values
        this.fireID = fireID;
        this.fireName = fireName;
        this.country = country;
        this.region = region;

        // For areaBurned
        if(areaBurned >= 0)
        {
            this.areaBurned = areaBurned;
        }
        
        else {
            this.areaBurned = 0.0;
        }
        
        // For containmentPercentage

        if (containmentPercentage >= 0.0 && containmentPercentage <= 100.0)
        {
            this.containmentPercentage = containmentPercentage;
        }
            
        else
        {
            this.containmentPercentage = 0.0;
        }
        
        // for riskLevel
        
        if (isValidRiskLevel(riskLevel))
        {
            this.riskLevel = riskLevel;
        }
        
        else
        {
            this.riskLevel = "Low";
        }
        
        // evacuation requirement
        this.evacuationRequired = evacuationRequired;
        
        numberOfWildfires++;
    }

    // check what is the risk level 
    public static boolean isValidRiskLevel(String level) {
    	
        if (level.equals("Low") || level.equals("Moderate") || level.equals("High") || level.equals("Extreme"))
        {
            return true;
        }
        
        else
        {
        	return false;
        }
    }

    // Print the number of wildfire objects created (counter for numberOfWildfires)
    
    public static int getNumberOfWildfires() {
        return numberOfWildfires;
    }
    
    // Getter Method Starts here 

    // getter methods to collect feature values
    
    public long getFireID() 
    {
        return fireID;
    }

    public String getFireName() 
    {
        return fireName;
    }

    public String getCountry() 
    {
        return country;
    }

    public String getRegion() 
    {
        return region;
    }

    public double getAreaBurned() 
    {
        return areaBurned;
    }

    public double getContainmentPercentage() 
    {
        return containmentPercentage;
    }

    public String getRiskLevel() 
    {
        return riskLevel;
    }

    public boolean isEvacuationRequired() 
    {
        return evacuationRequired;
    }
    
    // Getter Method Ends here 

    
    
    // Setter Method Starts here 
    
    // setter methods for feature values. Helps to change values later when required.
    // fireID is not to be changed so the setter method of fireID is not used here.
    
    public void setFireName(String fireName) 
    {
        this.fireName = fireName;
    }

    public void setCountry(String country) 
    {
        this.country = country;
    }

    public void setRegion(String region) 
    {
        this.region = region;
    }
    
    // I will use condition to check new set values so that it does not conflict with our parameterized constructor
    // area burned can not be less than zero
    
    public void setAreaBurned(double areaBurned) 
    {
    	
        if (areaBurned >= 0)
        {
            this.areaBurned = areaBurned;
        }
        else
        {
            System.out.println("area burned cannot be less than zero");
        }
        
    }

    // same here for containment percentage. The value can not be less than zero and more than 100 percent.
    public void setContainmentPercentage(double containmentPercentage) 
    {
    	
        if (containmentPercentage >= 0.0 && containmentPercentage <= 100.0)
        {
        	this.containmentPercentage = containmentPercentage;
        }
        
        else
        {	
        	System.out.println("containment percentage must be in-between 0 and 100");
        }
    }

    // Risk Level can not be other than low, moderate, high or extreme
    public void setRiskLevel(String riskLevel) {
    	
        if (isValidRiskLevel(riskLevel))
        {
            this.riskLevel = riskLevel;
        }
        
        else
        {
            System.out.println("risk level should be from the following values low, moderate, high, or extreme.");
        }
    }

    public void setEvacuationRequired(boolean evacuationRequired) {
        this.evacuationRequired = evacuationRequired;
    }

    // Setter Method Ends here 

    
    // Equals Method Starts here
    
    // I will use the equals method to check whether two wildfires are same or different by comparing the features: name, country and region.
    // This method will get an object and then compare that value with the current object in the memory.
    
    public boolean equals(Object obj) {
    	
    	 // First it will compare the literal values using == and then also check whether the value is also a null value.
        if (this == obj)
        {
            return true;
        }
        
        if (obj == null)
        {
        	return false;
        }
        
     // I will also check that the object is from the wildfire class.
        if (!(obj instanceof Wildfire))
        {
        	return false;
        }

     // Once the object is verified it is an instance of wildfire now i will cast it as wildfire object as other.
        Wildfire other = (Wildfire) obj;

        
     // I will also make sure that I ignore the upper and lower case input values.
        if (this.fireName.equalsIgnoreCase(other.fireName) && this.country.equalsIgnoreCase(other.country) && this.region.equalsIgnoreCase(other.region))
        {
            return true;
        }
        
        else
        {
        return false;
        }
    }

    // I will use the toString method to print all the attributes.
    
    public String toString() {
    	
        return "Fire ID: " + fireID + "\n" +
    
               "Fire Name: " + fireName + "\n" +
               
               "Country: " + country + "\n" +
               
               "Region: " + region + "\n" +
               
               "Area Burned: " + areaBurned + " hectares" + "\n" +
               
               "Containment Percentage: " + containmentPercentage + " %" + "\n" +
               
               "Risk Level: " + riskLevel + "\n" +
               
               "Evacuation Required: " + evacuationRequired;
    }
}