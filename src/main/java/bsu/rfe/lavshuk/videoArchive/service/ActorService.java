package bsu.rfe.lavshuk.videoArchive.service;

import bsu.rfe.lavshuk.videoArchive.dao.ActorDAO;
import bsu.rfe.lavshuk.videoArchive.entity.Actor;

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

    public void createActor(String name, String surname,String birthdate) {
        Actor actor = new Actor();
        actor.setName(name);
        actor.setSurname(surname);
        actor.setBirthdate(birthdate);
        actorDAO.create(actor);

    }


}
