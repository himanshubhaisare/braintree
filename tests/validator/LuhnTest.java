package tests.validator;

import org.junit.Test;
import validator.Luhn;

import static org.junit.Assert.assertEquals;

public class LuhnTest {

    public LuhnTest() {
    }

    @Test
    public void testLuhnInvalidCard() {
        String nonLuhnCard = "1234567890123456";
        boolean result = Luhn.validate(nonLuhnCard);
        assertEquals("testLuhnInvalidCard : ", false, result);
    }

    @Test
    public void testLuhnValidCard() {
        String luhnCard = "4111111111111111";
        boolean result = Luhn.validate(luhnCard);
        assertEquals("testLuhnValidCard : ", true, result);
    }

    @Test
    public void test19DigitLuhnCard() {
        String luhnCard = "6304219447607087665";
        boolean result = Luhn.validate(luhnCard);
        assertEquals("test19DigitLuhnCard : ", true, result);
    }

    @Test
    public void test20DigitLuhnCard() {
        String luhnCard = "63042194476070876651";
        boolean result = Luhn.validate(luhnCard);
        assertEquals("test20DigitLuhnCard : ", false, result);
    }
}
