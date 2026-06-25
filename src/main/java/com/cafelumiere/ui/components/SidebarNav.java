package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;
import com.k33ptoo.components.KGradientPanel;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Cubic;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Dark-brown gradient sidebar (KGradientPanel). A hamburger toggle at the top
 * slides the sidebar between the full width (brand + icon + label) and a thin
 * icon-only rail. The slide is animated with the Universal Tween Engine.
 */
public class SidebarNav extends KGradientPanel {

    private static final Font ICON_FONT = new Font("Segoe UI Symbol", Font.PLAIN, 16);
    private static final int EXPANDED_WIDTH = Theme.SIDEBAR_WIDTH; // 240
    private static final int COLLAPSED_WIDTH = 72;                 // icon rail

    static {
        Tween.registerAccessor(SidebarNav.class, new WidthAccessor());
    }

    private final Map<String, NavItem> items = new LinkedHashMap<>();
    private final Consumer<String> onSelect;
    private final TweenManager tweenManager = new TweenManager();
    private final Timer clock;

    private JLabel brand;
    private float currentWidth = EXPANDED_WIDTH;
    private boolean collapsed = false;
    private String activeId;

    public SidebarNav(Consumer<String> onSelect) {
        this.onSelect = onSelect;

        setkStartColor(Theme.BROWN_800);
        setkEndColor(Theme.BROWN_700);
        setkGradientFocus(450);
        setkFillBackground(true);
        setkBorderRadius(0);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(EXPANDED_WIDTH, 100));
        setMinimumSize(new Dimension(EXPANDED_WIDTH, 100));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(Theme.S16, 0, Theme.S16, 0));

        add(header());
        add(Box.createVerticalStrut(Theme.S32));

        addItem("dashboard", "Dashboard", "⊞");
        addItem("orders",    "Orders",    "☰");
        addItem("inventory", "Inventory", "▦");
        addItem("revenue",   "Revenue",   "◔");
        addItem("customers", "Customers", "👤");

        add(Box.createVerticalGlue());

        NavItem logout = makeItem("logout", "Log Out", "⇥");
        items.put("logout", logout);
        add(logout);

        // Drives the tween engine; stops itself once the slide finishes.
        clock = new Timer(15, e -> {
            tweenManager.update(0.015f);
            if (tweenManager.getRunningTweensCount() == 0) ((Timer) e.getSource()).stop();
        });
    }

    /** Top row: hamburger toggle (in the icon column) + brand name. */
    private JComponent header() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, Theme.ROW_MIN_HEIGHT));

        JLabel toggle = new JLabel();
        ImageIcon menu = loadIcon("menu");
        if (menu != null) {
            toggle.setIcon(menu);
        } else {
            toggle.setText("≡");
            toggle.setFont(ICON_FONT);
            toggle.setForeground(Theme.TEXT_ON_DARK);
        }
        toggle.setHorizontalAlignment(JLabel.CENTER);
        toggle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        toggle.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { toggle(); }
        });

        brand = new JLabel("Café Lumière");
        brand.setFont(Theme.heading());
        brand.setForeground(Theme.TEXT_ON_DARK);

        header.add(iconColumn(toggle), BorderLayout.WEST);
        header.add(brand, BorderLayout.CENTER);
        return header;
    }

    /** Toggles between the full sidebar and the icon rail, animating the width. */
    private void toggle() {
        collapsed = !collapsed;
        brand.setVisible(!collapsed);
        items.values().forEach(it -> it.setCollapsed(collapsed));

        float target = collapsed ? COLLAPSED_WIDTH : EXPANDED_WIDTH;
        tweenManager.killTarget(this);
        Tween.to(this, 0, 0.30f).target(target).ease(Cubic.INOUT).start(tweenManager);
        if (!clock.isRunning()) clock.start();
    }

    /** Called by the tween accessor on every frame to resize the sidebar. */
    private void applyWidth(float w) {
        currentWidth = w;
        int iw = Math.round(w);
        setPreferredSize(new Dimension(iw, getHeight() > 0 ? getHeight() : 600));
        setMinimumSize(new Dimension(iw, 0));
        setMaximumSize(new Dimension(iw, Integer.MAX_VALUE));
        revalidate();
        repaint();
    }

    private void addItem(String id, String label, String icon) {
        NavItem item = makeItem(id, label, icon);
        items.put(id, item);
        add(item);
        add(Box.createVerticalStrut(Theme.S4));
    }

    /** A fixed-width (COLLAPSED_WIDTH) column that centres its content, so the
     *  toggle and every nav icon line up — and stay centred in the icon rail. */
    private JPanel iconColumn(JComponent inner) {
        JPanel col = new JPanel(new GridBagLayout());
        col.setOpaque(false);
        Dimension d = new Dimension(COLLAPSED_WIDTH, Theme.ROW_MIN_HEIGHT);
        col.setPreferredSize(d);
        col.setMinimumSize(d);
        col.setMaximumSize(d);
        col.add(inner);
        return col;
    }

    /** Loads a white PNG from /icons/<name>.png scaled to ICON_SIZE, or null if missing. */
    private static ImageIcon loadIcon(String name) {
        try (InputStream in = SidebarNav.class.getResourceAsStream("/icons/" + name + ".png")) {
            if (in == null) return null;
            Image img = ImageIO.read(in)
                    .getScaledInstance(Theme.ICON_SIZE, Theme.ICON_SIZE, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return null;
        }
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

    /** Tween accessor: animates the single "width" value of the sidebar. */
    public static class WidthAccessor implements TweenAccessor<SidebarNav> {
        @Override
        public int getValues(SidebarNav t, int type, float[] returnValues) {
            returnValues[0] = t.currentWidth;
            return 1;
        }
        @Override
        public void setValues(SidebarNav t, int type, float[] newValues) {
            t.applyWidth(newValues[0]);
        }
    }

    /** A single nav row: centred icon column + label, rounded highlight by state. */
    private class NavItem extends JPanel {
        private final String id;
        private final JLabel iconLbl;
        private final JLabel textLbl;
        private boolean active;
        private boolean hover;

        NavItem(String id, String label, String icon) {
            this.id = id;
            setOpaque(false);
            setLayout(new BorderLayout());
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setAlignmentX(Component.LEFT_ALIGNMENT);
            setPreferredSize(new Dimension(EXPANDED_WIDTH, Theme.ROW_MIN_HEIGHT));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, Theme.ROW_MIN_HEIGHT));

            // White PNG icon when available (dashboard/orders/inventory/revenue);
            // falls back to the Unicode glyph (e.g. Log Out, which has no PNG).
            iconLbl = new JLabel();
            ImageIcon png = loadIcon(id);
            if (png != null) {
                iconLbl.setIcon(png);
            } else {
                iconLbl.setText(icon);
                iconLbl.setFont(ICON_FONT);
                iconLbl.setForeground(Theme.BEIGE_300);
            }
            iconLbl.setHorizontalAlignment(JLabel.CENTER);

            textLbl = new JLabel(label);

            add(iconColumn(iconLbl), BorderLayout.WEST);
            add(textLbl, BorderLayout.CENTER);
            refresh();
        }

        void setActive(boolean a) { this.active = a; refresh(); repaint(); }
        void setHover(boolean h)  { this.hover = h; refresh(); repaint(); }
        void setCollapsed(boolean c) { textLbl.setVisible(!c); }

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
