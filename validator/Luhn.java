package validator;

/**
 * Source :
 * https://www.rosettacode.org/wiki/Luhn_test_of_credit_card_numbers
 *
 * For example, if the trial number is 49927398716:

     Reverse the digits:
     61789372994
     Sum the oddly placed digits:
     6 + 7 + 9 + 7 + 9 + 4 = 42 = s1

     The evenly spaced digits:
     1,  8,  3,  2,  9
     Two times each evenly spaced digit:
     2, 16,  6,  4, 18
     Sum the digits of each multiplication:
     2,  7,  6,  4,  9
     Sum the last:
     2 + 7 + 6 + 4 + 9 = 28 = s2

     s1 + s2 = 70 which ends in zero which means that 49927398716 passes the Luhn test
 */
public class Luhn {

    /**
     * Validate a number string using Luhn algorithm
     *
     * @param numberString
     * @return
     */
    public static boolean validate(String numberString) {
        if (numberString.length() > 19) {
            return false;
        }
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(numberString).reverse().toString();
        for (int i = 0; i < reverse.length(); i++) {
            int digit = Character.digit(reverse.charAt(i), 10);
            if (i % 2 == 0) { // digit on an odd place
                s1 += digit;
            } else { // digits on even places
                s2 += 2 * digit; // add 2 * digit for 0-4,
                if (digit >= 5) {
                    s2 -= 9; //  add 2 * digit - 9 for 5-9 (2 * 5...9 >= 10) and we have to sum the digits
                }
            }
        }

        return (s1 + s2) % 10 == 0;
    }
}
