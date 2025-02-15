package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.ActorDAO;
import bsu.rfe.lavshuk.video.archive.entity.Actor;

import java.util.List;

public class ActorService {
    private volatile static ActorService instance;
    private final ActorDAO actorDAO;

    private ActorService() {
        actorDAO = new ActorDAO();
    }

    public static ActorService getInstance() {
        if (instance == null) {
            synchronized (ActorService.class) {
                if (instance == null) {
                    instance = new ActorService();
                }
            }
        }
        return instance;
    }

    public void createActor(String name, String surname, String birthdate) {
        Actor actor = new Actor();
        actor.setName(name);
        actor.setSurname(surname);
        actor.setBirthdate(birthdate);
        actorDAO.create(actor);
    }

    public List<Actor> getAll() {
        return actorDAO.getAll();
    }

}
