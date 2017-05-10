package validator;

import constants.Error;

public class UserServiceValidator {

    /**
     * Validate username
     *
     * @param args
     * @return
     */
    public Validation validateCreate(String[] args) {
        Validation validation = new Validation();
        if (args.length != 3) {
            validation.addError(Error.INVALID_ARGS);
            return validation;
        }

        String name = args[0];
        if (!Username.validate(name)) {
            validation.addError(Error.USERNAME_INVALID);
            return validation;
        }

        return validation;
    }
}
