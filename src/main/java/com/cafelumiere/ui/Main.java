package com.cafelumiere.ui;

import com.cafelumiere.ui.components.SidebarNav;
import com.cafelumiere.ui.theme.Theme;
import com.cafelumiere.ui.CustomerView;

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
 *
 * TODO (backend wiring — do this last, once CoffeeShopSystem is implemented):
 *   1. Create one shared system instance at the top of this class:
 *         private final CoffeeShopSystem system = new CoffeeShopSystem();
 *   2. Pass it into each screen that needs data:
 *         new Dashboard(system)
 *         new OrderEntryScreen(system, () -> onNavSelect("dashboard"))
 *         new InventoryView(system)
 *         new RevenueSummaryView(system)
 *   3. In showApp(), after rootLayout.show(), tell the Dashboard to refresh its
 *      KPI cards and chart with real data from system.
 *   4. In LoginScreen, add credential validation against a user store if required.
 */
public class Main {

    // outer layout: switches between "login" and "app" cards
    private final CardLayout rootLayout = new CardLayout();
    private final JPanel root = new JPanel(rootLayout);

    // inner layout: switches between the 4 main screens (dashboard/orders/inventory/revenue)
    private final CardLayout contentLayout = new CardLayout();
    private final JPanel content = new JPanel(contentLayout);

    private SidebarNav sidebar;

    private Main() {
        // ── Login card ──
        root.add(new LoginScreen(this::showApp), "login");

        // ── App card: sidebar on the left, scrollable content area on the right ──
        sidebar = new SidebarNav(this::onNavSelect);

        content.setBackground(Theme.SURFACE_PAGE);
        // each screen is wrapped in a scroll pane so long pages scroll vertically
        content.add(scroll(new Dashboard()), "dashboard");
        content.add(scroll(new OrderEntryScreen(() -> onNavSelect("dashboard"))), "orders");
        content.add(scroll(new InventoryView()), "inventory");
        content.add(scroll(new RevenueSummaryView()), "revenue");
        content.add(scroll(new CustomerView()), "customers");

        // app panel: sidebar pinned to the left, content fills the rest
        JPanel app = new JPanel(new BorderLayout());
        app.add(sidebar, BorderLayout.WEST);
        app.add(content, BorderLayout.CENTER);

        root.add(app, "app");
        rootLayout.show(root, "login"); // start on the login screen
    }

    // called by LoginScreen when Sign In is clicked
    private void showApp() {
        rootLayout.show(root, "app");
        onNavSelect("dashboard"); // land on the dashboard after login
    }

    // called by SidebarNav when a nav item is clicked; logout returns to login
    private void onNavSelect(String id) {
        if ("logout".equals(id)) {
            rootLayout.show(root, "login");
            return;
        }
        sidebar.setActive(id);          // highlights the selected nav item
        contentLayout.show(content, id); // flips the content area to the matching screen
    }

    // wraps a page in a scroll pane; hides the border and horizontal scrollbar
    private JScrollPane scroll(JComponent page) {
        JScrollPane sp = new JScrollPane(page);
        sp.setBorder(null);
        sp.getViewport().setBackground(Theme.SURFACE_PAGE);
        sp.getVerticalScrollBar().setUnitIncrement(16);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return sp;
    }

    // builds and sizes the root JFrame
    private JFrame buildFrame() {
        JFrame frame = new JFrame("Café Lumière — Coffee Shop Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.setMinimumSize(new Dimension(1024, 680));
        frame.setSize(1200, 780);
        frame.setLocationRelativeTo(null); // centres the window on screen
        return frame;
    }

    public static void main(String[] args) {
        Theme.loadFonts(); // must run before any UI is built
        SwingUtilities.invokeLater(() -> new Main().buildFrame().setVisible(true));
    }
}
