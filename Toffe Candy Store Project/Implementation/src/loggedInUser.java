/**
 * LoggedinUser class gets called after every user logs in
 *
 * @author Omar Ayman, Nour El-Din Tarek, Malak Walid
 */
public class loggedInUser {
    private String username;
    private String password;
    public String email;
    private Catalouge cat;
    private Cart cart = null;

    public loggedInUser(String logname, String logpw, String logemail, Catalouge cat) {
        this.cat = cat;
        this.username = logname;
        this.password = logpw;
        this.email = logemail;
    }

    /**
     * Function that shows itemlist and creates a new cart
     * 
     */
    public void startBuying() {
        showItems();
        cart = new Cart(cat);
    }

    /**
     * Function that gets username
     * 
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Function that gets password
     * 
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Function that gets email
     * 
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Function that shows items of the file
     *
     */
    public static void showItems() {
        FileManager.createItemsFile();
        FileManager.printItemsFile();
    }

    /**
     * Function that adds items to cart
     * 
     */
    public void addToCart() {
        cart.addToCart();
    }

    /**
     * Function that prints cart
     * 
     */
    public void printCart() {
        cart.printCart();
    }
}