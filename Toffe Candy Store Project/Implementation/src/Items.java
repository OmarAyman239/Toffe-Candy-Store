/**
 * Items class has attributes of every item
 *
 * @author Omar Ayman, Nour El-Din Tarek, Malak Walid
 */
public class Items {
    private String name;
    private double price;
    private int stock;

    public Items(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Function that gets items name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Function that gets items price
     * 
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Function that sets items stock
     * 
     * @return stock
     */
    public int setStock(int stk) {
        this.stock = stk;
        return stock;
    }

    /**
     * Function that gets items stock
     * 
     * @return stock
     */
    public int getStock() {
        return stock;
    }
}
