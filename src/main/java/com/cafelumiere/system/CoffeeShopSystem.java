package com.cafelumiere.system;

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
        //password and username login
        public boolean login(String username, String password) {
            return username.equals("admin") && password.equals("testtest");
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
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("cafedata.dat"))) {
                            customers = (List) in.readObject();
                            Customer.syncTracker(customers);  // call this right after loading
                            orders    = (List)    in.readObject();
                             inventory = (Inventory)      in.readObject();
                            } catch (IOException | ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                        }
               

            public List<Customer> getCustomers() { 
                return customers;
             }
            public List<Order> getOrders()    { 
                return orders; 
            }
            public Inventory getInventory() { 
                return inventory; 
            }
}
