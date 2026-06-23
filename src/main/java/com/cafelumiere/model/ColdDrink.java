package com.cafelumiere.model;

/**
 * An iced/cold-served drink (e.g. Iced Latte).
 * Extends MenuItem — matches the UML inheritance arrow.
 *
 * UML fields:
 *   - iceLevel: String
 *
 * UML methods:
 *   + calculatePrice(): double  {override}
 *
 * TODO: Implement calculatePrice() with any pricing logic your team decides on.
 *       For now it returns basePrice unchanged.
 */
public class ColdDrink extends MenuItem {

    private final String iceLevel;

    public ColdDrink(String name, double basePrice) {
        this(name, basePrice, "Regular");
    }

    public ColdDrink(String name, double basePrice, String iceLevel) {
        super(name, basePrice);
        this.iceLevel = iceLevel;
    }

    public String getIceLevel() {
        return iceLevel;
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}
