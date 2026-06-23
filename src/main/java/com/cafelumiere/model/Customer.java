package com.cafelumiere.model;

/**
 * A café customer.
 *
 * TODO: Extend this class for real use:
 *   - Add a phone number or email field for contact/loyalty lookup
 *   - Add a loyaltyPoints field if a loyalty system is required
 *   - Add a static loadAll() or a CustomerRepository class that reads customers
 *     from a data source (file, database, etc.) and returns List<Customer>
 *   - Wire loadAll() into OrderEntryScreen's combo box so the dropdown is populated
 *     on startup instead of being empty
 */
public class Customer {

    private final int customerId;
    private final String name;

    // TODO: private String phone;
    // TODO: private int loyaltyPoints;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    // TODO: public String getPhone() { ... }
    // TODO: public int getLoyaltyPoints() { ... }
    // TODO: public void addLoyaltyPoints(int points) { ... }

    @Override
    public String toString() {
        return name;
    }
}
