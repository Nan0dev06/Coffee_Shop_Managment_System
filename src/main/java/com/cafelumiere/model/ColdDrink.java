package com.cafelumiere.model;


public class ColdDrink extends MenuItem {
    //re
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
    switch (getIceLevel().toLowerCase()) {
        case "regular":
            return getBasePrice() + 0.30;
        case "light":
            return getBasePrice() + 0.15; 
        case "extra":
            return getBasePrice() + 0.45; 
        default:
            return getBasePrice(); 
    }
}
}
