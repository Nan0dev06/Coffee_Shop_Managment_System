package com.cafelumiere.ui;

import com.cafelumiere.ui.components.SidebarNav;
import com.cafelumiere.ui.theme.Theme;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

/**
 * Application shell. A root CardLayout swaps between the Login screen and the
 * main app (sidebar + a CardLayout content area for the four screens). Buttons
 * navigate only — no backend logic is wired in this visual prototype.
 */
public class Main {

    private final CardLayout rootLayout = new CardLayout();
    private final JPanel root = new JPanel(rootLayout);

    private final CardLayout contentLayout = new CardLayout();
    private final JPanel content = new JPanel(contentLayout);

    private SidebarNav sidebar;

    private Main() {
        // ── Login card ──
        root.add(new LoginScreen(this::showApp), "login");

        // ── App card: sidebar + content ──
        sidebar = new SidebarNav(this::onNavSelect);

        content.setBackground(Theme.SURFACE_PAGE);
        content.add(scroll(new Dashboard()), "dashboard");
        content.add(scroll(new OrderEntryScreen(() -> onNavSelect("dashboard"))), "orders");
        content.add(scroll(new InventoryView()), "inventory");
        content.add(scroll(new RevenueSummaryView()), "revenue");

        JPanel app = new JPanel(new BorderLayout());
        app.add(sidebar, BorderLayout.WEST);
        app.add(content, BorderLayout.CENTER);

        root.add(app, "app");
        rootLayout.show(root, "login");
    }

    private void showApp() {
        rootLayout.show(root, "app");
        onNavSelect("dashboard");
    }

    private void onNavSelect(String id) {
        if ("logout".equals(id)) {
            rootLayout.show(root, "login");
            return;
        }
        sidebar.setActive(id);
        contentLayout.show(content, id);
    }

    private JScrollPane scroll(JComponent page) {
        JScrollPane sp = new JScrollPane(page);
        sp.setBorder(null);
        sp.getViewport().setBackground(Theme.SURFACE_PAGE);
        sp.getVerticalScrollBar().setUnitIncrement(16);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return sp;
    }

    private JFrame buildFrame() {
        JFrame frame = new JFrame("Café Lumière — Coffee Shop Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.setMinimumSize(new Dimension(1024, 680));
        frame.setSize(1200, 780);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    public static void main(String[] args) {
        Theme.loadFonts();
        SwingUtilities.invokeLater(() -> new Main().buildFrame().setVisible(true));
    }
}
