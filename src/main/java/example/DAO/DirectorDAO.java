package example.DAO;


import example.Entities.Director;
import example.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDAO extends DAO<Director>{
    @Override
    public void add(Director director)  {

        String query = "INSERT INTO directors (name,surname,birthdate) VALUES(?,?,?)";
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){

            preparedStatement.setString(1, director.getName());
            preparedStatement.setString(2, director.getSurname());
            preparedStatement.setString(3, director.getBirthdate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Director getById(int id) {

        ResultSet resultSet= null;
        String query = "SELECT * FROM directors WHERE id_director=?";
        Director director = new Director();

        try(Connection connection=DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){

            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                director.setId(resultSet.getInt("id_director"));
                director.setName(resultSet.getString("name"));
                director.setSurname(resultSet.getString("surname"));
                director.setBirthdate(resultSet.getString("birthdate"));
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
        return director;
    }

    @Override
    public List<Director> getALL() {
        ResultSet resultSet =null;
        String query = "SELECT* FROM directors";
        List<Director> directors =new ArrayList<>();
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

        ){
            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()){
                Director director =new Director();
                director.setId(resultSet.getInt("id_director"));
                director.setName(resultSet.getString("name"));
                director.setSurname(resultSet.getString("surname"));
                director.setBirthdate(resultSet.getString("birthdate"));
                directors.add(director);
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

        return directors;
    }

    @Override
    public void remove(Director director) {
        String name = director.getName();
        String surname = director.getSurname();
        String query="DELETE FROM directors WHERE name=? AND surname=?";


        try( Connection connection = DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,surname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
