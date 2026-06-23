package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * A panel with a flat solid fill, rounded corners and an optional 1px solid
 * border — the card surface used throughout the design system. No shadows or
 * alpha, per the Swing rendering constraints.
 */
public class RoundedPanel extends JPanel {

    private Color fill = Theme.SURFACE_CARD;
    private Color borderColor = Theme.BORDER_LIGHT;
    private int radius = Theme.RADIUS_LG;
    private int borderThickness = 1;

    public RoundedPanel() {
        setOpaque(false);
    }

    public RoundedPanel setFill(Color c) { this.fill = c; return this; }
    public RoundedPanel setBorderColor(Color c) { this.borderColor = c; return this; }
    public RoundedPanel setRadius(int r) { this.radius = r; return this; }
    public RoundedPanel setBorderThickness(int t) { this.borderThickness = t; return this; }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        if (fill != null) {
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, w - 1, h - 1, radius, radius);
        }
        if (borderColor != null && borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new java.awt.BasicStroke(borderThickness));
            g2.drawRoundRect(0, 0, w - 1, h - 1, radius, radius);
        }
        g2.dispose();
        super.paintComponent(g);
    }
}
