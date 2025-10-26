package com.crfstech.MyRemote.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator {

    public static PasswordValidationResult isValidPassword(String password) {
        List<String> errors = new ArrayList<>();

        // 1. Password length: Must be between 8 and 20 characters
        if (password.length() < 8 || password.length() > 20) {
            errors.add("Password must be between 8 and 20 characters long.");
        }

        // 2. Contains at least one uppercase letter
        if (!Pattern.compile(".*[A-Z].*").matcher(password).matches()) {
            errors.add("Password must contain at least one uppercase letter.");
        }

        // 3. Contains at least one lowercase letter
        if (!Pattern.compile(".*[a-z].*").matcher(password).matches()) {
            errors.add("Password must contain at least one lowercase letter.");
        }

        // 4. Contains at least one digit
        if (!Pattern.compile(".*[0-9].*").matcher(password).matches()) {
            errors.add("Password must contain at least one digit.");
        }

        // 5. Contains at least one special character
        if (!Pattern.compile(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*").matcher(password).matches()) {
            errors.add("Password must contain at least one special character.");
        }

        boolean isValid = errors.isEmpty();
        return new PasswordValidationResult(isValid, errors);
    }


    public record PasswordValidationResult(boolean isValid, List<String> errors) {}
}
