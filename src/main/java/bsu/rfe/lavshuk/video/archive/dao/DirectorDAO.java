package bsu.rfe.lavshuk.video.archive.dao;

import bsu.rfe.lavshuk.video.archive.db.Connector;
import bsu.rfe.lavshuk.video.archive.entity.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectorDAO extends DAO<Director> {
    private static final Logger logger = LoggerFactory.getLogger(DirectorDAO.class);

    @Override
    public void create(Director director) {
        if (director == null) {
            logger.error("director is null");
            throw new RuntimeException();
        }

        String query = "INSERT INTO directors (name,surname,birthdate) VALUES(?,?,?)";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, director.getName());
                preparedStatement.setString(2, director.getSurname());
                preparedStatement.setString(3, director.getBirthdate());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public Director getById(int id) {
        String query = "SELECT id_director, name, surname, birthdate FROM directors WHERE id_director=?";

        try (Connection connection = Connector.get()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Director director = new Director();
                        director.setId(resultSet.getInt("id_director"));
                        director.setName(resultSet.getString("name"));
                        director.setSurname(resultSet.getString("surname"));
                        director.setBirthdate(resultSet.getString("birthdate"));
                        return director;
                    }
                    return null;
                }

            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Director> getAll() {

        String query = "SELECT id_director, name, surname, birthdate FROM directors";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<Director> directors = new ArrayList<>();
                    while (resultSet.next()) {
                        Director director = new Director();
                        director.setId(resultSet.getInt("id_director"));
                        director.setName(resultSet.getString("name"));
                        director.setSurname(resultSet.getString("surname"));
                        director.setBirthdate(resultSet.getString("birthdate"));
                        directors.add(director);
                    }
                    return directors;
                }

            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public Director getByFullName(String name, String surname) {
        String query = "SELECT id_director, name, surname, birthdate FROM directors WHERE name=? AND surname=?";
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    Director director = new Director();
                    director.setId(resultSet.getInt("id_director"));
                    director.setName(resultSet.getString("name"));
                    director.setSurname(resultSet.getString("surname"));
                    director.setBirthdate(resultSet.getString("birthdate"));
                    return director;
                }
            }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        @Override
        public void removeById ( int id){
            String query = "DELETE FROM directors WHERE id_director = ?";
            try (Connection connection = Connector.get()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }
    }
