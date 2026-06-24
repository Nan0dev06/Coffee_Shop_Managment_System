package com.cafelumiere.ui;

import com.cafelumiere.model.Menu;
import com.cafelumiere.model.MenuItem;
import com.cafelumiere.ui.components.ContentPage;
import com.cafelumiere.ui.components.RoundedPanel;
import com.cafelumiere.ui.components.StatCard;
import com.cafelumiere.ui.components.Table;
import com.cafelumiere.ui.theme.Theme;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.CategoryStyler;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dashboard: KPI stat row, popular-drinks bar chart and recent orders. All
 * metrics are empty placeholders — the backend will supply the real values.
 * Only the fixed drink names (chart categories) come from the menu.
 *
 * TODO (backend wiring):
 *   1. Change constructor to accept CoffeeShopSystem:
 *         public Dashboard(CoffeeShopSystem system)
 *   2. Replace the 4 StatCard "—" values with real KPIs:
 *         new StatCard("Total Revenue",   String.format("$%.2f", system.getTotalRevenue()))
 *         new StatCard("Orders",          String.valueOf(system.getTotalOrderCount()))
 *         new StatCard("Customers",       String.valueOf(system.getTotalCustomerCount()))
 *         new StatCard("Avg Order Value", String.format("$%.2f", system.getAvgOrderValue()))
 *   3. In buildChart(), set real sales data per drink:
 *         Map<String, Integer> sales = system.getSalesByDrink();
 *         // for each series at index i: y.set(i, sales.getOrDefault(names.get(i), 0))
 *   4. In ordersCard(), populate the recent orders table:
 *         for (Order o : system.getRecentOrders(5)) {
 *             table.addRow("#" + o.getOrderId(), o.getCustomer().getName(),
 *                          String.format("$%.2f", o.calculateTotal()),
 *                          o.getTimestamp().toLocalTime().toString());
 *         }
 *   5. Add a refresh() method so Main can call dashboard.refresh() after each new order.
 */
public class Dashboard extends ContentPage {

    private static final String EMPTY = "—";

    public Dashboard() {
        super("Dashboard"); // sets page title and beige background via ContentPage

        add(statRow());                          // 4 KPI cards across the top
        add(Box.createVerticalStrut(Theme.S24));
        add(section());                          // chart (left) + recent orders (right)
    }

    // horizontal row of 4 stat cards — all showing "—" until backend supplies real values
    private JComponent statRow() {
        JPanel row = new JPanel(new GridLayout(1, 4, Theme.S16, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        row.add(new StatCard("Total Revenue", EMPTY));
        row.add(new StatCard("Orders", EMPTY));
        row.add(new StatCard("Customers", EMPTY));
        row.add(new StatCard("Avg Order Value", EMPTY));
        return row;
    }

    // two-column layout: chart takes 3 parts width, recent orders takes 2 parts
    private JComponent section() {
        JPanel section = new JPanel(new GridBagLayout());
        section.setOpaque(false);
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.weightx = 3; // chart gets more horizontal space
        gc.insets = new java.awt.Insets(0, 0, 0, Theme.S24);
        section.add(chartCard(), gc);

        gc.gridx = 1;
        gc.weightx = 2;
        gc.insets = new java.awt.Insets(0, 0, 0, 0);
        section.add(ordersCard(), gc);

        return section;
    }

    // white card containing the XChart bar chart
    private JComponent chartCard() {
        RoundedPanel card = cardPanel();
        card.add(sectionTitle("Most Popular Drinks"));
        card.add(Box.createVerticalStrut(Theme.S12));

        XChartPanel<CategoryChart> chartPanel = new XChartPanel<>(buildChart());
        chartPanel.setOpaque(false);
        chartPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(chartPanel);
        return card;
    }

    // builds the XChart bar chart; categories = menu drink names, all bars start at 0
    private CategoryChart buildChart() {
        CategoryChart chart = new CategoryChartBuilder().width(620).height(280).build();

        // Categories are the fixed menu drinks; sales counts come from the
        // backend later, so every bar is zero (empty) for now.
        List<String> names = Menu.items().stream().map(MenuItem::getName).toList();
        for (int i = 0; i < names.size(); i++) {
            List<Integer> y = new ArrayList<>(Collections.nCopies(names.size(), 0));
            CategorySeries s = chart.addSeries("s" + i, names, y);
            s.setFillColor(Theme.CHART_COLORS[i % Theme.CHART_COLORS.length]); // cycle through brown palette
        }

        // style: match the card background, hide legend/labels, show horizontal grid only
        CategoryStyler st = chart.getStyler();
        st.setOverlapped(true);
        st.setAvailableSpaceFill(0.82);
        st.setLegendVisible(false);
        st.setLabelsVisible(false);
        st.setAntiAlias(true);
        st.setChartBackgroundColor(Theme.SURFACE_CARD);
        st.setPlotBackgroundColor(Theme.SURFACE_CARD);
        st.setPlotBorderVisible(false);
        st.setChartPadding(Theme.S8);
        st.setPlotGridVerticalLinesVisible(false);
        st.setPlotGridHorizontalLinesVisible(true);
        st.setPlotGridLinesColor(Theme.BEIGE_300);
        st.setAxisTitlesVisible(false);
        st.setAxisTicksMarksVisible(false);
        st.setAxisTickLabelsColor(Theme.BROWN_900);
        st.setBaseFont(Theme.body());
        st.setAxisTickLabelsFont(Theme.caption());
        st.setChartFontColor(Theme.BROWN_900);
        st.setXAxisLabelRotation(40); // angled labels so long drink names don't overlap
        st.setYAxisMin(0.0);
        st.setYAxisMax(100.0); // stable axis for the empty state
        return chart;
    }

    // white card with a 4-column table showing the most recent orders
    private JComponent ordersCard() {
        RoundedPanel card = cardPanel();
        card.add(sectionTitle("Recent Orders"));
        card.add(Box.createVerticalStrut(Theme.S12));

        // columns: order id | customer name | total (right-aligned) | time
        Table table = new Table(List.of(
            new Table.Column("ID", 70),
            new Table.Column("Customer"),
            new Table.Column("Total", 70, true),
            new Table.Column("Time", 80)
        ));
        table.addEmptyState("No recent orders"); // shown when the table has no rows
        table.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(table);
        card.add(Box.createVerticalGlue());
        return card;
    }

    // shared helper: white rounded card with padding, vertical stack layout
    private RoundedPanel cardPanel() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(Theme.S24, Theme.S24, Theme.S24, Theme.S24));
        return card;
    }
}
