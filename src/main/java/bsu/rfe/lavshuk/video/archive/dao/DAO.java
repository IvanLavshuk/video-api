package bsu.rfe.lavshuk.video.archive.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class DAO<T> {
    public abstract void create(T t) throws SQLException;

    public abstract Optional<T> findById(int t) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract void removeById(int t) throws SQLException;

}
