package bsu.rfe.lavshuk.video.archive.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PersonValidator {
    private static final Logger logger = LoggerFactory.getLogger(PersonValidator.class);

    public static void validateParameters(String name, String surname, String birthdate)
            throws ValidationException {
        List<String> fields = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            fields.add("name");
        }
        if (surname == null || surname.isEmpty()) {
            fields.add("surname");
        }
        if (birthdate == null || birthdate.isEmpty()) {
            fields.add("birthdate");
        }

        if (!fields.isEmpty()) {
            String error = "Person's parameters are incorrect : " + String.join(",", fields);
            logger.error(error);
            throw new ValidationException(error);
        }

    }
}
