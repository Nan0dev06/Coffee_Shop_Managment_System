package com.cafelumiere.model;

import java.io.Serializable;
import java.util.List;

/**
 * A café customer (Tier 1 — Data class).
 */
public class Customer implements Serializable {
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

    // After loading customers from disk, advance the ID counter past the highest
    // saved ID so newly added customers don't collide with loaded ones.
    public static void syncTracker(List<Customer> loaded) {
        for (Customer c : loaded) {
            if (c.customerId > idTracker) idTracker = c.customerId;
        }
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
