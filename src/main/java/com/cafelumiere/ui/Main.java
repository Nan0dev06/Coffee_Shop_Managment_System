package com.cafelumiere.ui;

import com.cafelumiere.system.CoffeeShopSystem;
import com.cafelumiere.ui.components.SidebarNav;
import com.cafelumiere.ui.theme.Theme;
import com.cafelumiere.ui.CustomerView;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Application shell. A root CardLayout swaps between the Login screen and the
 * main app (sidebar + a CardLayout content area for the five screens).
 */
public class Main {

    // outer layout: switches between "login" and "app" cards
    private final CardLayout rootLayout = new CardLayout();
    private final JPanel root = new JPanel(rootLayout);

    // inner layout: switches between the 5 main screens
    private final CardLayout contentLayout = new CardLayout();
    private final JPanel content = new JPanel(contentLayout);

    private SidebarNav sidebar;
    private final CoffeeShopSystem system = new CoffeeShopSystem();
    private final OrderEntryScreen orderEntry;

    private Main() {
        // ── Login card ──
        root.add(new LoginScreen(this::showApp, system), "login");

        // ── App card: sidebar on the left, scrollable content area on the right ──
        sidebar = new SidebarNav(this::onNavSelect);

        orderEntry = new OrderEntryScreen(system, () -> onNavSelect("dashboard"));

        content.setBackground(Theme.SURFACE_PAGE);
        // each screen is wrapped in a scroll pane so long pages scroll vertically
        content.add(scroll(new Dashboard(system)), "dashboard");
        content.add(scroll(orderEntry), "orders");
        content.add(scroll(new InventoryView(system)), "inventory");
        content.add(scroll(new RevenueSummaryView(system)), "revenue");
        content.add(scroll(new CustomerView(system)), "customers");

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
        if ("orders".equals(id)) {
            orderEntry.refreshCustomers(); // sync latest customers into the combo
        }
        sidebar.setActive(id);           // highlights the selected nav item
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
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                    frame,
                    "Save all data before exiting?",
                    "Exit Café Lumière",
                    JOptionPane.YES_NO_CANCEL_OPTION
                );
                if (choice == JOptionPane.YES_OPTION) {
                    system.saveData();
                    System.exit(0);
                } else if (choice == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
                // CANCEL — do nothing, keep the app open
            }
        });
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
