package tests.service;

import database.Database;
import org.junit.After;
import service.Braintree;
import service.CardService;
import service.UserService;

public class BraintreeTest {

    private UserService userService;

    private CardService cardService;

    private Braintree braintree;

    public BraintreeTest() {
        this.userService = new UserService();
        this.cardService = new CardService();
        this.braintree = new Braintree(this.userService, this.cardService);
    }

    @After
    public void tearDown() {
        Database.clearAll();
    }

//    @Test
//    public void testBraintreeHandlesEmptyInput() {
//        String input = "";
//        String result = this.braintree.handle(input);
//        assertEquals("testBraintreeHandlesEmptyInput: ", "", result);
//    }
//
//    @Test
//    public void testBraintreeHandlesInvalidCommand() {
//        String input = "BAD COMMAND";
//        String result = this.braintree.handle(input);
//        assertEquals("testBraintreeHandlesInvalidCommand: ", Error.COMMAND_NOT_RECOGNIZED, result);
//    }
//
//    @Test
//    public void testBraintreeHandlesAddCommand() {
//        String input = "Add Himanshu 5454545454545454 $1000";
//        String result = this.braintree.handle(input);
//        User user = Database.getUser("Himanshu");
//        Card card = Database.getCard("5454545454545454");
//        assertEquals("testBraintreeHandlesAddCommand: ", "Himanshu: $0", result);
//        assertNotNull("user Himanshu was created: ", user);
//        assertNotNull("card was added on Himanshu: ", card);
//    }
//
//    @Test
//    public void testBraintreeHandlesAddCommandWithNonLuhnCard() {
//        String input = "Add Himanshu 1234567890123456 $1000";
//        String result = this.braintree.handle(input);
//        User user = Database.getUser("Himanshu");
//        Card card = Database.getCard("1234567890123456");
//        assertEquals("testBraintreeHandlesAddCommandWithNonLuhnCard: ", "ERROR: this card is invalid\n" +
//                "Himanshu: error", result);
//        assertNotNull("user Himanshu was created: ", user);
//        assertNull("card was NOT created: ", card);
//    }
//
//    @Test
//    public void testBraintreeHandlesChargeCommand() {
//        String input = "Himanshu 5454545454545454 $1000";
//        String[] args = input.split(" ");
//        this.userService.create(args);
//        this.cardService.create(args);
//
//        input = "Charge Himanshu $800";
//        String result = this.braintree.handle(input);
//        Card card = Database.getCard("5454545454545454");
//        assertEquals("testBraintreeHandlesChargeCommand: ", "Himanshu: $800", result);
//        assertEquals("card balance increases after Charge: ", new BigDecimal("800"), card.getBalance());
//    }
//
//    @Test
//    public void testBraintreeHandlesChargeOverCreditLimit() {
//        String input = "Himanshu 5454545454545454 $1000";
//        String[] args = input.split(" ");
//        this.userService.create(args);
//        this.cardService.create(args);
//
//        input = "Charge Himanshu $8000";
//        String result = this.braintree.handle(input);
//        Card card = Database.getCard("5454545454545454");
//        assertEquals("testBraintreeHandlesChargeOverCreditLimit: ", "ERROR: charge declined\n" +
//                "Himanshu: $0", result);
//        assertEquals("card balance remains same: ", BigDecimal.ZERO, card.getBalance());
//    }
//
//    @Test
//    public void testBraintreeHandlesCreditCommand() {
//        Database.clearAll();
//        String input = "Himanshu 5454545454545454 $1000";
//        String[] args = input.split(" ");
//        this.userService.create(args);
//        this.cardService.create(args);
//
//        input = "Credit Himanshu $800";
//        String result = this.braintree.handle(input);
//        Card card = Database.getCard("5454545454545454");
//        assertEquals("testBraintreeHandlesCreditCommand: ", "Himanshu: $-800", result);
//        assertEquals("card balance decreases after credit: ", new BigDecimal("-800"), card.getBalance());
//    }
}
