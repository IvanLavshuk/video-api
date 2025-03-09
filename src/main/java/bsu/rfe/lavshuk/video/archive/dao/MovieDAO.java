package bsu.rfe.lavshuk.video.archive.dao;

import bsu.rfe.lavshuk.video.archive.db.Connector;
import bsu.rfe.lavshuk.video.archive.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDAO extends DAO<Movie> {
    private static final Logger logger = LoggerFactory.getLogger(MovieDAO.class);
    private static volatile MovieDAO INSTANCE;
    private MovieDAO(){}
    public static MovieDAO getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (MovieDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieDAO();
                }
            }
        }
        return INSTANCE;
    }
    @Override
    public void create(Movie movie) {
        if (movie == null) {
            logger.error("movie is null");
            throw new RuntimeException();
        }
        String query = "INSERT INTO movies (title,genre,country,release_date,id_director) VALUES(?,?,?,?,?)";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, movie.getTitle());
                preparedStatement.setString(2, movie.getGenre());
                preparedStatement.setString(3, movie.getCountry());
                preparedStatement.setString(4, movie.getReleaseDate());
                preparedStatement.setObject(5, movie.getDirector().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public Movie getByTitle(String title) {

        String query = "SELECT * FROM movies WHERE title=?";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, title);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Movie movie = new Movie();
                        movie.setId(resultSet.getInt("id_movie"));
                        movie.setTitle(resultSet.getString("title"));
                        movie.setGenre(resultSet.getString("genre"));
                        movie.setCountry(resultSet.getString("country"));
                        movie.setReleaseDate(resultSet.getString("release_date"));
                        movie.setDirector(DirectorDAO.getINSTANCE().getById(resultSet.getInt("id_director")).get());
                        return movie;
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Movie> getById(int id) {

        String query = "SELECT * FROM movies WHERE id_movie=?";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Movie movie = new Movie();
                        movie.setId(resultSet.getInt("id_movie"));
                        movie.setTitle(resultSet.getString("title"));
                        movie.setGenre(resultSet.getString("genre"));
                        movie.setCountry(resultSet.getString("country"));
                        movie.setReleaseDate(resultSet.getString("release_date"));
                        movie.setDirector(DirectorDAO.getINSTANCE().getById (resultSet.getInt("id_director")).get());
                        return Optional.of(movie);
                    }
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Movie> getAll() {
        String query = "SELECT* FROM movies";

        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery(query)) {
                    List<Movie> movies = new ArrayList<>();
                    while (resultSet.next()) {
                        Movie movie = new Movie();
                        movie.setId(resultSet.getInt("id_movie"));
                        movie.setTitle(resultSet.getString("title"));
                        movie.setGenre(resultSet.getString("genre"));
                        movie.setCountry(resultSet.getString("country"));
                        movie.setReleaseDate(resultSet.getString("release_date"));
                        movie.setDirector(DirectorDAO.getINSTANCE().getById(resultSet.getInt("id_director")).get());
                        movies.add(movie);
                    }
                    return movies;
                }

            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeById(int id) {
        String query = "DELETE FROM movies WHERE id_movie = ?";
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
