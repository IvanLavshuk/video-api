package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.DirectorDAO;
import bsu.rfe.lavshuk.video.archive.dao.MovieDAO;
import bsu.rfe.lavshuk.video.archive.entity.Movie;

import java.util.List;

public class MovieService {
    private volatile static MovieService instance;
    private final MovieDAO movieDAO;

    private MovieService() {
        movieDAO = new MovieDAO();
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

    public void createMovie(String title, String genre, String country, String releaseDate, int idDirector) {
        Movie movie = new Movie();
        movie.setReleaseDate(releaseDate);
        movie.setTitle(title);
        movie.setDirector(idDirector);
        movie.setGenre(genre);
        movie.setCountry(country);
        movieDAO.create(movie);
    }

    public boolean isExistByTitle(String title) {
        return movieDAO.getByTitle(title) != null;
    }

    public String getDirector(int id) {
        return DirectorService.getInstance().getFullNameById(id);
    }

    public List<Movie> getAll() {
        return movieDAO.getAll();
    }
}
