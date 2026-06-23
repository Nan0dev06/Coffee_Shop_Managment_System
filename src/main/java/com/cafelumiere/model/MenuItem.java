package com.cafelumiere.model;

/**
 * Abstract menu item. Concrete subclasses ({@link HotDrink}, {@link ColdDrink})
 * preserve the inheritance/polymorphism pattern from the project class diagram.
 * Pricing here is intentionally trivial — this is a visual prototype, not the
 * backend.
 */
public abstract class MenuItem {

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

    /** Price shown on the order screen. */
    public abstract double calculatePrice();
}
