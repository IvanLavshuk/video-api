package example.entities;

import java.util.Objects;

public class Review {
    private int id;
    private double rating;
    private String text;
    private Movie idMovie;
    private User idUser;

    public Review(){}
    public Review( double rating, String text, Movie Movie, User idUser) {

        this.rating = rating;
        this.text = text;
        this.idMovie = Movie;
        this.idUser = idUser;
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
        return idMovie;
    }

    public void setMovie(Movie idMovie) {
        this.idMovie = idMovie;
    }

    public User getUser() {
        return idUser;
    }

    public void setUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && Double.compare(review.rating, rating) == 0 && text.equals(review.text) && idMovie.equals(review.idMovie) && idUser.equals(review.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, text, idMovie, idUser);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                ", idMovie=" + idMovie +
                ", idUser=" + idUser +
                '}';
    }
}
