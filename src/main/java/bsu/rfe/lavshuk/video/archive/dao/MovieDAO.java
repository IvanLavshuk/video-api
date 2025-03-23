package bsu.rfe.lavshuk.video.archive.dao;

import bsu.rfe.lavshuk.video.archive.db.Connector;
import bsu.rfe.lavshuk.video.archive.entity.Movie;
import bsu.rfe.lavshuk.video.archive.validator.DaoException;
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
    private static final String CREATE_MOVIE_QUERY =
            "INSERT INTO movies (title, genre, country, release_date, id_director) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_MOVIE_BY_ID_QUERY =
            "SELECT id_movie,title, genre, country, release_date, id_director FROM movies WHERE id_movie = ?";
    private static final String FIND_ALL_MOVIES_QUERY =
            "SELECT id_movie,title, genre, country, release_date, id_director FROM movies";
    private static final String FIND_MOVIE_BY_TITLE_QUERY =
            "SELECT id_movie,title, genre, country, release_date, id_director FROM movies WHERE title = ?";
    private static final String DELETE_MOVIE_QUERY =
            "DELETE FROM movies WHERE id_movie = ?";
    private static final Logger logger = LoggerFactory.getLogger(MovieDAO.class);
    private static volatile MovieDAO INSTANCE;
    private static final String ID_MOVIE = "id_movie";
    private static final String TITLE = "title";
    private static final String GENRE = "genre";
    private static final String COUNTRY = "country";
    private static final String RELEASE_DATE = "release_date";
    private static final String ID_DIRECTOR = "id_director";

    private MovieDAO() {
    }

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
            throw new DaoException("Movie is null!!!");
        }
        String query = CREATE_MOVIE_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getGenre());
            preparedStatement.setString(3, movie.getCountry());
            preparedStatement.setString(4, movie.getReleaseDate());
            preparedStatement.setObject(5, movie.getDirector().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to create movie ", e);
        }

    }

    public Optional<Movie> findByTitle(String title) {

        String query = FIND_MOVIE_BY_TITLE_QUERY;

        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, title);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Movie movie = new Movie();
                    movie.setId(resultSet.getInt(ID_MOVIE));
                    movie.setTitle(resultSet.getString(TITLE));
                    movie.setGenre(resultSet.getString(GENRE));
                    movie.setCountry(resultSet.getString(COUNTRY));
                    movie.setReleaseDate(resultSet.getString(RELEASE_DATE));
                    Integer idDirector = resultSet.getInt(ID_DIRECTOR);
                    movie.setDirector(DirectorDAO.getINSTANCE().findById(idDirector).get());
                    return Optional.of(movie);
                }
                return Optional.empty();

            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find movie by title", e);
        }

    }

    @Override
    public Optional<Movie> findById(int id) {

        String query = FIND_MOVIE_BY_ID_QUERY;

        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Movie movie = new Movie();
                    movie.setId(resultSet.getInt(ID_MOVIE));
                    movie.setTitle(resultSet.getString(TITLE));
                    movie.setGenre(resultSet.getString(GENRE));
                    movie.setCountry(resultSet.getString(COUNTRY));
                    movie.setReleaseDate(resultSet.getString(RELEASE_DATE));
                    Integer idDirector = resultSet.getInt(ID_DIRECTOR);
                    movie.setDirector(DirectorDAO.getINSTANCE().findById(idDirector).get());
                    return Optional.of(movie);
                }
                return Optional.empty();

            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find movie by id", e);
        }

    }

    @Override
    public List<Movie> findAll() {
        String query = FIND_ALL_MOVIES_QUERY;
        try (Connection connection = Connector.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery(query)) {
                List<Movie> movies = new ArrayList<>();
                while (resultSet.next()) {
                    Movie movie = new Movie();
                    movie.setId(resultSet.getInt(ID_MOVIE));
                    movie.setTitle(resultSet.getString(TITLE));
                    movie.setGenre(resultSet.getString(GENRE));
                    movie.setCountry(resultSet.getString(COUNTRY));
                    movie.setReleaseDate(resultSet.getString(RELEASE_DATE));
                    Integer idDirector = resultSet.getInt(ID_DIRECTOR);
                    movie.setDirector(DirectorDAO.getINSTANCE().findById(idDirector).get());
                    movies.add(movie);
                }
                return movies;


            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to find all movies", e);
        }
    }

    @Override
    public void removeById(int id) {
        String query = DELETE_MOVIE_QUERY;
        try (Connection connection = Connector.get()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error executing query:" + query + ", errormessage: " + e.getMessage());
            throw new DaoException("Failed to delete director", e);
        }

    }
}
