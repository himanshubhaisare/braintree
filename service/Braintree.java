package service;

import constants.Command;
import resource.User;

import java.util.Arrays;
import java.util.Objects;

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
    public void handle(String input) {
        String[] inputs = input.split(" ");
        if (inputs.length > 1) {
            String command = inputs[0];
            String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
            if (command != null && !Objects.equals(command, "")) {
                switch (command) {
                    case Command.ADD:
                        User user = this.userService.create(args);
                        if (user != null) {
                            this.cardService.create(args);
                        }
                        break;
                    case Command.CHARGE:
                        this.cardService.charge(args);
                        break;
                    case Command.CREDIT:
                        this.cardService.credit(args);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
