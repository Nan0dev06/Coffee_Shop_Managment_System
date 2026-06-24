package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/** Pill-shaped status badge (warning / success / neutral). */
public class Badge extends JLabel {

    // WARNING — red pill (low stock, errors)
    // SUCCESS — green pill (in stock, OK)
    // NEUTRAL — beige pill (informational)
    public enum Variant { WARNING, SUCCESS, NEUTRAL }

    private final Color bg; // pill background colour, set from variant

    // text — label shown inside the pill
    // variant — determines pill colour (see Variant enum above)
    public Badge(String text, Variant variant) {
        super(text, SwingConstants.CENTER);
        Color fg;
        switch (variant) {
            case SUCCESS -> { bg = Theme.SUCCESS_BG;  fg = Theme.SUCCESS; }
            case NEUTRAL -> { bg = Theme.BEIGE_100;   fg = Theme.TEXT_SECONDARY; }
            default      -> { bg = Theme.WARNING_BG;  fg = Theme.WARNING_TEXT; } // WARNING
        }
        setForeground(fg);
        setFont(Theme.captionBold());
        setOpaque(false); // pill background is painted manually below
        setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 12, 4, 12)); // padding inside pill
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize(); // prevents the badge from stretching in flex layouts
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(bg);
        // radius == height produces fully round end caps (pill shape)
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
        g2.dispose();
        super.paintComponent(g); // draws the text on top
    }
}
