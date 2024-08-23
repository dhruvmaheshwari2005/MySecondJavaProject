import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        // Set locale to French
        Locale locale = Locale.FRENCH;

        // Load the resource bundle for the specified locale
        ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", locale);

        // Retrieve messages from the resource bundle
        String greeting = bundle.getString("greeting");
        String farewell = bundle.getString("farewell");
        String hello = bundle.getString("hello");


        // Use printf to display the messages
        System.out.printf("%s!%n", greeting);  // Output: Bonjour!
        System.out.printf("%s.%n", farewell);  // Output: Au revoir.
        System.out.printf("%s", hello);  // Output: nonono
    }
}
