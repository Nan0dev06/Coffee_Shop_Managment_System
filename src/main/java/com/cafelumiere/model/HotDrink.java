package com.cafelumiere.model;

/**
 * A hot-served drink (e.g. Americano, Latte).
 * Extends MenuItem — matches the UML inheritance arrow.
 *
 * UML fields:
 *   - temperature: String
 *
 * UML methods:
 *   + calculatePrice(): double  {override}
 *
 * TODO: Implement calculatePrice() with any pricing logic your team decides on.
 *       For now it returns basePrice unchanged.
 */
public class HotDrink extends MenuItem {

    private final String temperature;

    public HotDrink(String name, double basePrice) {
        this(name, basePrice, "Hot");
    }

    public HotDrink(String name, double basePrice, String temperature) {
        super(name, basePrice);
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}
