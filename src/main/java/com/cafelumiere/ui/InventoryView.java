package com.cafelumiere.ui;

import com.cafelumiere.ui.components.Badge;
import com.cafelumiere.ui.components.Buttons;
import com.cafelumiere.ui.components.ContentPage;
import com.cafelumiere.ui.components.RoundedPanel;
import com.cafelumiere.ui.components.Table;
import com.cafelumiere.ui.theme.Theme;
import com.cafelumiere.system.CoffeeShopSystem;
import com.k33ptoo.components.KButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import java.awt.Component;
import java.util.List;
import java.util.Map;

/**
 * Inventory: shows all ingredient stock with Low Stock / OK badges and Restock buttons.
 */
public class InventoryView extends ContentPage {
    private final CoffeeShopSystem system;
    public InventoryView(CoffeeShopSystem system) {
        super("Inventory"); // sets page title and beige background via ContentPage
        this.system=system;
        build();
    }

    // builds the page body from the current inventory state
    private void build() {
        add(tableCard());   // white card wrapping the ingredient table
        add(Box.createVerticalGlue());
    }

    // re-reads inventory and rebuilds the page — called on nav and after a restock
    public void refresh() {
        clearBody();
        build();
        revalidate();
        repaint();
    }

    // white card containing the 5-column inventory table
    private JComponent tableCard() {
        Table table = new Table(List.of(
            new Table.Column("Ingredient"),
            new Table.Column("Stock", 90, true),
            new Table.Column("Unit", 70),
            new Table.Column("Status", 130),
            new Table.Column("", 130)
        ));

        Map<String, Integer> stock = system.getInventory().getAllStock();
        if (stock.isEmpty()) {
            table.addEmptyState("No inventory data");
        } else {
            for (Map.Entry<String, Integer> entry : stock.entrySet()) {
                String ingredient = entry.getKey();
                Badge badge = system.getInventory().isLowStock(ingredient)
                    ? new Badge("Low Stock", Badge.Variant.WARNING)
                    : new Badge("OK",        Badge.Variant.SUCCESS);
                KButton restock = Buttons.create("Restock", Buttons.Variant.SECONDARY, Buttons.Size.SM);
                restock.addActionListener(e -> {
                    system.getInventory().restock(ingredient, 50);
                    refresh(); // update the stock number and badge immediately
                });
                table.addRow(ingredient, String.valueOf(entry.getValue()), "units", badge, restock);
            }
        }

        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(Theme.S24, Theme.S24, Theme.S24, Theme.S24));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        table.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(table);
        return card;
    }
}
