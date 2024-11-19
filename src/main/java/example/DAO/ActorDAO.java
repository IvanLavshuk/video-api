package example.DAO;

import example.Entities.Actor;
import example.Entities.User;
import example.db.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO extends DAO<Actor> {
    @Override
    public void add(Actor actor) {

        String query = "INSERT INTO actors (name,surname,birthdate) VALUES(?,?,?)";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {

            preparedStatement.setString(1, actor.getName());
            preparedStatement.setString(2, actor.getSurname());
            preparedStatement.setString(3, actor.getBirthdate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Actor getById(int id) {
        String query = "SELECT * FROM actors WHERE id_actor=?";
        Actor actor = new Actor();

        ResultSet resultSet = null;
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                actor.setId(resultSet.getInt("id_actor"));
                actor.setName(resultSet.getString("name"));
                actor.setSurname(resultSet.getString("surname"));
                actor.setBirthdate(resultSet.getString("birthdate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return actor;
    }

    @Override
    public List<Actor> getALL() {
        ResultSet resultSet = null;
        String query = "SELECT* FROM actors";
        List<Actor> actors = new ArrayList<>();
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {
            resultSet = preparedStatement.executeQuery(query);

            while (resultSet.next()) {
                Actor actor = new Actor();
                actor.setId(resultSet.getInt("id_actor"));
                actor.setName(resultSet.getString("name"));
                actor.setSurname(resultSet.getString("surname"));
                actor.setBirthdate(resultSet.getString("birthdate"));
                actors.add(actor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return actors;
    }

    @Override
    public void remove(Actor actor) {
        String name = actor.getName();
        String surname = actor.getSurname();
        String query = "DELETE FROM actors WHERE name=? AND surname=?";

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
