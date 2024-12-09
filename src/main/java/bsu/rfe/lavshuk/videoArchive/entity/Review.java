package bsu.rfe.lavshuk.videoArchive.entity;

import java.util.Objects;

public class Review {
    private int id;
    private double rating;
    private String text;
    private Movie movie;
    private User user;

    public Review(){}
    public Review( double rating, String text, Movie Movie, User idUser) {

        this.rating = rating;
        this.text = text;
        this.movie = Movie;
        this.user = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie idMovie) {
        this.movie = idMovie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User idUser) {
        this.user = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && Double.compare(review.rating, rating) == 0 && text.equals(review.text) && movie.equals(review.movie) && user.equals(review.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, text, movie, user);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                ", idMovie=" + movie +
                ", idUser=" + user +
                '}';
    }
}
