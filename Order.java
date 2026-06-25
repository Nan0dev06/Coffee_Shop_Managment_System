package com.cafelumiere.model;

import java.time.LocalDateTime;
import java.util.*;
import java.io.Serializable;
/**
 * ============================================================
 * ORDER CLASS
 * ============================================================
 *
 * This class represents a single order made by a customer.
 *
 * - addItem()        : add a drink to the order
 * - calculateTotal() : get the total price
 * - getItems()       : see what drinks are in the order
 * - getOrderId()     : get the order number
 * - getCustomer()    : get who made the order
 * - getDateTime()    : get when the order was made
 *
 * Data is saved to a file using Serializable.
 */
public class Order implements Serializable {

    //========data fields ==============
   
    private static int nextId = 1; 
    private final int orderId;  
    private final Customer customer;
    private final List<MenuItem> items;
    private final LocalDateTime dateTime;
    
//===============Constructor ===============
    // Called once per order. Auto-assigns ID and timestamps the order.
    public Order(Customer customer) {
        this.orderId  = nextId++;       
        this.customer = customer;
        this.items    = new ArrayList<>();
        this.dateTime = LocalDateTime.now();
    }
    
 //======getter method=============
    public int        
          getOrderId()  { return orderId; }
    public Customer     
         getCustomer() { return customer; }
    public LocalDateTime 
         getDateTime() { return dateTime; }
    
 //==============method ===================
    
// ==== addItem ===
    // Adds a drink to this order. Validates input first 
    public void addItem(MenuItem item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        items.add(item);
    }
//==claculate total=========
     // same method for hot and cold drink 
   public double calculateTotal() {
    double total = 0;
    for (MenuItem item : items) {
        total += item.calculatePrice();
    }
    return total;
}


    // her returns copy 
   public List<MenuItem> getItems() {
    return new ArrayList<>(items);
}

    
    @Override
    public String toString() {
        return String.format("Order #%d | %s | %d items | $%.2f",
            orderId, customer.getName(), items.size(), calculateTotal());
    }
}
