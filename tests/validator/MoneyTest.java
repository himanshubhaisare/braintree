package tests.validator;

import org.junit.Test;
import validator.Money;

import static org.junit.Assert.assertEquals;

public class MoneyTest {

    public MoneyTest() {
    }

    @Test
    public void testAmountWithoutDollarSign() {
        String amount = "1000";
        boolean result = Money.validate(amount);
        assertEquals("testAmountWithoutDollarSign: ", false, result);
    }

    @Test
    public void testValidAmount() {
        String amount = "$1000";
        boolean result = Money.validate(amount);
        assertEquals("testValidAmount: ", true, result);
    }

    @Test
    public void testInvalidAmount() {
        String amount = "$---xf";
        boolean result = Money.validate(amount);
        assertEquals("testInvalidAmount: ", false, result);
    }

    @Test
    public void testValidAmountWithCents() {
        String amount = "$1000.50";
        boolean result = Money.validate(amount);
        assertEquals("testValidAmountWithCents: ", true, result);
    }

    @Test
    public void testValidAmountWithLeadingZeros() {
        String amount = "$01000";
        boolean result = Money.validate(amount);
        assertEquals("testValidAmountWithLeadingZeros: ", true, result);
    }

    @Test
    public void testAmountWithTrailingDollarSign() {
        String amount = "1000$";
        boolean result = Money.validate(amount);
        assertEquals("testAmountWithTrailingDollarSign: ", false, result);
    }
}
