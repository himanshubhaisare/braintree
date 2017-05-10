package tests.service;

import constants.Error;
import database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import resource.Card;
import service.CardService;
import service.UserService;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardServiceTest {

    private UserService userService;

    private CardService cardService;

    public CardServiceTest() {
        this.userService = new UserService();
        this.cardService = new CardService();
    }

    @Before
    public void setUp() {
        String input = "Himanshu 5454545454545454 $1000";
        String[] args = input.split(" ");
        this.userService.create(args);
    }

    @After
    public void tearDown() {
        Database.clearAll();
    }

    @Test
    public void testCreateCardWithoutUsername() {
        String input = "";
        String[] args = input.split(" ");
        String result = this.cardService.create(args);
        assertEquals("testCreateCardWithoutUsername: ", Error.INVALID_ARGS, result);
    }

    @Test
    public void testCreateCardWithoutCardNumber() {
        String input = "Himanshu";
        String[] args = input.split(" ");
        String result = this.cardService.create(args);
        assertEquals("testCreateCardWithoutCardNumber: ", Error.INVALID_ARGS, result);
    }

    @Test
    public void testCreateCardWithoutCreditLimit() {
        String input = "Himanshu 5454545454545454";
        String[] args = input.split(" ");
        String result = this.cardService.create(args);
        assertEquals("testCreateCardWithoutCreditLimit: ", Error.INVALID_ARGS, result);
    }

    @Test
    public void testCreateValidCard() {
        String input = "Himanshu 5454545454545454 $1000";
        String[] args = input.split(" ");
        String result = this.cardService.create(args);
        assertEquals("testCreateValidCard: ", "", result);
        assertNotNull(Database.getCard("5454545454545454"));
    }

    @Test
    public void testCreateCardWithInvalidCreditLimit() {
        String input = "Himanshu 5454545454545454 1000";
        String[] args = input.split(" ");
        String result = this.cardService.create(args);
        assertEquals("testCreateCardWithInvalidCreditLimit: ", Error.CREDIT_LIMIT_AMOUNT_INVALID, result);
    }

    @Test
    public void testCreateCardWithoutUser() {
        Database.clearAll();
        String input = "Himanshu 5454545454545454 $1000";
        String[] args = input.split(" ");
        String result = this.cardService.create(args);
        assertEquals("testCreateCardWithoutUser: ", Error.USER_NOT_FOUND, result);
    }

    @Test
    public void testCreateCardThatBelongsToAnotherUser() {
        String input = "Milana 5454545454545454 $1000";
        String[] args = input.split(" ");
        this.userService.create(args);
        this.cardService.create(args);

        input = "Himanshu 5454545454545454 $1000";
        args = input.split(" ");
        String result = this.cardService.create(args);
        assertEquals("testCreateCardThatBelongsToAnotherUser: ", Error.CARD_BELONGS_TO_ANOTHER_USER, result);
    }

    @Test
    public void testCreateCardAnotherCardOnSameUser() {
        String input = "Himanshu 5454545454545454 $1000";
        String[] args = input.split(" ");
        this.cardService.create(args);
        String result = this.cardService.create(args);
        assertEquals("testCreateCardAnotherCardOnSameUser: ", Error.USER_ALREADY_HAS_CARD, result);
    }

    @Test
    public void testCreateInvalidCard() {
        String input = "Himanshu 1234567890123456 $1000";
        String[] args = input.split(" ");
        String result = this.cardService.create(args);
        assertEquals("testCreateCardWithoutUser: ", Error.CARD_NUMBER_INVALID, result);
    }

    @Test
    public void testChargeWithoutDollarAmount() {
        String input = "Himanshu";
        String[] args = input.split(" ");
        String result = this.cardService.charge(args);
        assertEquals("testChargeWithoutDollarAmount: ", Error.INVALID_ARGS, result);
    }

    @Test
    public void testChargeWithInvalidAmount() {
        String input = "Himanshu 5454545454545454 $1000";
        String[] args = input.split(" ");
        this.cardService.create(args);

        input = "Himanshu 500";
        args = input.split(" ");
        String result = this.cardService.charge(args);
        assertEquals("testChargeWithInvalidAmount: ", Error.CHARGE_AMOUNT_INVALID, result);
    }

    @Test
    public void testChargeOnUserThatDoesNotExist() {
        Database.clearAll();
        String input = "Himanshu $500";
        String[] args = input.split(" ");
        String result = this.cardService.charge(args);
        assertEquals("testChargeOnUserThatDoesNotExist: ", Error.USER_NOT_FOUND, result);
    }


    @Test
    public void testChargeOverAllowedCreditLimit() {
        String input = "Himanshu 5454545454545454 $1000";
        String[] args = input.split(" ");
        this.cardService.create(args);

        input = "Himanshu $1111";
        args = input.split(" ");
        String result = this.cardService.charge(args);
        Card card = Database.getCard("5454545454545454");
        assertEquals("testChargeOverAllowedCreditLimit: ", Error.CHARGE_DECLINED, result);
        assertEquals("testChargeWithDollarAmount: does not update balance ", BigDecimal.ZERO, card.getBalance());
    }

    @Test
    public void testChargeWithDollarAmount() {
        String input = "Himanshu 5454545454545454 $1000";
        String[] args = input.split(" ");
        this.cardService.create(args);

        input = "Himanshu $500";
        args = input.split(" ");
        String result = this.cardService.charge(args);
        Card card = Database.getCard("5454545454545454");
        assertEquals("testChargeWithDollarAmount: ", "", result);
        assertEquals("testChargeWithDollarAmount: updates balance ", new BigDecimal("500"), card.getBalance());
    }

    @Test
    public void testCreditWithoutDollarAmount() {
        String input = "Himanshu";
        String[] args = input.split(" ");
        String result = this.cardService.credit(args);
        assertEquals("testCreditWithoutDollarAmount: ", Error.INVALID_ARGS, result);
    }

    @Test
    public void testCreditWithInvalidDollarAmount() {
        String input = "Himanshu 100";
        String[] args = input.split(" ");
        String result = this.cardService.credit(args);
        assertEquals("testCreditWithInvalidDollarAmount: ", Error.CREDIT_AMOUNT_INVALID, result);
    }

    @Test
    public void testCreditWithoutUser() {
        Database.clearAll();
        String input = "Himanshu $100";
        String[] args = input.split(" ");
        String result = this.cardService.credit(args);
        assertEquals("testCreditWithoutUser: ", Error.USER_NOT_FOUND, result);
    }

    @Test
    public void testCreditWithoutCard() {
        String input = "Himanshu $100";
        String[] args = input.split(" ");
        String result = this.cardService.credit(args);
        assertEquals("testCreditWithoutCard: ", Error.CARD_NOT_FOUND, result);
    }

    @Test
    public void testValidCreditDecreasesBalance() {
        String input = "Himanshu 5454545454545454 $1000";
        String[] args = input.split(" ");
        this.cardService.create(args);

        input = "Himanshu $100";
        args = input.split(" ");
        String result = this.cardService.credit(args);
        Card card = Database.getCard("5454545454545454");
        assertEquals("testValidCreditDecreasesBalance: ", "", result);
        assertEquals("testValidCreditDecreasesBalance: ", new BigDecimal("-100"), card.getBalance());
    }
}
