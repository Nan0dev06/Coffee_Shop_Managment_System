package com.cafelumiere.model;

/**
 * A café customer (Tier 1 — Data class).
 *
 * TODO: Add the missing fields from the UML:
 *   - phone: String
 *   - address: String
 *
 * TODO: Add the missing getters from the UML:
 *   - getPhone(): String
 *   - getAddress(): String
 *
 * TODO: Update the constructor to accept phone and address.
 *
 * TODO: Once Customer is complete, wire it into CoffeeShopSystem.addCustomer()
 *       and populate the combo box in OrderEntryScreen with system.getCustomers().
 */
public class Customer {

    private final int customerId;
    private final String name;
    // TODO: private String phone;
    // TODO: private String address;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        // TODO: also accept phone and address here
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    // TODO: public String getPhone() { return phone; }
    // TODO: public String getAddress() { return address; }

    @Override
    public String toString() {
        return name;
    }
}
