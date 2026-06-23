package com.cafelumiere.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A single customer order (Tier 2 — Logic class).
 *
 * TODO: Add all fields from the UML:
 *   - orderId: int  {unique, auto-increment}  — assigned by CoffeeShopSystem
 *   - customer: Customer
 *   - items: List<MenuItem>
 *   - dateTime: LocalDateTime
 *
 * TODO: Implement the constructor:
 *   public Order(int orderId, Customer customer, List<MenuItem> items) {
 *       this.orderId = orderId;
 *       this.customer = customer;
 *       this.items = items;
 *       this.dateTime = LocalDateTime.now();
 *   }
 *
 * TODO: Implement the methods from the UML:
 *
 *   + addItem(item: MenuItem): void
 *       items.add(item);
 *
 *   + calculateTotal(): double
 *       return items.stream().mapToDouble(MenuItem::calculatePrice).sum();
 *
 *   + getOrderId(): int
 *       return orderId;
 *
 *   + getCustomer(): Customer
 *       return customer;
 *
 * TODO: Wire into CoffeeShopSystem.placeOrder() — it creates the Order,
 *       sets the orderId, and adds it to the orders list.
 *
 * TODO: Once done, update OrderEntryScreen's Place Order popup to use:
 *       Order order = system.placeOrder(selectedCustomer, selectedItems);
 *       "Order placed for " + order.getCustomer().getName()
 *       + " — Total: $" + String.format("%.2f", order.calculateTotal())
 */
public class Order {

    // TODO: private int orderId;
    // TODO: private Customer customer;
    // TODO: private List<MenuItem> items;
    // TODO: private LocalDateTime dateTime;

    // TODO: public Order(int orderId, Customer customer, List<MenuItem> items) { ... }

    // TODO: public void addItem(MenuItem item) { ... }
    // TODO: public double calculateTotal() { ... }
    // TODO: public int getOrderId() { ... }
    // TODO: public Customer getCustomer() { ... }
    // TODO: public List<MenuItem> getItems() { ... }
    // TODO: public LocalDateTime getDateTime() { ... }
}
