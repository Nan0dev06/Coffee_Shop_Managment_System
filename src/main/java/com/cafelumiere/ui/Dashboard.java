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
 */
public class Dashboard extends ContentPage {

    private static final String EMPTY = "—";

    public Dashboard() {
        super("Dashboard");

        add(statRow());
        add(Box.createVerticalStrut(Theme.S24));
        add(section());
    }

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

    private JComponent section() {
        JPanel section = new JPanel(new GridBagLayout());
        section.setOpaque(false);
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridy = 0;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.weightx = 3;
        gc.insets = new java.awt.Insets(0, 0, 0, Theme.S24);
        section.add(chartCard(), gc);

        gc.gridx = 1;
        gc.weightx = 2;
        gc.insets = new java.awt.Insets(0, 0, 0, 0);
        section.add(ordersCard(), gc);

        return section;
    }

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

    private CategoryChart buildChart() {
        CategoryChart chart = new CategoryChartBuilder().width(620).height(280).build();

        // Categories are the fixed menu drinks; sales counts come from the
        // backend later, so every bar is zero (empty) for now.
        List<String> names = Menu.items().stream().map(MenuItem::getName).toList();
        for (int i = 0; i < names.size(); i++) {
            List<Integer> y = new ArrayList<>(Collections.nCopies(names.size(), 0));
            CategorySeries s = chart.addSeries("s" + i, names, y);
            s.setFillColor(Theme.CHART_COLORS[i % Theme.CHART_COLORS.length]);
        }

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
        st.setXAxisLabelRotation(40);
        st.setYAxisMin(0.0);
        st.setYAxisMax(100.0); // stable axis for the empty state
        return chart;
    }

    private JComponent ordersCard() {
        RoundedPanel card = cardPanel();
        card.add(sectionTitle("Recent Orders"));
        card.add(Box.createVerticalStrut(Theme.S12));

        Table table = new Table(List.of(
            new Table.Column("ID", 70),
            new Table.Column("Customer"),
            new Table.Column("Total", 70, true),
            new Table.Column("Time", 80)
        ));
        table.addEmptyState("No recent orders");
        table.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(table);
        card.add(Box.createVerticalGlue());
        return card;
    }

    private RoundedPanel cardPanel() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(Theme.S24, Theme.S24, Theme.S24, Theme.S24));
        return card;
    }
}
