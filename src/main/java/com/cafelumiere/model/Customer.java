package com.cafelumiere.model;

/** A café customer. Plain data holder for the prototype. */
public class Customer {

    private final int customerId;
    private final String name;

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

    @Override
    public String toString() {
        return name;
    }
}
