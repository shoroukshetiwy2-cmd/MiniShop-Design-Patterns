package composite;

public class ProductLeaf implements CategoryComponent {
    private String name;

    public ProductLeaf(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("  Product: " + name);
    }
}