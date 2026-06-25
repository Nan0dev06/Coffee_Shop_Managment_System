package com.cafelumiere.ui;

import com.cafelumiere.model.Customer;
import com.cafelumiere.system.CoffeeShopSystem;
import com.cafelumiere.ui.components.Buttons;
import com.cafelumiere.ui.components.ContentPage;
import com.cafelumiere.ui.components.LabeledField;
import com.cafelumiere.ui.components.RoundedPanel;
import com.cafelumiere.ui.components.Table;
import com.cafelumiere.ui.theme.Theme;
import com.k33ptoo.components.KButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer management screen: a form to add a new customer and a table
 * listing all current customers with a Remove button per row.
 */
public class CustomerView extends ContentPage {

    // in-memory list until CoffeeShopSystem is wired in
    private final List<Customer> customers = new ArrayList<>();
    private final CoffeeShopSystem system;
    private LabeledField nameField;
    private LabeledField phoneField;
    private LabeledField addressField;
    private Table table;
    private RoundedPanel tableCard;

    public CustomerView(CoffeeShopSystem system) {
        super("Customers");
        this.system = system;
        add(formCard());                         // add-customer form at the top
        add(Box.createVerticalStrut(Theme.S24));
        add(tableCard());                        // customer list below
        add(Box.createVerticalGlue());
    }

    /** White card with three input fields and an Add Customer button. */
    private JComponent formCard() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(Theme.S24, Theme.S24, Theme.S24, Theme.S24));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        // three input fields side by side
        nameField    = new LabeledField("Name",    "e.g. Jamie Park");
        phoneField   = new LabeledField("Phone",   "e.g. 555-0123");
        addressField = new LabeledField("Address", "e.g. 12 Main St");

        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        phoneField.setAlignmentX(Component.LEFT_ALIGNMENT);
        addressField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel fieldsRow = new JPanel();
        fieldsRow.setOpaque(false);
        fieldsRow.setLayout(new BoxLayout(fieldsRow, BoxLayout.X_AXIS));
        fieldsRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldsRow.add(fixedWidth(nameField,    220));
        fieldsRow.add(Box.createHorizontalStrut(Theme.S16));
        fieldsRow.add(fixedWidth(phoneField,   180));
        fieldsRow.add(Box.createHorizontalStrut(Theme.S16));
        fieldsRow.add(fixedWidth(addressField, 260));
        fieldsRow.add(Box.createHorizontalGlue());

        // primary brown button — triggers validation then adds the customer
        KButton addBtn = Buttons.create("Add Customer", Buttons.Variant.PRIMARY, Buttons.Size.MD);
        addBtn.addActionListener(e -> onAddCustomer());

        JPanel actionsRow = new JPanel();
        actionsRow.setOpaque(false);
        actionsRow.setLayout(new BoxLayout(actionsRow, BoxLayout.X_AXIS));
        actionsRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionsRow.add(addBtn);
        actionsRow.add(Box.createHorizontalGlue());

        card.add(sectionTitle("Add a customer"));
        card.add(Box.createVerticalStrut(Theme.S16));
        card.add(fieldsRow);
        card.add(Box.createVerticalStrut(Theme.S16));
        card.add(actionsRow);
        return card;
    }

    // caps a field's width so the fields row doesn't stretch
    private JComponent fixedWidth(JComponent field, int width) {
        field.setMaximumSize(new Dimension(width, field.getPreferredSize().height + 40));
        field.setPreferredSize(new Dimension(width, field.getPreferredSize().height));
        return field;
    }

    // validates the form, creates the customer, clears the fields, and refreshes the table
    private void onAddCustomer() {
        String name    = nameField.getText().trim();
        String phone   = phoneField.getText().trim();
        String address = addressField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required.",
                    "Missing info", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // phone must match format: digits-digits e.g. 555-0123
        if (!phone.matches("\\d{3}-\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Phone must be in the format: 555-0123",
                    "Invalid phone", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // address must start with a number followed by a street name e.g. 12 Main St
        if (!address.matches("\\d+\\s+.+")) {
            JOptionPane.showMessageDialog(this, "Address must be in the format: 12 Main St",
                    "Invalid address", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Customer c = new Customer(0, name, phone, address);
        system.addCustomer(c);
        customers.add(c);

        nameField.clear();
        phoneField.clear();
        addressField.clear();
        refresh();
    }

    // removes the given customer from the list and refreshes the table
    private void onRemoveCustomer(Customer c) {
        system.removeCustomer(c.getCustomerId());
        customers.remove(c);
        refresh();
    }

    /** White card wrapping the customers table. */
    private JComponent tableCard() {
        tableCard = new RoundedPanel();
        tableCard.setLayout(new BoxLayout(tableCard, BoxLayout.Y_AXIS));
        tableCard.setBorder(BorderFactory.createEmptyBorder(Theme.S24, Theme.S24, Theme.S24, Theme.S24));
        tableCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        tableCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        tableCard.setMinimumSize(new Dimension(100, 120));
        rebuildTable();
        tableCard.add(table);
        return tableCard;
    }

    // builds the table from the current customers list
    private void rebuildTable() {
        // columns: id | name | phone | address | remove button
        table = new Table(List.of(
            new Table.Column("ID",      60,  true),
            new Table.Column("Name"),
            new Table.Column("Phone",   140),
            new Table.Column("Address"),
            new Table.Column("",        110)
        ));
        table.setAlignmentX(Component.LEFT_ALIGNMENT);

        if (customers.isEmpty()) {
            table.addEmptyState("No customers yet");
        } else {
            for (Customer c : customers) {
                KButton remove = Buttons.create("Remove", Buttons.Variant.DANGER, Buttons.Size.SM);
                remove.addActionListener(e -> onRemoveCustomer(c));
                table.addRow(
                    String.valueOf(c.getCustomerId()),
                    c.getName(),
                    c.getPhone(),
                    c.getAddress(),
                    remove
                );
            }
        }
    }

    // replaces the old table with a freshly built one after an add or remove
    private void refresh() {
        tableCard.remove(table);
        rebuildTable();
        tableCard.add(table);
        tableCard.revalidate();
        tableCard.repaint();
    }
}
