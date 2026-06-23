package com.cafelumiere.model;

/**
 * A café customer (Tier 1 — Data class).
 *
 * 
 *
 * TODO: Once Customer is complete, wire it into CoffeeShopSystem.addCustomer()
 *       and populate the combo box in OrderEntryScreen with system.getCustomers().
 */
public class Customer {
    private static int idTracker = 0;
    private final int customerId;
    private final String name;
    
    private String phone;

   
    private String address;
    public Customer(int customerId, String name,String phone,String address) {
        idTracker++;
        this.customerId = idTracker; 
        this.name = name;
        this.phone=phone;
        this.address=address;
        
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
    


    public String getAddress() {
        return address;
    }
    @Override
    public String toString() {
        return name;
    }
}
