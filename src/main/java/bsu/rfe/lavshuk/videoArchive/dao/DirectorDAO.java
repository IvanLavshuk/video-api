package bsu.rfe.lavshuk.videoArchive.dao;
import bsu.rfe.lavshuk.videoArchive.db.Connector;
import bsu.rfe.lavshuk.videoArchive.entity.Director;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DirectorDAO extends DAO<Director> {
    private static final Logger logger = Logger.getLogger(DirectorDAO.class.getSimpleName());

    @Override
    public void create(Director director) {
        if(director==null){
            logger.info("director is null");
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
            logger.info("Error executing query:" + query+", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public Director getById(int id) {
        String query = "SELECT * FROM directors WHERE id_director=?";

        try (Connection connection = Connector.get()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Director director = new Director();
                    director.setId(id);
                    while (resultSet.next()) {
                        director.setId(resultSet.getInt("id_director"));
                        director.setName(resultSet.getString("name"));
                        director.setSurname(resultSet.getString("surname"));
                        director.setBirthdate(resultSet.getString("birthdate"));
                    }
                    return director;
                }
            }
        } catch (SQLException e) {
            logger.info("Error executing query:" + query+", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Director> getAll() {

        String query = "SELECT* FROM directors";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery(query)) {
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
            logger.info("Error executing query:" + query+", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }


    @Override
    public void removeById(int id) {
        String query = "DELETE FROM directors WHERE id_director=?";
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
