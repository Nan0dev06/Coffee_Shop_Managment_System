package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Dashboard KPI card: left accent gradient strip + metric label + large value.
 * The trend indicator from the design-system spec is intentionally omitted —
 * the prototype has no trend data.
 */
public class StatCard extends RoundedPanel {

    public StatCard(String label, String value) {
        this(label, value, true);
    }

    public StatCard(String label, String value, boolean showAccent) {
        setLayout(new BorderLayout());
        setRadius(Theme.RADIUS_LG);
        setFill(Theme.SURFACE_CARD);
        setBorderColor(Theme.BORDER_LIGHT);
        setMinimumSize(new Dimension(Theme.CARD_MIN_WIDTH, 92));
        setPreferredSize(new Dimension(230, 96));

        if (showAccent) {
            add(new AccentStrip(), BorderLayout.WEST);
        }

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(
                Theme.S24, Theme.S24, Theme.S24, Theme.S24));

        JLabel labelLbl = new JLabel(label);
        labelLbl.setFont(Theme.labelPlain());
        labelLbl.setForeground(Theme.TEXT_SECONDARY);
        labelLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel valueLbl = new JLabel(value);
        valueLbl.setFont(Theme.heading());
        valueLbl.setForeground(Theme.TEXT_PRIMARY);
        valueLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        content.add(labelLbl);
        content.add(javax.swing.Box.createVerticalStrut(Theme.S4));
        content.add(valueLbl);

        add(content, BorderLayout.CENTER);
    }

    /** 4px vertical 2-colour gradient strip (brown-600 → brown-400). */
    private static class AccentStrip extends JComponent {
        AccentStrip() {
            setPreferredSize(new Dimension(4, 10));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(new GradientPaint(0, 0, Theme.BROWN_600, 0, getHeight(), Theme.BROWN_400));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }
}
