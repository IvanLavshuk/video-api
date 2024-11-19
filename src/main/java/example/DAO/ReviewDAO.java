package example.DAO;

import example.Entities.Movie;
import example.Entities.Review;
import example.db.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO extends DAO<Review>{

    @Override
    public void add(Review review)  {
        String query = "INSERT INTO reviews (rating,text,id_user,id_movie) VALUES(?,?,?,?)";
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(query);
        ){
            preparedStatement.setDouble(1, review.getRating());
            preparedStatement.setString(2, review.getText());
            preparedStatement.setInt(3,review.getIdUser());
            preparedStatement.setInt(4,review.getIdMovie());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Review getById(int id) {
        ResultSet resultSet=null;
        String query = "SELECT id_review,rating,text,id_user,id_movie FROM reviews WHERE id_review=?";
        Review review =new Review();
        review.setId(id);
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){

            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               review.setId(resultSet.getInt("id_review"));
               review.setRating(resultSet.getDouble("rating"));
               review.setText(resultSet.getString("text"));
               review.setIdUser(resultSet.getInt("id_user"));
               review.setIdMovie(resultSet.getInt("id_movie"));

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
        return review;
    }

    @Override
    public List<Review> getALL() {
        ResultSet resultSet=null;
        String query = "SELECT* FROM reviews";
        List<Review> reviews = new ArrayList<>();
        try(Connection connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Review review = new Review();
                review.setId(resultSet.getInt("id_review"));
                review.setRating(resultSet.getDouble("rating"));
                review.setText(resultSet.getString("text"));
                review.setIdUser(resultSet.getInt("id_user"));
                review.setIdMovie(resultSet.getInt("id_movie"));
                reviews.add(review);
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
        return reviews;
    }

    @Override
    public void remove(Review review) {
        String query="DELETE FROM reviews WHERE id_user=? AND id_movie=? AND text=?";
        try( Connection connection=DBconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query) ) {
            preparedStatement.setInt(1,review.getIdUser());
            preparedStatement.setInt(2,review.getIdMovie());
            preparedStatement.setString(3,review.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

