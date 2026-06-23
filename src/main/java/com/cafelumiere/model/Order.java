package com.cafelumiere.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a single customer order.
 *
 * TODO: Implement this class fully:
 *   - Add fields: orderId, customer, items (List<MenuItem> + quantities), status, timestamp
 *   - Add a constructor that accepts a Customer and a list of ordered items
 *   - Add calculateTotal() that sums item.calculatePrice() * quantity for each item
 *   - Add getters for all fields
 *   - Add an enum OrderStatus { PENDING, PREPARING, COMPLETED, CANCELLED }
 */
public class Order {

    // TODO: private int orderId;
    // TODO: private Customer customer;
    // TODO: private List<OrderLine> lines;  (OrderLine = MenuItem + quantity)
    // TODO: private OrderStatus status;
    // TODO: private LocalDateTime timestamp;

    // TODO: public Order(Customer customer, List<OrderLine> lines) { ... }

    // TODO: public double calculateTotal() {
    //           return lines.stream().mapToDouble(l -> l.item().calculatePrice() * l.quantity()).sum();
    //       }

    // TODO: public int getOrderId() { ... }
    // TODO: public Customer getCustomer() { ... }
    // TODO: public List<OrderLine> getLines() { ... }
    // TODO: public OrderStatus getStatus() { ... }
    // TODO: public void setStatus(OrderStatus status) { ... }
    // TODO: public LocalDateTime getTimestamp() { ... }

    /**
     * TODO: Create this inner record once the class is implemented.
     * Represents one line in the order: which item and how many.
     *
     *   public record OrderLine(MenuItem item, int quantity) {}
     */
}
