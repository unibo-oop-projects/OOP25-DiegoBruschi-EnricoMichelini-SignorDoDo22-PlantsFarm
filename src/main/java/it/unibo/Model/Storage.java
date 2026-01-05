package it.unibo.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Storage {

    private final Map<PlantType, Integer> products;

    public Storage() {
        this.products = new HashMap<>();
        
        for (PlantType type : PlantType.values()) {
            if (isEdible(type)) {
                products.put(type, 0);
            }
        }
    }

    public void addProduct(PlantType type, int amount) {
        if (!isEdible(type)) return; //TO DO ERROR

        int current = products.getOrDefault(type, 0);
        products.put(type, current + amount);
    }

    public boolean removeProduct(PlantType type, int amount) {
        int current = products.getOrDefault(type, 0);
        if (current >= amount) {
            products.put(type, current - amount);
            return true;
        }
        return false;
    }

    public int getQuantity(PlantType type) {
        return products.getOrDefault(type, 0);
    }

    public Map<PlantType, Integer> getAllProducts() {
        return Collections.unmodifiableMap(products);
    }

    private boolean isEdible(PlantType type) {
        String cat = type.getCategory();
        return "Fruit".equals(cat) || "Vegetable".equals(cat);
    }
}
