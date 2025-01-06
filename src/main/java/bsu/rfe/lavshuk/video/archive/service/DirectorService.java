package bsu.rfe.lavshuk.video.archive.service;

import bsu.rfe.lavshuk.video.archive.dao.DirectorDAO;
import bsu.rfe.lavshuk.video.archive.entity.Director;

import java.util.List;

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

    public boolean isExist(int id) {
        return directorDAO.getById(id) == null;
    }

    public void createDirector(String name, String surname, String birthdate) {
        Director director = new Director();
        director.setName(name);
        director.setSurname(surname);
        director.setBirthdate(birthdate);
        directorDAO.create(director);

    }

    public String getFullNameById(int id){
        return directorDAO.getById(id).getName()+" "+directorDAO.getById(id).getSurname();
    }

    public List<Director> getAll() {
        return directorDAO.getAll();
    }
}
