package example.Entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private String country;
    private String releaseDate;
    private int idDirector;

    public Movie(){}

    public Movie(int id, String title, String genre, String country, String releaseDate, int idDirector) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.country = country;
        this.releaseDate = releaseDate;

        this.idDirector = idDirector;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                ", releaseDate=" + releaseDate +
                ", idDirector=" + idDirector +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && idDirector == movie.idDirector
                && title.equals(movie.title) && genre.equals(movie.genre) &&
                country.equals(movie.country) && releaseDate.equals(movie.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, country, releaseDate, idDirector);
    }
}

