package com.cafelumiere.reports;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cafelumiere.model.MenuItem;
import com.cafelumiere.model.Order;


public class RevenueSummary {

    private LocalDate date;
    private double totalRevenue;
    private int orderCount;
    private String bestSellingItem;

    public RevenueSummary(LocalDate date, List<Order> orders) {
        this.date = date;
        this.orderCount = orders.size();
        this.totalRevenue = orders.stream().mapToDouble(Order::calculateTotal).sum();
        this.bestSellingItem = calculateBestSeller(orders);
    }

    // counts how often each drink appears across all orders; returns the most frequent
    public String calculateBestSeller(List<Order> orders) {
        Map<String, Integer> counts = new HashMap<>();
        for (Order o : orders){
            for (MenuItem item : o.getItems())
                counts.merge(item.getName(), 1, Integer::sum);
        }
        return counts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("N/A");
    }

    public String generateReport() {
        return String.format("Date: %s | Revenue: $%.2f | Orders: %d | Best Seller: %s",
        date, totalRevenue, orderCount, bestSellingItem);
    }

    public LocalDate getDate() { 
        return date; 
    }
    public double getTotalRevenue() { 
        return totalRevenue; 

    }
    public int getOrderCount(){ 
        return orderCount; 
    }
    public String getBestSellingItem(){ 
        return bestSellingItem; 
    }
    public double getAvgOrderValue()  { 
        return orderCount > 0 ? totalRevenue / orderCount : 0; 
    }
}
