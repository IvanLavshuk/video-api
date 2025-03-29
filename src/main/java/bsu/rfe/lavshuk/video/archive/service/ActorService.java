package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.ActorDAO;
import bsu.rfe.lavshuk.video.archive.entity.Actor;
import bsu.rfe.lavshuk.video.archive.validator.ActorValidator;
import bsu.rfe.lavshuk.video.archive.validator.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ActorService {
    private final ActorDAO actorDAO;
    private static final Logger logger = LoggerFactory.getLogger(ActorService.class);

    private ActorService() {
        actorDAO = ActorDAO.getINSTANCE();
    }

    private static volatile ActorService INSTANCE;

    public static ActorService getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (ActorService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ActorService();
                }
            }
        }
        return INSTANCE;
    }

    public void createActor(String name, String surname, String birthdate) throws ValidationException {
        try {
            ActorValidator.validateActorParameters(name, surname, birthdate);
        } catch (ValidationException e) {
            logger.error("Failed to create actor. Invalid parameters");
            throw e;
        }
        Actor actor = new Actor();
        actor.setName(name);
        actor.setSurname(surname);
        actor.setBirthdate(birthdate);
        actorDAO.create(actor);
    }

    public List<Actor> getAll() {
        return actorDAO.findAll();
    }

}
