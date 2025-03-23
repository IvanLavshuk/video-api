package bsu.rfe.lavshuk.video.archive.dao;

import bsu.rfe.lavshuk.video.archive.db.Connector;
import bsu.rfe.lavshuk.video.archive.entity.Review;
import bsu.rfe.lavshuk.video.archive.validator.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDAO extends DAO<Review> {
    private static final String CREATE_REVIEW_QUERY =
            "INSERT INTO reviews (rating, text, id_user, id_movie) VALUES(?, ?, ?, ?)";
    private static final String FIND_REVIEW_BY_ID_QUERY =
            "SELECT id_review, rating, text, id_user, id_movie FROM reviews WHERE id_review = ?";
    private static final String FIND_ALL_REVIEWS_QUERY =
            "SELECT id_review, rating, text, id_user, id_movie FROM reviews";
    private static final String DELETE_REVIEW_QUERY =
            "DELETE FROM reviews WHERE id_review = ?";
    private static final Logger logger = LoggerFactory.getLogger(ReviewDAO.class);
    private static volatile ReviewDAO INSTANCE;
    private static final String ID_REVIEW = "id_review";
    private static final String RATING = "rating";
    private static final String TEXT = "text";
    private static final String ID_USER = "id_user";
    private static final String ID_MOVIE = "id_movie";

    private ReviewDAO() {
    }

    public static ReviewDAO getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (ReviewDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReviewDAO();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void create(Review review) {

        if (review == null) {
            logger.error("review is null");
            throw new DaoException("Review is null!");
        }

        String query = CREATE_REVIEW_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, review.getRating());
            preparedStatement.setString(2, review.getText());
            preparedStatement.setInt(3, review.getUser().getId());
            preparedStatement.setInt(4, review.getMovie().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to create review ", e);
        }

    }

    @Override
    public Optional<Review> findById(int id) {
        String query = FIND_REVIEW_BY_ID_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Review review = new Review();
                    review.setId(resultSet.getInt(ID_REVIEW));
                    review.setRating(resultSet.getDouble(RATING));
                    review.setText(resultSet.getString(TEXT));
                    Integer idUser = resultSet.getInt(ID_USER);
                    Integer idMovie = resultSet.getInt(ID_MOVIE);
                    review.setUser(UserDAO.getINSTANCE().findById(idUser).get());
                    review.setMovie(MovieDAO.getINSTANCE().findById(idMovie).get());
                    return Optional.of(review);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find review by id");
        }

    }

    @Override
    public List<Review> findAll() {
        String query = FIND_ALL_REVIEWS_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Review> reviews = new ArrayList<>();
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getInt(ID_REVIEW));
                review.setRating(resultSet.getDouble(RATING));
                review.setText(resultSet.getString(TEXT));
                Integer idUser = resultSet.getInt(ID_USER);
                Integer idMovie = resultSet.getInt(ID_MOVIE);
                review.setUser(UserDAO.getINSTANCE().findById(idUser).get());
                review.setMovie(MovieDAO.getINSTANCE().findById(idMovie).get());
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to get all reviews", e);
        }


    }

    @Override
    public void removeById(int id) {
        String query = DELETE_REVIEW_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to delete review", e);
        }
    }
}

