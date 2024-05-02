/**
 * This is the class that gets called to run the app
 *
 * @author Omar Ayman, Nour El-Din Tarek, Malak Walid
 */
public class AppManager {
    AppManager() {
        DB db = new DB();
        Catalouge cat = new Catalouge();
        GeneralUser user1 = new GeneralUser(db, cat);
        GeneralUser user2 = new GeneralUser(db, cat);
        user1.isLoggedIn();
        user2.isLoggedIn();

    }

    /**
     * this method closes out the application
     */
    public void close() {
        System.out.println("Application closing");
        return;
    }
}
