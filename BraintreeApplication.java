import service.Braintree;
import service.CardService;
import service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BraintreeApplication {

    /**
     * Braintree application runner
     *
     * @param args
     */
    public static void main(String[] args) {
        UserService userService = new UserService();
        CardService cardService = new CardService();
        Braintree braintree = new Braintree(userService, cardService);
        if (args.length > 0) {
            try {
                File inputFile = new File(args[0]);
                if(inputFile.exists() && !inputFile.isDirectory()) {
                    Scanner scanner = new Scanner(inputFile);
                    while (scanner.hasNext()) {
                        String input = scanner.nextLine();
                        braintree.handle(input);
                    }
                    String summary = userService.summary() + "\n";
                    System.out.print(summary);
                } else {
                    System.out.println("Input file " + args[0] + " does not exist.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
