package example.db;

import example.DAO.*;
import example.Entities.*;

import java.util.List;

public class Example_Test {
    public static void main(String[] args) {

//        new ReviewDAO().add(new Review(7.8,"Пойдет ",1,1));
//        new ReviewDAO().add(new Review(9.8,"Хорошо ",3,3));
//        new  ReviewDAO().add(new Review(6.8,"на один раз ",2,6));
//        new  ReviewDAO().add(new Review(6.8,"на один раз ",2,6));
        new ReviewDAO().remove(new Review(6.8,"на один раз ",2,6));
        System.out.println(new ReviewDAO().getById(1));
        List<Review> us0 = new ReviewDAO().getALL();
        //User u1 = new User("Vlad","Zepesh","nacollubogo","draxc@gmail.com");
        //u1.setId(5);
        //System.out.println(u1);
        //add(new User("Vlad","Zepesh","nacollubogo","draxc@gmail.com"));
        //new UserDAO().remove(u1);
        //new UserDAO().add(new User("Слава","Литр","пароль","www.com"));

        //new UserDAO().remove(new User("Слава","Литр","пароль","www.com"));


        //new ActorDAO().add(new Actor("Jorah","Marmont","2004-09-13"));
        //System.out.println(new ActorDAO().getById(3));
        //System.out.println(new ActorDAO().getALL());
//        new ActorDAO().remove(a);
         //Director d = new Director("Evgen","Skob","2006-5-22");
         //new DirectorDAO().remove(d);
        //new  DirectorDAO().add(new Director("Evgen","Skob","2006-5-22"));
       /// System.out.println(new MovieDAO().getALL());
        //System.out.println(new UserDAO().getALL());
       // System.out.println(new DirectorDAO().getALL());
//        List<Director> us = new DirectorDAO().getALL();
//        List<Actor> us1 = new ActorDAO().getALL();
//        List<Movie> us2 = new MovieDAO().getALL();
//        List<User> us3 = new UserDAO().getALL();
//        for(Movie u : us2){
//            System.out.println(u);
//        }
//        //new ActorDAO().remove(new Actor("Jorah","Marmont","2004-09-13"));
//        for(Review u : us0){
//            System.out.println(u);
//        }
//        System.out.println();
//        for(Movie u : us2){
//            System.out.println(u);
//        }
//        System.out.println();
//       for(Director u : us){
//          System.out.println(u);
//       }
//        System.out.println();
//       for(Actor u : us1){
//            System.out.println(u);
//       }
//        System.out.println(new MovieDAO().getById(1));





    }

//        try (Connection connection = DBconnection.getConnection()) {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM actors");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("name"));
//                System.out.println(resultSet.getString("birthdate"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
