package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * Padded page scaffold on the page surface, with a heading at the top. Inner
 * screens (Dashboard, Orders, Inventory, Revenue) extend this and append their
 * content with {@link #add}. Implements {@link Scrollable} so pages fill the
 * viewport width and scroll only vertically.
 */
public abstract class ContentPage extends JPanel implements Scrollable {

    private final String title;

    // title — page heading shown at the top (e.g. "Dashboard", "Inventory")
    protected ContentPage(String title) {
        this.title = title;
        setBackground(Theme.SURFACE_PAGE); // cream beige page background
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(Theme.S32, Theme.S32, Theme.S32, Theme.S32));
        addHeader();
    }

    // adds the page title label at the very top of the screen
    private void addHeader() {
        JLabel header = new JLabel(title);
        header.setFont(Theme.heading());
        header.setForeground(Theme.TEXT_PRIMARY);
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(header);
        add(Box.createVerticalStrut(Theme.S24)); // gap between title and first section
    }

    // clears all content and re-adds the header; subclasses call this in refresh()
    protected void clearBody() {
        removeAll();
        addHeader();
    }

    /** A white rounded card wrapping the given content with internal padding. */
    protected static JPanel card(Component content) {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(Theme.S24, Theme.S24, Theme.S24, Theme.S24));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        if (content instanceof javax.swing.JComponent jc) {
            jc.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        card.add(content);
        return card;
    }

    // bold subheading label used as a title inside white cards (e.g. "Recent Orders")
    protected static JLabel sectionTitle(String text) {
        JLabel l = new JLabel(text);
        l.setFont(Theme.subheading());
        l.setForeground(Theme.TEXT_PRIMARY);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    // ── Scrollable: fill viewport width, scroll vertically only ──
    @Override public Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
    @Override public int getScrollableUnitIncrement(Rectangle r, int o, int d) { return 16; }
    @Override public int getScrollableBlockIncrement(Rectangle r, int o, int d) { return r.height - 32; }
    @Override public boolean getScrollableTracksViewportWidth() { return true; }  // stretch to fill width
    @Override public boolean getScrollableTracksViewportHeight() { return false; } // allow vertical scroll
}
