package com.cafelumiere.ui;

import com.cafelumiere.model.Customer;
import com.cafelumiere.model.Menu;
import com.cafelumiere.model.MenuItem;
import com.cafelumiere.ui.components.Buttons;
import com.cafelumiere.ui.components.ContentPage;
import com.cafelumiere.ui.components.DrinkCard;
import com.cafelumiere.ui.components.RoundedPanel;
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
import javax.swing.JSeparator;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Order entry: pick a customer from the combo and browse the 10 drink cards.
 */
public class OrderEntryScreen extends ContentPage {

    private static final int COLUMNS = 5; // drinks per row in the grid

    private JComboBox<Customer> combo; // field so Place Order can read the selection
    private final CoffeeShopSystem system;
    private final List<MenuItem> cart = new ArrayList<>();
    private final List<DrinkCard> drinkCards = new ArrayList<>();
    private final RoundedPanel cartPanel = new RoundedPanel(); // live cart summary below the grid

    public OrderEntryScreen(CoffeeShopSystem system, Runnable onPlaceOrder) {
        super("Order Entry"); // sets page title and beige background via ContentPage
        this.system = system;
        add(topBar(onPlaceOrder)); // customer selector + Place Order button
        add(Box.createVerticalStrut(Theme.S24));
        add(drinkGrid());          // 5-column grid of drink cards
        add(Box.createVerticalStrut(Theme.S24));
        add(buildCartPanel());     // cart summary — hidden until something is added
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
        placeOrder.addActionListener(e -> {
            Customer selected = (Customer) combo.getSelectedItem();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a customer first.",
                        "No customer selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add at least one drink to the cart.",
                        "Empty cart", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // check every item in the cart has enough stock before committing the order
            for (MenuItem item : cart) {
                if (!system.getInventory().checkAvailability(item)) {
                    JOptionPane.showMessageDialog(this,
                        "Cannot place order — ingredients for \"" + item.getName() + "\" are out of stock.\n"
                        + "Please restock from the Inventory screen first.",
                        "Out of Stock", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            system.placeOrder(selected, cart);
            double total = cart.stream().mapToDouble(MenuItem::calculatePrice).sum();
            cart.clear();
            drinkCards.forEach(DrinkCard::reset);
            refreshCart();

            UIManager.put("OptionPane.background", Theme.SURFACE_CARD);
            UIManager.put("Panel.background", Theme.SURFACE_CARD);
            UIManager.put("OptionPane.messageForeground", Theme.TEXT_PRIMARY);
            UIManager.put("OptionPane.messageFont", Theme.body());
            UIManager.put("Button.background", Theme.BTN_PRIMARY_START);
            UIManager.put("Button.foreground", java.awt.Color.WHITE);
            UIManager.put("Button.font", Theme.label());

            JOptionPane.showMessageDialog(
                this,
                "Order placed for " + selected.getName() + " — Total: " + String.format("$%.2f", total),
                "Order Confirmed",
                JOptionPane.INFORMATION_MESSAGE
            );
            onPlaceOrder.run();
        });

        bar.add(selector);
        bar.add(Box.createHorizontalGlue());
        bar.add(placeOrder);
        return bar;
    }

    public void refreshCustomers() {
        combo.setModel(new DefaultComboBoxModel<>(system.getCustomers().toArray(new Customer[0])));
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

    // 5-column grid — one DrinkCard per menu item
    private JComponent drinkGrid() {
        JPanel grid = new JPanel(new GridLayout(0, COLUMNS, Theme.S16, Theme.S16));
        grid.setOpaque(false);
        grid.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (MenuItem item : Menu.items()) {
            DrinkCard card = new DrinkCard(item.getName(), String.format("$%.2f", item.calculatePrice()),
                qty -> {
                    for (int i = 0; i < qty; i++) cart.add(item);
                    refreshCart();
                });
            drinkCards.add(card);
            grid.add(card);
        }
        grid.setMaximumSize(new Dimension(Integer.MAX_VALUE, grid.getPreferredSize().height));
        return grid;
    }

    // builds the initial (empty, hidden) cart panel and returns it
    private JComponent buildCartPanel() {
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(Theme.S24, Theme.S24, Theme.S24, Theme.S24));
        cartPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cartPanel.setVisible(false); // hidden until something is added
        return cartPanel;
    }

    // rebuilds the cart panel contents from the current cart list
    private void refreshCart() {
        cartPanel.removeAll();

        if (cart.isEmpty()) {
            cartPanel.setVisible(false);
            cartPanel.revalidate();
            cartPanel.repaint();
            return;
        }

        // group by drink name, preserving insertion order
        Map<String, Integer> counts = new LinkedHashMap<>();
        Map<String, MenuItem> byName = new LinkedHashMap<>();
        for (MenuItem item : cart) {
            counts.merge(item.getName(), 1, Integer::sum);
            byName.put(item.getName(), item);
        }

        JLabel title = new JLabel("Cart");
        title.setFont(Theme.subheading());
        title.setForeground(Theme.TEXT_PRIMARY);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        cartPanel.add(title);
        cartPanel.add(Box.createVerticalStrut(Theme.S12));

        JSeparator sep = new JSeparator();
        sep.setForeground(Theme.BORDER_LIGHT);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);
        cartPanel.add(sep);
        cartPanel.add(Box.createVerticalStrut(Theme.S12));

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String name = entry.getKey();
            int qty      = entry.getValue();
            MenuItem item = byName.get(name);
            double subtotal = item.calculatePrice() * qty;

            JPanel row = new JPanel();
            row.setOpaque(false);
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

            JLabel nameLbl = new JLabel(name);
            nameLbl.setFont(Theme.body());
            nameLbl.setForeground(Theme.TEXT_PRIMARY);

            JLabel qtyLbl = new JLabel("×" + qty);
            qtyLbl.setFont(Theme.bodyBold());
            qtyLbl.setForeground(Theme.TEXT_SECONDARY);

            JLabel priceLbl = new JLabel(String.format("$%.2f", subtotal));
            priceLbl.setFont(Theme.body());
            priceLbl.setForeground(Theme.TEXT_PRIMARY);

            // removes one copy of this drink from the cart
            KButton minus = Buttons.create("−", Buttons.Variant.DANGER, Buttons.Size.SM);
            minus.setPreferredSize(new Dimension(34, 28));
            minus.setMaximumSize(new Dimension(34, 28));
            minus.addActionListener(ev -> {
                // find and remove the last occurrence of this item
                for (int i = cart.size() - 1; i >= 0; i--) {
                    if (cart.get(i).getName().equals(name)) {
                        cart.remove(i);
                        break;
                    }
                }
                refreshCart();
            });

            row.add(nameLbl);
            row.add(Box.createHorizontalStrut(Theme.S12));
            row.add(qtyLbl);
            row.add(Box.createHorizontalGlue());
            row.add(priceLbl);
            row.add(Box.createHorizontalStrut(Theme.S12));
            row.add(minus);

            cartPanel.add(row);
            cartPanel.add(Box.createVerticalStrut(Theme.S8));
        }

        // total row at the bottom
        cartPanel.add(Box.createVerticalStrut(Theme.S4));
        JSeparator sepBottom = new JSeparator();
        sepBottom.setForeground(Theme.BORDER_LIGHT);
        sepBottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sepBottom.setAlignmentX(Component.LEFT_ALIGNMENT);
        cartPanel.add(sepBottom);
        cartPanel.add(Box.createVerticalStrut(Theme.S12));

        double total = cart.stream().mapToDouble(MenuItem::calculatePrice).sum();
        JPanel totalRow = new JPanel();
        totalRow.setOpaque(false);
        totalRow.setLayout(new BoxLayout(totalRow, BoxLayout.X_AXIS));
        totalRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel totalLbl = new JLabel("Total");
        totalLbl.setFont(Theme.label());
        totalLbl.setForeground(Theme.TEXT_PRIMARY);

        JLabel totalAmt = new JLabel(String.format("$%.2f", total));
        totalAmt.setFont(Theme.subheading());
        totalAmt.setForeground(Theme.TEXT_PRIMARY);

        totalRow.add(totalLbl);
        totalRow.add(Box.createHorizontalGlue());
        totalRow.add(totalAmt);
        cartPanel.add(totalRow);

        cartPanel.setVisible(true);
        cartPanel.revalidate();
        cartPanel.repaint();
    }
}
