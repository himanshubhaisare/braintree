package validator;

import java.util.ArrayList;
import java.util.List;

public class Validation {

    private List<String> errors;

    public Validation() {
        this.errors = new ArrayList<String>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public boolean isValid() {
        return (this.errors.isEmpty());
    }

    public String getErrorString() {
        String errorString = "";
        int count = 0;
        for (String error : errors) {
            count++;
            errorString += error;
            if (count < errors.size()) {
                errorString += " ";
            }
        }
        return errorString;
    }
}
