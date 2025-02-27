package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.DirectorDAO;
import bsu.rfe.lavshuk.video.archive.entity.Director;
import java.util.List;

public class DirectorService {
    private volatile static DirectorService INSTANCE;
    private final DirectorDAO directorDAO;

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

    public boolean isExist(String name, String surname) {
        return directorDAO.getByFullName(name, surname) == null;
    }

    public void createDirector(String name, String surname, String birthdate) {
        Director director = new Director();
        director.setName(name);
        director.setSurname(surname);
        director.setBirthdate(birthdate);
        directorDAO.create(director);

    }

    public List<Director> getAll() {
        return directorDAO.getAll();
    }
}
