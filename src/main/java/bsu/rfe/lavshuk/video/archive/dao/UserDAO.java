package bsu.rfe.lavshuk.video.archive.dao;

import bsu.rfe.lavshuk.video.archive.db.Connector;
import bsu.rfe.lavshuk.video.archive.entity.User;
import bsu.rfe.lavshuk.video.archive.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO extends DAO<User> {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users (name, surname, password, email) VALUES(?, ?, ?, ?)";
    private static final String FIND_USER_BY_ID_QUERY =
            "SELECT id_user, name, surname, password, email FROM users WHERE id_user = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT id_user, name, surname, password, email FROM users";
    private static final String FIND_USER_BY_EMAIL_QUERY =
            "SELECT id_user, name, surname, password, email FROM users WHERE email = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id_user = ?";
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private static volatile UserDAO INSTANCE;
    private static final String ID_USER = "id_user";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";

    private UserDAO() {
    }

    public static UserDAO getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (UserDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserDAO();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void create(User user) {

        if (user == null) {
            logger.error("user is null");
            throw new DaoException("User is null!!!");
        }

        String query = CREATE_USER_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to create user ", e);
        }

    }

    @Override
    public Optional<User> findById(int id) {

        String query = FIND_USER_BY_ID_QUERY;

        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean isFound = resultSet.next();
                if (isFound) {
                    User user = new User();
                    user.setId(resultSet.getInt(ID_USER));
                    user.setName(resultSet.getString(NAME));
                    user.setSurname(resultSet.getString(SURNAME));
                    user.setEmail(resultSet.getString(EMAIL));
                    user.setPassword(resultSet.getString(PASSWORD));
                    return Optional.of(user);
                }
                return Optional.empty();


            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find user by id ", e);
        }

    }

    @Override
    public List<User> findAll() {

        String query = FIND_ALL_USERS_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery(query)
        ) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID_USER));
                user.setName(resultSet.getString(NAME));
                user.setSurname(resultSet.getString(SURNAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find all users ", e);
        }

    }

    @Override
    public void removeById(int id) {
        String query = DELETE_USER_QUERY;

        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to delete user", e);
        }

    }

    public Optional<User> getByEmail(String email) {

        String query = FIND_USER_BY_EMAIL_QUERY;

        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean isFound = resultSet.next();
                if (isFound) {
                    User user = new User();
                    user.setId(resultSet.getInt(ID_USER));
                    user.setName(resultSet.getString(NAME));
                    user.setSurname(resultSet.getString(SURNAME));
                    user.setEmail(resultSet.getString(EMAIL));
                    user.setPassword(resultSet.getString(PASSWORD));
                    return Optional.of(user);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.info("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find user by email");
        }


    }
}

