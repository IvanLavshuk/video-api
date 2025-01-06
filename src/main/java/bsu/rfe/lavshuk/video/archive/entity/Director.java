package bsu.rfe.lavshuk.video.archive.entity;

import java.util.Objects;

public class Director {
    private int id;
    private String name;
    private String surname;
    private String birthdate;

    public Director() {
    }

    public Director(String name, String surname, String birthdate) {

        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return id == director.id && name.equals(director.name) &&
                surname.equals(director.surname) &&
                birthdate.equals(director.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthdate);
    }
}
