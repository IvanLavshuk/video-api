package bsu.rfe.lavshuk.videoArchive.service;

import bsu.rfe.lavshuk.videoArchive.dao.ReviewDAO;
import bsu.rfe.lavshuk.videoArchive.entity.Movie;
import bsu.rfe.lavshuk.videoArchive.entity.User;
import bsu.rfe.lavshuk.videoArchive.entity.Review;

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

    public void ReviewMovie(double rating , String text, Movie Movie, User user) {
        Review review = new Review();
        review.setMovie(Movie);
        review.setRating(rating);
        review.setText(text);
        review.setUser(user);
        reviewDAO.create(review);
    }
}
