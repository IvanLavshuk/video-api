package example.services;

import example.dao.*;

import example.entities.Movie;
import example.entities.User;

public class MovieService {
    private volatile static MovieService instance;
    private final MovieDAO movieDAO;

    private MovieService() {
        movieDAO = new  MovieDAO();
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

   public void createMovie(String title, String genre, String country, String releaseDate,int idDirector) {
       Movie movie = new Movie();
       movie.setReleaseDate(releaseDate);
       movie.setTitle(title);
       movie.setIdDirector(idDirector);
       movie.setGenre(genre);
       movie.setCountry(country);
       movieDAO.create(movie);
    }


}
