package bsu.rfe.lavshuk.video.archive.dao;

import bsu.rfe.lavshuk.video.archive.db.Connector;
import bsu.rfe.lavshuk.video.archive.entity.Actor;
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


public class ActorDAO extends DAO<Actor> {
    private static final String CREATE_ACTOR_QUERY =
            "INSERT INTO actors (name, surname, birthdate) VALUES(?, ?, ?)";
    private static final String FIND_ACTOR_BY_ID_QUERY =
            "SELECT id_actor, name, surname, birthdate FROM actors WHERE id_actor = ?";
    private static final String FIND_ALL_ACTORS_QUERY =
            "SELECT id_actor, name, surname, birthdate FROM actors";
    private static final String DELETE_ACTOR_QUERY =
            "DELETE FROM actors WHERE id_actor = ?";
    private static final Logger logger = LoggerFactory.getLogger(ActorDAO.class);
    private static volatile ActorDAO INSTANCE;
    private static final String ID_ACTOR = "id_actor";
    private static final String NAME_ACTOR = "name";
    private static final String SURNAME_ACTOR = "surname";
    private static final String BIRTHDATE_ACTOR = "birthdate";

    private ActorDAO() {
    }

    public static ActorDAO getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (ActorDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ActorDAO();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void create(Actor actor) {
        if (actor == null) {
            logger.error("actor is null");
            throw new DaoException("Actor is null!");
        }

        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ACTOR_QUERY)) {
            preparedStatement.setString(1, actor.getName());
            preparedStatement.setString(2, actor.getSurname());
            preparedStatement.setString(3, actor.getBirthdate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing query:" + CREATE_ACTOR_QUERY + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to create actor", e);
        }

    }

    @Override
    public Optional<Actor> findById(int id) {
        String query = FIND_ACTOR_BY_ID_QUERY;

        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Actor actor = new Actor();
                    actor.setId(resultSet.getInt(ID_ACTOR));
                    actor.setName(resultSet.getString(NAME_ACTOR));
                    actor.setSurname(resultSet.getString(SURNAME_ACTOR));
                    actor.setBirthdate(resultSet.getString(BIRTHDATE_ACTOR));
                    return Optional.of(actor);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find actor by id", e);
        }

    }

    @Override
    public List<Actor> findAll() {
        String query = FIND_ALL_ACTORS_QUERY;

        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery(query)) {

            List<Actor> actors = new ArrayList<>();
            while (resultSet.next()) {
                Actor actor = new Actor();
                actor.setId(resultSet.getInt(ID_ACTOR));
                actor.setName(resultSet.getString(NAME_ACTOR));
                actor.setSurname(resultSet.getString(SURNAME_ACTOR));
                actor.setBirthdate(resultSet.getString(BIRTHDATE_ACTOR));
                actors.add(actor);
            }
            return actors;


        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to get actors ", e);
        }
    }

    @Override
    public void removeById(int id) {

        String query = DELETE_ACTOR_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to delete actor ", e);
        }
    }

}
