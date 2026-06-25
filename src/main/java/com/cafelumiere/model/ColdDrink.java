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
    // ice level is just a preference, any size will costs $0.50 extra
        return getBasePrice() +0.50;
    }
}

