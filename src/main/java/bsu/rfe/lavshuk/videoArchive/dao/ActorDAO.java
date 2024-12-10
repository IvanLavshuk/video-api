package bsu.rfe.lavshuk.videoArchive.dao;

import bsu.rfe.lavshuk.videoArchive.db.Connector;
import bsu.rfe.lavshuk.videoArchive.entity.Actor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ActorDAO extends DAO<Actor> {
    private static final Logger logger = LoggerFactory.getLogger(ActorDAO.class.getSimpleName());

    public static void main(String[] args) {
        logger.info("HELLO");
    }

    @Override
    public void create(Actor actor) {
        if (actor == null) {
            logger.error("Object :{} is null", actor.getClass().getSimpleName());
            throw new RuntimeException();
        }

        String query = "INSERT INTO actors (name,surname,birthdate) VALUES(?,?,?)";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, actor.getName());

                preparedStatement.setString(2, actor.getSurname());

                preparedStatement.setString(3, actor.getBirthdate());

            }

        } catch (SQLException e) {
            logger.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
        }

    }

    @Override
    public Actor getById(int id) {
        String query = "SELECT * FROM actors WHERE id_actor=?";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Actor actor = new Actor();
                    actor.setId(id);
                    while (resultSet.next()) {
                        actor.setId(resultSet.getInt("id_actor"));
                        actor.setName(resultSet.getString("name"));
                        actor.setSurname(resultSet.getString("surname"));
                        actor.setBirthdate(resultSet.getString("birthdate"));
                    }
                    return actor;
                }
            }

        } catch (SQLException e) {
            logger.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
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
            logger.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeById(int id) {

        String query = "DELETE FROM actors WHERE id_actor=?";
        try (Connection connection = Connector.get()){
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
