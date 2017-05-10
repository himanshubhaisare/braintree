package tests.validator;

import org.junit.Test;
import validator.Validation;

import static org.junit.Assert.assertEquals;

public class ValidationTest {

    public ValidationTest() {
    }

    @Test
    public void testValidationWithErrors() {
        Validation validation = new Validation();
        validation.addError("Random error happened");
        boolean result = validation.isValid();
        assertEquals("testValidationWithErrors: ", false, result);
    }

    @Test
    public void testValidationWithoutErrors() {
        Validation validation = new Validation();
        boolean result = validation.isValid();
        assertEquals("testValidationWithoutErrors: ", true, result);
    }

    @Test
    public void testErrorStringWithMultipleErrors() {
        Validation validation = new Validation();
        validation.addError("Error 1");
        validation.addError("Error 2");
        String errorString = validation.getErrorString();
        assertEquals("testErrorStringWithMultipleErrors: ", "Error 1 Error 2", errorString);
    }

}
