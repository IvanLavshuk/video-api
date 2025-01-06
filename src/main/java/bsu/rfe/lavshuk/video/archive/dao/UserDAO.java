package bsu.rfe.lavshuk.video.archive.dao;

import bsu.rfe.lavshuk.video.archive.db.Connector;
import bsu.rfe.lavshuk.video.archive.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO extends DAO<User> {

    private static final Logger logger = Logger.getLogger(UserDAO.class.getSimpleName());

    @Override
    public void create(User user) {

        if (user == null) {
            logger.info("user is null");
            throw new RuntimeException();
        }

        String query = "INSERT INTO users (name,surname,password,email) VALUES(?,?,?,?)";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.info("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public User getById(int id) {

        String query = "SELECT id_user,name,surname,password,email FROM users WHERE id_user=?";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    boolean isFound = resultSet.next();
                    if (isFound) {
                        User user = new User();
                        user.setId(resultSet.getInt("id_user"));
                        user.setName(resultSet.getString("name"));
                        user.setSurname(resultSet.getString("surname"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        return user;
                    }
                    return null;
                }

            }
        } catch (SQLException e) {
            logger.info("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<User> getAll() {

        String query = "SELECT* FROM users";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery(query)) {
                    List<User> users = new ArrayList<>();
                    while (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getInt("id_user"));
                        user.setName(resultSet.getString("name"));
                        user.setSurname(resultSet.getString("surname"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        users.add(user);
                    }
                    return users;
                }

            }
        } catch (SQLException e) {
            logger.info("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeById(int id) {
        String query = "DELETE FROM users WHERE id_user=?";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.info("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public User getByEmail(String email) {

        String query = "SELECT id_user,name,surname,password,email FROM users WHERE email=?";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    boolean isFound = resultSet.next();
                    if (isFound) {
                        User user = new User();
                        user.setId(resultSet.getInt("id_user"));
                        user.setName(resultSet.getString("name"));
                        user.setSurname(resultSet.getString("surname"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                        return user;
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.info("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }


    }
}

