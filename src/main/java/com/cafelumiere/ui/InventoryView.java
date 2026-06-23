package com.cafelumiere.ui;

import com.cafelumiere.ui.components.ContentPage;
import com.cafelumiere.ui.components.RoundedPanel;
import com.cafelumiere.ui.components.Table;
import com.cafelumiere.ui.theme.Theme;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import java.awt.Component;
import java.util.List;

/**
 * Inventory: ingredient stock list. Rows are supplied by the backend (low-stock
 * badges and Restock buttons will render per row then); empty in the prototype.
 *
 * TODO (backend wiring):
 *   1. Change constructor to accept CoffeeShopSystem:
 *         public InventoryView(CoffeeShopSystem system)
 *   2. In tableCard(), replace addEmptyState() with real rows:
 *         for (Inventory.IngredientStock s : system.getInventory().getAll()) {
 *             Badge statusBadge = system.getInventory().isLowStock(s)
 *                 ? new Badge("Low Stock", Badge.Variant.WARNING)
 *                 : new Badge("OK",        Badge.Variant.SUCCESS);
 *             KButton restock = Buttons.create("Restock", Buttons.Variant.SECONDARY, Buttons.Size.SM);
 *             restock.addActionListener(e -> {
 *                 system.getInventory().restock(s.ingredient(), 50.0);
 *                 // TODO: refresh the table after restocking
 *             });
 *             table.addRow(s.ingredient(), String.valueOf(s.quantity()), s.unit(), statusBadge, restock);
 *         }
 *   3. Add a refresh() method that clears and repopulates the table,
 *      called after any restock action or after a new order deducts stock.
 */
public class InventoryView extends ContentPage {

    public InventoryView() {
        super("Inventory");
        add(tableCard());
        add(Box.createVerticalGlue());
    }

    private JComponent tableCard() {
        Table table = new Table(List.of(
            new Table.Column("Ingredient"),
            new Table.Column("Stock", 90, true),
            new Table.Column("Unit", 70),
            new Table.Column("Status", 130),
            new Table.Column("", 130)
        ));
        table.addEmptyState("No inventory data");

        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(Theme.S24, Theme.S24, Theme.S24, Theme.S24));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        table.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(table);
        return card;
    }
}
