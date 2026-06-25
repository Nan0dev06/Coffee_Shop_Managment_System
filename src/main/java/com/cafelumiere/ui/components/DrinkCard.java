package com.cafelumiere.ui.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.cafelumiere.ui.theme.Theme;
import com.k33ptoo.components.KButton;


/**
 * Product card for the Order Entry screen: circular drink photo, name, price,
 * a quantity stepper (− / count / +) and a full-width "Add to Cart" button.
 * Images are loaded from /images/ at runtime; falls back to a plain beige circle.
 */
public class DrinkCard extends RoundedPanel {

    private static final int CARD_WIDTH = 200;
    private int quantity = 1; // current stepper value; starts at 1

    // name — drink name (used to derive the image filename)
    // price — formatted price string e.g. "$3.50"
    // onAdd — called with the current quantity when "Add to Cart" is clicked
    public DrinkCard(String name, String price, Consumer<Integer> onAdd) {
        setRadius(Theme.RADIUS_LG);
        setFill(Theme.SURFACE_CARD);
        setBorderColor(Theme.BORDER_LIGHT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(
                Theme.S16, Theme.S16, Theme.S16, Theme.S16));
        Dimension fixed = new Dimension(CARD_WIDTH, 300);
        setPreferredSize(fixed);
        setMaximumSize(fixed);

        // iced drinks have no prefix (e.g. "iced_latte.png"), hot drinks use "hot_" prefix
        String prefix = name.startsWith("Iced ") ? "" : "hot_";
        String filename = "/images/" + prefix + name.toLowerCase().replace(" ", "_") + ".png";
        ImageIcon icon;
        try {
             java.io.InputStream stream = getClass().getResourceAsStream(filename);
             icon= new ImageIcon(stream.readAllBytes());
        } catch (Exception e) {
            icon = null; // file missing — circle renders empty (beige background only)
        }

        // circular image area — clips the PNG to a 120px circle
        CirclePlaceholder circle = new CirclePlaceholder(icon);
        circle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // drink name — bold subheading, centred
        JLabel nameLbl = new JLabel(name, SwingConstants.CENTER);
        nameLbl.setFont(Theme.subheading());
        nameLbl.setForeground(Theme.TEXT_PRIMARY);
        nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // price — secondary text, centred
        JLabel priceLbl = new JLabel(price, SwingConstants.CENTER);
        priceLbl.setFont(Theme.labelPlain());
        priceLbl.setForeground(Theme.TEXT_SECONDARY);
        priceLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // − / quantity / + stepper row
        JPanel qty = buildQuantityStepper();
        qty.setAlignmentX(Component.CENTER_ALIGNMENT);

        // full-width primary button — notifies OrderEntryScreen with the selected quantity
        KButton addBtn = Buttons.create("Add to Cart", Buttons.Variant.PRIMARY, Buttons.Size.MD);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        addBtn.addActionListener(e -> onAdd.accept(quantity));

        // stack all elements top-to-bottom with spacing
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

    // horizontal row: minus button | quantity label | plus button
    private JPanel buildQuantityStepper() {
        JPanel row = new JPanel();
        row.setOpaque(false);
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        // centred quantity display label, fixed 40px wide
        JLabel qtyLbl = new JLabel(String.valueOf(quantity), SwingConstants.CENTER);
        qtyLbl.setFont(Theme.bodyBold());
        qtyLbl.setForeground(Theme.TEXT_PRIMARY);
        qtyLbl.setPreferredSize(new Dimension(40, 28));
        qtyLbl.setMaximumSize(new Dimension(40, 28));
        qtyLbl.setHorizontalAlignment(SwingConstants.CENTER);

        KButton minus = stepperButton("−");
        KButton plus = stepperButton("+");
        minus.addActionListener(e -> {
            if (quantity > 1) { quantity--; qtyLbl.setText(String.valueOf(quantity)); } // minimum 1
        });
        plus.addActionListener(e -> {
            quantity++; qtyLbl.setText(String.valueOf(quantity));
        });

        row.add(minus);
        row.add(qtyLbl);
        row.add(plus);
        return row;
    }

    // small secondary square button used for the − and + controls
    private KButton stepperButton(String text) {
        KButton b = Buttons.create(text, Buttons.Variant.SECONDARY, Buttons.Size.SM);
        Dimension d = new Dimension(34, 28);
        b.setPreferredSize(d);
        b.setMaximumSize(d);
        return b;
    }

    /** 120px circle: beige-100 background, PNG image clipped to circle shape. */
    private static class CirclePlaceholder extends JComponent {

        ImageIcon icon; // null if the image file wasn't found

        CirclePlaceholder(ImageIcon icon) {
            this.icon = icon;
            Dimension d = new Dimension(120, 120);
            setPreferredSize(d);
            setMaximumSize(d);
            setMinimumSize(d);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int d = Math.min(getWidth(), getHeight());
            int x = (getWidth() - d) / 2; // centre horizontally

            g2.setColor(Theme.BEIGE_100);
            g2.fillOval(x, 0, d, d); // beige circle background

            if (icon != null) {
                g2.setClip(new java.awt.geom.Ellipse2D.Float(x, 0, d, d)); // clip to circle
                g2.drawImage(icon.getImage(), x, 0, d, d, this);            // draw PNG inside circle
            }
            g2.dispose();
        }
    }
}
