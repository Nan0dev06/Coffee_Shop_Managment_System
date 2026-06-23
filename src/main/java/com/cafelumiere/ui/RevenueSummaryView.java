package com.cafelumiere.ui;

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

/**
 * Revenue summary: date, KPI stat cards and the day's orders. Metrics are empty
 * placeholders pending the backend; only today's date is shown.
 */
public class RevenueSummaryView extends ContentPage {

    private static final String EMPTY = "—";

    public RevenueSummaryView() {
        super("Revenue Summary");

        add(dateRow());
        add(Box.createVerticalStrut(Theme.S24));
        add(statRow());
        add(Box.createVerticalStrut(Theme.S24));
        add(ordersCard());
        add(Box.createVerticalGlue());
    }

    private JComponent dateRow() {
        JPanel row = new JPanel();
        row.setOpaque(false);
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel label = new JLabel("Date");
        label.setFont(Theme.label());
        label.setForeground(Theme.TEXT_PRIMARY);

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

    private JComponent statRow() {
        JPanel row = new JPanel(new java.awt.GridLayout(1, 4, Theme.S16, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        row.add(new StatCard("Revenue Today", EMPTY));
        row.add(new StatCard("Orders Today", EMPTY));
        row.add(new StatCard("Avg Order Value", EMPTY));
        row.add(new StatCard("Best Seller", EMPTY));
        return row;
    }

    private JComponent ordersCard() {
        Table table = new Table(List.of(
            new Table.Column("Order ID", 90),
            new Table.Column("Customer"),
            new Table.Column("Items"),
            new Table.Column("Total", 90, true)
        ));
        table.addEmptyState("No orders for this date");
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
