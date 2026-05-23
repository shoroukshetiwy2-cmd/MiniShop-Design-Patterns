package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotal() {
        double total = 0;

        for (Product p : products) {
            total += p.getPrice();
        }

        return total;
    }

    public void showCart() {
        System.out.println("Cart items:");

        for (Product p : products) {
            System.out.println("- " + p.getName() + " : " + p.getPrice());
        }

        System.out.println("Total = " + getTotal());
    }
}