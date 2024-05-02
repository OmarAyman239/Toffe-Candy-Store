
/**
 * Cart Ckass which handles operations on Cart
 *
 *@author Omar Ayman, Nour El-Din Tarek, Malak Walid
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private List<Items> boughtItems;
    private Catalouge cat;
    private double totalValue = 0;
    private boolean itemFound = false;
    Scanner input = new Scanner(System.in);

    public Cart(Catalouge cat) {
        this.cat = cat;
        boughtItems = new ArrayList<>();
        addToCart();
        printCart();
    }

    /**
     *
     * Functions which adds items to cart
     */
    public void addToCart() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter item name: ");
        String itemName = input.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = input.nextInt();
        for (Items item : cat.getItemList()) {
            if (item.getName().equals(itemName)) {
                itemFound = true;
                if (item.getStock() >= quantity) {
                    for (int i = 0; i < quantity; i++) {
                        boughtItems.add(item);
                        item.setStock(item.getStock() - 1);
                    }
                    System.out.println("Item added to cart!\n");
                    return;
                } else {
                    System.out.println("Not enough stock!\n");
                    System.out.println("Enter a valid ammount!\n");
                    addToCart();
                    return;
                }
            }
        }
        if (!itemFound) {
            System.out.println("Item Not in stock!\n");
            addToCart();
            return;
        }
    }

    /**
     *
     * Function to print
     */
    public void printCart() {
        System.out.println("Items in cart:");
        for (Items item : boughtItems) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        for (Items item : boughtItems) {
            totalValue = totalValue + item.getPrice();
        }
        System.out.println("Total Price is $" + totalValue);
        System.out.println("--Checkout--");
        Payment payment = new Payment(totalValue);
        payment.pay();
    }

    public List<Items> getCart() {
        return boughtItems;
    }
}
