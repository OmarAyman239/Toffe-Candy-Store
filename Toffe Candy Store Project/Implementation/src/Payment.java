
/**
 * Payment class handles the order payment
 *
 * @author Omar Ayman, Nour El-Din Tarek, Malak Walid
 */
import java.util.Scanner;

public class Payment {
    private String phoneNumber;
    private int method;
    public String address;
    private double amount;
    Scanner input = new Scanner(System.in);

    public Payment(double amount) {
        this.amount = amount;

    }

    /**
     * Function that takes the payment method and shows total cost
     *
     */
    public void pay() {
        regPhone(input);
        System.out.println("Your orders will cost: $" + amount + " + 2$ for shipping ");
        double totalAmount = amount + 2.0;
        System.out.println("Total: $" + totalAmount);
        System.out.println("Enter payment method: ");
        System.out.println(" 1 - Cash On Delivery");
        System.out.println(" 2 - Credit Card");
        System.out.println(" 3 - Pay with a voucher");
        System.out.println(" 4 - Pay with loyalty points");
        this.method = input.nextInt();
        if (method >= 1 && method <= 4) {
            if (method == 1) {
                cashOnDelivery();
            } else {
                System.out.println("This Payment system is still under construction");
                cashOnDelivery();
            }
        }
    }

    /**
     * Function that chooses cash on delivery
     *
     */
    public void cashOnDelivery() {
        Scanner input = new Scanner(System.in);
        System.out.println("Cash on delivery selected.");
        System.out.print("Enter your address: ");
        String address = input.nextLine();
        System.out.println("Your Order is going to be delivered to " + address + " in 2-3 business days! ");
        FileManager.createItemsFile();
        System.out.println("Thanks for choosing TOFFE!");
    }

    /**
     * Function that takes phone number
     *
     * @param input phone number
     */
    public void regPhone(Scanner input) {
        String regex = "^(?:\\+20|0)?1[0125]\\d{8}$";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter Your Phone Number: ");
            this.phoneNumber = input.nextLine();
            if (this.phoneNumber.matches(regex)) {
                valid = true;
            } else {
                System.out.println(
                        "Please enter a valid EGYPTIAN phone number.");
            }
        }
    }

    public void close() {
        input.close();
    }
}