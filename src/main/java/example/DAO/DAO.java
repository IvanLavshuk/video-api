package example.DAO;

import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    public abstract void  add(T t) throws SQLException;
    public abstract T getById(int t);
    public abstract List<T> getALL();
    public abstract void remove(T t);

}
