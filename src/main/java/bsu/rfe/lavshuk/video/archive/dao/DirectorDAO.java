package bsu.rfe.lavshuk.video.archive.dao;

import bsu.rfe.lavshuk.video.archive.db.Connector;
import bsu.rfe.lavshuk.video.archive.entity.Director;
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

public class DirectorDAO extends DAO<Director> {
    private static final String CREATE_DIRECTOR_QUERY =
            "INSERT INTO directors (name, surname, birthdate) VALUES(?, ?, ?)";
    private static final String FIND_DIRECTOR_BY_ID_QUERY =
            "SELECT id_director, name, surname, birthdate FROM directors WHERE id_director = ?";
    private static final String FIND_ALL_DIRECTORS_QUERY =
            "SELECT id_director, name, surname, birthdate FROM directors";
    private static final String FIND_DIRECTOR_BY_FULL_NAME =
            "SELECT id_director, name, surname, birthdate FROM directors WHERE name = ? AND surname = ?";
    private static final String DELETE_DIRECTOR_QUERY =
            "DELETE FROM directors WHERE id_director = ?";
    private static final Logger logger = LoggerFactory.getLogger(DirectorDAO.class);
    private static volatile DirectorDAO INSTANCE;
    private static final String ID_DIRECTOR = "id_director";
    private static final String NAME_DIRECTOR = "name";
    private static final String SURNAME_DIRECTOR = "surname";
    private static final String BIRTHDATE_DIRECTOR = "birthdate";

    private DirectorDAO() {
    }

    public static DirectorDAO getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (DirectorDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DirectorDAO();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void create(Director director) {
        if (director == null) {
            logger.error("director is null");
            throw new DaoException("Director is null!");
        }

        String query = CREATE_DIRECTOR_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, director.getName());
            preparedStatement.setString(2, director.getSurname());
            preparedStatement.setString(3, director.getBirthdate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to create director", e);
        }

    }

    @Override
    public Optional<Director> findById(int id) {
        String query = FIND_DIRECTOR_BY_ID_QUERY;

        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Director director = new Director();
                    director.setId(resultSet.getInt(ID_DIRECTOR));
                    director.setName(resultSet.getString(NAME_DIRECTOR));
                    director.setSurname(resultSet.getString(SURNAME_DIRECTOR));
                    director.setBirthdate(resultSet.getString(BIRTHDATE_DIRECTOR));
                    return Optional.of(director);
                }
                return Optional.empty();

            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find director by id", e);
        }
    }

    @Override
    public List<Director> findAll() {

        String query = FIND_ALL_DIRECTORS_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Director> directors = new ArrayList<>();
            while (resultSet.next()) {
                Director director = new Director();
                director.setId(resultSet.getInt(ID_DIRECTOR));
                director.setName(resultSet.getString(NAME_DIRECTOR));
                director.setSurname(resultSet.getString(SURNAME_DIRECTOR));
                director.setBirthdate(resultSet.getString(BIRTHDATE_DIRECTOR));
                directors.add(director);
            }
            return directors;

        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find all directors", e);
        }

    }

    public Optional<Director> findByFullName(String name, String surname) {
        String query = FIND_DIRECTOR_BY_FULL_NAME;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Director director = new Director();
                    director.setId(resultSet.getInt(ID_DIRECTOR));
                    director.setName(resultSet.getString(NAME_DIRECTOR));
                    director.setSurname(resultSet.getString(SURNAME_DIRECTOR));
                    director.setBirthdate(resultSet.getString(BIRTHDATE_DIRECTOR));
                    return Optional.of(director);
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find director by full name", e);
        }

    }

    @Override
    public void removeById(int id) {
        String query = DELETE_DIRECTOR_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to delete exception");
        }

    }
}
