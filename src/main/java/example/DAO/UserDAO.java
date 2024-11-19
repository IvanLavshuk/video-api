package example.DAO;

import example.Entities.User;
import example.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<User> {
    @Override
    public void add(User user)  {
        String query = "INSERT INTO users (name,surname,password,email) VALUES(?,?,?,?)";
        try( Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(query)
        ){
             preparedStatement.setString(1, user.getName());
             preparedStatement.setString(2, user.getSurname());
             preparedStatement.setString(3,user.getPassword());
             preparedStatement.setString(4,user.getEmail());
             preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getById(int id) {
        ResultSet resultSet =null;
        String query = "SELECT id_user,name,surname,password,email FROM users WHERE id_user=?";
        User user =new User();

        try( Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){

        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            user.setId(resultSet.getInt("id_user"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally
        {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<User> getALL() {
        ResultSet resultSet =null;
        String query = "SELECT* FROM users";
        List<User> users =new ArrayList<>();
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

        ){
            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()){
                User user =new User();
                user.setId(resultSet.getInt("id_user"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
       finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void remove(User user) {
        String email = user.getEmail();
        String query="DELETE FROM users WHERE email=?";

        try( Connection connection=DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query) ) {
           preparedStatement.setString(1,email);
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
