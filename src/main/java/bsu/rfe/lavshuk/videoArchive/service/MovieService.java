package bsu.rfe.lavshuk.videoArchive.service;

import bsu.rfe.lavshuk.videoArchive.dao.MovieDAO;
import bsu.rfe.lavshuk.videoArchive.entity.Movie;

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

    public boolean isExistByTitle(String title){
        if(movieDAO.getByTitle(title)==null){
            return false;
        }
        return true;
    }

    public List<Movie> getAll(){
        return movieDAO.getAll();
    }
}
