package service;

import database.Database;
import resource.Card;
import resource.User;
import validator.CardServiceValidator;
import validator.Validation;

import java.math.BigDecimal;

public class CardService {

    private CardServiceValidator validator;

    public CardService() {
        this.validator = new CardServiceValidator();
    }

    /**
     * Create card
     *
     * @param args
     * @return
     */
    public Card create(String[] args) {
        Card card = null;
        Validation validation = validator.validateCreate(args);
        if (validation.isValid()) {
            User user = Database.getUser(args[0]);
            String cardNumber = args[1];
            BigDecimal creditLimit = new BigDecimal(args[2].replace("$", ""));

            card = new Card(cardNumber, user, creditLimit);
            Database.setCard(card);
            user.setCard(card);
            Database.setUser(user);
        }

        return card;
    }

    /**
     * Create charge on user's card and update card's balance
     *
     * @param args
     * @return
     */
    public void charge(String[] args) {
        Validation validation = validator.validateCharge(args);
        if (validation.isValid()) {
            // add charge on user's card and increase balance
            String username = args[0];
            BigDecimal charge = new BigDecimal(args[1].replace("$", ""));
            User user = Database.getUser(username);
            Card card = user.getCard();
            BigDecimal newBalance = card.getBalance().add(charge);
            card.setBalance(newBalance);

            // persist new card balance
            Database.setCard(card);
            user.setCard(card);
            Database.setUser(user);
        }
    }

    /**
     * Credit money towards a card's balance
     *
     * @param args
     * @return
     */
    public void credit(String[] args) {
        Validation validation = validator.validateCredit(args);
        if (validation.isValid()) {
            // credit money on user's card and decrease balance
            String username = args[0];
            BigDecimal credit = new BigDecimal(args[1].replace("$", ""));
            User user = Database.getUser(username);
            Card card = user.getCard();
            BigDecimal newBalance = card.getBalance().subtract(credit);
            card.setBalance(newBalance);

            // persist new card balance
            Database.setCard(card);
            user.setCard(card);
            Database.setUser(user);
        }
    }

}
