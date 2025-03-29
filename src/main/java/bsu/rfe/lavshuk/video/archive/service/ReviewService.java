package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.MovieDAO;
import bsu.rfe.lavshuk.video.archive.dao.ReviewDAO;
import bsu.rfe.lavshuk.video.archive.dao.UserDAO;
import bsu.rfe.lavshuk.video.archive.entity.Movie;
import bsu.rfe.lavshuk.video.archive.entity.Review;
import bsu.rfe.lavshuk.video.archive.entity.User;

public class ReviewService {
    private volatile static ReviewService INSTANCE;
    private final ReviewDAO reviewDAO;

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

    public void createReview(double rating, String text, String Movie, String usersEmail) {
        Review review = new Review();
        review.setRating(rating);
        review.setText(text);
        Movie movie = MovieDAO.getINSTANCE().findByTitle(Movie).get();
        review.setMovie(movie);
        User user =UserDAO.getINSTANCE().getByEmail(usersEmail).get();
        review.setUser(user);
        reviewDAO.create(review);
    }
}
