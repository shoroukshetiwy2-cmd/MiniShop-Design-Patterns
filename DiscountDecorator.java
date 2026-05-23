package decorator;

import models.Product;

public class DiscountDecorator extends ProductDecorator {
    public DiscountDecorator(Product product) {
        super(product);
    }

    public String getName() {
        return product.getName() + " with discount";
    }

    public double getPrice() {
        return product.getPrice() - 10;
    }
}