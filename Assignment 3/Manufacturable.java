// Assignment 3
// Written by: MIHIR VIPULBHAI PANCHAL [40366948]


/**
 * This is an interface that will define the contract for manufacturing operations in the Assembly Manager
 * This checks the logic for validating and performing manufacturing process
 *
 * @author MIHIR VIPULBHAI PANCHAL
 */


public interface Manufacturable 
{
    /**
     * Checks the required inventory to produce requested number of scooters 
     *
     * @param quantity number of scooters to check
     * @return true if all materials are sufficient or false otherwise
     */


    public boolean canManufacture(int quantity);

    /**
     * Manufactures the requested number of scooters
     * Deducts required materials from inventory
     * All checks must pass before any inventory is modified
     * 
     * 
     * @param quantity number of scooters to manufacture
     * @throws InsufficientMaterialsException if any material is insufficient
     */
    
    public void manufacture(int quantity) throws InsufficientMaterialsException;
}