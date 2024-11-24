package example.dao;

import example.db.Connector;
import example.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
    @Override
    public void create(User user) {
        String query = "INSERT INTO users (name,surname,password,email) VALUES(?,?,?,?)";
        try (Connection connection = Connector.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.executeUpdate();
            }
        }catch (SQLException e) {
            LOG.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public User getById(int id) {

        String query = "SELECT id_user,name,surname,password,email FROM users WHERE id_user=?";

        try (Connection connection = Connector.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery();){
                    User user = new User();
                    user.setId(id);
                    while (resultSet.next()){
                        user.setId(resultSet.getInt("id_user"));
                        user.setName(resultSet.getString("name"));
                        user.setSurname(resultSet.getString("surname"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                    }
                    return user;
                }
            }
        }catch (SQLException e) {
            LOG.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<User> getAll() {

        String query = "SELECT* FROM users";
        try (Connection connection = Connector.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                try(ResultSet resultSet = preparedStatement.executeQuery(query)){
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
        }catch (SQLException e) {
            LOG.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeById(int id) {
        String query = "DELETE FROM users WHERE id_user=?";

        try (Connection connection = Connector.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public User getByEmail(String email) {

        String query = "SELECT id_user,name,surname,password,email FROM users WHERE email=?";


        try (Connection connection = Connector.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    User user = new User();
                    user.setEmail(email);
                    while (resultSet.next()) {
                        user.setId(resultSet.getInt("id_user"));
                        user.setName(resultSet.getString("name"));
                        user.setSurname(resultSet.getString("surname"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));
                    }
                    return user;
                }
            }
        } catch (SQLException e) {
            LOG.error("Error executing query:{}, errormessage: {}", query, e.getMessage());
            throw new RuntimeException(e);
        }


    }
}

