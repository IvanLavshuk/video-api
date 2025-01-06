package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.MovieDAO;
import bsu.rfe.lavshuk.video.archive.dao.ReviewDAO;
import bsu.rfe.lavshuk.video.archive.dao.UserDAO;
import bsu.rfe.lavshuk.video.archive.entity.Review;

public class ReviewService {
    private volatile static ReviewService instance;
    private final ReviewDAO reviewDAO;

    private ReviewService() {
        reviewDAO = new ReviewDAO();
    }

    public static ReviewService getInstance() {
        if (instance == null) {
            synchronized (ReviewService.class) {
                if (instance == null) {
                    instance = new ReviewService();
                }
            }
        }
        return instance;
    }

    public void createReview(double rating, String text, String Movie, String usersEmail) {
        Review review = new Review();
        review.setRating(rating);
        review.setText(text);
        review.setMovie(new MovieDAO().getByTitle(Movie));
        review.setUser(new UserDAO().getByEmail(usersEmail));
        reviewDAO.create(review);
    }
}
