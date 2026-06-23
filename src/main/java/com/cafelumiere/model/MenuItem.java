package com.cafelumiere.model;

/**
 * Abstract menu item. Concrete subclasses ({@link HotDrink}, {@link ColdDrink})
 * preserve the inheritance/polymorphism pattern from the project class diagram.
 *
 * TODO: Consider extending for backend use:
 *   - Add a description field (shown on the order card or receipt)
 *   - Add an isAvailable flag so out-of-stock items can be greyed out in the UI
 *   - If pricing gets more complex (e.g. size upgrades, seasonal pricing), override
 *     calculatePrice() in subclasses rather than changing the base price here
 */
public abstract class MenuItem {

    private final String name;
    private final double basePrice;

    // TODO: private String description;
    // TODO: private boolean isAvailable = true;

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

    // TODO: public String getDescription() { ... }
    // TODO: public boolean isAvailable() { ... }
    // TODO: public void setAvailable(boolean available) { ... }

    /** Price shown on the order screen and used in Order.calculateTotal(). */
    public abstract double calculatePrice();
}
