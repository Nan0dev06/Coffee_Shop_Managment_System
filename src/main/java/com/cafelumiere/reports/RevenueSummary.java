package com.cafelumiere.reports;

import com.cafelumiere.model.Order;
import java.time.LocalDate;
import java.util.List;

/**
 * Aggregates completed orders for a given date into revenue KPIs.
 *
 * TODO: Implement this class fully:
 *
 * 1. Add a static factory method that builds a RevenueSummary from a list of orders:
 *       public static RevenueSummary from(LocalDate date, List<Order> orders) { ... }
 *    Inside it, compute:
 *       - totalRevenue  = sum of order.calculateTotal() for all orders
 *       - orderCount    = orders.size()
 *       - avgOrderValue = totalRevenue / orderCount  (guard against divide-by-zero)
 *       - bestSellingItem = the MenuItem name that appears most across all order lines
 *
 * 2. Add getters for all four fields so RevenueSummaryView can read them.
 *
 * HOW TO WIRE INTO THE UI (RevenueSummaryView.java):
 *   - Pass a RevenueSummary instance (or a supplier) into the RevenueSummaryView constructor
 *   - Replace the "—" placeholders in statRow() with:
 *       String.format("$%.2f", summary.getTotalRevenue())
 *       String.valueOf(summary.getOrderCount())
 *       String.format("$%.2f", summary.getAvgOrderValue())
 *       summary.getBestSellingItem()
 *   - In ordersCard(), loop over the orders and call table.addRow(...) for each one
 *     with: orderId, customer name, item summary string, formatted total
 */
public class RevenueSummary {

    private LocalDate date;
    private double totalRevenue;
    private int orderCount;
    private String bestSellingItem;

    // TODO: private double avgOrderValue;

    // TODO: public static RevenueSummary from(LocalDate date, List<Order> orders) { ... }

    // TODO: public LocalDate getDate() { ... }
    // TODO: public double getTotalRevenue() { ... }
    // TODO: public int getOrderCount() { ... }
    // TODO: public double getAvgOrderValue() { ... }
    // TODO: public String getBestSellingItem() { ... }
}
