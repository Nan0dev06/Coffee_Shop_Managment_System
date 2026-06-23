package com.cafelumiere.ui;

import com.cafelumiere.model.Customer;
import com.cafelumiere.model.Menu;
import com.cafelumiere.model.MenuItem;
import com.cafelumiere.ui.components.Buttons;
import com.cafelumiere.ui.components.ContentPage;
import com.cafelumiere.ui.components.DrinkCard;
import com.cafelumiere.ui.theme.Theme;
import com.k33ptoo.components.KButton;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Order entry: pick a customer and browse the fixed 10 drink cards. The drink
 * menu (names + prices) is fixed data; the customer list is supplied by the
 * backend (empty in this visual prototype).
 */
public class OrderEntryScreen extends ContentPage {

    private static final int COLUMNS = 5;

    public OrderEntryScreen(Runnable onPlaceOrder) {
        super("Order Entry");

        add(topBar(onPlaceOrder));
        add(Box.createVerticalStrut(Theme.S24));
        add(drinkGrid());
        add(Box.createVerticalGlue());
    }

    /** Customer selector on the left, Place Order button on the right. */
    private JComponent topBar(Runnable onPlaceOrder) {
        JPanel bar = new JPanel();
        bar.setOpaque(false);
        bar.setLayout(new BoxLayout(bar, BoxLayout.X_AXIS));
        bar.setAlignmentX(Component.LEFT_ALIGNMENT);
        bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

        JPanel selector = customerSelector();
        selector.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        KButton placeOrder = Buttons.create("Place Order", Buttons.Variant.PRIMARY, Buttons.Size.LG);
        placeOrder.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        placeOrder.addActionListener(e -> onPlaceOrder.run());

        bar.add(selector);
        bar.add(Box.createHorizontalGlue());
        bar.add(placeOrder);
        return bar;
    }

    private JPanel customerSelector() {
        JPanel wrap = new JPanel();
        wrap.setOpaque(false);
        wrap.setLayout(new BoxLayout(wrap, BoxLayout.Y_AXIS));
        wrap.setMaximumSize(new Dimension(260, 60));

        JLabel label = new JLabel("Customer");
        label.setFont(Theme.label());
        label.setForeground(Theme.TEXT_PRIMARY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Populated from the backend later; empty in the prototype.
        JComboBox<Customer> combo = new JComboBox<>(new DefaultComboBoxModel<>(new Customer[0]));
        combo.setFont(Theme.body());
        combo.setForeground(Theme.TEXT_PRIMARY);
        combo.setBackground(Theme.SURFACE_INPUT);
        combo.setBorder(BorderFactory.createLineBorder(Theme.BORDER_MEDIUM));
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);
        combo.setMaximumSize(new Dimension(260, 38));

        wrap.add(label);
        wrap.add(Box.createVerticalStrut(Theme.S4));
        wrap.add(combo);
        return wrap;
    }

    private JComponent drinkGrid() {
        JPanel grid = new JPanel(new GridLayout(0, COLUMNS, Theme.S16, Theme.S16));
        grid.setOpaque(false);
        grid.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (MenuItem item : Menu.items()) {
            grid.add(new DrinkCard(item.getName(), String.format("$%.2f", item.calculatePrice())));
        }
        // Cap height so BoxLayout doesn't stretch the cards vertically.
        grid.setMaximumSize(new Dimension(Integer.MAX_VALUE, grid.getPreferredSize().height));
        return grid;
    }
}
