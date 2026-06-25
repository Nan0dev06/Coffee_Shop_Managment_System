package com.cafelumiere.reports;

import java.time.LocalDate;
import java.util.List;

import com.cafelumiere.model.Order;

/**
 * Aggregates completed orders into revenue KPIs for a given date.
 *
 * UML fields:
 *   - date: LocalDate
 *   - totalRevenue: double
 *   - orderCount: int
 *   - bestSellingItem: String
 *
 * UML methods:
 *   + generateReport(): String
 *   + calculateBestSeller(orders: List<Order>): String
 *
 * TODO: Add all fields and implement both methods:
 *
 *   + calculateBestSeller(orders): String
 *       Count how many times each MenuItem name appears across all order items.
 *       Return the name with the highest count.
 *       Example:
 *           Map<String, Integer> counts = new HashMap<>();
 *           for (Order o : orders)
 *               for (MenuItem item : o.getItems())
 *                   counts.merge(item.getName(), 1, Integer::sum);
 *           return counts.entrySet().stream()
 *               .max(Map.Entry.comparingByValue())
 *               .map(Map.Entry::getKey)
 *               .orElse("N/A");
 *
 *   + generateReport(): String
 *       Return a formatted summary string, e.g.:
 *           "Date: 2025-06-23 | Revenue: $245.50 | Orders: 12 | Best Seller: Iced Latte"
 *       Used for printing or logging the day's summary.
 *
 * TODO: Add a constructor that accepts date and a List<Order> and computes all fields:
 *   public RevenueSummary(LocalDate date, List<Order> orders) {
 *       this.date = date;
 *       this.orderCount = orders.size();
 *       this.totalRevenue = orders.stream().mapToDouble(Order::calculateTotal).sum();
 *       this.bestSellingItem = calculateBestSeller(orders);
 *   }
 *
 * TODO: Add getters for all 4 fields.
 *
 * TODO: Wire into CoffeeShopSystem.getRevenueSummary(date):
 *       return new RevenueSummary(date, getOrdersForDate(date));
 *
 * TODO: Wire into RevenueSummaryView — replace the "—" stat cards with:
 *       summary.getTotalRevenue(), summary.getOrderCount(), summary.getBestSellingItem()
 */
public class RevenueSummary {

    private LocalDate date;
    private double totalRevenue;
    private int orderCount;
    private String bestSellingItem;
    
    public RevenueSummary(LocalDate date, List<Order> orders){
            this.date = date;
            this.orderCount = orders.size();
 }
    // TODO: public String calculateBestSeller(List<Order> orders) { ... }
    

    
    // TODO: public String generateReport() { ... }

    // TODO: public LocalDate getDate() { ... }
    // TODO: public double getTotalRevenue() { ... }
    // TODO: public int getOrderCount() { ... }
    // TODO: public String getBestSellingItem() { ... }
}
