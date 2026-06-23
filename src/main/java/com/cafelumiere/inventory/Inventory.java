package com.cafelumiere.inventory;

import com.cafelumiere.model.MenuItem;
import java.util.Map;

/**
 * Manages ingredient stock levels (Tier 2 — Logic class).
 *
 * UML fields:
 *   - ingredientStock: Map<String, Integer>   (ingredient name → quantity)
 *   - lowStockThreshold: int
 *
 * UML methods:
 *   + checkAvailability(item: MenuItem): boolean
 *   + decrementStock(item: MenuItem): void
 *   + isLowStock(ingredient: String): boolean
 *   + restock(ingredient: String, amount: int): void
 *
 * TODO: Implement all fields and methods:
 *
 *   private Map<String, Integer> ingredientStock = new HashMap<>();
 *   private int lowStockThreshold = 10;
 *
 *   public Inventory() {
 *       // Add starting stock for each ingredient:
 *       // Coffee Beans, Water, Milk, Ice Cubes, Cocoa Powder,
 *       // Bug Spray Syrup (Java Easter Egg), String Sugar (Java Easter Egg)
 *       ingredientStock.put("Coffee Beans", 100);
 *       ingredientStock.put("Milk", 80);
 *       // ... etc
 *   }
 *
 *   + checkAvailability(item): boolean
 *       Check if all ingredients needed for this item are above 0.
 *       Returns true if the drink can be made, false if any ingredient is out.
 *
 *   + decrementStock(item): void
 *       Subtract the required ingredient amounts for this drink.
 *       Called by CoffeeShopSystem.placeOrder() after an order is confirmed.
 *
 *   + isLowStock(ingredient): boolean
 *       return ingredientStock.getOrDefault(ingredient, 0) < lowStockThreshold;
 *       Used by InventoryView to decide whether to show a "Low Stock" or "OK" badge.
 *
 *   + restock(ingredient, amount): void
 *       ingredientStock.merge(ingredient, amount, Integer::sum);
 *       Called when the Restock button in InventoryView is clicked.
 *
 * TODO: Wire into InventoryView:
 *   - Call system.getInventory().ingredientStock.entrySet() to get rows for the table
 *   - For each entry: show ingredient name, quantity, and isLowStock() badge
 *   - Restock button calls system.getInventory().restock(name, amount)
 */
public class Inventory {

    // TODO: private Map<String, Integer> ingredientStock;
    // TODO: private int lowStockThreshold;

    // TODO: public Inventory() { ... }

    // TODO: public boolean checkAvailability(MenuItem item) { ... }
    // TODO: public void decrementStock(MenuItem item) { ... }
    // TODO: public boolean isLowStock(String ingredient) { ... }
    // TODO: public void restock(String ingredient, int amount) { ... }

    // TODO: public Map<String, Integer> getIngredientStock() { return ingredientStock; }
}
