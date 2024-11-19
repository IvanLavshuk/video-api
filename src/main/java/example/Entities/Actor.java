package example.Entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Actor {
    private int id;
    private String name;
    private String surname;
    private String birthdate;

    public Actor() {}

    public Actor(String name, String surname, String birthdate) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Actor{" +
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
        Actor actor = (Actor) o;
        return id == actor.id && name.equals(actor.name) && surname.equals(actor.surname) && birthdate.equals(actor.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthdate);
    }
}
