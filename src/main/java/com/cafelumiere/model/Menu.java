package com.cafelumiere.model;

import java.util.List;

/**
 * The fixed café menu: 5 drinks, each available Hot ({@link HotDrink}) and
 * Iced ({@link ColdDrink}) — 10 distinct items. Hardcoded for the prototype.
 */
public final class Menu {

    private Menu() {}

    public static List<MenuItem> items() {
        return List.of(
            new HotDrink("Americano",   4.50),
            new ColdDrink("Iced Americano",  4.50),
            new HotDrink("Latte",       5.00),
            new ColdDrink("Iced Latte",      5.00),
            new HotDrink("Cappuccino",  4.75),
            new ColdDrink("Iced Cappuccino", 4.75),
            new HotDrink("Espresso",    3.50),
            new ColdDrink("Iced Espresso",   3.50),
            new HotDrink("Java",        4.25),
            new ColdDrink("Iced Java",       4.25)
        );
    }
}
