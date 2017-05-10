package tests.service;

import constants.Error;
import org.junit.Test;
import service.CardService;
import service.UserService;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    public UserServiceTest() {
    }

    @Test
    public void testCreateUserWithoutUsername() {
        UserService service = new UserService();
        String input = "";
        String[] args = input.split(" ");
        String result = service.create(args);
        assertEquals("testCreateUserWithoutUsername: ", Error.INVALID_ARGS, result);
    }

    @Test
    public void testCreateUserWithoutCardNumber() {
        UserService service = new UserService();
        String input = "Himanshu";
        String[] args = input.split(" ");
        String result = service.create(args);
        assertEquals("testCreateUserWithoutCardNumber: ", Error.INVALID_ARGS, result);
    }

    @Test
    public void testCreateUserWithoutCreditLimit() {
        UserService service = new UserService();
        String input = "Himanshu 5454545454545454";
        String[] args = input.split(" ");
        String result = service.create(args);
        assertEquals("testCreateUserWithoutCreditLimit: ", Error.INVALID_ARGS, result);
    }

    @Test
    public void testCreateUser() {
        UserService service = new UserService();
        String input = "Himanshu 5454545454545454 $1000";
        String[] args = input.split(" ");
        String result = service.create(args);
        assertEquals("testCreateUser: ", "", result);
    }

    @Test
    public void testCreateUserWithInvalidUserName() {
        UserService service = new UserService();
        String input = "HimanshuVasantBhaisareTheSecond 5454545454545454 $1000";
        String[] args = input.split(" ");
        String result = service.create(args);
        assertEquals("testCreateUserWithInvalidUserName: ", Error.USERNAME_INVALID, result);
    }

    @Test
    public void testSummary() {
        UserService service = new UserService();
        CardService cardService = new CardService();
        String input1 = "Himanshu 5454545454545454 $1000";
        String[] args = input1.split(" ");
        service.create(args);
        cardService.create(args);
        String input2 = "Milana 4111111111111111 $1000";
        args = input2.split(" ");
        service.create(args);
        cardService.create(args);
        String summary = service.summary();
        assertEquals("testSummary: ", "Himanshu: $0\n" +
                "Milana: $0", summary);
    }
}
