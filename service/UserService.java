package service;

import database.Database;
import resource.Card;
import resource.User;
import validator.UserServiceValidator;
import validator.Validation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserService {

    private UserServiceValidator validator;

    public UserService() {
        this.validator = new UserServiceValidator();
    }

    /**
     * Validate and create a user
     *
     * @param args
     * @return
     */
    public User create(String[] args) {
        User user = null;
        Validation validation = validator.validateCreate(args);
        if (validation.isValid()) {
            String name = args[0];
            user = new User(name);
            Database.setUser(user);
        }

        return user;
    }


    /**
     * return balances of all users
     *
     * @return
     */
    public String summary() {
        String summary = "";
        Map<String, User> users = Database.getUsers();
        List<String> balances = users.entrySet().stream().map(userEntry -> {
            User user = userEntry.getValue();
            Card card = user.getCard();
            String userName = user.getName();
            String balance = "error";
            if (card != null) {
                balance = "$" + card.getBalance().toString();
            }
            return userName + ": " + balance;
        }).collect(Collectors.toList());

        int count = 0;
        for (String userAndBalance : balances) {
            count++;
            summary += userAndBalance;
            if (count < balances.size()) {
                summary += "\n";
            }
        }

        return summary;
    }
}
