
/**
 * DB class which controls the database of users
 *
 *@author Omar Ayman, Nour El-Din Tarek, Malak Walid
 */
import java.util.ArrayList;

public class DB {
    private ArrayList<GeneralUser> generalUsers;

    public DB() {
        generalUsers = new ArrayList<>();
    }

    /**
     * Function that adds every user to the array of users
     * 
     * @param user the user that gets added
     */
    public void addGeneralUser(GeneralUser user) {
        generalUsers.add(user);

    }

    /**
     * Function that returns list of registered users
     * 
     * @return list of users registered
     */
    public ArrayList<GeneralUser> getGeneralUsers() {
        return generalUsers;
    }

    /**
     * Function that returns size of list of registered users
     * 
     * @return size of list of users registered
     */
    public int getSize() {
        return generalUsers.size();
    }

}
