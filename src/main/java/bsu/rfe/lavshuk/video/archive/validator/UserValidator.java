package bsu.rfe.lavshuk.video.archive.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

    public static void validateUserParameters(String name, String surname, String password, String email)
            throws ValidationException {
        List<String> fields = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            fields.add("name");
        }
        if (surname == null || surname.isEmpty()) {
            fields.add("surname");
        }
        if (password == null || password.isEmpty()) {
            fields.add("password");
        }
        if (email == null || email.isEmpty()) {
            fields.add("email");
        }


        if (!fields.isEmpty()) {
            String error = "User's parameters are incorrect : " + String.join(",", fields);
            logger.error(error);
            throw new ValidationException(error);
        }

    }
}
