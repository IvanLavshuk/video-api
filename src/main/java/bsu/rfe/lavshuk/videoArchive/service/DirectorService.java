package bsu.rfe.lavshuk.videoArchive.service;

import bsu.rfe.lavshuk.videoArchive.dao.DirectorDAO;

import bsu.rfe.lavshuk.videoArchive.entity.Director;

public class DirectorService {
    private volatile static DirectorService instance;
    private final DirectorDAO directorDAO;

    private DirectorService() {
        directorDAO = new DirectorDAO();
    }

    public static DirectorService getInstance() {
        if (instance == null) {
            synchronized (DirectorService.class) {
                if (instance == null) {
                    instance = new DirectorService();
                }
            }
        }
        return instance;
    }

    public void createDirector(String name, String surname,String birthdate) {
        Director director = new Director();
        director.setName(name);
        director.setSurname(surname);
        director.setBirthdate(birthdate);
        directorDAO.create(director);

    }
}
