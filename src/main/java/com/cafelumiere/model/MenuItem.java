package com.cafelumiere.model;

import java.io.Serializable;

/**
 * Abstract menu item (Tier 1 — Data class).
 * Concrete subclasses {@link HotDrink} and {@link ColdDrink} each override
 * calculatePrice() — this is the polymorphism pattern from the UML.
 *
 * Fields and methods match the UML exactly:
 *   - name: String
 *   - basePrice: double
 *   + getName(): String
 *   + calculatePrice(): double  {abstract}
 *
 * No other fields or methods should be added here unless the UML is updated.
 */
public abstract class MenuItem implements Serializable {

    private final String name;
    private final double basePrice;

    protected MenuItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    protected double getBasePrice() {
        return basePrice;
    }

    public abstract double calculatePrice();
}
