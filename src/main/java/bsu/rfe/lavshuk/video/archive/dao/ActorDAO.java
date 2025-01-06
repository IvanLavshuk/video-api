package bsu.rfe.lavshuk.video.archive.dao;

import bsu.rfe.lavshuk.video.archive.db.Connector;
import bsu.rfe.lavshuk.video.archive.entity.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class ActorDAO extends DAO<Actor> {
    private static final Logger logger = Logger.getLogger(ActorDAO.class.getSimpleName());


    @Override
    public void create(Actor actor) {
        if (actor == null) {
            logger.info("actor is null");
            throw new RuntimeException();
        }

        String query = "INSERT INTO actors (name,surname,birthdate) VALUES(?,?,?)";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, actor.getName());

                preparedStatement.setString(2, actor.getSurname());

                preparedStatement.setString(3, actor.getBirthdate());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            logger.info("Error executing query:" + query + ", errormessage: " + e.getMessage());
        }

    }

    @Override
    public Actor getById(int id) {
        String query = "SELECT * FROM actors WHERE id_actor=?";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Actor actor = new Actor();
                        actor.setId(resultSet.getInt("id_actor"));
                        actor.setName(resultSet.getString("name"));
                        actor.setSurname(resultSet.getString("surname"));
                        actor.setBirthdate(resultSet.getString("birthdate"));
                        return actor;
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
    public List<Actor> getAll() {
        String query = "SELECT* FROM actors";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery(query)) {
                    List<Actor> actors = new ArrayList<>();
                    while (resultSet.next()) {
                        Actor actor = new Actor();
                        actor.setId(resultSet.getInt("id_actor"));
                        actor.setName(resultSet.getString("name"));
                        actor.setSurname(resultSet.getString("surname"));
                        actor.setBirthdate(resultSet.getString("birthdate"));
                        actors.add(actor);
                    }
                    return actors;
                }
            }
        } catch (SQLException e) {
            logger.info("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeById(int id) {

        String query = "DELETE FROM actors WHERE id_actor=?";
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


}
