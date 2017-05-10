package validator;

import constants.Error;
import database.Database;
import resource.Card;
import resource.User;

import java.math.BigDecimal;

public class CardServiceValidator {

    /**
     * Run validations before creating card
     *
     * @param args
     * @return
     */
    public Validation validateCreate(String[] args) {
        Validation validation = new Validation();
        Card card;
        if (args.length != 3) {
            validation.addError(Error.INVALID_ARGS);
            return validation;
        }

        String username = args[0];
        String cardNumber = args[1];
        String creditLimit = args[2];

        if (!Money.validate(creditLimit)) {
            validation.addError(Error.CREDIT_LIMIT_AMOUNT_INVALID);
            return validation;
        }

        User user = Database.getUser(username);
        card = Database.getCard(cardNumber);
        if (user == null) {
            validation.addError(Error.USER_NOT_FOUND);
            return validation;
        }
        if (user.getCard() != null) {
            validation.addError(Error.USER_ALREADY_HAS_CARD);
            return validation;
        }
        if (card != null) {
            validation.addError(Error.CARD_BELONGS_TO_ANOTHER_USER);
            return validation;
        }
        if (!Luhn.validate(cardNumber)) {
            validation.addError(Error.CARD_NUMBER_INVALID);
            return validation;
        }

        return validation;
    }

    /**
     * Run validations before charging a user's card
     *
     * @param args
     * @return
     */
    public Validation validateCharge(String[] args) {
        Validation validation = new Validation();
        if (args.length != 2) {
            validation.addError(Error.INVALID_ARGS);
            return validation;
        }

        String username = args[0];
        String charge = args[1];
        if (!Money.validate(charge)) {
            validation.addError(Error.CHARGE_AMOUNT_INVALID);
            return validation;
        }

        User user = Database.getUser(username);
        if (user == null) {
            validation.addError(Error.USER_NOT_FOUND);
            return validation;
        }

        Card card = user.getCard();
        if (card == null) {
            validation.addError(Error.CARD_NOT_FOUND);
            return validation;
        }
        if (!Luhn.validate(card.getNumber())) {
            validation.addError(Error.CARD_NUMBER_INVALID);
            return validation;
        }

        BigDecimal chargeAmount = new BigDecimal(charge.replace("$", ""));
        BigDecimal newBalance = card.getBalance().add(chargeAmount);
        if (newBalance.compareTo(card.getCreditLimit()) == 1) {
            validation.addError(Error.CHARGE_DECLINED);
        }

        return validation;
    }

    /**
     * Run validations before crediting money towards a card's balance
     *
     * @param args
     * @return
     */
    public Validation validateCredit(String[] args) {
        Validation validation = new Validation();
        if (args.length != 2) {
            validation.addError(Error.INVALID_ARGS);
            return validation;
        }

        String username = args[0];
        String credit = args[1];
        if (!Money.validate(credit)) {
            validation.addError(Error.CREDIT_AMOUNT_INVALID);
            return validation;
        }

        User user = Database.getUser(username);
        if (user == null) {
            validation.addError(Error.USER_NOT_FOUND);
            return validation;
        }

        Card card = user.getCard();
        if (card == null) {
            validation.addError(Error.CARD_NOT_FOUND);
            return validation;
        }
        if (!Luhn.validate(card.getNumber())) {
            validation.addError(Error.CARD_NUMBER_INVALID);
            return validation;
        }

        return validation;
    }
}
