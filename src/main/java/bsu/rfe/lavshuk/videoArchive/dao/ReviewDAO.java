package bsu.rfe.lavshuk.videoArchive.dao;

import bsu.rfe.lavshuk.videoArchive.db.Connector;
import bsu.rfe.lavshuk.videoArchive.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO extends DAO<Review> {

    private static final Logger logger = LoggerFactory.getLogger(ReviewDAO.class);

    @Override
    public void create(Review review) {

        if(review == null){
            logger.error("Object :{} is null",review.getClass().getSimpleName());
            throw new RuntimeException();
        }

        String query = "INSERT INTO reviews (rating,text,id_user,id_movie) VALUES(?,?,?,?)";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, review.getRating());
                preparedStatement.setString(2, review.getText());
                preparedStatement.setInt(3, review.getUser().getId());
                preparedStatement.setInt(4, review.getMovie().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public Review getById(int id) {

        String query = "SELECT id_review,rating,text,id_user,id_movie FROM reviews WHERE id_review=?";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Review review = new Review();
                    review.setId(id);
                    while (resultSet.next()) {
                        review.setId(resultSet.getInt("id_review"));
                        review.setRating(resultSet.getDouble("rating"));
                        review.setText(resultSet.getString("text"));
                        review.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                        review.setMovie(new MovieDAO().getById(resultSet.getInt("id_movie")));

                    }
                    return review;
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Review> getAll() {

        String query = "SELECT* FROM reviews";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<Review> reviews = new ArrayList<>();
                    while (resultSet.next()) {
                        Review review = new Review();
                        review.setId(resultSet.getInt("id_review"));
                        review.setRating(resultSet.getDouble("rating"));
                        review.setText(resultSet.getString("text"));
                        review.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                        review.setMovie(new MovieDAO().getById(resultSet.getInt("id_movie")));
                        reviews.add(review);
                    }
                    return reviews;
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @Override
    public void removeById(int id) {
        String query = "DELETE FROM reviews WHERE id_review=?";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

