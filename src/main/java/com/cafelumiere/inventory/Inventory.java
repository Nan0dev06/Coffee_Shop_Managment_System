package com.cafelumiere.inventory;

import com.cafelumiere.model.MenuItem;
import java.util.List;

/**
 * Manages ingredient stock levels for the café.
 *
 * TODO: Implement this class fully:
 *
 * 1. Define an IngredientStock inner class / record:
 *       public record IngredientStock(String ingredient, double quantity, String unit) {}
 *    This is one row in the Inventory screen table.
 *
 * 2. Store a list of IngredientStock objects (the café's pantry):
 *       private List<IngredientStock> stock;
 *    Populate it in the constructor with starting quantities for each ingredient
 *    (e.g. Coffee Beans, Milk, Sugar, Ice, Cups).
 *
 * 3. Implement getAll():
 *       public List<IngredientStock> getAll() { return Collections.unmodifiableList(stock); }
 *    InventoryView calls this to populate the table rows.
 *
 * 4. Implement deductForOrder(MenuItem item, int quantity):
 *    When an order is placed, subtract the required ingredient amounts.
 *    Example: one Latte uses 18g coffee + 200ml milk.
 *    Define a recipe map: Map<String, Map<String, Double>> recipes
 *    keyed by drink name → ingredient → amount per unit.
 *
 * 5. Implement isLowStock(IngredientStock s):
 *       public boolean isLowStock(IngredientStock s) { return s.quantity() < LOW_STOCK_THRESHOLD; }
 *    InventoryView uses this to show a "Low Stock" badge (Badge.Variant.WARNING)
 *    vs an "OK" badge (Badge.Variant.SUCCESS) in the Status column.
 *
 * 6. Implement restock(String ingredient, double amount):
 *    Called when the Restock button in InventoryView is clicked.
 *    Adds the given amount back to the ingredient's quantity.
 *
 * HOW TO WIRE INTO THE UI (InventoryView.java):
 *   - Pass an Inventory instance into the InventoryView constructor
 *   - In tableCard(), call inventory.getAll() and loop: table.addRow(...)
 *   - In the Status column, pass a Badge component based on inventory.isLowStock(s)
 *   - In the action column, pass a Restock KButton whose ActionListener calls
 *     inventory.restock(ingredient, amount) then refreshes the table
 */
public class Inventory {

    // TODO: private static final double LOW_STOCK_THRESHOLD = 10.0;
    // TODO: private List<IngredientStock> stock;
    // TODO: private Map<String, Map<String, Double>> recipes;

    // TODO: public Inventory() { ... }  initialise stock and recipes here

    // TODO: public List<IngredientStock> getAll() { ... }

    // TODO: public void deductForOrder(MenuItem item, int quantity) { ... }

    // TODO: public boolean isLowStock(IngredientStock s) { ... }

    // TODO: public void restock(String ingredient, double amount) { ... }
}
