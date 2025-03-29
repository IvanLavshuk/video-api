package bsu.rfe.lavshuk.video.archive.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MovieValidator {
    private static final Logger logger = LoggerFactory.getLogger(MovieValidator.class);

    public static void validateMovieParameters(String title, String genre, String country, String releaseDate,
                                               String directorName, String directorSurname) throws ValidationException {
        List<String> fields = new ArrayList<>();
        if (title == null || title.isEmpty()) {
            fields.add("title");
        }
        if (genre == null || genre.isEmpty()) {
            fields.add("genre");
        }
        if (country == null || country.isEmpty()) {
            fields.add("country");
        }
        if (releaseDate == null || releaseDate.isEmpty()) {
            fields.add("releaseDate");
        }
        if (directorName == null || directorName.isEmpty()) {
            fields.add("directorName");
        }
        if (directorSurname == null || directorSurname.isEmpty()) {
            fields.add("directorSurname");
        }

        if (!fields.isEmpty()) {
            String error = "Movie's parameters are incorrect : " + String.join(",", fields);
            logger.error(error);
            throw new ValidationException(error);
        }

    }
}
