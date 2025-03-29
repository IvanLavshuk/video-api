package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.MovieDAO;
import bsu.rfe.lavshuk.video.archive.dao.ReviewDAO;
import bsu.rfe.lavshuk.video.archive.dao.UserDAO;
import bsu.rfe.lavshuk.video.archive.entity.Movie;
import bsu.rfe.lavshuk.video.archive.entity.Review;
import bsu.rfe.lavshuk.video.archive.entity.User;
import bsu.rfe.lavshuk.video.archive.validator.ReviewValidator;
import bsu.rfe.lavshuk.video.archive.validator.ServiceException;
import bsu.rfe.lavshuk.video.archive.validator.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReviewService {
    private volatile static ReviewService INSTANCE;
    private final ReviewDAO reviewDAO;
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    private ReviewService() {
        reviewDAO = ReviewDAO.getINSTANCE();
    }

    public static ReviewService getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (ReviewService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReviewService();
                }
            }
        }
        return INSTANCE;
    }

    public void createReview(Double rating, String text, String movieTitle, String usersEmail)
            throws ValidationException, ServiceException {
        try {
            ReviewValidator.validateReviewParameters(rating, text, movieTitle, usersEmail);
        } catch (ValidationException e) {
            logger.error("Failed to create review. Invalid parameters");
            throw e;
        }

        Review review = new Review();
        review.setRating(rating);
        review.setText(text);
        Movie movie = MovieDAO.getINSTANCE().findByTitle(movieTitle).
                orElseThrow(() -> new ServiceException("Movie from review is not found"));
        review.setMovie(movie);
        User user = UserDAO.getINSTANCE().getByEmail(usersEmail).
                orElseThrow(() -> new ServiceException("User from review is not found"));
        review.setUser(user);
        reviewDAO.create(review);
    }
}
