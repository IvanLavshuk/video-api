package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.DirectorDAO;
import bsu.rfe.lavshuk.video.archive.dao.MovieDAO;
import bsu.rfe.lavshuk.video.archive.entity.Director;
import bsu.rfe.lavshuk.video.archive.entity.Movie;
import bsu.rfe.lavshuk.video.archive.validator.MovieValidator;
import bsu.rfe.lavshuk.video.archive.validator.ServiceException;
import bsu.rfe.lavshuk.video.archive.validator.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MovieService {
    private volatile static MovieService INSTANCE;
    private final MovieDAO movieDAO;

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    private MovieService() {
        movieDAO = MovieDAO.getINSTANCE();
    }

    public static MovieService getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (MovieService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieService();
                }
            }
        }
        return INSTANCE;
    }

    public void createMovie(String title, String genre, String country, String releaseDate, String directorName,
                            String directorSurname) throws ValidationException,ServiceException{
        try {
            MovieValidator.validateMovieParameters(title, genre, country, releaseDate, directorName, directorSurname);
        } catch (ValidationException e) {
            logger.error("Failed to create movie. Invalid parameters");
            throw e;
        }

        Movie movie = new Movie();
        movie.setReleaseDate(releaseDate);
        movie.setTitle(title);
        Director director = DirectorDAO.getINSTANCE().findByFullName(directorName, directorSurname).
                orElseThrow(() ->
                        new ServiceException("Director " + directorName + " " + directorSurname + "is not found"));
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setCountry(country);
        movieDAO.create(movie);
    }

    public boolean isExistByTitle(String title) {
        return movieDAO.findByTitle(title) != null;
    }


    public List<Movie> getAll() {
        return movieDAO.findAll();
    }
}
