package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;
import com.k33ptoo.components.KButton;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Product card for the Order Entry screen: circular placeholder (first letter),
 * drink name, price, a quantity stepper and a full-width primary "Add to Cart"
 * button. No real image upload, per the design-system spec.
 */
public class DrinkCard extends RoundedPanel {

    private static final int CARD_WIDTH = 200;
    private int quantity = 1;

    public DrinkCard(String name, String price) {
        setRadius(Theme.RADIUS_LG);
        setFill(Theme.SURFACE_CARD);
        setBorderColor(Theme.BORDER_LIGHT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(
                Theme.S16, Theme.S16, Theme.S16, Theme.S16));
        Dimension fixed = new Dimension(CARD_WIDTH, 300);
        setPreferredSize(fixed);
        setMaximumSize(fixed);

        CirclePlaceholder circle = new CirclePlaceholder(name.replace("Iced ", "").charAt(0));
        circle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLbl = new JLabel(name, SwingConstants.CENTER);
        nameLbl.setFont(Theme.subheading());
        nameLbl.setForeground(Theme.TEXT_PRIMARY);
        nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLbl = new JLabel(price, SwingConstants.CENTER);
        priceLbl.setFont(Theme.labelPlain());
        priceLbl.setForeground(Theme.TEXT_SECONDARY);
        priceLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel qty = buildQuantityStepper();
        qty.setAlignmentX(Component.CENTER_ALIGNMENT);

        KButton addBtn = Buttons.create("Add to Cart", Buttons.Variant.PRIMARY, Buttons.Size.MD);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        // Visual prototype: no cart logic wired.

        add(circle);
        add(Box.createVerticalStrut(Theme.S8));
        add(nameLbl);
        add(Box.createVerticalStrut(Theme.S4));
        add(priceLbl);
        add(Box.createVerticalStrut(Theme.S12));
        add(qty);
        add(Box.createVerticalStrut(Theme.S12));
        add(addBtn);
    }

    private JPanel buildQuantityStepper() {
        JPanel row = new JPanel();
        row.setOpaque(false);
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        JLabel qtyLbl = new JLabel(String.valueOf(quantity), SwingConstants.CENTER);
        qtyLbl.setFont(Theme.bodyBold());
        qtyLbl.setForeground(Theme.TEXT_PRIMARY);
        qtyLbl.setPreferredSize(new Dimension(40, 28));
        qtyLbl.setMaximumSize(new Dimension(40, 28));
        qtyLbl.setHorizontalAlignment(SwingConstants.CENTER);

        KButton minus = stepperButton("−");
        KButton plus = stepperButton("+");
        minus.addActionListener(e -> {
            if (quantity > 1) { quantity--; qtyLbl.setText(String.valueOf(quantity)); }
        });
        plus.addActionListener(e -> {
            quantity++; qtyLbl.setText(String.valueOf(quantity));
        });

        row.add(minus);
        row.add(qtyLbl);
        row.add(plus);
        return row;
    }

    private KButton stepperButton(String text) {
        KButton b = Buttons.create(text, Buttons.Variant.SECONDARY, Buttons.Size.SM);
        Dimension d = new Dimension(34, 28);
        b.setPreferredSize(d);
        b.setMaximumSize(d);
        return b;
    }

    /** 120px circle (beige-100) with a single centred placeholder letter. */
    private static class CirclePlaceholder extends JComponent {
        private final char letter;

        CirclePlaceholder(char letter) {
            this.letter = letter;
            Dimension d = new Dimension(120, 120);
            setPreferredSize(d);
            setMaximumSize(d);
            setMinimumSize(d);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            int d = Math.min(getWidth(), getHeight());
            int x = (getWidth() - d) / 2;
            g2.setColor(Theme.BEIGE_100);
            g2.fillOval(x, 0, d, d);
            g2.setColor(Theme.BROWN_400);
            g2.setFont(Theme.font(java.awt.Font.BOLD, 36));
            String s = String.valueOf(letter);
            java.awt.FontMetrics fm = g2.getFontMetrics();
            int tx = x + (d - fm.stringWidth(s)) / 2;
            int ty = (d - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(s, tx, ty);
            g2.dispose();
        }
    }
}
