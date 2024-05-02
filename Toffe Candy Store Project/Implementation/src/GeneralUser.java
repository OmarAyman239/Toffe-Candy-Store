
/**
 * GeneralUser class has all attributes of a new user
 *
 *@author Omar Ayman, Nour El-Din Tarek, Malak Walid
 */
import java.util.Scanner;

import java.util.Random;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GeneralUser {
    private String username;
    private String password;
    private String email;
    private int choice;
    private DB db;
    private Catalouge cat;
    // public String address;
    // private static Map<String, String> registeredUsers = new HashMap<>();
    private boolean loggedIn = false;
    private loggedInUser loggedUser = null;
    Scanner input = new Scanner(System.in);

    public GeneralUser(DB db, Catalouge cat) {
        this.db = db;
        this.cat = cat;
        menu();

        // System.out.print("Enter address: ");
        // this.address = input.nextLine();

    }

    /**
     * Function that shows the menu
     *
     */
    public void menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("-- Welcome User --");
        System.out.println("1 - Register");
        System.out.println("2 - Login");
        System.out.print("Enter choice: ");
        this.choice = input.nextInt();
        if (choice == 1) {
            register();
            login(cat, 3);
        } else if (choice == 2)
            login(cat, 3);
        else {
            System.out.println("Wrong Choice!");
            menu();
            return;
        }
    }

    /**
     * Function that registers a user
     *
     */
    public void register() {
        System.out.println("--Register--");
        Scanner input = new Scanner(System.in);
        regUsername(input);
        regPW();
        if (!isValidPassword(password)) {
            System.out.print("--You Have one Try left--");
            regPW();
            if (!isValidPassword(password)) {
                System.out.print("--You Have no more Tries left--");
                return;
            }
        }
        regEmail(input);

        if (FileManager.isUsernameRegistered(username)) {
            System.out.println("Username already registered");
            register();
        } else if (FileManager.isEmailRegistered(email)) {
            System.out.println("Email already registered");
            register();
        } else {
            String otp = generateOTP();
            sendOTP(email, otp);

            // Prompt user to enter OTP
            System.out.print("Enter OTP sent to your email: ");
            String enteredOTP = input.nextLine();
            if (enteredOTP.equals(otp)) {
                db.addGeneralUser(this);
                FileManager.addUser(db);
                System.out.println("User " + username + " registered successfully.");
            } else {
                System.out.println("Incorrect OTP. Registration failed.");
            }
        }
    }

    /**
     * Function that takes and validates username
     *
     */
    public void regUsername(Scanner input) {
        String regex = "^[a-zA-Z0-9_-]{3,16}$";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter username: ");
            this.username = input.nextLine();
            if (this.username.matches(regex)) {
                valid = true;
            } else {
                System.out.println(
                        "Username must be 3-16 characters long and can only contain letters, numbers, underscores, and hyphens.");
            }
        }
    }

    /**
     * Function that takes password
     *
     */
    public void regPW() {
        System.out.print("Enter password: ");
        this.password = input.nextLine();
    }

    /**
     * Function that takes and validates email
     *
     */
    public void regEmail(Scanner input) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter Email: ");
            this.email = input.nextLine();
            if (this.email.matches(regex)) {
                valid = true;
            } else {
                System.out.println("Invalid email address. Please try again.");
            }
        }
    }

    /**
     * Function that logs in the user
     * 
     * @param cat      it takes an instance of catalouge to print
     * @param attempts number of attempts that user can fail logging in
     *
     */
    public void login(Catalouge cat, int attempts) {
        Scanner input = new Scanner(System.in);
        System.out.println("------Login-------");
        System.out.print("Enter username: ");
        String logname = input.nextLine();

        // Check if the entered username exists in the file
        boolean usernameExists = FileManager.isUsernameRegistered(logname);
        if (!usernameExists) {
            if (attempts > 1) {
                System.out.println("Username does not exist. Please try again.");
                System.out.println((attempts - 1) + " attempts left");
                login(cat, attempts - 1);
            } else {
                System.out.println("Out Of attempts. Login failed.");
                System.exit(0);
            }
        }

        System.out.print("Enter password: ");
        String logpw = input.nextLine();
        // Compare the entered data with the data in hte file
        boolean credentialsMatch = FileManager.checkCredentials(logname, logpw);
        if (credentialsMatch) {
            System.out.println("User " + logname + " logged in successfully.");
            loggedIn = true;
            loggedUser = new loggedInUser(logname, logpw, email, cat);
            loggedUser.startBuying();
        } else {
            if (attempts > 1) {
                System.out.println("Incorrect password. Please try again.");
                System.out.println((attempts - 1) + " attempts left");
                login(cat, attempts - 1);
            } else {
                System.out.println("Out Of attempts. Login failed.");
                System.exit(0);
            }
        }
    }

    /**
     * Function that gets username
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Function that gets pw
     * 
     * @return pw
     */
    public String getPassword() {
        return password;
    }

    /**
     * Function that gets email
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Function that validates password
     * 
     * @param password that gets entered to validate
     * @return is the password valid ? true : false
     */
    public static boolean isValidPassword(String password) {
        boolean isValid = true;
        if (password.length() > 15 || password.length() < 8) {
            System.out.println("Password length must be between 8 to 15 characters");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars)) {
            System.out.println("Password must have atleast one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars)) {
            System.out.println("Password must have atleast one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers)) {
            System.out.println("Password must have atleast one number");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars)) {
            System.out.println("Password must atleast have one special character");
            isValid = false;
        }
        return isValid;
    }

    /**
     * Function that generates otp
     * 
     * @return OTP password that will be sent to email
     */
    public String generateOTP() {
        // Generate a 6-digit OTP
        Random random = new Random();
        int otpValue = random.nextInt(900000) + 100000;
        return Integer.toString(otpValue);
    }

    /**
     * Function that gets username
     * 
     * @param email the email that's gonna register
     * @param otp   the otp taken from generateOTP()
     */
    public void sendOTP(String email, String otp) {

        // Set up the email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.starttls.enable", "true");

        // Set up the email session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("toffeotp@gmail.com", "gepadlvndijelhrb");
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("toffeotp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("OTP for Registration");
            message.setText("Your OTP is: " + otp);

            // Send the email message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }

    public void close() {
        input.close();
    }
}
