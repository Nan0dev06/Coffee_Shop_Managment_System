package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Labelled text input: label above a flat, rounded field with a focus ring
 * (1px border-medium → 2px brown-600 on focus). Matches the InputField spec.
 */
public class LabeledField extends JPanel {

    private final JTextComponent field;

    // label — text shown above the input box
    // password — true uses JPasswordField (masked chars), false uses plain text
    // placeholder — greyed hint text shown when the field is empty (ignored for password fields)
    public LabeledField(String label, boolean password, String placeholder) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // label text above the input box
        JLabel labelLbl = new JLabel(label);
        labelLbl.setFont(Theme.label());
        labelLbl.setForeground(Theme.TEXT_PRIMARY);
        labelLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        // actual text input — password fields mask characters
        field = password ? new JPasswordField() : new PlaceholderTextField(placeholder);
        field.setFont(Theme.body());
        field.setForeground(Theme.TEXT_PRIMARY);
        field.setOpaque(false); // FieldBox paints its own background
        field.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        field.setCaretColor(Theme.BROWN_600);

        // FieldBox draws the rounded border and changes it on focus
        FieldBox box = new FieldBox(field);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);

        // notify the box when focus changes so it can repaint the border
        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { box.setFocused(true); }
            @Override public void focusLost(FocusEvent e)   { box.setFocused(false); }
        });

        add(labelLbl);
        add(javax.swing.Box.createVerticalStrut(Theme.S4));
        add(box); // box wraps the field — do not add field directly
    }

    public String getText() {
        return field instanceof JPasswordField p ? new String(p.getPassword()) : field.getText();
    }

    /** Rounded white container that draws the focus ring around the field. */
    private static class FieldBox extends JPanel {
        private boolean focused = false;

        FieldBox(Component inner) {
            setLayout(new BorderLayout());
            setOpaque(false);
            add(inner, BorderLayout.CENTER);
        }

        void setFocused(boolean f) { this.focused = f; repaint(); }

        @Override
        public Dimension getMaximumSize() {
            return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            g2.setColor(Theme.SURFACE_INPUT);
            g2.fillRoundRect(0, 0, w - 1, h - 1, Theme.RADIUS_MD, Theme.RADIUS_MD);
            if (focused) {
                g2.setColor(Theme.BROWN_600);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.drawRoundRect(1, 1, w - 3, h - 3, Theme.RADIUS_MD, Theme.RADIUS_MD);
            } else {
                g2.setColor(Theme.BORDER_MEDIUM);
                g2.setStroke(new java.awt.BasicStroke(1));
                g2.drawRoundRect(0, 0, w - 1, h - 1, Theme.RADIUS_MD, Theme.RADIUS_MD);
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /** Text field that paints greyed placeholder text when empty and unfocused. */
    private static class PlaceholderTextField extends JTextField {
        private final String placeholder;

        PlaceholderTextField(String placeholder) {
            this.placeholder = placeholder == null ? "" : placeholder;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getText().isEmpty() && !isFocusOwner() && !placeholder.isEmpty()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setColor(Theme.TEXT_SECONDARY);
                g2.setFont(getFont());
                java.awt.Insets in = getInsets();
                int y = (getHeight() + g2.getFontMetrics().getAscent()
                        - g2.getFontMetrics().getDescent()) / 2;
                g2.drawString(placeholder, in.left, y);
                g2.dispose();
            }
        }
    }
}
