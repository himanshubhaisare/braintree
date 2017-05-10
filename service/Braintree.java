package service;

import constants.Command;
import java.util.Arrays;
import static constants.Error.*;

public class Braintree {

    private UserService userService;

    private CardService cardService;

    /**
     * Braintree Application runs with user, card and payment services
     *
     * @param userService
     * @param cardService
     */
    public Braintree(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    /**
     * Parse and execute the input commands
     *
     * @param input
     */
    public String handle(String input) {
        String error;
        String[] inputs = input.split(" ");
        if (inputs.length < 1) {
            error = "";
        } else {
            String command = inputs[0];
            String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
            if (command == null || command.equals("")) {
                error = "";
            } else {
                switch (command) {
                    case Command.ADD:
                        error = this.userService.create(args);
                        if (error.equals("")) {
                            error = this.cardService.create(args);
                        }
                        break;
                    case Command.CHARGE:
                        error = this.cardService.charge(args);
                        break;
                    case Command.CREDIT:
                        error = this.cardService.credit(args);
                        break;
                    default:
                        error = COMMAND_NOT_RECOGNIZED;
                        break;
                }
            }
        }

        String summary = this.userService.summary();
        return formatOutput(error, summary); //Uncomment to see output with errors
    }

    /**
     * Shows output with errors
     *
     * @param error
     * @param summary
     * @return
     */
    private String formatOutput(String error, String summary) {
        String output = "";
        if (!error.equals("")) {
            if (!summary.equals("")) {
                output = error + "\n" + summary;
            } else {
                output = error;
            }
        } else {
            if (!summary.equals("")) {
                output = summary;
            }
        }

        return output;
    }
}
