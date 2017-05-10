package tests.validator;

import org.junit.Test;
import validator.Username;

import static org.junit.Assert.assertEquals;

public class UsernameTest {

    public UsernameTest() {
    }

    @Test
    public void testUsernameSmallerThan2Chars() {
        String username = "A";
        boolean result = Username.validate(username);
        assertEquals("testUsernameSmallerThan2Chars", false, result);
    }

    @Test
    public void testUsernameGreaterThan15Chars() {
        String username = "HimanshuVasantBhaisareTheSecond";
        boolean result = Username.validate(username);
        assertEquals("testUsernameGreaterThan15Chars", false, result);
    }
    
    @Test
    public void testUsernameWithAllCaps() {
        String username = "HIMANSHU";
        boolean result = Username.validate(username);
        assertEquals("testUsernameWithAllCaps", true, result);
    }

    @Test
    public void testUsernameWithAllSmalls() {
        String username = "himanshu";
        boolean result = Username.validate(username);
        assertEquals("testUsernameWithAllSmalls", true, result);
    }

    @Test
    public void testUsernameWithLettersAndNumbers() {
        String username = "Himanshu123";
        boolean result = Username.validate(username);
        assertEquals("testUsernameWithLettersAndNumbers", true, result);
    }
}
