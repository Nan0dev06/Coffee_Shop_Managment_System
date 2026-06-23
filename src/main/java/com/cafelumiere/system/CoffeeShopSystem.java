package com.cafelumiere.system;

import com.cafelumiere.inventory.Inventory;
import com.cafelumiere.model.Customer;
import com.cafelumiere.model.MenuItem;
import com.cafelumiere.model.Order;
import com.cafelumiere.reports.RevenueSummary;

import java.time.LocalDate;
import java.util.List;

/**
 * Central backend controller — the single entry point the UI talks to.
 * All screens should call methods on this class instead of managing data themselves.
 *
 * TODO: Implement this class fully. Suggested order of implementation:
 *
 * ── STEP 1: Customer management ──────────────────────────────────────────────
 *
 * TODO: Add a list of customers and implement:
 *       public List<Customer> getCustomers()
 *   - Returns all registered customers
 *   - OrderEntryScreen calls this to populate the Customer dropdown on startup:
 *       combo.setModel(new DefaultComboBoxModel<>(system.getCustomers().toArray(new Customer[0])))
 *
 * TODO: public void addCustomer(Customer customer)
 *   - Registers a new customer (if a "New Customer" flow is added later)
 *
 * ── STEP 2: Order placement ──────────────────────────────────────────────────
 *
 * TODO: Add an order history list and implement:
 *       public Order placeOrder(Customer customer, List<Order.OrderLine> lines)
 *   - Creates a new Order object with a unique orderId and current timestamp
 *   - Calls inventory.deductForOrder() for each line to reduce stock
 *   - Adds the order to the history list
 *   - Returns the completed Order so the UI can show the confirmation popup:
 *       "Order placed for " + order.getCustomer().getName()
 *       + " — Total: $" + String.format("%.2f", order.calculateTotal())
 *
 * TODO: public List<Order> getRecentOrders(int limit)
 *   - Returns the most recent N orders (newest first)
 *   - Dashboard calls this to populate the Recent Orders table
 *
 * TODO: public List<Order> getOrdersForDate(LocalDate date)
 *   - Returns all orders placed on a given date
 *   - RevenueSummaryView calls this for the Today's Orders table
 *
 * ── STEP 3: Inventory ────────────────────────────────────────────────────────
 *
 * TODO: Expose the Inventory object:
 *       public Inventory getInventory()
 *   - InventoryView calls this to get the stock list and restock functionality
 *
 * ── STEP 4: Revenue reporting ────────────────────────────────────────────────
 *
 * TODO: public RevenueSummary getRevenueSummary(LocalDate date)
 *   - Calls RevenueSummary.from(date, getOrdersForDate(date))
 *   - RevenueSummaryView calls this to populate the 4 stat cards
 *
 * ── STEP 5: Dashboard KPIs ───────────────────────────────────────────────────
 *
 * TODO: public double getTotalRevenue()
 *   - Sum of all completed order totals — shown in Dashboard "Total Revenue" card
 *
 * TODO: public int getTotalOrderCount()
 *   - Total number of orders ever placed — Dashboard "Orders" card
 *
 * TODO: public int getTotalCustomerCount()
 *   - Number of registered customers — Dashboard "Customers" card
 *
 * TODO: public double getAvgOrderValue()
 *   - getTotalRevenue() / getTotalOrderCount() — Dashboard "Avg Order Value" card
 *
 * TODO: public Map<String, Integer> getSalesByDrink()
 *   - Returns drink name → number of units sold
 *   - Dashboard bar chart calls this and updates each series value from 0 to real count
 *
 * ── HOW TO PASS THIS INTO THE UI ─────────────────────────────────────────────
 *
 * In Main.java, create one shared instance:
 *       CoffeeShopSystem system = new CoffeeShopSystem();
 * Then pass it into each screen constructor that needs it:
 *       new Dashboard(system)
 *       new OrderEntryScreen(system, () -> onNavSelect("dashboard"))
 *       new InventoryView(system)
 *       new RevenueSummaryView(system)
 */
public class CoffeeShopSystem {

    // TODO: private final List<Customer> customers = new ArrayList<>();
    // TODO: private final List<Order> orderHistory = new ArrayList<>();
    // TODO: private final Inventory inventory = new Inventory();
    // TODO: private int nextOrderId = 1;

    // TODO: public CoffeeShopSystem() { }  (load seed data here if needed)

    // TODO: public List<Customer> getCustomers() { ... }
    // TODO: public void addCustomer(Customer customer) { ... }

    // TODO: public Order placeOrder(Customer customer, List<Order.OrderLine> lines) { ... }
    // TODO: public List<Order> getRecentOrders(int limit) { ... }
    // TODO: public List<Order> getOrdersForDate(LocalDate date) { ... }

    // TODO: public Inventory getInventory() { ... }

    // TODO: public RevenueSummary getRevenueSummary(LocalDate date) { ... }

    // TODO: public double getTotalRevenue() { ... }
    // TODO: public int getTotalOrderCount() { ... }
    // TODO: public int getTotalCustomerCount() { ... }
    // TODO: public double getAvgOrderValue() { ... }
    // TODO: public Map<String, Integer> getSalesByDrink() { ... }
}
