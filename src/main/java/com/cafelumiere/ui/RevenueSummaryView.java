package com.cafelumiere.ui;

import com.cafelumiere.model.Order;
import com.cafelumiere.reports.RevenueSummary;
import com.cafelumiere.system.CoffeeShopSystem;
import com.cafelumiere.ui.components.ContentPage;
import com.cafelumiere.ui.components.RoundedPanel;
import com.cafelumiere.ui.components.StatCard;
import com.cafelumiere.ui.components.Table;
import com.cafelumiere.ui.theme.Theme;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Revenue summary: today's date, KPI stat cards, and today's orders table.
 */
public class RevenueSummaryView extends ContentPage {


    private final CoffeeShopSystem system;
    public RevenueSummaryView(CoffeeShopSystem system) {
        super("Revenue Summary"); // sets page title and beige background via ContentPage
        this.system = system;
        add(dateRow());                          // "Date" label + today's date chip
        add(Box.createVerticalStrut(Theme.S24));
        add(statRow());                          // 4 KPI stat cards (all "—" for now)
        add(Box.createVerticalStrut(Theme.S24));
        add(ordersCard());                       // white card with today's orders table
        add(Box.createVerticalGlue());
    }

    // single row: "Date" label + a rounded chip showing today's date
    private JComponent dateRow() {
        JPanel row = new JPanel();
        row.setOpaque(false);
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel label = new JLabel("Date");
        label.setFont(Theme.label());
        label.setForeground(Theme.TEXT_PRIMARY);

        // rounded pill showing today's date — purely visual, not a date picker
        RoundedPanel chip = new RoundedPanel();
        chip.setRadius(Theme.RADIUS_MD).setBorderColor(Theme.BORDER_MEDIUM);
        chip.setLayout(new BoxLayout(chip, BoxLayout.X_AXIS));
        chip.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        chip.setMaximumSize(new Dimension(180, 38));
        JLabel date = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));
        date.setFont(Theme.body());
        date.setForeground(Theme.TEXT_PRIMARY);
        chip.add(date);

        row.add(label);
        row.add(Box.createHorizontalStrut(Theme.S12));
        row.add(chip);
        row.add(Box.createHorizontalGlue());
        return row;
    }

    // 4 KPI stat cards for today built from RevenueSummary
    private JComponent statRow() {
        List<Order> todayOrders = system.getOrders().stream()
            .filter(o -> o.getDateTime().toLocalDate().equals(LocalDate.now()))
            .collect(Collectors.toList());
        RevenueSummary summary = new RevenueSummary(LocalDate.now(), todayOrders);

        JPanel row = new JPanel(new java.awt.GridLayout(1, 4, Theme.S16, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        row.add(new StatCard("Revenue Today",   String.format("$%.2f", summary.getTotalRevenue())));
        row.add(new StatCard("Orders Today",    String.valueOf(summary.getOrderCount())));
        row.add(new StatCard("Avg Order Value", String.format("$%.2f", summary.getAvgOrderValue())));
        row.add(new StatCard("Best Seller",     summary.getBestSellingItem()));
        return row;
    }

    // white card with a table listing all orders placed today
    private JComponent ordersCard() {
        Table table = new Table(List.of(
            new Table.Column("Order ID", 90),
            new Table.Column("Customer"),
            new Table.Column("Items"),
            new Table.Column("Total", 90, true)
        ));

        List<Order> todayOrders = system.getOrders().stream()
            .filter(o -> o.getDateTime().toLocalDate().equals(LocalDate.now()))
            .collect(Collectors.toList());

        if (todayOrders.isEmpty()) {
            table.addEmptyState("No orders for this date");
        } else {
            for (Order o : todayOrders) {
                String items = o.getItems().stream()
                    .map(i -> i.getName())
                    .collect(Collectors.joining(", "));
                table.addRow("#" + o.getOrderId(), o.getCustomer().getName(),
                    items, String.format("$%.2f", o.calculateTotal()));
            }
        }
        table.setAlignmentX(Component.LEFT_ALIGNMENT);

        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(Theme.S24, Theme.S24, Theme.S24, Theme.S24));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sectionTitle("Today's Orders"));
        card.add(Box.createVerticalStrut(Theme.S12));
        card.add(table);
        return card;
    }
}
