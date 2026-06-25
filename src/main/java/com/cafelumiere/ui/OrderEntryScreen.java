package com.cafelumiere.ui;

import com.cafelumiere.model.Customer;
import com.cafelumiere.model.Menu;
import com.cafelumiere.model.MenuItem;
import com.cafelumiere.ui.components.Buttons;
import com.cafelumiere.ui.components.ContentPage;
import com.cafelumiere.ui.components.DrinkCard;
import com.cafelumiere.ui.theme.Theme;
import com.k33ptoo.components.KButton;
import com.cafelumiere.system.CoffeeShopSystem;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Order entry: pick a customer from the combo and browse the 10 drink cards.
 */
public class OrderEntryScreen extends ContentPage {

    private static final int COLUMNS = 5; // drinks per row in the grid

    private JComboBox<Customer> combo; // field so Place Order can read the selection
    private final CoffeeShopSystem system;
    public OrderEntryScreen(CoffeeShopSystem system,Runnable onPlaceOrder) {
        super("Order Entry"); // sets page title and beige background via ContentPage
        this.system = system;
        add(topBar(onPlaceOrder)); // customer selector + Place Order button
        add(Box.createVerticalStrut(Theme.S24));
        add(drinkGrid());          // 5-column grid of drink cards
        add(Box.createVerticalGlue());
    }

    /** Customer selector on the left, Place Order button on the right. */
    private JComponent topBar(Runnable onPlaceOrder) {
        // horizontal bar pinned to full width, fixed 64px tall
        JPanel bar = new JPanel();
        bar.setOpaque(false);
        bar.setLayout(new BoxLayout(bar, BoxLayout.X_AXIS));
        bar.setAlignmentX(Component.LEFT_ALIGNMENT);
        bar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

        JPanel selector = customerSelector();
        selector.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        // primary brown button — shows confirmation popup, then navigates to dashboard
        KButton placeOrder = Buttons.create("Place Order", Buttons.Variant.PRIMARY, Buttons.Size.LG);
        placeOrder.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        placeOrder.addActionListener(e -> {
            Customer selected = (Customer) combo.getSelectedItem();
            String customerName = selected != null ? selected.getName() : "Guest";
            String total = "$0.00";

            // apply design-system colors to the native JOptionPane dialog
            UIManager.put("OptionPane.background", Theme.SURFACE_CARD);
            UIManager.put("Panel.background", Theme.SURFACE_CARD);
            UIManager.put("OptionPane.messageForeground", Theme.TEXT_PRIMARY);
            UIManager.put("OptionPane.messageFont", Theme.body());
            UIManager.put("Button.background", Theme.BTN_PRIMARY_START);
            UIManager.put("Button.foreground", java.awt.Color.WHITE);
            UIManager.put("Button.font", Theme.label());

            JOptionPane.showMessageDialog(
                this,
                "Order placed for " + customerName + " — Total: " + total,
                "Order Confirmed",
                JOptionPane.INFORMATION_MESSAGE
            );
            onPlaceOrder.run(); // navigate back to dashboard after confirming
        });

        bar.add(selector);
        bar.add(Box.createHorizontalGlue()); // pushes Place Order to the right edge
        bar.add(placeOrder);
        return bar;
    }

    // label + combo box wrapped vertically
    private JPanel customerSelector() {
        JPanel wrap = new JPanel();
        wrap.setOpaque(false);
        wrap.setLayout(new BoxLayout(wrap, BoxLayout.Y_AXIS));
        wrap.setMaximumSize(new Dimension(260, 60));

        JLabel label = new JLabel("Customer");
        label.setFont(Theme.label());
        label.setForeground(Theme.TEXT_PRIMARY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        
        combo = new JComboBox<>(new DefaultComboBoxModel<>(system.getCustomers().toArray(new Customer[0])));
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

    // 5-column grid — one DrinkCard per menu item (name + calculated price)
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
