package com.cafelumiere.inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cafelumiere.model.MenuItem;

/**
 * Manages ingredient stock levels (Tier 2 — Logic class).
 * Implements Serializable so inventory state can be saved to file.
 *
 * ingredientStock — ingredient name → current quantity
 * lowStockThreshold — quantity below which isLowStock() returns true
 */
public class Inventory implements Serializable {

    private static final int MAX_STOCK = 50; // an ingredient can never exceed this

    private Map<String, Integer> ingredientStock;
    private int lowStockThreshold;

    // default threshold of 10 units — used by CoffeeShopSystem
    public Inventory() {
        this(10);
    }

    public Inventory(int lowStockThreshold) {
        this.ingredientStock  = new HashMap<>();
        this.lowStockThreshold = lowStockThreshold;
        initializeIngredients();
    }

    // Seeds the café's starting stock for every known ingredient
    private void initializeIngredients() {
        ingredientStock.put("Coffee Beans", 50);
        ingredientStock.put("Water",        50);
        ingredientStock.put("Milk",         50);
        ingredientStock.put("Ice Cubes",    50);
        ingredientStock.put("Cocoa Powder", 50);
        ingredientStock.put("Bug Spray Syrup", 50);
        ingredientStock.put("String Sugar",    50);
    }

    // Returns true if every ingredient the drink needs is in stock
    public boolean checkAvailability(MenuItem item) {
        for (String ingredient : RecipeManager.getIngredients(item.getName())) {
            if (ingredientStock.getOrDefault(ingredient, 0) <= 0) return false;
        }
        return true;
    }

    // Deducts 1 unit per ingredient used in the drink — called after an order is confirmed
    public void decrementStock(MenuItem item) {
        for (String ingredient : RecipeManager.getIngredients(item.getName())) {
            int current = ingredientStock.getOrDefault(ingredient, 0);
            if (current > 0) ingredientStock.put(ingredient, current - 1);
        }
    }

    // Used by InventoryView to decide whether to show a "Low Stock" badge
    public boolean isLowStock(String ingredient) {
        return ingredientStock.getOrDefault(ingredient, 0) < lowStockThreshold;
    }

    // Adds stock to an ingredient, never going above MAX_STOCK — called from the Restock button
    public void restock(String ingredient, int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        int current = ingredientStock.getOrDefault(ingredient, 0);
        ingredientStock.put(ingredient, Math.min(current + amount, MAX_STOCK));
    }

    public int getStock(String ingredient) {
        return ingredientStock.getOrDefault(ingredient, 0);
    }

    // Returns a copy so callers cannot mutate the internal map
    public Map<String, Integer> getAllStock() {
        return new HashMap<>(ingredientStock);
    }

    // Maps each drink name to the list of ingredients it requires
    public static class RecipeManager {
        private static final Map<String, List<String>> recipes = new HashMap<>();

        static {
            recipes.put("Espresso",        Arrays.asList("Coffee Beans", "Water"));
            recipes.put("Iced Espresso",   Arrays.asList("Coffee Beans", "Water", "Ice Cubes"));
            recipes.put("Latte",           Arrays.asList("Coffee Beans", "Water", "Milk"));
            recipes.put("Iced Latte",      Arrays.asList("Coffee Beans", "Water", "Milk", "Ice Cubes"));
            recipes.put("Americano",       Arrays.asList("Coffee Beans", "Water"));
            recipes.put("Iced Americano",  Arrays.asList("Coffee Beans", "Water", "Ice Cubes"));
            recipes.put("Cappuccino",      Arrays.asList("Coffee Beans", "Water", "Milk", "Cocoa Powder"));
            recipes.put("Iced Cappuccino", Arrays.asList("Coffee Beans", "Water", "Milk", "Ice Cubes", "Cocoa Powder"));
            recipes.put("Java",            Arrays.asList("Coffee Beans", "Water", "Bug Spray Syrup", "String Sugar"));
            recipes.put("Iced Java",       Arrays.asList("Coffee Beans", "Water", "Ice Cubes", "Bug Spray Syrup", "String Sugar"));
        }

        public static List<String> getIngredients(String drinkName) {
            return recipes.getOrDefault(drinkName, new ArrayList<>());
        }
    }
}
