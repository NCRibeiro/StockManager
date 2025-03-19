import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StockManager {

    private Map<String, Integer> stock = new HashMap<>();

    public void addProduct(String product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(String product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (stock.containsKey(product)) {
            int currentQuantity = stock.get(product);
            if (currentQuantity >= quantity) {
                stock.put(product, currentQuantity - quantity);
            } else {
                throw new IllegalArgumentException("Insufficient stock for " + product);
            }
        } else {
            throw new IllegalArgumentException(product + " not found in stock.");
        }
    }

    public void updateProduct(String product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (stock.containsKey(product)) {
            stock.put(product, quantity);
        } else {
            throw new IllegalArgumentException(product + " not found in stock.");
        }
    }

    public int checkStock(String product) {
        return stock.getOrDefault(product, 0);
    }

    public Set<String> listProducts() {
        return stock.keySet();
    }

    public static void main(String[] args) {
        StockManager manager = new StockManager();

        try {
            manager.addProduct("Rice", 100);
            manager.addProduct("Beans", 50);

            System.out.println("Rice stock: " + manager.checkStock("Rice"));
            System.out.println("Beans stock: " + manager.checkStock("Beans"));

            manager.removeProduct("Rice", 30);
            System.out.println("Rice stock after removal: " + manager.checkStock("Rice"));

            manager.updateProduct("Beans", 70);
            System.out.println("Beans stock after update: " + manager.checkStock("Beans"));

            System.out.println("Products in stock: " + manager.listProducts());

            manager.removeProduct("Coffee", 20); // Non-existent product
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}