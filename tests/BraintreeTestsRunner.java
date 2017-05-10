package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import tests.service.BraintreeTest;
import tests.service.CardServiceTest;
import tests.service.UserServiceTest;
import tests.validator.LuhnTest;
import tests.validator.MoneyTest;
import tests.validator.UsernameTest;
import tests.validator.ValidationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    LuhnTest.class,
    MoneyTest.class,
    UsernameTest.class,
    ValidationTest.class,
    CardServiceTest.class,
    UserServiceTest.class,
    BraintreeTest.class
})
public class BraintreeTestsRunner {

    /**
     * Braintree tests runner
     *
     * @param args
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BraintreeTestsRunner.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests finished successfully...");
        }
    }
}
