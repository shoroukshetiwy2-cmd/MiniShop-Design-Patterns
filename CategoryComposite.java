package composite;

import java.util.ArrayList;
import java.util.List;

public class CategoryComposite implements CategoryComponent {
    private String name;
    private List<CategoryComponent> components = new ArrayList<>();

    public CategoryComposite(String name) {
        this.name = name;
    }

    public void add(CategoryComponent component) {
        components.add(component);
    }

    public void show() {
        System.out.println("Category: " + name);

        for (CategoryComponent c : components) {
            c.show();
        }
    }
}