package bsu.rfe.lavshuk.video.archive.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ReviewValidator {
    private static final Logger logger = LoggerFactory.getLogger(ReviewValidator.class);

    public static void validateReviewParameters(Double rating, String text, String movieTitle, String usersEmail)
            throws ValidationException {
        List<String> fields = new ArrayList<>();
        if (rating == null || rating.isNaN()) {
            fields.add("rating");
        }
        if (text == null || text.isEmpty()) {
            fields.add("text");
        }
        if (movieTitle == null || movieTitle.isEmpty()) {
            fields.add("movieTitle");
        }
        if (usersEmail == null || usersEmail.isEmpty()) {
            fields.add("usersEmail");
        }


        if (!fields.isEmpty()) {
            String error = "Review's parameters are incorrect : " + String.join(",", fields);
            logger.error(error);
            throw new ValidationException(error);
        }

    }
}
