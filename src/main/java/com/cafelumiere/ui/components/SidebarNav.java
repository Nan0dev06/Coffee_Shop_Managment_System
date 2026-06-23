package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;
import com.k33ptoo.components.KGradientPanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Dark-brown gradient sidebar (KGradientPanel) with the brand name, nav items
 * and a bottom-pinned Log Out. Maps to the SidebarNav design-system spec.
 */
public class SidebarNav extends KGradientPanel {

    private static final Font ICON_FONT = new Font("Segoe UI Symbol", Font.PLAIN, 16);

    private final Map<String, NavItem> items = new LinkedHashMap<>();
    private final Consumer<String> onSelect;
    private String activeId;

    public SidebarNav(Consumer<String> onSelect) {
        this.onSelect = onSelect;

        setkStartColor(Theme.BROWN_800);
        setkEndColor(Theme.BROWN_700);
        setkGradientFocus(450);
        setkFillBackground(true);
        setkBorderRadius(0);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(Theme.SIDEBAR_WIDTH, 100));
        setMinimumSize(new Dimension(Theme.SIDEBAR_WIDTH, 100));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(Theme.S16, 0, Theme.S16, 0));

        JLabel brand = new JLabel("Café Lumière");
        brand.setFont(Theme.heading());
        brand.setForeground(Theme.TEXT_ON_DARK);
        brand.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, Theme.S24, 0, Theme.S24));
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(brand);
        add(Box.createVerticalStrut(Theme.S32));

        addItem("dashboard", "Dashboard", "⊞");
        addItem("orders",    "Orders",    "☰");
        addItem("inventory", "Inventory", "▦");
        addItem("revenue",   "Revenue",   "◔");

        add(Box.createVerticalGlue());

        NavItem logout = makeItem("logout", "Log Out", "⇥");
        items.put("logout", logout);
        add(logout);
    }

    private void addItem(String id, String label, String icon) {
        NavItem item = makeItem(id, label, icon);
        items.put(id, item);
        add(item);
        add(Box.createVerticalStrut(Theme.S4));
    }

    private NavItem makeItem(String id, String label, String icon) {
        NavItem item = new NavItem(id, label, icon);
        item.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { item.setHover(true); }
            @Override public void mouseExited(MouseEvent e)  { item.setHover(false); }
            @Override public void mouseClicked(MouseEvent e) { onSelect.accept(id); }
        });
        return item;
    }

    public void setActive(String id) {
        this.activeId = id;
        items.forEach((k, v) -> v.setActive(k.equals(id)));
    }

    /** A single nav row: rounded background by state, icon + label. */
    private class NavItem extends JPanel {
        private final String id;
        private final JLabel textLbl;
        private boolean active;
        private boolean hover;

        NavItem(String id, String label, String icon) {
            this.id = id;
            setOpaque(false);
            setLayout(new BorderLayout(Theme.S8, 0));
            setBorder(javax.swing.BorderFactory.createEmptyBorder(0, Theme.S16, 0, Theme.S16));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setAlignmentX(Component.LEFT_ALIGNMENT);
            Dimension d = new Dimension(Theme.SIDEBAR_WIDTH - Theme.S24, Theme.ROW_MIN_HEIGHT);
            setPreferredSize(d);
            setMaximumSize(new Dimension(Integer.MAX_VALUE, Theme.ROW_MIN_HEIGHT));

            JLabel iconLbl = new JLabel(icon);
            iconLbl.setFont(ICON_FONT);
            iconLbl.setPreferredSize(new Dimension(Theme.ICON_SIZE, Theme.ICON_SIZE));
            iconLbl.setHorizontalAlignment(JLabel.CENTER);

            textLbl = new JLabel(label);

            add(iconLbl, BorderLayout.WEST);
            add(textLbl, BorderLayout.CENTER);
            refresh();
        }

        void setActive(boolean a) { this.active = a; refresh(); repaint(); }
        void setHover(boolean h)  { this.hover = h; refresh(); repaint(); }

        private void refresh() {
            Color fg;
            Font font;
            if (active) {
                fg = Theme.WHITE; font = Theme.label();
            } else if (hover) {
                fg = Theme.TEXT_ON_DARK; font = Theme.labelPlain();
            } else {
                fg = Theme.BEIGE_300; font = Theme.labelPlain();
            }
            textLbl.setForeground(fg);
            textLbl.setFont(font);
            for (Component c : getComponents()) {
                if (c instanceof JLabel l && l != textLbl) l.setForeground(fg);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Color bg = null;
            if (active) {
                bg = Theme.BROWN_600;
            } else if (hover) {
                bg = id.equals("logout") ? Theme.WARNING_TEXT : Theme.BROWN_700;
            }
            if (bg != null) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), Theme.RADIUS_MD, Theme.RADIUS_MD);
                g2.dispose();
            }
            super.paintComponent(g);
        }
    }
}
