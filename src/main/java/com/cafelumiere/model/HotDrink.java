package com.cafelumiere.model;

/**
 * A hot-served drink (e.g. Americano, Latte).
 *
 * TODO: If size options are added (Small / Medium / Large), apply a price
 *       multiplier inside calculatePrice() based on the chosen size.
 *       Example: Small = basePrice, Medium = basePrice + 0.50, Large = basePrice + 1.00
 */
public class HotDrink extends MenuItem {

    private final String temperature;

    // TODO: private Size size;  (Size enum: SMALL, MEDIUM, LARGE)

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
        // TODO: apply size surcharge once Size is added
        return getBasePrice();
    }
}
