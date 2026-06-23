package com.cafelumiere.model;


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
        // Temperature is just a preference, so it costs $0.00 extra
        return getBasePrice();
    }
}
