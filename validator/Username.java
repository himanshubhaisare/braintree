package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Username {

    /**
     * allow alphanumeric, dashes, underscores, min 2 & max 15 characters
     */
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{2,15}$";

    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

    /**
     * Validate user name
     *
     * @param username
     * @return
     */
    public static boolean validate(String username) {
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
}
