package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;

import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple alternating-row table for Inventory and Recent Orders. No pagination
 * or search, per the design-system spec. Cells may be Strings (rendered as
 * labels) or arbitrary Components (e.g. {@link Badge}s, buttons).
 */
public class Table extends JPanel {

    // Column record: label = header text, width = fixed px (0 = flexible), rightAlign = right-justify text
    public record Column(String label, int width, boolean rightAlign) {
        public Column(String label) { this(label, 0, false); }
        public Column(String label, int width) { this(label, width, false); }
    }

    private final List<Column> columns;

    // columns — list defining all column headers and their widths
    public Table(List<Column> columns) {
        this.columns = columns;
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addHeader(); // always added first as the top row
    }

    // renders the header row with bold secondary-coloured labels and a bottom border
    private void addHeader() {
        JPanel header = newRow();
        for (Column col : columns) {
            JLabel l = new JLabel(col.label());
            l.setFont(Theme.captionBold());
            l.setForeground(Theme.TEXT_SECONDARY);
            l.setHorizontalAlignment(col.rightAlign() ? JLabel.RIGHT : JLabel.LEFT);
            header.add(wrapCell(l, col));
        }
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER_LIGHT)); // divider line under header
        add(header);
    }

    /** Adds a data row. Each cell is a String or a Component. */
    public void addRow(Object... cells) {
        int index = getComponentCount() - 1; // count existing rows (minus header) for stripe colour
        Color bg = index % 2 == 0 ? Theme.SURFACE_CARD : Theme.SURFACE_ALT_ROW; // alternating row colours
        JPanel row = newRow();
        row.setOpaque(true);
        row.setBackground(bg);
        for (int i = 0; i < columns.size(); i++) {
            Column col = columns.get(i);
            Object cell = i < cells.length ? cells[i] : "";
            Component comp;
            if (cell instanceof Component c) {
                // component cell (e.g. Badge or KButton) — wrap in a holder for alignment
                JPanel holder = new JPanel();
                holder.setOpaque(false);
                holder.setLayout(new BoxLayout(holder, BoxLayout.X_AXIS));
                if (col.rightAlign()) holder.add(Box.createHorizontalGlue());
                holder.add(c);
                if (!col.rightAlign()) holder.add(Box.createHorizontalGlue());
                comp = holder;
            } else {
                // string cell — render as a plain label
                JLabel l = new JLabel(String.valueOf(cell));
                l.setFont(Theme.body());
                l.setForeground(Theme.TEXT_PRIMARY);
                l.setHorizontalAlignment(col.rightAlign() ? JLabel.RIGHT : JLabel.LEFT);
                comp = l;
            }
            row.add(wrapCell(comp, col));
        }
        add(row);
    }

    /** Adds a centred muted message spanning the table — used when there is no data. */
    public void addEmptyState(String message) {
        JPanel row = newRow();
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, Theme.ROW_MIN_HEIGHT + Theme.S16));
        JLabel l = new JLabel(message);
        l.setFont(Theme.body());
        l.setForeground(Theme.TEXT_SECONDARY);
        // horizontal glue on both sides centres the message
        row.add(Box.createHorizontalGlue());
        row.add(l);
        row.add(Box.createHorizontalGlue());
        add(row);
    }

    // creates a blank horizontal row panel with a fixed height equal to ROW_MIN_HEIGHT
    private JPanel newRow() {
        JPanel row = new JPanel();
        row.setOpaque(false);
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, Theme.ROW_MIN_HEIGHT));
        row.setMinimumSize(new Dimension(10, Theme.ROW_MIN_HEIGHT));
        return row;
    }

    /** Applies fixed/flexible column sizing and cell padding. */
    private JComponent wrapCell(Component inner, Column col) {
        JPanel cell = new JPanel();
        cell.setOpaque(false);
        cell.setLayout(new BoxLayout(cell, BoxLayout.X_AXIS));
        cell.setBorder(BorderFactory.createEmptyBorder(Theme.S12, Theme.S8, Theme.S12, Theme.S8));
        cell.add(inner);

        int h = Theme.ROW_MIN_HEIGHT;
        if (col.width() > 0) {
            // fixed-width column — all three sizes set to prevent stretching
            cell.setPreferredSize(new Dimension(col.width(), h));
            cell.setMaximumSize(new Dimension(col.width(), h));
            cell.setMinimumSize(new Dimension(col.width(), h));
        } else {
            // flexible column — grows to fill available horizontal space
            cell.setPreferredSize(new Dimension(160, h));
            cell.setMaximumSize(new Dimension(Integer.MAX_VALUE, h));
            cell.setMinimumSize(new Dimension(80, h));
        }
        return cell;
    }
}
