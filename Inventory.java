package com.cafelumiere.inventory;

import com.cafelumiere.model.MenuItem;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
/**
 * ============================================================
 * INVENTORY CLASS
 * ============================================================
 *
 * Inventory class — tracks ingredient stock for the café.
 *
 * What it does:
 * - checkAvailability() : checks if a drink can be made before an order
 * - decrementStock()    : reduces stock 
 * - isLowStock()        : warns when an ingredient is running low
 * - restock()           : lets the owner add more stock
 *
 * Saved to file using Serializable.
 */
 

public class Inventory implements Serializable{
    
 //===============data field===============
    
  /**Stores the quantity of each ingredient
  1.the ingredient name is the key (String)
  2.the quantity is the value (Integer).
  *HashMap gives O(1) lookup — instant, no matter how many ingredients.
  */
 private Map<String, Integer> ingredientStock;
 private int lowStockThreshold;
    
 
 //============constructor===========
                      
/**
 lowStockThreshold the minimum quantity before an ingredient
 is considered low stock and triggers a warning
 */
public Inventory(int lowStockThreshold) {

    // Initialize the stock map — starts empty, ingredients added via restock()
    this.ingredientStock = new HashMap<>();

    // Store the threshold 
    this.lowStockThreshold = lowStockThreshold;
    // seed the café's starting inventory
        initializeIngredients();
        
        
}  

 /**
     * This method is called once by the constructor.
     * It ensures all possible ingredients exist in the system,
     * starting with 0 stock (owner will restock when needed).
     */
private void initializeIngredients() {
       
        ingredientStock.put("Coffee Beans", 50);
        

        ingredientStock.put("Water", 50);
        ingredientStock.put("Milk", 50);
        ingredientStock.put("Ice Cubes", 50);
    
        ingredientStock.put("Cocoa Powder", 50);
        ingredientStock.put("Bug Spray Syrup", 50);
        ingredientStock.put("String Sugar", 50);
        
     
    }
    

/***********************************************/
                    //methods


 //==========check Availability===========
/**
     * Check if we have all ingredients to make a menu item
     * Process:
     * 1. Get required ingredients from MenuItem
     * 2. Check if we have EACH ingredient
     * 3. Return true only if ALL ingredients sufficient
     * 4. Return false if ANY ingredient is insufficient
 */
public boolean checkAvailability(MenuItem item) {
    for (String ingredient : RecipeManager.getIngredients(item.getName())) {
        if (ingredientStock.getOrDefault(ingredient, 0) <= 0) return false;
    }
    return true;
}
 
 // ============decrementStock =================
 
/**  this method called just if  an order is confirmed.
  * Deducts 1 unit per ingredient used in the drink.
 */

public void decrementStock(MenuItem item) {
    for (String ingredient : RecipeManager.getIngredients(item.getName())) {
        int currentQuantity = ingredientStock.getOrDefault(ingredient, 0);
        if (currentQuantity > 0) {
            ingredientStock.put(ingredient, currentQuantity - 1);
        }
    }
}

 // ======= ====isLowStock =============
    // Checks one ingredient. The UI uses this to paint a warning label.
 public boolean isLowStock(String ingredient) {
        int currentStock = ingredientStock.getOrDefault(ingredient, 0);
        return currentStock < lowStockThreshold;
    }
 
//==========restock ================
// Add stock to an ingredient. Validates that amount is positive.
    public void restock(String ingredient, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        ingredientStock.merge(ingredient, amount, Integer::sum);
    }
    
 
 //=====getStock=============
 public int getStock(String ingredient) {
        return ingredientStock.getOrDefault(ingredient, 0);
    }
 public Map<String, Integer> getAllStock() {
        return new HashMap<>(ingredientStock);
    }
 

public class RecipeManager {
    private static final Map<String, List<String>> recipes = new HashMap<>();

    static {
        // Espresso
        recipes.put("Hot Espresso", Arrays.asList("Coffee Beans", "Water"));
        recipes.put("Iced Espresso", Arrays.asList("Coffee Beans", "Water", "Ice Cubes"));

        // Latte
        recipes.put("Hot Latte", Arrays.asList("Coffee Beans", "Water", "Milk"));
        recipes.put("Iced Latte", Arrays.asList("Coffee Beans", "Water", "Milk", "Ice Cubes"));

        // Americano
        recipes.put("Hot Americano", Arrays.asList("Coffee Beans", "Water"));
        recipes.put("Iced Americano", Arrays.asList("Coffee Beans", "Water", "Ice Cubes"));

        // Cappuccino
        recipes.put("Hot Cappuccino", Arrays.asList("Coffee Beans", "Water", "Milk", "Cocoa Powder"));
        recipes.put("Iced Cappuccino", Arrays.asList("Coffee Beans", "Water", "Milk", "Ice Cubes", "Cocoa Powder"));

        // Java
        recipes.put("Hot Java", Arrays.asList("Coffee Beans", "Water", "Bug Spray Syrup", "String Sugar"));
        recipes.put("Iced Java", Arrays.asList("Coffee Beans", "Water", "Ice Cubes", "Bug Spray Syrup", "String Sugar"));
    }

    public static List<String> getIngredients(String drinkName) {
        return recipes.getOrDefault(drinkName, List.of());
    }
}

}
