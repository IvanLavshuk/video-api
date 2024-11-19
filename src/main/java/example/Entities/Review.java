package example.Entities;

import java.util.Objects;

public class Review {
    private int id;
    private double rating;
    private String text;
    private int  idMovie;
    private int  idUser;

    public Review(){}
    public Review( double rating, String text, int idMovie, int idUser) {

        this.rating = rating;
        this.text = text;
        this.idMovie = idMovie;
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

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && Double.compare(review.rating, rating) == 0 &&
                idMovie == review.idMovie && idUser == review.idUser && text.equals(review.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, text, idMovie, idUser);
    }
}
