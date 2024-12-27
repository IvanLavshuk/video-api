package bsu.rfe.lavshuk.videoArchive.dao;

import bsu.rfe.lavshuk.videoArchive.db.Connector;
import bsu.rfe.lavshuk.videoArchive.entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReviewDAO extends DAO<Review> {

    private static final Logger logger = Logger.getLogger(MovieDAO.class.getSimpleName());

    @Override
    public void create(Review review) {

        if(review == null){
            logger.info("review is null");
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
            logger.info("Error executing query:" + query+", errormessage: " + e.getMessage());
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


                    if(resultSet.next()) {
                        Review review = new Review();
                        review.setId(resultSet.getInt("id_review"));
                        review.setRating(resultSet.getDouble("rating"));
                        review.setText(resultSet.getString("text"));
                        review.setUser(new UserDAO().getById(resultSet.getInt("id_user")));
                        review.setMovie(new MovieDAO().getById(resultSet.getInt("id_movie")));
                        return review;
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.info("Error executing query:" + query+", errormessage: " + e.getMessage());
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
            logger.info("Error executing query:" + query+", errormessage: " + e.getMessage());
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
            logger.info("Error executing query:" + query+", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

