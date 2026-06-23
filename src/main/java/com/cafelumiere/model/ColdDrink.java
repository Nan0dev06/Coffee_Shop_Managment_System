package com.cafelumiere.model;

/**
 * An iced/cold-served drink (e.g. Iced Latte).
 *
 * TODO: If size options are added (Small / Medium / Large), apply a price
 *       multiplier inside calculatePrice() based on the chosen size.
 *       Example: Small = basePrice, Medium = basePrice + 0.50, Large = basePrice + 1.00
 *
 * TODO: iceLevel is currently a plain String ("Regular", "Less", "Extra").
 *       Consider converting it to an enum IceLevel { LESS, REGULAR, EXTRA }
 *       so the UI can show a dropdown with fixed choices.
 */
public class ColdDrink extends MenuItem {

    private final String iceLevel;

    // TODO: private Size size;  (Size enum: SMALL, MEDIUM, LARGE)

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
        // TODO: apply size surcharge once Size is added
        return getBasePrice();
    }
}
