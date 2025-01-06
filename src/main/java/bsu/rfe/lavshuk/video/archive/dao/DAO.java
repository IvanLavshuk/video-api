package bsu.rfe.lavshuk.video.archive.dao;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    public abstract void create(T t) throws SQLException;

    public abstract T getById(int t) throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    public abstract void removeById(int t) throws SQLException;

}
