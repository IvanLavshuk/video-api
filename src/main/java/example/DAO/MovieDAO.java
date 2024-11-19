package example.DAO;

import example.Entities.Director;
import example.Entities.Movie;
import example.Entities.User;
import example.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO extends DAO<Movie> {
    @Override
    public void add(Movie movie)  {

        String query = "INSERT INTO movies (title,genre,country,release_date,id_director) VALUES(?,?,?,?,?)";
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getGenre());
            preparedStatement.setString(3,movie.getCountry());
            preparedStatement.setString(4,movie.getReleaseDate());
            preparedStatement.setObject(5,movie.getIdDirector());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Movie getById(int id) {
        ResultSet resultSet=null;
        String query = "SELECT * FROM movies WHERE id_movie=?";
        Movie movie =new Movie();
        movie.setId(id);
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){

            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                movie.setId(resultSet.getInt("id_movie"));
                movie.setTitle(resultSet.getString("title"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setCountry(resultSet.getString("country"));
                movie.setReleaseDate(resultSet.getString("release_date"));
                movie.setIdDirector(resultSet.getInt("id_director"));
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
        return movie;
    }

    @Override
    public List<Movie> getALL() {
          ResultSet resultSet=null;
        String query = "SELECT* FROM movies";
        List<Movie> movies =new ArrayList<>();
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

        ){
            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()){
                Movie movie =new Movie();
                movie.setId(resultSet.getInt("id_movie"));
                movie.setTitle(resultSet.getString("title"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setCountry(resultSet.getString("country"));
                movie.setReleaseDate(resultSet.getString("release_date"));
                movie.setIdDirector(resultSet.getInt("id_director"));
                movies.add(movie);
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
        return movies;
    }

    @Override
    public void remove(Movie movie) {
        String query="DELETE FROM movies WHERE title=? AND release_date=?";
        try( Connection connection=DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query) ) {

            preparedStatement.setString(1,movie.getTitle());
            preparedStatement.setString(2,movie.getReleaseDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
