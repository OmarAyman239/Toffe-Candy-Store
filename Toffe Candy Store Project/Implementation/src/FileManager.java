
/**
 * FileManager class which controls the 2 main files
 *
 *@author Omar Ayman, Nour El-Din Tarek, Malak Walid
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "users.txt";
    private static final String ITEMS_FILE_NAME = "ItemsFile.txt";
    private DB db;

    public FileManager(DB db) {
        this.db = db;
    }

    /**
     * Function that adds every user to the file of users
     * 
     * @param db the user is taken from this database and added to the file
     */
    public static void addUser(DB db) {
        File file = new File(FILE_NAME);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (GeneralUser user : db.getGeneralUsers()) {
                bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that checks if the username is registered
     *
     * @param username of the user registering
     * @return it returns a boolean of if the param is registered
     */
    public static boolean isUsernameRegistered(String username) {
        File file = new File(FILE_NAME);

        try {
            if (!file.exists()) {
                return false;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    br.close();
                    return true;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Function that checks if the email is registered
     *
     * @param email of the user registering
     * @return it returns a boolean of if the param is registered
     */
    public static boolean isEmailRegistered(String email) {
        File file = new File(FILE_NAME);

        try {
            if (!file.exists()) {
                return false;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[2].equals(email)) {
                    br.close();
                    return true;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Function that checks if the username and password are in the file and correct
     *
     * @param username of the user registering
     * @param password of the user registering
     */
    public static boolean checkCredentials(String username, String password) {
        File file = new File(FILE_NAME);

        try {
            if (!file.exists()) {
                return false;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    br.close();
                    return true;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Function that creates and updates itemFile
     *
     */
    public static void createItemsFile() {
        File file = new File(ITEMS_FILE_NAME);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (Items item : Catalouge.getItemList()) {
                bw.write(item.getName() + "," + item.getPrice() + "," + item.getStock());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that prints itemFile
     *
     */
    public static void printItemsFile() {
        File file = new File(ITEMS_FILE_NAME);

        try {
            if (!file.exists()) {
                System.out.println("ItemsFile does not exist.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String price = parts[1];
                String stock = parts[2];
                System.out.println(name + " - $" + price + ": " + stock);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that gets the stock of every item
     * 
     * @return stocklist is a list of stock values of every item
     */
    public static List<Integer> getStockList() {
        List<Integer> stockList = new ArrayList<>();
        File file = new File(ITEMS_FILE_NAME);

        try {
            if (!file.exists()) {
                System.out.println("ItemsFile does not exist.");
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int stock = Integer.parseInt(parts[2]);
                stockList.add(stock);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stockList;
    }

}