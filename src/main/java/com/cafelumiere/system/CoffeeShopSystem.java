package com.cafelumiere.system;

import com.cafelumiere.inventory.Inventory;
import com.cafelumiere.model.Customer;
import com.cafelumiere.model.MenuItem;
import com.cafelumiere.model.Order;

import java.util.List;

/**
 * Central controller for the coffee shop (Tier 3 — Controller class).
 * All screens should call methods on this class.
 *
 * UML fields:
 *   - customers: List<Customer>
 *   - orders: List<Order>
 *   - menu: List<MenuItem>       ← the fixed 10 drinks from Menu.items()
 *   - inventory: Inventory
 *
 * UML methods:
 *   + login(password: String): boolean
 *   + placeOrder(customer: Customer, items: List<MenuItem>): Order
 *   + addCustomer(c: Customer): void
 *   + removeCustomer(id: int): void
 *   + sortOrdersByDate(asc: boolean): List<Order>
 *   + saveData(): void
 *   + loadData(): void
 *
 * ── IMPLEMENTATION GUIDE (follow this order) ─────────────────────────────────
 *
 * STEP 1 — Fields & constructor:
 *   private List<Customer> customers = new ArrayList<>();
 *   private List<Order> orders = new ArrayList<>();
 *   private List<MenuItem> menu = Menu.items();
 *   private Inventory inventory = new Inventory();
 *   private int nextOrderId = 1;   // auto-increment helper, not in UML but needed
 *
 * STEP 2 — login(password):
 *   Simple hardcoded check for now: return "admin".equals(password);
 *   Wire into LoginScreen: replace the no-op onLogin with system.login(passwordField.getText())
 *
 * STEP 3 — addCustomer(c) / removeCustomer(id):
 *   addCustomer: customers.add(c);
 *   removeCustomer: customers.removeIf(c -> c.getCustomerId() == id);
 *   After adding, refresh the combo box in OrderEntryScreen.
 *
 * STEP 4 — placeOrder(customer, items):
 *   Order order = new Order(nextOrderId++, customer, items);
 *   inventory.decrementStock() for each item;
 *   orders.add(order);
 *   return order;
 *   The returned Order is used by OrderEntryScreen's confirmation popup.
 *
 * STEP 5 — sortOrdersByDate(asc):
 *   return orders.stream()
 *       .sorted(asc ? Comparator.comparing(Order::getDateTime)
 *                   : Comparator.comparing(Order::getDateTime).reversed())
 *       .collect(Collectors.toList());
 *   Used by RevenueSummaryView and Dashboard to get ordered lists of orders.
 *
 * STEP 6 — saveData() / loadData():
 *   Persist customers and orders to a file (e.g. JSON or plain text).
 *   Call loadData() in the constructor and saveData() after every placeOrder/addCustomer.
 *
 * ── HOW TO WIRE INTO THE UI ──────────────────────────────────────────────────
 *
 * In Main.java, create ONE shared instance:
 *   CoffeeShopSystem system = new CoffeeShopSystem();
 *
 * Pass it into each screen:
 *   new Dashboard(system)
 *   new OrderEntryScreen(system, () -> onNavSelect("dashboard"))
 *   new InventoryView(system)
 *   new RevenueSummaryView(system)
 */
public class CoffeeShopSystem {

    private List<Customer> customers;
    private List<Order> orders;
    private List<MenuItem> menu;
    private Inventory inventory;
    private int nextOrderId = 1;
    

    // TODO: public CoffeeShopSystem() { loadData(); }
    public CoffeeShopSystem(){
        loadData();
    }
        //admin is the password that is true
        public boolean login(String password) { 
            boolean result = false;
            if (password.equals("testtest")) result = true;
            return result;
        }
    // TODO: public Order placeOrder(Customer customer, List<MenuItem> items) { ... }
    // TODO: public void addCustomer(Customer c) { ... }
    // TODO: public void removeCustomer(int id) { ... }
    // TODO: public List<Order> sortOrdersByDate(boolean asc) { ... }
    // TODO: public void saveData() { ... }
            public void loadData() { 

             }

    // TODO: public List<Customer> getCustomers() { return customers; }
    // TODO: public Inventory getInventory() { return inventory; }
}
