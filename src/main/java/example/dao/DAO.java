package example.dao;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    public abstract void create(T t) throws SQLException;

    public abstract T getById(int t);

    public abstract List<T> getAll();

    public abstract void removeById(int t);

}
