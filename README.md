# Café Lumière — Coffee Shop Management System
 
A desktop coffee shop management system built in Java Swing for CSCI207 (Object-Oriented Programming), USAL University.
 
## Overview
 
Café Lumière lets a single owner manage daily café operations: taking orders, tracking ingredient inventory, and reviewing revenue.
 
## Features
 
- **Order entry** — select a customer and a fixed set of drinks, place an order, see the total calculated automatically
- **Inventory tracking** — ingredient stock decreases automatically per order; low-stock ingredients are flagged
- **Customer management** — add and remove customers
- **Revenue summary** — daily total revenue, order count, and best-selling drink (by order frequency)
- **Owner login** — single-owner access gate, no multi-user roles
- **Persistence** — all data (customers, orders, menu, inventory) is saved to file on close and loaded on startup
## Tech stack
 
| Layer | Choice |
|---|---|
| Language | Java |
| UI | Java Swing |
| UI components | [KControls](https://github.com/k33ptoo/KControls) (`KButton`, `KGradientPanel`) |
| Charts | [XChart](https://github.com/knowm/XChart) (bar chart for popular drinks) |
| Persistence | Java file serialization (`Serializable`, `ObjectOutputStream` / `ObjectInputStream`) — no database |
 
## Architecture
 
Full editable version on the [Figma board](https://www.figma.com/board/mDg9ctDhKL7xdbJJ17juTX/Welcome-to-FigJam). Class diagram:
 
```mermaid
classDiagram
    direction TB
 
    class CoffeeShopSystem {
        -List~Customer~ customers
        -List~Order~ orders
        -List~MenuItem~ menu
        -Inventory inventory
        +login(password) boolean
        +placeOrder(customer, items) Order
        +addCustomer(c) void
        +removeCustomer(id) void
        +sortOrdersByDate(asc) List~Order~
        +saveData() void
        +loadData() void
    }
 
    class Order {
        -int orderId
        -Customer customer
        -List~MenuItem~ items
        -LocalDateTime dateTime
        +addItem(item) void
        +calculateTotal() double
        +getOrderId() int
        +getCustomer() Customer
    }
 
    class Inventory {
        -Map~String, Integer~ ingredientStock
        -int lowStockThreshold
        +checkAvailability(item) boolean
        +decrementStock(item) void
        +isLowStock(ingredient) boolean
        +restock(ingredient, amount) void
    }
 
    class RevenueSummary {
        -LocalDate date
        -double totalRevenue
        -int orderCount
        -String bestSellingItem
        +generateReport() String
        +calculateBestSeller(orders) String
    }
 
    class Customer {
        -int customerId
        -String name
        -String phone
        -String address
        +getCustomerId() int
        +getName() String
        +getPhone() String
        +getAddress() String
    }
 
    class MenuItem {
        <<abstract>>
        -String name
        -double basePrice
        +getName() String
        +calculatePrice() double
    }
 
    class HotDrink {
        -String temperature
        +calculatePrice() double
    }
 
    class ColdDrink {
        -String iceLevel
        +calculatePrice() double
    }
 
    MenuItem <|-- HotDrink
    MenuItem <|-- ColdDrink
    Order o-- Customer
    Order o-- MenuItem
    Inventory ..> MenuItem : checks
    RevenueSummary ..> Order : aggregates
    CoffeeShopSystem *-- Order
    CoffeeShopSystem *-- Inventory
    CoffeeShopSystem *-- Customer
    CoffeeShopSystem *-- MenuItem
    CoffeeShopSystem *-- RevenueSummary
```
