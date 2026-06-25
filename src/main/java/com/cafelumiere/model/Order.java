package com.cafelumiere.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    private static int nextId = 1; // shared counter across all Order objects
    private final int orderId;
    private final Customer customer;
    private final List<MenuItem> items;
    private final LocalDateTime dateTime;

    // Called once per order. Auto-assigns ID and timestamps the order.
    public Order(Customer customer) {
        this.orderId  = nextId++;
        this.customer = customer;
        this.items    = new ArrayList<>();
        this.dateTime = LocalDateTime.now();
    }

    public int           getOrderId()  { return orderId; }
    public Customer      getCustomer() { return customer; }
    public LocalDateTime getDateTime() { return dateTime; }

    // Adds a drink to this order.
    public void addItem(MenuItem item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        items.add(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.calculatePrice();
        }
        return total;
    }

    // Returns a defensive copy — outside code cannot modify the internal list
    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public String toString() {
        return String.format("Order #%d | %s | %d items | $%.2f",
            orderId, customer.getName(), items.size(), calculateTotal());
    }
}
