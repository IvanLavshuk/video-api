package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.DirectorDAO;
import bsu.rfe.lavshuk.video.archive.dao.MovieDAO;
import bsu.rfe.lavshuk.video.archive.entity.Movie;

import java.util.List;

public class MovieService {
    private volatile static MovieService instance;
    private final MovieDAO movieDAO;

    private MovieService() {
        movieDAO = MovieDAO.getINSTANCE();
    }

    public static MovieService getInstance() {
        if (instance == null) {
            synchronized (MovieService.class) {
                if (instance == null) {
                    instance = new MovieService();
                }
            }
        }
        return instance;
    }

    public void createMovie(String title, String genre, String country, String releaseDate, String directorName, String directorSurname) {
        Movie movie = new Movie();
        movie.setReleaseDate(releaseDate);
        movie.setTitle(title);
        movie.setDirector(DirectorDAO.getINSTANCE().getByFullName(directorName,directorSurname));
        movie.setGenre(genre);
        movie.setCountry(country);
        movieDAO.create(movie);
    }

    public boolean isExistByTitle(String title) {
        return movieDAO.getByTitle(title) != null;
    }


    public List<Movie> getAll() {
        return movieDAO.getAll();
    }
}
