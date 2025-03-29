package bsu.rfe.lavshuk.video.archive.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DirectorValidator {
    private static final Logger logger = LoggerFactory.getLogger(DirectorValidator.class);

    public static void validateDirectorParameters(String name, String surname, String birthdate) throws ValidationException {
        List<String> fields = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            fields.add("name");
        }
        if (surname == null || name.isEmpty()) {
            fields.add("surname");
        }
        if (birthdate == null || birthdate.isEmpty()) {
            fields.add("birthdate");
        }

        if (!fields.isEmpty()) {
            String error = "Director's parameters are incorrect : " + String.join(",", fields);
            logger.error(error);
            throw new ValidationException(error);
        }

    }
}
