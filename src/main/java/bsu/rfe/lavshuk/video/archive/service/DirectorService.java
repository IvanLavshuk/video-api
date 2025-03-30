package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.DirectorDAO;
import bsu.rfe.lavshuk.video.archive.entity.Director;
import bsu.rfe.lavshuk.video.archive.validator.PersonValidator;
import bsu.rfe.lavshuk.video.archive.validator.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class DirectorService {
    private volatile static DirectorService INSTANCE;
    private final DirectorDAO directorDAO;
    private static final Logger logger = LoggerFactory.getLogger(DirectorService.class);

    private DirectorService() {
        directorDAO = DirectorDAO.getINSTANCE();
    }

    public static DirectorService getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (DirectorService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DirectorService();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Director> getByFullName(String name, String surname) {
        return directorDAO.findByFullName(name, surname);
    }

    public void createDirector(String name, String surname, String birthdate) throws ValidationException {
        try{
            PersonValidator.validateParameters(name,surname,birthdate);
        }catch (ValidationException e){
            logger.error("Failed to create director. Invalid parameters");
            throw e;
        }
        Director director = new Director();
        director.setName(name);
        director.setSurname(surname);
        director.setBirthdate(birthdate);
        directorDAO.create(director);

    }

    public List<Director> getAll() {
        return directorDAO.findAll();
    }
}
