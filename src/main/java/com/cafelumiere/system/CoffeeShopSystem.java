package com.cafelumiere.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.cafelumiere.inventory.Inventory;
import com.cafelumiere.model.Customer;
import com.cafelumiere.model.MenuItem;
import com.cafelumiere.model.Order;


public class CoffeeShopSystem {

    private List<Customer> customers;
    private List<Order> orders;
    private Inventory inventory;
    
    

    
    public CoffeeShopSystem(){
        customers = new ArrayList<>();
        orders    = new ArrayList<>();
        inventory = new Inventory();
        loadData();
    }
        //admin is the password that is true
        public boolean login(String password) { 
            boolean result = false;
            if (password.equals("testtest")) result = true;
            return result;
        }

        public Order placeOrder(Customer customer, List<MenuItem> items) {
            Order order = new Order(customer);
            for (MenuItem item : items) {
                 order.addItem(item);
                inventory.decrementStock(item);  // reduce ingredient stock
                    }
                orders.add(order);   // save to orders list
                saveData();          // persist
                return order;
            }
        public void addCustomer(Customer c) { 
            customers.add(c);
            saveData();
         }
        public void removeCustomer(int id) { 
            customers.removeIf(c -> c.getCustomerId() == id); 
             }

            public List<Order> sortOrdersByDate(boolean asc) {
    return orders.stream()
                .sorted(asc ? Comparator.comparing(Order::getDateTime)
                    : Comparator.comparing(Order::getDateTime).reversed())
                .collect(Collectors.toList());
            }

             public void saveData() {
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cafedata.dat"))) {
                    out.writeObject(customers);
                    out.writeObject(orders);
                    out.writeObject(inventory);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                public void loadData() {
                    File file = new File("cafedata.dat");
                        if (!file.exists()) return; // first run, no file yet
                            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                                customers = (List<Customer>) in.readObject();
                                orders    = (List<Order>)    in.readObject();
                                inventory = (Inventory)      in.readObject();
                                Customer.syncTracker(customers); // avoid ID collisions with loaded customers
                            } catch (IOException | ClassNotFoundException e) {
                                    e.printStackTrace();
                            }
                        }
               

            public List<Customer> getCustomers() { return customers; }
            public List<Order>    getOrders()    { return orders; }
            public Inventory      getInventory() { return inventory; }
}
